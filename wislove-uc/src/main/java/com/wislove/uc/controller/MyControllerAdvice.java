package com.wislove.uc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wislove.api.ApiCode;
import com.wislove.api.ApiResult;
import com.wislove.exception.ParamsException;

/**
 * 统一的异常处理类
 * 
 * @author: 廖双龙
 * @date: 2018年6月28日下午2:48:18
 */
@RestControllerAdvice
public class MyControllerAdvice {
	
	/**
	 * 处理参数异常
	 * @param exception
	 * @return ApiResult
	 * @author 廖双龙
	 * @date 2018年6月28日 下午5:18:10
	 * @version v1.0.0
	 */
	@ExceptionHandler({ ParamsException.class })
	public ApiResult handleParamsException(ParamsException exception) {
		return ApiResult.fail(ApiCode.PARAM_EXCEPTION.getCode(), exception.getMessage());
	}
	
	/**
	 * 处理其他异常
	 * @param exception
	 * @return ApiResult
	 * @author 廖双龙
	 * @date 2018年6月28日 下午5:18:23
	 * @version v1.0.0
	 */
	@ExceptionHandler({ Exception.class })
	public ApiResult handleException(Exception exception) {
		return ApiResult.fail(ApiCode.PARAM_EXCEPTION.getCode(), exception.getMessage());
	}
}
