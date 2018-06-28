package com.wislove.exception;

/**
* 自定义参数异常类
* @author: 廖双龙
* @date: 2018年6月28日上午11:20:27  
*/
public class ParamsException extends Exception{

	/**  
	*
	*/
	private static final long serialVersionUID = 1L;
	
	public ParamsException(String message){
		super(message);
	}
}
