package com.wislove.pay.rpc.api;

import com.wislove.pay.dto.PayResult;
import com.wislove.pay.dto.PlaceOrderParams;

/**
 * 支付接口
 * Created by 廖双龙 on 2018/11/16.
 */
public interface PayRpcService {

    /**
     * 统一支付下单
     * @param params
     * @return
     */
    PayResult placeOrder(PlaceOrderParams params);
}
