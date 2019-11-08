package com.jinzhun.common.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户发车讯信息实体
 */
@Data
@ApiModel("车讯信息")
@EqualsAndHashCode(callSuper = false)
@TableName("ums_dispatch_car")
public class UmsDispatchCar extends SuperEntity<UmsDispatchCar> {
	
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
	 * 手机号
	 */
	@NotNull
	@Pattern(regexp = "^(1[3-9])\\\\d{9}$")
	@ApiModelProperty("手机号")
	private String mobile;
	
	/**
	 * 车辆ID
	 */
	@NotNull
	@Max(value = Long.MAX_VALUE)
	@ApiModelProperty("车辆ID")
	private Long carId;
	
	/**
	 * 车牌号
	 */
	@NotNull
	@ApiModelProperty("车牌号")
	private String licence;
	
	/**
	 * 车辆颜色
	 */
	@NotNull
	@Size(min = 1,max = 10)
	private Integer color;
	
	/**
	 * 起始地
	 */
	@NotNull
	@ApiModelProperty("起始地")
	private String source;
	
	/**
	 * 起始地
	 */
	@ApiModelProperty("途径地")
	private String passes;
	
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
	 * 时间类型:1.今天.2明天
	 */
	@NotNull
	@Size(min = 1,max = 2)
	@ApiModelProperty("时间类型:1.今天.2明天")
	private Integer date;
	
	/**
	 * 发车时间
	 */
	@TableField("start_time")
	@ApiModelProperty("发车时间")
	private Long startTime;
	
	/**
	 * 备注信息,特殊要求说明(如:小孩、行李、等)
	 */
	@ApiModelProperty("备注信息")
	private String remark;
}
