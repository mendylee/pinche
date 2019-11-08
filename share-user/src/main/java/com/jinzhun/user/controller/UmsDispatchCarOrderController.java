package com.jinzhun.user.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsDispatchCarOrder;
import com.jinzhun.common.utils.CommonResult;
import com.jinzhun.user.service.IUmsDispatchCarOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 车主端订单API
 */
@Slf4j
@Api(tags = "车主端订单API")
@RestController
@RequestMapping("/owner/order")
public class UmsDispatchCarOrderController {
	
	@Autowired private IUmsDispatchCarOrderService dispatchCarOrderService;

	@GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询车主订单信息")
	@SysLog(MODULE = "ums", REMARK = "根据ID查询车主订单信息")
	public Result<UmsDispatchCarOrder> findDispatchOrderById(@PathVariable Long id) {
		return Result.succeed(dispatchCarOrderService.getById(id));
	}
	
	@GetMapping("/list")
    @ApiOperation(value = "获取车主订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
                        @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")})
	@SysLog(MODULE = "ums", REMARK = "获取车主订单列表")
    public Object getHistoryByPage(@RequestParam Map<String, Object> params) {
		int page = MapUtils.getInteger(params,"page");
		int limit = MapUtils.getInteger(params, "limit");
		try {
			return new CommonResult().success(dispatchCarOrderService.page(new Page<UmsDispatchCarOrder>(page,limit)));
		}catch (Exception e) {
            log.error("根据条件查询所有用户列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }
	
	@PutMapping("/state")
    @ApiOperation(value = "更新车主订单状态信息")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "Integer"),
                         @ApiImplicitParam(name = "state", value = "订单状态", required = true, dataType = "Integer")})
	@SysLog(MODULE = "ums", REMARK = "更新车主订单状态信息")
	public Result<Boolean> updateShareCarOrderState(@RequestParam Map<String, Object> params) {
		Long id = MapUtils.getLong(params, "id");
		return Result.succeed(true);
	}
	
}
