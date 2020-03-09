package com.cyh.project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理页面
 * @Author chenyinghui
 * @Date 2020/1/15 11:24
 */
@Controller
public class ErrorPageController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handelError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return "/error/500";
        }else if (statusCode == 404) {
            return "/error/404";
        } else if (statusCode == 403) {
            return "/error/403";
        } else {
            return "/error/500";
        }
    }

}
