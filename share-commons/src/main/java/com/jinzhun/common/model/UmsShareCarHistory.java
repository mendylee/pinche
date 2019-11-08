package com.jinzhun.common.model;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户拼车信息
 */
@Data
@ApiModel("拼车历史")
@EqualsAndHashCode(callSuper = false)
@TableName("ums_share_car_history")
public class UmsShareCarHistory extends UmsShareCar {

	private static final long serialVersionUID = 6456863799427005195L;

}
