package com.jinzhun.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinzhun.common.model.UmsDispatchCar;

/**
 * 车主端车讯管理接口
 */
public interface IUmsDispatchCarService extends IService<UmsDispatchCar> {

	/**
	 * 保存数据
	 */
	void publish(UmsDispatchCar dispatchCar);

	/**
	 * 根据起始地和目的地获取匹配的车讯列表
	 */
	List<UmsDispatchCar> getMatchDispatchCars(Long uid,int page, int limit);

}
