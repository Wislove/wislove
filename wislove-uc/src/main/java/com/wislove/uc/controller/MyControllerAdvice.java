package com.wislove.uc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wislove.api.ApiCode;
import com.wislove.api.ApiResult;
import com.wislove.exception.ParamsException;

/**
* 统一的异常处理类
* @author: 廖双龙
* @date: 2018年6月28日下午2:48:18  
*/
@RestControllerAdvice
public class MyControllerAdvice {
	
	 @ExceptionHandler({ ParamsException.class })
	 public ApiResult handleParamsException(ParamsException exception){
		 System.out.println(exception.getClass().getName());
		 return ApiResult.fail(ApiCode.PARAM_EXCEPTION.getCode(), exception.getMessage());	 
	 }
	 
	 @ExceptionHandler({ Exception.class })
	 public ApiResult handleException(Exception exception){
		 System.out.println(exception.getClass().getName());
		 System.out.println(ApiCode.PARAM_EXCEPTION.getCode());
		 return ApiResult.fail(ApiCode.PARAM_EXCEPTION.getCode(), exception.getMessage());	 
	 }
}
