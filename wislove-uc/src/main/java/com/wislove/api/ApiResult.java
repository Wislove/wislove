package com.wislove.api;

import lombok.Data;

/**
* api统一返回结果
* @author: 廖双龙
* @date: 2018年6月26日下午5:15:33  
*/
@Data
public class ApiResult {
	
	/**状态码**/
	private int code;
	/**消息**/
	private String message;
	/**返回数据**/
	private Object data;
	
	
	public static ApiResult success(){
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCode.SUCCESS.getCode());
		apiResult.setMessage(ApiCode.SUCCESS.getMessage());
		
		return apiResult;		
	}
	
	public static ApiResult success(Object data){
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(ApiCode.SUCCESS.getCode());
		apiResult.setMessage(ApiCode.SUCCESS.getMessage());
		apiResult.setData(data);
		return apiResult;		
	}
	
	
	public static ApiResult fail(int code, String message){
		ApiResult apiResult = new ApiResult();
		apiResult.setCode(code);
		apiResult.setMessage(message);
		
		return apiResult;
	}
	
}