package com.wislove.wechat.api;

import java.util.HashMap;
import java.util.Map;

import com.wislove.wechat.utils.HttpClientUtil;

/**
 * access_token 相关微信api
 * Created by 廖双龙 on 2018/7/14.
 */
public class AccessTokenApi {
	

    /**
     * 获取access_token
     * @param grant_type 授权类型:默认为client_credential
     * @param appid 
     * @param secret 
     * @return
     */
    public static String getAccessToken(String grant_type, String appid, String secret){
        final String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
		params.put("appid", appid);
		params.put("secret", secret);
            
        return  HttpClientUtil.sendGetRequest(url, null, params);
    }
    
}
