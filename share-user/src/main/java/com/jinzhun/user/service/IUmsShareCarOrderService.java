package com.jinzhun.user.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsShareCarOrder;

/**
 * 乘客拼车订单接口
 */
public interface IUmsShareCarOrderService extends IService<UmsShareCarOrder> {

	/**
	 * 状态变更
	 */
	Result updateState(Map<String, Object> params);
}
