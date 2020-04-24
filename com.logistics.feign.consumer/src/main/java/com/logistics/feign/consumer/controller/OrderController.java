package com.logistics.feign.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.consumer.feign.UserFeignClient;
import com.logistics.util.EasyUIDataGridResult;

/**
 * 
 * 简述部分:订单Controller
 * 
 * Copyright: 版权所有 (c) JOIN-CHEER
 * Company: 久其
 *
 * @author lijiawen
 * @version 2020年4月23日
 */
@RestController()
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private UserFeignClient userFeignClient;
	/**
	 * 
	 * TODO
	 * @param 
	 * @return EasyUIDataGridResult
	 */
	@PostMapping("/getall")
	public EasyUIDataGridResult getAllOrder(@RequestBody @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, @RequestParam(value = "asin", required = false) String name,
			@RequestParam(value = "reviewerName", required = false) String issend,
			@RequestParam(value = "keyWord", required = false) String place, @RequestParam(value = "identity", required = false) String isend){
				return userFeignClient.getAllOrder(pageNum, pageSize, name, issend, place, isend);
		
	}
}
