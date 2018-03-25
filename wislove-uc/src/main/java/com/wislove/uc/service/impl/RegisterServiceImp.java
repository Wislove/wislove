package com.wislove.uc.service.impl;

import com.wislove.uc.entity.UserBaseInfo;
import com.wislove.uc.mapper.UserBaseInfoMapper;
import com.wislove.uc.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 廖双龙 on 2018/3/25.
 */
@Service
public class RegisterServiceImp implements RegisterService{

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Override
    public UserBaseInfo register(UserBaseInfo userBaseInfo) {
        userBaseInfoMapper.insert(userBaseInfo);
        return userBaseInfoMapper.selectByPrimaryKey(Long.valueOf(1));
    }
}
