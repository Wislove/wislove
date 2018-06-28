package com.wislove.api;

/**
 * api状态码
 * 
 * @author: 廖双龙
 * @date: 2018年6月26日下午5:47:13
 */
public enum ApiCode {

	/** 成功 **/
	SUCCESS(200, "成功"),
	// 权限异常
	ACCESS_EXCEPTION(101, "权限不足"),
	// 参数检验异常,参数错误
	PARAM_EXCEPTION(414, "参数异常");
	// 代码逻辑异常,全局异常处理

	// 业务逻辑判断异常

	// 数据异常(数据已存在,未找到等)

	// 调用异常(rpc异常,第三方异常,网络异常等)
	
	/** 状态码 **/
	private Integer code;
	/** 消息 **/
	private String message;
	
	private ApiCode(Integer code, String messagge) {
		this.code = code;
		this.message = messagge;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
