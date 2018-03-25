package com.wislove.uc.controller;

import com.wislove.uc.entity.UserBaseInfo;
import com.wislove.uc.service.LoginService;
import com.wislove.uc.service.RegisterService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 廖双龙 on 2018/3/17.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("/uc")
public class UserController {

    @Resource
    private LoginService loginService;
    @Resource
    private RegisterService registerService;

   @RequestMapping(value = "/login")
   public UserBaseInfo login(String account, String password, HttpServletRequest request, HttpServletResponse response){
        UserBaseInfo userBaseInfo = loginService.login(account, password);

        return userBaseInfo;
   }

    @RequestMapping(value = "/register")
    public UserBaseInfo register(UserBaseInfo userBaseInfo, HttpServletRequest request, HttpServletResponse response){
        UserBaseInfo userBaseInfo1 = registerService.register(userBaseInfo);
        return userBaseInfo;
    }
}
