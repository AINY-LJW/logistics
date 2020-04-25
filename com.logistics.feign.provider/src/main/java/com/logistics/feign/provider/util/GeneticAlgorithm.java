package com.logistics.feign.provider.util;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 简述部分:遗传算法的主体 选择、交叉、变异
 *启发式算法
 * @author helantian
 * @version 2020年4月25日
 */
public class GeneticAlgorithm {
	private int cityNum;
	List<Map<String, Double>> coors;
	public GeneticAlgorithm(int size,List<Map<String, Double>> coors) {
		this.cityNum =size;
		this.coors = coors;
	}
	/**
	 * 开始遗传
	 * TODO
	 * @param 
	 * @return SpeciesIndividual
	 */
	public SpeciesIndividual run(SpeciesPopulation list) {
		// 创建初始种群
		createBeginningSpecies(list);

		for (int i = 1; i <= TSPData.DEVELOP_NUM; i++) {
			// 选择
			select(list);

			// 交叉
			crossover(list);

			// 变异
			mutate(list);
		}

		return getBest(list);
	}

	// 创建初始种群
	void createBeginningSpecies(SpeciesPopulation list) {
		// 100%随机
		int randomNum = (int) (TSPData.SPECIES_NUM);
		for (int i = 1; i <= randomNum; i++) {
			SpeciesIndividual species = new SpeciesIndividual(cityNum, coors);// 创建结点
			species.createByRandomGenes();// 初始种群基因

			list.add(species);// 添加物种
		}

		// //40%贪婪
		// int greedyNum=TSPData.SPECIES_NUM-randomNum;
		// for(int i=1;i<=greedyNum;i++)
		// {
		// SpeciesIndividual species=new SpeciesIndividual();//创建结点
		// species.createByGreedyGenes();//初始种群基因
		//
		// this.add(species);//添加物种
		// }
	}

	/**
	 * 计算每一物种被选中的概率
	 * TODO
	 * @param 
	 * @return void
	 */
	void calRate(SpeciesPopulation list) {
		// 计算总适应度
		float totalFitness = 0.0f;
		list.speciesNum = 0;
		SpeciesIndividual point = list.head.next;// 游标
		while (point != null)// 寻找表尾结点
		{
			point.calFitness();// 计算适应度

			totalFitness += point.fitness;
			list.speciesNum++;

			point = point.next;
		}

		// 计算选中概率
		point = list.head.next;// 游标
		while (point != null)// 寻找表尾结点
		{
			point.rate = point.fitness / totalFitness;
			point = point.next;
		}
	}

	/**
	 * 选择优秀物种（轮盘赌）
	 * TODO
	 * @param 
	 * @return void
	 */
	void select(SpeciesPopulation list) {
		// 计算适应度
		calRate(list);

		// 找出最大适应度物种
		float talentDis = Float.MAX_VALUE;
		SpeciesIndividual talentSpecies = null;
		SpeciesIndividual point = list.head.next;// 游标

		while (point != null) {
			if (talentDis > point.distance) {
				talentDis = point.distance;
				talentSpecies = point;
			}
			point = point.next;
		}

		// 将最大适应度物种复制talentNum个
		SpeciesPopulation newSpeciesPopulation = new SpeciesPopulation(cityNum,coors);
		int talentNum = (int) (list.speciesNum / 4);
		for (int i = 1; i <= talentNum; i++) {
			// 复制物种至新表
			SpeciesIndividual newSpecies = talentSpecies.clone();
			newSpeciesPopulation.add(newSpecies);
		}

		// 轮盘赌list.speciesNum-talentNum次
		int roundNum = list.speciesNum - talentNum;
		for (int i = 1; i <= roundNum; i++) {
			// 产生0-1的概率
			float rate = (float) Math.random();

			SpeciesIndividual oldPoint = list.head.next;// 游标
			while (oldPoint != null && oldPoint != talentSpecies)// 寻找表尾结点
			{
				if (rate <= oldPoint.rate) {
					SpeciesIndividual newSpecies = oldPoint.clone();
					newSpeciesPopulation.add(newSpecies);

					break;
				} else {
					rate = rate - oldPoint.rate;
				}
				oldPoint = oldPoint.next;
			}
			if (oldPoint == null || oldPoint == talentSpecies) {
				// 复制最后一个
				point = list.head;// 游标
				while (point.next != null)// 寻找表尾结点
					point = point.next;
				SpeciesIndividual newSpecies = point.clone();
				newSpeciesPopulation.add(newSpecies);
			}

		}
		list.head = newSpeciesPopulation.head;
	}

	/**
	 * 交叉操作
	 * TODO
	 * @param 
	 * @return void
	 */
	void crossover(SpeciesPopulation list) {
		// 以概率pcl~pch进行
		float rate = (float) Math.random();
		if (rate > TSPData.pcl && rate < TSPData.pch) {
			SpeciesIndividual point = list.head.next;// 游标
			Random rand = new Random();
			int find = rand.nextInt(list.speciesNum);
			while (point != null && find != 0)// 寻找表尾结点
			{
				point = point.next;
				find--;
			}

			if (point.next != null) {
				int begin = rand.nextInt(cityNum);

				// 取point和point.next进行交叉，形成新的两个染色体
				for (int i = begin; i < cityNum; i++) {
					// 找出point.genes中与point.next.genes[i]相等的位置fir
					// 找出point.next.genes中与point.genes[i]相等的位置sec
					int fir, sec;
					for (fir = 0; !point.genes[fir].equals(point.next.genes[i]); fir++)
						;
					for (sec = 0; !point.next.genes[sec].equals(point.genes[i]); sec++)
						;
					// 两个基因互换
					String tmp;
					tmp = point.genes[i];
					point.genes[i] = point.next.genes[i];
					point.next.genes[i] = tmp;

					// 消去互换后重复的那个基因
					point.genes[fir] = point.next.genes[i];
					point.next.genes[sec] = point.genes[i];

				}
			}
		}
	}

	/**
	 * 变异操作
	 * TODO
	 * @param 
	 * @return void
	 */
	void mutate(SpeciesPopulation list) {
		// 每一物种均有变异的机会,以概率pm进行
		SpeciesIndividual point = list.head.next;
		while (point != null) {
			float rate = (float) Math.random();
			if (rate < TSPData.pm) {
				// 寻找逆转左右端点
				Random rand = new Random();
				int left = rand.nextInt(cityNum);
				int right = rand.nextInt(cityNum);
				if (left > right) {
					int tmp;
					tmp = left;
					left = right;
					right = tmp;
				}

				// 逆转left-right下标元素
				while (left < right) {
					String tmp;
					tmp = point.genes[left];
					point.genes[left] = point.genes[right];
					point.genes[right] = tmp;

					left++;
					right--;
				}
			}
			point = point.next;
		}
	}

	/**
	 * 获得适应度最大的物种
	 * TODO
	 * @param 
	 * @return SpeciesIndividual
	 */
	SpeciesIndividual getBest(SpeciesPopulation list) {
		float distance = Float.MAX_VALUE;
		SpeciesIndividual bestSpecies = null;
		SpeciesIndividual point = list.head.next;// 游标
		while (point != null)// 寻找表尾结点
		{	
			// 限制起点终点
			if (distance > point.distance && Integer.parseInt(point.genes[0]) == 1) {
				bestSpecies = point;
				distance = point.distance;
			}
			// 不限制起点终点
//			if (distance > point.distance) {
//				bestSpecies = point;
//				distance = point.distance;
//			}

			point = point.next;
		}

		return bestSpecies;
	}
}
