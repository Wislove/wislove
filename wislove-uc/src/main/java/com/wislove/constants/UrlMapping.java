package com.wislove.constants;

/**
* 统一的url地址管理常量(在RequestMapping的value中使用):
* 每个web项目一个,或者统一出来一个常量jar包统一导入
* 命名规则遵循restful风格.单词表示不完,以下划线命名
* 通用api相关命名规则: /资源的复数/操作   比如:/users(列表);/users/{id}(获取单个);users/create(创建),users/add(添加);users/delete(删除)
* 特定的角色特定的api命名规则: /角色/操作  比如:/admin/login
* 通用操作api命名: /操作  比如:/login,/register,/logout,/refresh_token
* 
* @author: 廖双龙
* @date: 2018年7月10日上午9:49:19  
*/
public class UrlMapping {
	// 业务场景:
	// 用户使用手机号验证码直接登录,用户未注册,自动注册。注销登录以后,使用手机号作为账号登录,这个时候没有密码,怎么处理?(接口返回，前端交互)
	// 一个userId绑定多种等登陆方式,可以以多种账号登录,看到的都是同一用户
	// 注册，尽量让用户感受不到注册流程。注册尽量简单，甚至没有注册
	// 
	
	// 注册用户来源:自己手动注册,第三方授权,手机号验证码
	
	/**登录**/
	public static final String LOGIN = "/login";
	/**注销退出**/
	public static final String LOGOUT = "/logout";
	/**注册(用户主动的行为)**/
	public static final String REGISTER = "/register";
	/**获取token**/
	public static final String TOKEN = "/token";
	/**刷新token**/
	public static final String REFRESH_TOKEN = "/refresh_token";
	/**获取验证码**/
	public static final String VERIFICATION_CODE = "/verification_code";
}
