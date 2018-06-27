package com.wislove.api;

import lombok.Data;

/**
* api统一返回结果
* @author: 廖双龙
* @date: 2018年6月26日下午5:15:33  
*/
@Data
public class ApiResult {
	
	private int code;
	private String message;
	private Object data;
}