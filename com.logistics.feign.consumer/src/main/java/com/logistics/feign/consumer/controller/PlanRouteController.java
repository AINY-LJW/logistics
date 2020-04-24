package com.logistics.feign.consumer.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.consumer.feign.UserFeignClient;
import com.logistics.util.R;

@RestController()
@RequestMapping("/planRote")
public class PlanRouteController {
	@Autowired
	private UserFeignClient userFeignClient;
	
	@PostMapping("/getall")
	public R getAll() {
		return userFeignClient.getAll();
	}
	
	@PostMapping("/getPlanList")
	public R getPlanList(@RequestBody Map<String, Object> datas) {
		R r= R.ok() ;
		r.put("datas", datas);
		return r;
	}
}
