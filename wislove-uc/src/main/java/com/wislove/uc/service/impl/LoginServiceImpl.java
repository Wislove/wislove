package com.wislove.uc.service.impl;

import com.wislove.uc.entity.UserBaseInfo;
import com.wislove.uc.mapper.UserBaseInfoMapper;
import com.wislove.uc.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 廖双龙 on 2018/3/25.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public UserBaseInfo login(String account, String password) {
        UserBaseInfo userBaseInfo = userBaseInfoMapper.selectByPrimaryKey(Long.valueOf(1));

        return userBaseInfo;
    }
}
