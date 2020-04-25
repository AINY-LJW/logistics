package com.logistics.feign.provider.util;

import java.util.List;
import java.util.Map;

/**
 * 
 * 简述部分: 物种种群
 *
 * @author helantian
 * @version 2020年4月25日
 */
public class SpeciesPopulation {
	/**
	 * 种群类，总群由物种组成。该类功能主要是把物种聚集起来形成总群的。暂时当做一个物种只有一个个体。
	 */
	/**
	 *  头结点
	 */
	SpeciesIndividual head;
	/**
	 * 物种数量
	 */
	 int speciesNum;
	 private int citynum;
	 List<Map<String, Double>> coors;
	SpeciesPopulation(int size, List<Map<String, Double>> coors) {
		head = new SpeciesIndividual(citynum,coors);
		speciesNum = TSPData.SPECIES_NUM;
		this.citynum = size;
		this.coors = coors;
	}

	/**
	 * 添加物种
	 * TODO
	 * @param 
	 * @return void
	 */
	void add(SpeciesIndividual species) {
		SpeciesIndividual point = head;// 游标
		while (point.next != null)// 寻找表尾结点
			point = point.next;
		point.next = species;
	}

	/**
	 * 遍历
	 * TODO
	 * @param 
	 * @return void
	 */
	void traverse() {
		SpeciesIndividual point = head.next;// 游标
		while (point != null)// 寻找表尾结点
		{
			for (int i = 0; i < speciesNum; i++)
				System.out.print(point.genes[i] + " ");
			System.out.println(point.distance);
			point = point.next;
		}
		System.out.println("_______________________");
	}
}
