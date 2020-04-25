package com.logistics.feign.provider.util;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 简述部分:物种个体 每一个个体的染色体对应着一个解决方案
 *
 * @author helantian
 * @version 2020年4月25日
 */
public class SpeciesIndividual {
	/**
	 * 基因：这里要解决的是TSP问题，因此我们直接采用城市序列作为基因的编码。染色体由随机排列的基因组成。
	 * 物种适应度：解决方案的总距离越小越好,优胜略汰
	 * 因此直接用了总距离的倒数作为物种适应度。那么，总距离越小，物种适应度相应就越大了。
	 */

	String[] genes;// 基因序列
	float distance;// 路程
	float fitness;// 适应度
	SpeciesIndividual next;//物种个体
	float rate;
	
	private TSPData tspData;
	 List<Map<String, Double>> coors;
	/**
	 * 
	 */
	private int cityNum;
	SpeciesIndividual(int size, List<Map<String, Double>> coors) {
		//初始化
		this.genes = new String[size];
		this.distance = 0.0f;
		this.fitness = 0.0f;
		this.next = null;
		rate = 0.0f;
		this.cityNum = size;
		this.tspData =new TSPData(coors);
		this.coors = coors;
	}

	/**
	 * 初始物种基因(随机)
	 * 城市序列
	 * TODO
	 * @param 
	 * @return void
	 */
	void createByRandomGenes() {
		//初始化基因为1-CITY_NUM序列
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Integer.toString(i + 1);
		}

		//获取随机种子（城市）
		Random rand = new Random();

		for (int j = 0; j < genes.length; j++) {
			int num = j + rand.nextInt(genes.length - j);
			if(j == 0 || num == 0) {
				continue;
			}
			//交换位置
			String tmp;
			tmp = genes[num];
			genes[num] = genes[j];
			genes[j] = tmp;
		}
	}

	/**
	 * 初始物种基因(贪婪)
	 * TODO
	 * @param 
	 * @return void
	 */
	void createByGreedyGenes() {
		Random rand = new Random();
		int i = rand.nextInt(cityNum); // 随机产生一个城市作为起点
		genes[0] = Integer.toString(i + 1);
		int j;// 终点
		int cityNum = 0;
		do {
			cityNum++;

			//选出单源最短城市
			float minDis = Integer.MAX_VALUE;
			int minCity = 0;
			for (j = 0; j < cityNum; j++) {
				if (j != i) {
					//判是否和已有重复
					boolean repeat = false;
					for (int n = 0; n < cityNum; n++) {
						if (Integer.parseInt(genes[n]) == j + 1) {
							repeat = true;
							break;
						}
					}
					if (repeat == false){
						//判长度
						if (tspData.disMap[i][j] < minDis) {
							minDis = tspData.disMap[i][j];
							minCity = j;
						}
					}
				}
			}

			//加入到染色体
			genes[cityNum] = Integer.toString(minCity + 1);
			i = minCity;
		} while (cityNum < cityNum - 1);
	}

	/**
	 * 
	 * TODO
	 * @param 
	 * @return void
	 */
	void calFitness() {
		// 总距离
		float totalDis = 0.0f;
		for (int i = 0; i < cityNum; i++) {
			int curCity = Integer.parseInt(this.genes[i]) - 1;
			int nextCity = Integer.parseInt(this.genes[(i + 1) % cityNum]) - 1;

			totalDis += tspData.disMap[curCity][nextCity];
		}

		this.distance = totalDis;
		this.fitness = 1.0f / totalDis;
	}

	/**
	 * 深拷贝
	 */
	public SpeciesIndividual clone() {
		SpeciesIndividual species = new SpeciesIndividual(cityNum,coors);

		//复制值
		for (int i = 0; i < this.genes.length; i++)
			species.genes[i] = this.genes[i];
		species.distance = this.distance;
		species.fitness = this.fitness;

		return species;
	}

	public void printRate() {
		System.out.print("\n最短路线：");
		for (int i = 0; i < genes.length; i++)
			System.out.print(genes[i] + "->");
		System.out.print(genes[0] + "\n");
		System.out.print("最短长度：" + distance);
	}

}
