package com.jinzhun.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinzhun.common.model.SysRoleUser;

/**
 * 用户和角色关系表 Mapper 接口
 */
@Mapper
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {

}
