package com.logistics.feign.provider.controller;
/**
 * 
 * 简述部分:规划路线
 * 
 * @author lijiawen
 * @version 2020年4月21日
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.provider.service.CoordinateService;
import com.logistics.util.R;

@RestController()
@RequestMapping("/planRote")
public class PlanRouteController {
	@Autowired
	private CoordinateService coordinateService;

	@PostMapping("/getall")
	public R getAll() {
		R r = R.ok();
		List<Map<String, Double>> selectAll = coordinateService.selectAll();
		if(selectAll.size() != 0) {
			r.put("objs", selectAll);
			return r;
		}else {
			return R.error("没有坐标信息");
					
		}
	}
}
