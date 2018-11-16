package com.wislove.pay.rpc.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wislove.pay.dto.PayResult;
import com.wislove.pay.dto.PlaceOrderParams;
import com.wislove.pay.rpc.api.PayRpcService;


/**
 * Created by 廖双龙 on 2018/11/16.
 */
@Service(version =  "1.0.0")
public class PayRpcServiceImpl implements PayRpcService {

    public PayResult placeOrder(PlaceOrderParams params) {
        return null;
    }
}