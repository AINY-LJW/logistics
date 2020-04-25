package com.logistics.feign.provider.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * 简述部分: 遗传算法
 *
 * @author helantian 
 * @version 2020年4月25日
 */
public class GeneticAlgorithmPlanRoute {
	public List<Map<String, Double>> getPlanRote(List<Map<String, Double>> list) {
		// 创建遗传算法驱动对象
		GeneticAlgorithm GA = new GeneticAlgorithm(list.size(), list);

		// 创建初始种群（所有类型解决方案）
		SpeciesPopulation speciesPopulation = new SpeciesPopulation(list.size(), list);

		// 开始遗传算法（选择算子、交叉算子、变异算子）
		// 最好方案
		SpeciesIndividual bestRate = GA.run(speciesPopulation);

		// 打印路径与最短距离
		bestRate.printRate();
		
		List<Map<String, Double>> returnList =new ArrayList<Map<String,Double>>();
		String[] genes = bestRate.genes;
		for (int i = 0; i < genes.length; i++) {
			returnList.add(list.get(Integer.parseInt(genes[i]) - 1));
		}
		returnList.add(list.get(0));
		return returnList;
	}
}
