package com.jinzhun.common.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户拼车信息
 */
@Data
@ApiModel("乘客车讯")
@EqualsAndHashCode(callSuper = false)
@TableName("ums_share_car")
public class UmsShareCar extends SuperEntity<UmsShareCar> {
	
	private static final long serialVersionUID = -1295989586127402465L;
	
	/**
	 * 用户ID
	 */
	@NotNull
	@Max(value = Long.MAX_VALUE)
	@ApiModelProperty("用户ID")
	private Long uid;
	
	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名")
	private String username;
	
	/**
	 * 家庭住址:A1,A2,A3,A4,AN,H1,H2,H3,..N
	 */
	@ApiParam("家庭住址,如:A1幢")
	private String address;
	
	/**
	 * 手机号
	 */
	@NotNull
	@Pattern(regexp = "^(1[3-9])\\\\d{9}$")
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 起始地
	 */
	@NotNull
	@ApiModelProperty("起始地")
	private String source;
	
	/**
	 * 目的地
	 */
	@NotNull
	@ApiModelProperty("目的地")
	private String target;
	
	/**
	 * 旅程类型:1.上班,2.回家
	 */
	@NotNull
	@Size(min = 1,max = 2)
	@ApiModelProperty("旅程类型:1.上班,2.回家")
	private Integer type;
	
	/**
	 * 座位数
	 */
	@NotNull
	@Size(min = 1,max = 6)
	@ApiModelProperty("座位数")
	private Integer stall;
	
	/**
	 * 乘坐日期,1.今天，2.明天
	 */
	@NotNull
	@Size(min = 1,max = 2)
	@ApiModelProperty("乘坐日期,1.今天，2.明天")
	private Integer date;
	
	/**
	 * 乘坐时间
	 */
	@TableField("start_time")
	@ApiModelProperty("乘坐时间")
	private Long startTime;
	
	/**
	 * 备注信息,特殊要求说明(如:小孩、行李、等)
	 */
	@ApiModelProperty("备注信息")
	private String remark;
}
