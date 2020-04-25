package com.logistics.feign.provider.controller;
/**
 * 
 * 简述部分:规划路线
 * 
 * @author helantian
 * @version 2020年4月21日
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.feign.provider.service.CoordinateService;
import com.logistics.feign.provider.util.GreedyPlanRoute;
import com.logistics.util.R;
/**
 * 
 * 简述部分:计划
 * 
 *
 * @author helantian
 * @version 2020年4月25日
 */
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
	/**
	 * 规划
	 * TODO
	 * @param 
	 * @return R
	 */
	@PostMapping("/getPlanList")
	public R getPlanList(@RequestBody Map<String, Object> datas) {
		R r= R.ok() ;
		@SuppressWarnings("unchecked")
		List<Map<String, Double>> list = (List<Map<String, Double>>) datas.get("datas");
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("lat", 41.04736312121212);
		map.put("lng", 118.29761111111111);
		// 仓库坐标  模拟		
		list.add(0, map);
		
//		Collections.sort(list, new Comparator<Map<String, Double>>() {  
//			@Override
//			public int compare(Map<String, Double> o1, Map<String, Double> o2) {
//				// TODO Auto-generated method stub
//				return 0;
//			}  
//        });  
		GreedyPlanRoute tsp=new GreedyPlanRoute();
		List<Map<String, Double>> readData = tsp.readData(list);
		r.put("datas", readData);
		return r;
	}
}
