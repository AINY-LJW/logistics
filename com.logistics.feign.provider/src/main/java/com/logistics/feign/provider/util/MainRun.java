package com.logistics.feign.provider.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class MainRun {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<>();
		// 起点 纬度,经度
		params.put("origin", "43.887223,125.3243");
		// 终点
		params.put("destination", "43.22222,125.3243");
		params.put("ak", "S8deQDKHCZCLkyAUF6Gt9MyvpgRFlccx");
		// 途经点  英文竖线分割
		//	params.put("waypoints", value);
		//	params.put("", value);
		
		JSONObject doGet = HttpUtil.doGet("http://api.map.baidu.com/directionlite/v1/driving", (JSONObject)JSON.toJSON(params));
		System.out.println(doGet);
		JSONObject result = (JSONObject) ((JSONObject)JSON.parse((String) doGet.get("data"))).get("result");
		JSONArray routes = (JSONArray) result.get("routes");
		for (Object object : routes) {
			JSONArray steps = (JSONArray) ((JSONObject)object).get("steps");
			for (Object step : steps) {
				JSONObject step1 = (JSONObject)step;
				Map<String, Double> start = (Map<String, Double>) step1.get("start_location");
				Map<String, Double> end = (Map<String, Double>) step1.get("end_location");
				System.out.println(start);
				System.out.println(end);
				break;
			}
		}
//		System.out.println(routes);
//		List<Map<String, Double>> list =new ArrayList<Map<String,Double>>();
//		Map<String, Double> map = new HashMap<>();
//		map.put("lat", 12.2);
//		map.put("lng", 12.2);
//		list.add(map);
//		Map<String, Double> map1 = new HashMap<>();
//		map1.put("lat", 1.2);
//		map1.put("lng", 1.2);
//		list.add(map1);
//		Map<String, Double> map2 = new HashMap<>();
//		map2.put("lat", 4.2);
//		map2.put("lng", 4.2);
//		list.add(map2);
//		//创建遗传算法驱动对象
//		GeneticAlgorithm GA=new GeneticAlgorithm(3,list);
//
//		//创建初始种群（所有类型解决方案）
//		SpeciesPopulation speciesPopulation = new SpeciesPopulation(3,list);
//
//		//开始遗传算法（选择算子、交叉算子、变异算子）
//		// 最好方案
//		SpeciesIndividual bestRate=GA.run(speciesPopulation);
//
//		//打印路径与最短距离
//		bestRate.printRate();

		}
}
