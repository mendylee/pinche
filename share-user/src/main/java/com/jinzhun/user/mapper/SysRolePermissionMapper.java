package com.jinzhun.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinzhun.common.model.SysRoleMenu;

/**
 * 用户角色和权限关系表 Mapper接口
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRoleMenu> {

}
