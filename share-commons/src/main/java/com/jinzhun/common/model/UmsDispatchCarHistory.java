package com.jinzhun.common.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户拼车信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_dispatch_car_history")
public class UmsDispatchCarHistory extends UmsDispatchCar {
	
	private static final long serialVersionUID = -1295989586127402465L;

}
