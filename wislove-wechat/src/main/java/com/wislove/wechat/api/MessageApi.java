package com.wislove.wechat.api;

import com.wislove.wechat.params.SendTemplateMessageParams;

/**
 * 微信消息api
 * Created by 廖双龙 on 2018/7/14.
 */
public class MessageApi {

    /**
     * 发送模板消息
     * @param params
     * @return
     */
    public static String send(SendTemplateMessageParams params, String access_token){
        final String api = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;


        return  "";
    }

}
