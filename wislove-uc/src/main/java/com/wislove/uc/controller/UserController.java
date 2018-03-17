package com.wislove.uc.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 廖双龙 on 2018/3/17.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/uc")
public class UserController {

    @GetMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        return  "你访问的是首页哦";
    }
}
