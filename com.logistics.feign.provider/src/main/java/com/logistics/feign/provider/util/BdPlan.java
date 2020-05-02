package com.logistics.feign.provider.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	 * @return 
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public final static List<Map<String, Double>> getPlan(List<Map<String, Double>> waypoints) {
		Map<String, String> params = new HashMap<>();
		// 起点 纬度,经度
		Map<String, Double> orign = waypoints.get(0);
		String or = orign.get("lat") + "," + orign.get("lng");
//		params.put("origin", "40.01116,116.339303");
//		// 终点
//		params.put("destination", "39.936404,116.452562");
		params.put("origin", or);
		// 终点
		params.put("destination", or);
		// ak		
		params.put("ak", "S8deQDKHCZCLkyAUF6Gt9MyvpgRFlccx");
		// 途经点  英文竖线分割
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < waypoints.size() - 1; i++) {
			Map<String, Double> map = waypoints.get(i);
			sb.append(map.get("lat")).append(",")
			.append(map.get("lng"));
			if(i != waypoints.size() - 2) {
				sb.append("|");
			}
		}
			params.put("waypoints", sb.toString());
		//	params.put("", value);
		List<Map<String, Double>> returnList = new ArrayList<Map<String,Double>>();
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
				returnList.add(start);
				returnList.add(end);
//				break;
			}
			break;
		}
		return returnList;
	}
}
