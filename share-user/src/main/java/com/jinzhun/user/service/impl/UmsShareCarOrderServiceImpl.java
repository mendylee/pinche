package com.jinzhun.user.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsShareCarOrder;
import com.jinzhun.user.mapper.UmsShareCarOrderMapper;
import com.jinzhun.user.service.IUmsShareCarOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UmsShareCarOrderServiceImpl extends ServiceImpl<UmsShareCarOrderMapper, UmsShareCarOrder> implements IUmsShareCarOrderService {@Override
	
	public Result updateState(Map<String, Object> params) {
		return null;
	}

}
