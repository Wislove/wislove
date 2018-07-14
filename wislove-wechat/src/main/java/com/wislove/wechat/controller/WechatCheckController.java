package com.wislove.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wislove.wechat.utils.CheckUtils;

/**
 * 微信公众号接入验证接口
 * 
 * @author: 廖双龙
 * @date: 2018年7月12日下午3:48:15
 */
@RestController
public class WechatCheckController {

	@GetMapping(value = "/")
	public  String index(){
		return  "这是首页哦！";
	}
	
	@GetMapping(value = "/check")
	public String check(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		boolean pass = CheckUtils.checkSignature(signature, timestamp, nonce);
		if (pass){
			return echostr;
		}
		
		return "";
	}

}
