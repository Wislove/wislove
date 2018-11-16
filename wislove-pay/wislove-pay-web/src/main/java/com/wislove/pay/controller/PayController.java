package com.wislove.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by 廖双龙 on 2018/11/16.
 */
@Controller
public class PayController {


    @PostMapping(value = "/{payWay}/notify")
    public void notify(@PathVariable("payWay") String payWay){

    }
}
