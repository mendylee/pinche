package com.jinzhun.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户拼车信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_dispatch_car_order")
public class UmsDispatchCarOrder extends SuperEntity<UmsDispatchCarOrder> {
	
	private static final long serialVersionUID = -1295989586127402465L;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 订单ID
	 */
	private String orderId;
	
	/**
	 * 乘客ID列表
	 */
	private String passengers;

	/**
	 * 起始地
	 */
	private String source;
	
	/**
	 * 起始地
	 */
	private String passes;
	
	/**
	 * 目的地
	 */
	private String target;
	
	/**
	 * 旅程类型:1.上班,2.回家
	 */
	private Integer type;
	
	/**
	 * 座位数
	 */
	private Integer stall;
	
	/**
	 * 发车时间
	 */
	@TableField("start_time")
	private Long startTime;
	
	/**
	 * 订单状态:0已取消,1已发布,2进行中,3.已完成
	 */
	private int state;
}
