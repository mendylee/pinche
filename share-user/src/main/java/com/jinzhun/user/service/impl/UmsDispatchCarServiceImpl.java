package com.jinzhun.user.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzhun.common.model.SysCar;
import com.jinzhun.common.model.UmsDispatchCar;
import com.jinzhun.common.model.UmsDispatchCarHistory;
import com.jinzhun.common.model.UmsShareCar;
import com.jinzhun.common.utils.Codec;
import com.jinzhun.user.mapper.SysCarMapper;
import com.jinzhun.user.mapper.UmsDispatchCarHistoryMapper;
import com.jinzhun.user.mapper.UmsDispatchCarMapper;
import com.jinzhun.user.mapper.UmsDispatchCarOrderMapper;
import com.jinzhun.user.mapper.UmsShareCarMapper;
import com.jinzhun.user.service.IUmsDispatchCarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UmsDispatchCarServiceImpl extends ServiceImpl<UmsDispatchCarMapper, UmsDispatchCar> implements IUmsDispatchCarService {
	
	@Resource private SysCarMapper carMapper;
	@Resource private UmsShareCarMapper umsShareCarMapper;
	@Resource private UmsDispatchCarMapper dispatchCarMapper;
	@Resource private UmsDispatchCarOrderMapper dispatchCarOrderMapper;
	@Resource private UmsDispatchCarHistoryMapper dispatchCarHistoryMapper;
	@Resource private RedisTemplate<String,Object> redisTemplate;
	
	@Override
	public void publish(UmsDispatchCar dispatchCar) {
		SysCar car = new SysCar();
		car.setType(1);
		car.setLicence(dispatchCar.getLicence());
		car.setColor(dispatchCar.getColor());
		carMapper.insert(car);
		
		UmsDispatchCar saves = new UmsDispatchCar();
		try {
			BeanUtils.copyProperties(saves, dispatchCar);
			saves.setCarId(car.getId());
			saves.setLicence(car.getLicence());
			dispatchCarMapper.insert(saves);
			put2Cache(saves);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("保存车辆信息失败");
			e.printStackTrace();
		} 
		
		try {
			UmsDispatchCarHistory dispatchHistory = new UmsDispatchCarHistory();
			dispatchHistory.setLicence(saves.getLicence());
			BeanUtils.copyProperties(dispatchHistory, saves);
			dispatchCarHistoryMapper.insert(dispatchHistory);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("保存车讯历史失败");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<UmsDispatchCar> getMatchDispatchCars(Long uid,int page, int limit) {
		UmsShareCar shareCar = umsShareCarMapper.selectOne(new QueryWrapper<UmsShareCar>().eq("uid", uid));
		//从缓存中获取与之匹配的车辆信息
		List<UmsDispatchCar> dispatchCars2 = (List<UmsDispatchCar>) redisTemplate.opsForValue().get(getKey(shareCar.getSource(), shareCar.getTarget()));
		if(CollectionUtils.isEmpty(dispatchCars2))  
			return null;
		List<Long> ids = new ArrayList<Long>();
		for(UmsDispatchCar dispatch : dispatchCars2) {
			ids.add(dispatch.getId());
		}
		//获取与乘客匹配路线的车，必须保证车主空闲位>0、行程类型保持一致,发车时间与乘客时间相匹配的车辆
		return dispatchCarMapper.selectPage(new Page<UmsDispatchCar>(page,limit),new QueryWrapper<UmsDispatchCar>()
																					 .gt("stall", 0)
																					 .eq("type", shareCar.getType())
																					 .le("start_time", shareCar.getStartTime())
																					 .in("id", ids)
																					 .orderByDesc("start_time")).getRecords();
		
	}
	

	@SuppressWarnings("unchecked")
	private void put2Cache(UmsDispatchCar dispatchCar) {
		String hashKey = null;
		List<UmsDispatchCar> dispatchCars = null;
		
		//这里为了将信息合并处理,将途径地和目的地进行合并
		String[] passSrc = dispatchCar.getPasses().split(",");
		String[] passTar = new String[passSrc.length + 1];
		System.arraycopy(passSrc, 0, passTar, 0, passSrc.length);
		passTar[passSrc.length] = dispatchCar.getTarget();
		List<String> passes = Arrays.asList(passTar);
		for(String pass : passes) {
			hashKey = getKey(dispatchCar.getSource(), pass);
			dispatchCars = (List<UmsDispatchCar>) redisTemplate.opsForValue().get(hashKey);
			if(dispatchCars == null) {
				dispatchCars = new LinkedList<UmsDispatchCar>();
				dispatchCars.add(dispatchCar);
				redisTemplate.opsForValue().setIfAbsent(hashKey, dispatchCars);
			} else {
				dispatchCars.add(dispatchCar);//如果缓存中已存在，则添加到缓存列表中
				redisTemplate.opsForValue().setIfPresent(hashKey, dispatchCars);
			}
		}
	}
	
	private String getKey(String source, String target) {
		return Codec.hexMd5(String.format("%s_%s", source,target));
	}

}
