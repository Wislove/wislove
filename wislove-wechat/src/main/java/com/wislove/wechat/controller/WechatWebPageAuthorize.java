package com.wislove.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 微信网页授权验证
* @author: 廖双龙
* @date: 2018年7月18日下午8:31:45  
*/
@RestController
public class WechatWebPageAuthorize {
	
	/**
	 * 微信网页授权验证跳转接口
	 * @param code
	 * @param state
	 * @return String
	 * @author 廖双龙
	 * @date 2018年7月18日 下午9:44:03
	 * @version v1.0.0
	 */
	@GetMapping(value = "/authorize")
	public String authorize(String code, String state, HttpServletRequest request){
		// 获取到code,使用code去获取openid
		System.out.println("--->>微信网页授权返回的code: "+code);
		
		
		return "";
	}
}
