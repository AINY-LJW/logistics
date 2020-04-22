package com.logistics.feign.consumer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.consumer.feign.UserFeignClient;
import com.logistics.util.EasyUIDataGridResult;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private HttpServletRequest request;
	
	@PostMapping("/getall")
	EasyUIDataGridResult getResumeByKeywords(@RequestBody @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, @RequestParam(value = "asin", required = false) String name,
			@RequestParam(value = "reviewerName", required = false) String classtype,
			@RequestParam(value = "keyWord", required = false) String place, @RequestParam(value = "identity",required = false) String other) {
				return userFeignClient.getAllProducts(pageNum, pageSize, name, classtype, place, other);
		
	}
}
