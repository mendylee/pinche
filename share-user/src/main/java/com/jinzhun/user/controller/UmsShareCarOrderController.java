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

import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsShareCarOrder;
import com.jinzhun.user.service.IUmsShareCarOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 乘客端订单API
 */
@Api(tags = "乘客端订单API")
@RestController
@RequestMapping("/passenger/order")
public class UmsShareCarOrderController {

	@Autowired private IUmsShareCarOrderService umsShareCarOrderService;
	
	@GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询乘车订单信息")
	@SysLog(MODULE = "ums", REMARK = "根据ID查询乘车订单信息")
	public Result<UmsShareCarOrder> findShareCarOrderById(@PathVariable Long id) {
		return Result.succeed(umsShareCarOrderService.getById(id));
	}
	
	@PutMapping("/state")
    @ApiOperation(value = "更新乘客乘车订单状态信息")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "Integer"),
                         @ApiImplicitParam(name = "state", value = "订单状态", required = true, dataType = "Integer")})
	@SysLog(MODULE = "ums", REMARK = "更新乘客乘车订单状态信息")
	public Result<Boolean> updateShareCarOrderState(@RequestParam Map<String, Object> params) {
		Long id = MapUtils.getLong(params, "id");
		return Result.succeed(true);
	}
}
