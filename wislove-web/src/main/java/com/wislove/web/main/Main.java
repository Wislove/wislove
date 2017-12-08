package com.wislove.web.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 廖双龙 on 2017/12/8.
 */
public class Main {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
    }
}
