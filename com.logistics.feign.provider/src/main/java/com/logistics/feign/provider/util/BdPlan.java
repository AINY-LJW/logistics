package com.logistics.feign.provider.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 简述部分:
 * 
 *
 * @author helantian
 * @version 2020年4月28日
 */
public final class BdPlan {
	/**
	 * 
	 * TODO
	 * @param 
	 * @return void
	 */
	public final static void getPlan(Map<String, Double> nn) {
		Map<String, String> params = new HashMap<>();
		// 起点 纬度,经度
		params.put("origin", "40.01116,116.339303");
		// 终点
		params.put("destination", "39.936404,116.452562");
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
				Object start = step1.get("start_location");
				Object end = step1.get("end_location");
				System.out.println(start);
				System.out.println(end);
				break;
			}
		}
	}
}
