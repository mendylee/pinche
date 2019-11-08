package com.jinzhun.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_user")
public class UmsUser extends SuperEntity<UmsUser> {
	
	private static final long serialVersionUID = -5886012896705137070L;
	
	/**
	 * 用户名称
	 */
	private String username;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	@TableField("head_img_url")
	private String headImgUrl;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 用户性别：1.男,0.女
	 */
	private Integer sex;
	
	/**
	 * 是否可用：1.启用,0.禁用
	 */
	private Boolean enabled;
	
	/**
	 * 家庭住址
	 */
	private String adress;
	
	/**
	 * 第三方登录ID
	 */
	@TableField("open_id")
	private String openId;
	
	/**
	 * 是否删除，0否，1是
	 */
	@TableLogic
	@TableField("is_del")
	private boolean isDel;
}
