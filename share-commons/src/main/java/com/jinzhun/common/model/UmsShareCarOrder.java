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
@TableName("ums_share_car_order")
public class UmsShareCarOrder extends SuperEntity<UmsShareCarOrder> {
	
	private static final long serialVersionUID = -1295989586127402465L;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 订单ID
	 */
	@TableField("order_id")
	private String orderId;
	
	/**
	 * 车主ID
	 */
	@TableField("owner_id")
	private Long ownerId;

	/**
	 * 起始地
	 */
	private String source;
	
	/**
	 * 目的地
	 */
	private String target;
	
	/**
	 * 座位数
	 */
	private Integer stall;
	
	/**
	 * 乘坐时间
	 */
	@TableField("start_time")
	private Long startTime;
	
	/**
	 * 订单状态:0已取消,1已发布,2进行中,3已完成
	 */
	private Integer state;
}
