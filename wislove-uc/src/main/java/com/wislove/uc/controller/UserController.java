package com.wislove.uc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wislove.uc.entity.UserBaseInfo;
import com.wislove.uc.service.LoginService;
import com.wislove.uc.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by 廖双龙 on 2018/3/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/uc")
@Slf4j
public class UserController {

    
    @Resource
    private LoginService loginService;
    @Resource
    private RegisterService registerService;
    
    
   @RequestMapping(value = "/login")
   public UserBaseInfo login(String account, String password, HttpServletRequest request, HttpServletResponse response){
       log.info("调用登陆接口");   
       UserBaseInfo userBaseInfo = loginService.login(account, password);
       return userBaseInfo;
   }

    @RequestMapping(value = "/register")
    public UserBaseInfo register(UserBaseInfo userBaseInfo, HttpServletRequest request, HttpServletResponse response){
        UserBaseInfo userBaseInfo1 = registerService.register(userBaseInfo);
        return userBaseInfo;
    }
}
