package com.jinzhun.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.model.UmsShareCarOrder;

/**
 * UmsShareCarOrder-Mapper接口
 */
@Mapper
public interface UmsShareCarOrderMapper extends BaseMapper<UmsShareCarOrder> {

	List<UmsShareCarOrder> findList(Page<UmsShareCarOrder> page, @Param("u") Map<String, Object> params);
}
