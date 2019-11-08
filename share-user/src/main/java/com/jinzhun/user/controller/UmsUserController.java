package com.jinzhun.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinzhun.common.annotation.LoginUser;
import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.UmsUser;
import com.jinzhun.user.service.IUmsUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户API
 */
@Api(tags = "用户API")
@RestController
public class UmsUserController {

	@Autowired private IUmsUserService umsUserService;
	
	@GetMapping("/users/current")
	@ApiOperation(value = "根据access_token当前登录用户")
	@SysLog(MODULE = "ums", REMARK = "根据access_token当前登录用户")
	public LoginUser getLoginUserByToken(@LoginUser(isFull = true) UmsUser user) {
		return  null;
	}
	
	@GetMapping("/users/info")
    @ApiOperation(value = "获取当前登录用户信息")
	@SysLog(MODULE = "ums", REMARK = "获取当前登录用户信息")
	public Object getUserInfo(@LoginUser(isFull = true) UmsUser user) {
		return null;
	}
	
    @GetMapping(value = "/users/mobile", params = "mobile")
    @ApiOperation(value = "根据手机号查询用户")
    @SysLog(MODULE = "ums", REMARK = "根据手机号查询用户信息")
    public UmsUser findUserByMobile(@RequestParam(required = true, name = "mobile") String mobile) {
    	return null;
    }
    
    @GetMapping(value = "/users/openId", params = "openId")
    @ApiOperation(value = "根据OpenId查询用户")
    @SysLog(MODULE = "ums", REMARK = "根据OpenId查询用户")
    public UmsUser findUserByOpenId(@RequestParam(required = true, name = "openId") String openId) {
    	return null;
    }
    
    @GetMapping("/users/{uid}")
    @ApiOperation(value = "根据UID查询用户")
    @SysLog(MODULE = "ums", REMARK = "根据UID查询用户")
    public UmsUser findUserByUid(@PathVariable Long uid) {
        return null;
    }
    
    @PostMapping("users/register")
    @ApiOperation(value = "用户注册")
    @SysLog(MODULE = "ums", REMARK = "用户注册")
	public Object registerUser(@RequestBody UmsUser user) {
    	return null;
    }
    
    @PutMapping(value = "users/{id}")
    @ApiOperation(value = "更新用户")
    @SysLog(MODULE = "sys", REMARK = "更新用户")
	public Object updateUser(@RequestBody UmsUser user) {
    	return null;
    }
}
