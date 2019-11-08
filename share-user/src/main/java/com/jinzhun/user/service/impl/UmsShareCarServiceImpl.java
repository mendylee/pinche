package com.jinzhun.user.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsDispatchCar;
import com.jinzhun.common.model.UmsDispatchCarOrder;
import com.jinzhun.common.model.UmsShareCar;
import com.jinzhun.common.model.UmsShareCarHistory;
import com.jinzhun.common.model.UmsShareCarOrder;
import com.jinzhun.user.mapper.UmsDispatchCarMapper;
import com.jinzhun.user.mapper.UmsDispatchCarOrderMapper;
import com.jinzhun.user.mapper.UmsShareCarHistoryMapper;
import com.jinzhun.user.mapper.UmsShareCarMapper;
import com.jinzhun.user.mapper.UmsShareCarOrderMapper;
import com.jinzhun.user.service.IUmsDispatchCarOrderService;
import com.jinzhun.user.service.IUmsShareCarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UmsShareCarServiceImpl extends ServiceImpl<UmsShareCarMapper, UmsShareCar> implements IUmsShareCarService {
	
	@Resource private UmsShareCarMapper shareCarMapper;
	@Resource private UmsDispatchCarMapper dispatchCarMapper;
	@Resource private UmsShareCarOrderMapper shareCarOrderMapper;
	@Resource private UmsDispatchCarOrderMapper dispatchCarOrderMapper;
	@Resource private UmsShareCarHistoryMapper shareCarHistoryMapper;
	
	@Resource private IUmsDispatchCarOrderService dispatchCarOrderService;
	
	@Override
	public void publish(UmsShareCar shareCar) {
		UmsShareCar saves = new UmsShareCar();
		try {
			//保存车讯信息
			BeanUtils.copyProperties(saves, shareCar);
			shareCarMapper.insert(saves);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("保存车辆信息失败");
			e.printStackTrace();
		}
		
		try {
			// 保存历史记录
			UmsShareCarHistory shareHistory = new UmsShareCarHistory();
			BeanUtils.copyProperties(shareHistory, shareCar);
			shareCarHistoryMapper.insert(shareHistory);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("保存车讯历史失败");
			e.printStackTrace();
		}
	}
	
	private void handleShareOrderId(UmsShareCarOrder shareOrder,Long shareCarId) {
		if(StringUtils.isEmpty(shareOrder.getOrderId())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            StringBuilder sb = new StringBuilder();
            sb.append(sdf.format(new Date()));
            sb.append(String.format("%04d", shareCarId));
            sb.append(String.format("%03d", 1));
            shareOrder.setOrderId(sb.toString());
		}
	}
	
	public void handleDispatchOrderId(UmsDispatchCarOrder dispatchCarOrder,Long dispatchCarId) {
		if(StringUtils.isEmpty(dispatchCarOrder.getOrderId())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            StringBuilder sb = new StringBuilder();
            sb.append(sdf.format(new Date()));
            sb.append(String.format("%04d", dispatchCarId));
            sb.append(String.format("%03d", 1));
            dispatchCarOrder.setOrderId(sb.toString());
		}
	}

	@Override
	public Result<?> subcribe(Long id,Long uid) {
		UmsShareCar shareCar = shareCarMapper.selectOne(new QueryWrapper<UmsShareCar>().eq("uid", uid));
		UmsDispatchCar dispatchCar = dispatchCarMapper.selectOne(new QueryWrapper<UmsDispatchCar>().eq("id", id));
		try {
			//创建乘客端订单记录
			UmsShareCarOrder shareOrder = new UmsShareCarOrder();
			BeanUtils.copyProperties(shareOrder, shareCar);
			shareOrder.setState(2);
			shareOrder.setOwnerId(dispatchCar.getUid());
			handleShareOrderId(shareOrder,shareCar.getId());
			shareCarOrderMapper.insert(shareOrder);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("乘客订单生成失败");
			e.printStackTrace();
		}
		
		try {
			//创建车主端订单记录
			UmsDispatchCarOrder dispatchCarOrder = new UmsDispatchCarOrder();
			BeanUtils.copyProperties(dispatchCarOrder, dispatchCar);
			dispatchCarOrder.setState(1);
			dispatchCarOrder.setPassengers(uid + ",");
			dispatchCarOrder.setStall(dispatchCar.getStall() - shareCar.getStall());
			handleDispatchOrderId(dispatchCarOrder, dispatchCar.getCarId());
			dispatchCarOrderMapper.insert(dispatchCarOrder);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error("乘客订单生成失败");
			e.printStackTrace();
		}
		return Result.succeed("预定成功");
	}

}
