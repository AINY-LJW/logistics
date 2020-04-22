package com.logistics.feign.provider.service;

import java.util.List;
import java.util.Map;


public interface CoordinateService {
	/**
	 * 获取所有坐标
	 * TODO
	 * @param 
	 * @return List<CoordinateDomain>
	 */
	List<Map<String, Double>> selectAll();
}
