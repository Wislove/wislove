package com.wislove.uc.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.wislove.filter.AuthTokenFilter;

/**
 * 对Filter的自定义注册
 * 
 * @author: 廖双龙
 * @date: 2018年7月9日下午4:47:28
 */
@Configuration
@Component
public class RestFilterConfig {
	
	@Autowired
	private AuthTokenFilter authTokenFilter;
	
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> registrationBean  = new FilterRegistrationBean<Filter>();
		registrationBean.setFilter(authTokenFilter);
		
		// 设置需要过滤的uri名单		
		
		return registrationBean;
	}
}
