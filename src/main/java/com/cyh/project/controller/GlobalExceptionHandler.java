package com.cyh.project.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/16 19:25
 *  @Description: 全局捕获异常类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    //如果返回json格式@ResponseBody,返回页面返回string类型,类型结果制定404页面
    @ResponseBody
    public Map<String,Object> resultError(){
        Map<String,Object> result = new HashMap<>();
        result.put("errorCode","500");
        result.put("errorMsg","系统错误!");
        return result;
    }
}
