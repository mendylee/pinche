package com.jinzhun.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinzhun.common.annotation.SysLog;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsDispatchCar;
import com.jinzhun.common.model.UmsShareCar;
import com.jinzhun.user.service.IUmsDispatchCarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "车主端车讯API")
@RestController
@RequestMapping("/owner/publish")
public class UmsDispatchCarController {

	@Autowired private IUmsDispatchCarService dispatchCarService;
	
	@PostMapping("")
    @ApiOperation(value = "发布车讯")
	@SysLog(MODULE = "ums", REMARK = "发布车讯")
    public Result<UmsShareCar> saveShareCar(@RequestBody UmsDispatchCar dispatchCar) {
		dispatchCarService.publish(dispatchCar);
        return Result.succeed("发布车讯成功");
    }
	
	@GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询发布信息")
	@SysLog(MODULE = "ums", REMARK = "根据ID查询发布信息")
    public Result<UmsDispatchCar> findShareCarById(@PathVariable Long id) {
		UmsDispatchCar result = dispatchCarService.getById(id);
        return Result.succeed(result, "查询成功");
    }
	
	@GetMapping("/dispatchs/{uid}")
	@ApiOperation(value = "根据用户ID起始和目的地获取匹配的车讯列表")
	@SysLog(MODULE = "ums", REMARK = "根据起始和目的地获取匹配的车讯列表")
	public Result<List<UmsDispatchCar>> getDispatchList(@PathVariable("uid") Long uid,
														@RequestParam("page") int page, 
														@RequestParam("limit") int limit) {
		page = page <= 0 ? 1 : page;
		limit = limit <= 0 ? 10 : limit;
		List<UmsDispatchCar> result = dispatchCarService.getMatchDispatchCars(uid, page, limit);
		return Result.succeed(result, "查询成功");
	}
}
