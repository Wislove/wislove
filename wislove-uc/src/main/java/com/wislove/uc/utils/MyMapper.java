package com.wislove.uc.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用继承mapper
 * Created by 廖双龙 on 2018/3/22.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>{


}
