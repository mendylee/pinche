package com.jinzhun.user.vo;

import com.jinzhun.common.model.UmsShareCar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("乘客车讯VO")
public class UmsShareCarParam extends UmsShareCar {
	
	private static final long serialVersionUID = 4092488296214480094L;

	/**
	 * 用户名称
	 */
	@ApiParam("用户名")
	private String username;
	
	/**
	 * 手机号
	 */
	@ApiParam("手机号")
	private String mobile;
	
	/**
	 * 家庭住址:A1,A2,A3,A4,AN,H1,H2,H3,..N
	 */
	@ApiParam("家庭住址,如:A1幢")
	private String address;
}
