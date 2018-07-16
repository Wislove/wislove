package com.wislove.wechat.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
* spring-boot启动类
* @author: 廖双龙
* @date: 2018年7月13日上午9:27:43  
*/
@SpringBootApplication
@ComponentScan(basePackages = {"com.wislove.*"})
public class ApplicationMain {
	
	public static  void main(String[] args){
        SpringApplication.run(ApplicationMain.class);
    }
}