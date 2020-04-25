package com.logistics.feign.provider.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainRun {
	public static void main(String[] args) {
		List<Map<String, Double>> list =new ArrayList<Map<String,Double>>();
		Map<String, Double> map = new HashMap<>();
		map.put("lat", 12.2);
		map.put("lng", 12.2);
		list.add(map);
		Map<String, Double> map1 = new HashMap<>();
		map1.put("lat", 1.2);
		map1.put("lng", 1.2);
		list.add(map1);
		Map<String, Double> map2 = new HashMap<>();
		map2.put("lat", 4.2);
		map2.put("lng", 4.2);
		list.add(map2);
		//创建遗传算法驱动对象
		GeneticAlgorithm GA=new GeneticAlgorithm(3,list);

		//创建初始种群（所有类型解决方案）
		SpeciesPopulation speciesPopulation = new SpeciesPopulation(3,list);

		//开始遗传算法（选择算子、交叉算子、变异算子）
		// 最好方案
		SpeciesIndividual bestRate=GA.run(speciesPopulation);

		//打印路径与最短距离
		bestRate.printRate();

		}
}
