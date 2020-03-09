package com.cyh.project.controller;

import com.cyh.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author: chenyinghui
 *  @Date: 2020/2/21 21:19
 *  @Description: beetlSql
 */
@RestController
@RequestMapping("/beetlSql")
public class BeetlController {

    @Autowired
    private UserService userService;

    @GetMapping("/test1")
    public String test1(){
        userService.test1();
        return null;
    }
}
