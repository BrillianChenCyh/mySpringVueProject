package com.cyh.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/22 11:42
 *  @Description:
 */
@Controller
@RequestMapping("/demo2")
public class Demo2Controller {

    @GetMapping("/toWebsocketPage")
    public String toWebsocketPage() {
		return "websocket";
	}

    @GetMapping("/toUploadPage")
    public String toUploadPage(){
        return "upload";
    }

    @GetMapping("/toUploadPages")
    public String toUploadPages(){
        return "uploads";
    }

    @GetMapping("/uploadElePage")
    public String uploadElePage(){
        return "uploadDemo";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request){
        try {
            //创建文件在服务端的存放路径
            String dir = request.getServletContext().getRealPath("/uplaod");
            File fileDir = new File(dir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //生成文件在服务器端存放的名字
            int i = file.getOriginalFilename().lastIndexOf(".");
            String fileSuffix = file.getOriginalFilename().substring(i);
            String fileName = UUID.randomUUID().toString() + fileSuffix;
            File files = new File(fileDir+"/"+fileName);
            //上传
            file.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @ResponseBody
    @PostMapping("/uploadBatch")
    public String upload(MultipartFile[] file, HttpServletRequest request){
        try {
            //创建文件在服务端的存放路径
            String dir = request.getServletContext().getRealPath("/uplaod");
            File fileDir = new File(dir);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }
            //生成文件在服务器端存放的名字
            for (MultipartFile mulFile : file) {
                int i = mulFile.getOriginalFilename().lastIndexOf(".");
                String fileSuffix = mulFile.getOriginalFilename().substring(i);
                String fileName = UUID.randomUUID().toString() + fileSuffix;
                File files = new File(fileDir+"/"+fileName);
                //上传
                mulFile.transferTo(files);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
