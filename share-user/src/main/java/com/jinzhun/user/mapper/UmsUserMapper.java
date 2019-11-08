package com.jinzhun.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.model.SysUser;
import com.jinzhun.common.model.UmsUser;

/**
 * UmsUser Mapper 接口
 */
@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {

	List<UmsUser> findList(Page<SysUser> page, @Param("u") Map<String, Object> params);
}
