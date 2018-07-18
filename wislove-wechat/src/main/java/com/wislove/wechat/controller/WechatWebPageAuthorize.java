package com.wislove.wechat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author: 廖双龙
* @date: 2018年7月18日下午8:31:45  
*/
@RestController
public class WechatWebPageAuthorize {
	
	
	@GetMapping(value = "/authorize")
	public String authorize(String code, String state){
		// 获取到code,使用code去获取openId
		
		
		return "";
	}
}
