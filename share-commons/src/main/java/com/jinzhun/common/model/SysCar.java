package com.jinzhun.common.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车辆信息实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_car")
public class SysCar extends SuperEntity<SysCar> {
	
	private static final long serialVersionUID = -5886012896705137070L;

	/**
	 * 车辆颜色
	 */
	@NotNull
	@Size(min = 1,max = 10)
	private Integer color;
	
	/**
	 * 车牌号
	 */
	@NotNull
	private String licence;
	
	/**
	 * 车辆类型：1.小型汽车,2.SUV,3.商务车,4.其它/未知
	 */
	@NotNull
	@Size(min = 1,max = 4)
	private Integer type;
	
	/**
	 * 汽车品牌名称
	 */
	private String brand;
}
