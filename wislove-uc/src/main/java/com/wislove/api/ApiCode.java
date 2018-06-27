package com.wislove.api;

/**
* api状态码
* @author: 廖双龙
* @date: 2018年6月26日下午5:47:13  
*/
public enum ApiCode {
	
	/**成功**/
	SUCCESS(200, "成功");
	
	
	
	/**状态码**/
	private int code;
	/**消息**/
	private String message;
	
	private ApiCode(int code, String messagge){
		code = this.code;
		messagge = this.message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
