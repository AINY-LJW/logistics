package com.logistics.feign.consumer.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.consumer.feign.UserFeignClient;
import com.logistics.util.R;

@RestController()
@RequestMapping("/planRote")
public class PlanRouteController {
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private HttpServletRequest request;
	
	@PostMapping("/getall")
	public R getAll() {
		return userFeignClient.getAll();
	}
}
