package com.wislove.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by 廖双龙 on 2017/7/27.
 */
public class Config {

    public static  void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-base.xml");
        context.start();
        System.in.read();
    }
}
