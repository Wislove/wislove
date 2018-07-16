package com.wislove.wechat.api;

import org.springframework.util.StringUtils;

/**
 * access_token 相关微信api
 * Created by 廖双龙 on 2018/7/14.
 */
public class AccessTokenApi {

    private static final String APPID = "wxc7ca1dfd1ff1d99f";

    private static final String SECRET = "7b80c66a1c7bb308dd3de189c2b57335";

    /**
     * 获取access_token
     * @param grant_type 授权类型:默认为client_credential
     * @param appid
     * @param secret
     * @return
     */
    public static  String getAccessToken(String grant_type, String appid, String secret){
        final String api = "https://api.weixin.qq.com/cgi-bin/token";

        

        return  "";
    }
}
