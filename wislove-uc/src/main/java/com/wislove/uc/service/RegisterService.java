package com.wislove.uc.service;

import com.wislove.uc.entity.UserBaseInfo;

/**
 * 注册相关业务接口
 * Created by 廖双龙 on 2018/3/25.
 */
public interface RegisterService {

    /**
     * 注册
     * @param userBaseInfo
     * @return
     */
    UserBaseInfo register(UserBaseInfo userBaseInfo);
}
