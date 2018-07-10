package com.wislove.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.wislove.constants.AppConstants;

import lombok.extern.slf4j.Slf4j;

/**
* 验证token的授权拦截器
* @author: 廖双龙
* @date: 2018年7月9日下午3:04:47  
*/
@Component
@Slf4j
public class AuthTokenFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		log.info("--->>请请求的url为:{}", req.getRequestURI());
		
		String access_token = req.getHeader(AppConstants.ACCESS_TOKEN);	
		log.info("--->> access_token的值为:{}", access_token);
		
		// 这里对token的值进行业务判断
		boolean isPass = false;
		
		// 数据库中获取白名单,不需要token的
		
		// 如果token正确或者url在白名单中
		if (isPass){
			chain.doFilter(request, response);
		}else{
			throw new ServletException("--->> access_token 验证失败");
		}
	}

	@Override
	public void destroy() {
		
	}

}
