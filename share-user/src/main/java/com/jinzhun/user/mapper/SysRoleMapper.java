package com.jinzhun.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.model.SysRole;

/**
 * 角色Mapper接口
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRole> findRolesByUserId(Long id);
	
	List<SysRole> findList(Page<SysRole> page, @Param("r") Map<String, Object> params);
}
