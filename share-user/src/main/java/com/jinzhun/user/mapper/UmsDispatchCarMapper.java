package com.jinzhun.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.model.UmsDispatchCar;

/**
 * UmsDispatchCar-Mapper接口
 */
@Mapper
public interface UmsDispatchCarMapper extends BaseMapper<UmsDispatchCar> {

	List<UmsDispatchCar> findList(Page<UmsDispatchCar> page, @Param("u") Map<String, Object> params);
}
