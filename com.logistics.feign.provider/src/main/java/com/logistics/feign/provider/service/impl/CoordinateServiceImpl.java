package com.logistics.feign.provider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.feign.provider.dao.CoordinateDomainMapper;
import com.logistics.feign.provider.domain.CoordinateDomain;
import com.logistics.feign.provider.service.CoordinateService;

@Service
public class CoordinateServiceImpl implements CoordinateService{
	@Autowired
	private CoordinateDomainMapper coordinateDomainMapper;
	@Override
	public List<Map<String, Double>> selectAll() {
		List<CoordinateDomain> selectAll = coordinateDomainMapper.selectAll();
		List<Map<String, Double>> returnList = new ArrayList<Map<String,Double>>();
		for (CoordinateDomain coordinateDomain : selectAll) {
			Map<String, Double> map =new HashMap<String, Double>();
			map.put("lat", coordinateDomain.getLatitude());
			map.put("lng", coordinateDomain.getLongitude());
			returnList.add(map);
		}
		return returnList;
	}

}
