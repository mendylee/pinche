package com.jinzhun.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsShareCar;

/**
 * 乘客端拼车管理接口
 */
public interface IUmsShareCarService extends IService<UmsShareCar> {

	/**
	 * 保存数据
	 */
	void publish(UmsShareCar dispatchCar);
	
	/**
	 * 预定车位
	 */
	Result<?> subcribe(Long id, Long uid);
}
