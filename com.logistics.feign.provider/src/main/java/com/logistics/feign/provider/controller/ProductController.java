package com.logistics.feign.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * 简述部分:农产品
 * 
 *
 * @author lijiawen
 * @version 2020年4月22日
 */

import com.logistics.feign.provider.service.ProductService;
import com.logistics.util.EasyUIDataGridResult;
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired 
	private ProductService productService;
	
	/**
	 * 根据关键词农产品
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return EasyUIDataGridResult
	 */
	@PostMapping("/getall")
	//
	EasyUIDataGridResult getAllProducts(@RequestBody @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, @RequestParam(value = "asin", required = false) String name,
			@RequestParam(value = "reviewerName", required = false) String classtype,
			@RequestParam(value = "keyWord", required = false) String place, @RequestParam(value = "identity", required = false) String other) {
				return productService.getAllProduct(pageNum, pageSize, name, classtype, place, other);
		
	}
}
