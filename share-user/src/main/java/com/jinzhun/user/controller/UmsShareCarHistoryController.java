package com.jinzhun.user.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.UmsShareCarHistory;
import com.jinzhun.common.utils.CommonResult;
import com.jinzhun.user.service.IUmsShareCarHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 乘客端历史路线管理API
 */
@Slf4j
@Api(tags = "乘客端拼车历史API")
@RestController
@RequestMapping("/passenger/history")
public class UmsShareCarHistoryController {
	
	@Autowired private IUmsShareCarHistoryService umsShareCarHistoryService;
	
	@GetMapping("/list")
    @ApiOperation(value = "获取乘客行程历史列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
                        @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")})
	@SysLog(MODULE = "ums", REMARK = "获取乘客行程历史列表")
    public Object findHistories(@RequestParam Map<String, Object> params) {
		int page = MapUtils.getInteger(params,"page");
		int limit = MapUtils.getInteger(params, "limit");
		try {
			return new CommonResult().success(umsShareCarHistoryService.page(new Page<UmsShareCarHistory>(page,limit)));
		}catch (Exception e) {
            log.error("根据条件查询所有用户列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

}
