package com.jinzhun.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.model.SysCar;

/**
 * 车辆信息Mapper 接口
 */
@Mapper
public interface SysCarMapper extends BaseMapper<SysCar> {

	List<SysCar> findList(Page<SysCar> page, @Param("u") Map<String, Object> params);
}
