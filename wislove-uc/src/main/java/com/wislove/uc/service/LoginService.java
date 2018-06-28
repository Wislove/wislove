package com.wislove.uc.service;

import com.wislove.uc.entity.UserBaseInfo;

/**
 * 登录相关业务方法
 * Created by 廖双龙 on 2018/3/25.
 */
public interface LoginService {

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    UserBaseInfo login(String account, String password);
}
