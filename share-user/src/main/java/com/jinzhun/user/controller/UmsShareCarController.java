package com.jinzhun.user.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsShareCar;
import com.jinzhun.user.service.IUmsShareCarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "乘客端车讯API")
@RestController
@RequestMapping("/passenger")
public class UmsShareCarController {

	@Autowired private IUmsShareCarService shareCarService;
	
	@PostMapping("/publish")
    @ApiOperation(value = "发布车讯")
	@SysLog(MODULE = "ums", REMARK = "发布车讯")
    public Result<UmsShareCar> publishCar(@RequestBody UmsShareCar umsShareCar) {
        shareCarService.publish(umsShareCar);
        return Result.succeed("发布成功");
    }
	
	@GetMapping("/publish/{id}")
    @ApiOperation(value = "根据ID查询发布信息")
	@SysLog(MODULE = "ums", REMARK = "根据ID查询发布信息")
    public Result<UmsShareCar> findShareCarById(@PathVariable Long id) {
        UmsShareCar result = shareCarService.getById(id);
        return Result.succeed(result, "查询成功");
    }
	
	@PutMapping("/subcribe")
	@ApiOperation(value = "预定车位/拼车")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "车讯ID", required = true, dataType = "Long"),
        				@ApiImplicitParam(name = "uid", value = "车主ID", required = true, dataType = "Long")})
	@SysLog(MODULE = "ums", REMARK = "预定车位/拼车")
	public Result<Boolean> subcribeCar(@RequestParam Map<String,Object> params) {
		Long id = MapUtils.getLong(params, "id");
		Long uid = MapUtils.getLong(params, "uid");
		shareCarService.subcribe(id,uid);
		return Result.succeed("拼车成功");
	}

}
