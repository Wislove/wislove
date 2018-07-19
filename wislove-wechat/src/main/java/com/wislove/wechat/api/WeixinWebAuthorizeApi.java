package com.wislove.wechat.api;

import java.util.HashMap;
import java.util.Map;

import com.wislove.wechat.utils.HttpClientUtil;

/**
* 微信网页授权API
* @author: 廖双龙
* @date: 2018年7月18日下午8:30:39  
*/
public class WeixinWebAuthorizeApi {
	
	
	
	/**
	 * 根据code获取openId
	 * @param code 
	 * @return String 
	 * @author 廖双龙
	 * @date 2018年7月18日 下午9:46:33
	 * @version v1.0.0
	 */
	public static String getOpenId(String code, String appId, String secret){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		
		Map<String, String> params = new HashMap<>();
		params.put("appid", appId);
		params.put("secret", secret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		return HttpClientUtil.sendGetRequest(url, null, params);
	}
	
	
}
