package com.jinzhun.user.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinzhun.common.model.LoginUser;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsUser;

/**
 * 用户业务服务接口
 */
@SuppressWarnings("rawtypes")
public interface IUmsUserService extends IService<UmsUser> {

	/**
	 * 获取当前登录用户信息
	 */
	LoginUser getLoginUser(UmsUser user);
	
	/**
	 * 根据openId获取当前登录用户信息
	 */
	LoginUser findByOpenId(String openId);
	
	/**
	 * 根据手机号获取当前登录用户信息
	 */
	LoginUser findByMobile(String mobile);
	
	/**
	 * 根据用户名获取当前登录用户信息
	 */
	LoginUser findByUsername(String username);
	
	/**
	 * 根据openId获取用户
	 */
	UmsUser selectByOpenId(String openId);
	
	/**
	 * 根据手机号获取用户信息
	 */
	UmsUser selectByMobile(String mobile);
	
	/**
	 * 用户用户名称/昵称获取用户信息
	 */
	UmsUser selectByUsername(String username);
	
	/**
	 * 状态变更
	 */
	Result updateEnabled(Map<String, Object> params);
	
	/**
	 * 保存用户信息
	 */
	boolean saves(UmsUser user);
	
	/**
	 * 更新用户信息
	 */
	boolean updates(Long id, UmsUser user);

}
