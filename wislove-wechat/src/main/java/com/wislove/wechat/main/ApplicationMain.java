package com.wislove.wechat.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 廖双龙 on 2018/7/14.
 */
@SpringBootApplication
@ComponentScan(value = {"com.wislove.wechat"})
public class ApplicationMain {

    public  static void  main(String[] args){
        SpringApplication.run(ApplicationMain.class);
    }
}
