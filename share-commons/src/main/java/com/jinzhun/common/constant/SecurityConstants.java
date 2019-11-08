package com.jinzhun.common.constant;

public interface SecurityConstants {
	
    /**
     * 用户信息分隔符
     */
    String USER_SPLIT = ":";
	
    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";
    
    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";
    
    /**
     * 基础角色
     */
    String BASE_ROLE = "ROLE_USER";

    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";
    
    /**
     * 密码模式
     */
    String PASSWORD = "password";

    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";

	/**
	 * 缓存client的redis key，这里是hash结构存储
	 */
	String CACHE_CLIENT_KEY = "oauth_client_details";
	
	/**
	 * OAUTH模式登录处理地址
	 */
	String OAUTH_LOGIN_PRO_URL = "/user/login";
	/**
	 * PASSWORD模式登录处理地址
	 */
	String PASSWORD_LOGIN_PRO_URL = "/oauth/user/token";
	/**
	 * 获取授权码地址
	 */
	String AUTH_CODE_URL = "/oauth/authorize";
	/**
	 * 默认的OPENID登录请求处理url
	 */
	String OPENID_TOKEN_URL = "/oauth/openId/token";
	/**
	 * 手机登录URL
	 */
	String MOBILE_TOKEN_URL = "/oauth/mobile/token";
	/**
	 * 登出URL
	 */
	String LOGOUT_URL = "/oauth/remove/token";
}
