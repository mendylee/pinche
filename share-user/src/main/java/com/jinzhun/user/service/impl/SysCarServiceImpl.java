package com.jinzhun.user.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzhun.common.model.SysCar;
import com.jinzhun.user.mapper.SysCarMapper;
import com.jinzhun.user.service.ISysCarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysCarServiceImpl extends ServiceImpl<SysCarMapper, SysCar> implements ISysCarService {

}
