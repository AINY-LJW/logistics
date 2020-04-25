package com.logistics.feign.provider.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 简述部分: 贪心算法求最优路径
 * 10个以下
 * 每次选取距离当前城市距离最近的城市 最后回原点
 *
 * @author helantian
 * @version 2020年4月25日
 */
public class GreedyPlanRoute {
	double s=0;//总距离值
	private int citynumbers;//城市数目
	private double[][] distance;//距离矩阵，距离为欧式空间距离
	int[] visited;//确定是否访问过
	int[] path;//存放路径，路径的值从1开始
	
	public GreedyPlanRoute() {
	}
	
	//读取数据，计算距离矩阵
	public List<Map<String, Double>> readData(List<Map<String, Double>> coors) {
		citynumbers = coors.size();
		double[] x=new double[citynumbers];//存放x坐标的数组
		double[] y=new double[citynumbers];//存放y坐标的数组
		distance=new double[citynumbers][citynumbers];//距离矩阵
		for (int i = 0; i < citynumbers; i++) {
			x[i]=coors.get(i).get("lat");//x坐标数组 纬度
			y[i]=coors.get(i).get("lng");//y坐标数组 经度
		}
		//计算距离矩阵
		for(int i=0;i<citynumbers;i++) {
			for(int j=0;j<citynumbers;j++) {
				distance[i][j]=Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]));//计算欧式距离
			}
		}
		
		solve();
		
		// 返回规划结果
		List<Map<String, Double>> result = new ArrayList<>();
		for(int i=0;i<citynumbers;i++) {
			result.add(coors.get(path[i] - 1));
		}
		// 回到远点
		result.add(coors.get(0));
		return result;
	}
	
	private void solve() {
		visited=new int[citynumbers];//确定是否访问过
		path=new int[citynumbers];
		for(int i=0;i<citynumbers;i++) {
			visited[i]=0;//0代表未被访问过，1代表访问过
		}
		path[0]=1;//从城市1开始
		visited[0]=1;//城市1访问置1
		int k=0; //k代表当前的城市,0代表城市1
		int next=k+1;//next表示下一个访问城市，每次开始先确定为k+1
		double min=Double.MAX_VALUE;//min代表最小距离，设置为这个数，必然会被更新
		int count=1;//计数
		while( count<citynumbers) {
			for(int j=0;j<citynumbers;j++) {
				if(visited[j]==0) {//未被访问
					if(distance[k][j]<min) {//找到更小的距离值，则更新距离最小值
						min=distance[k][j];
						next=j;
					}
				}
			}
			s=s+min;//累加距离
			path[count]=next+1;//存放找到下一个城市
			visited[next]=1;//置访问标记为1
			k=next;//更新当前城市
			count++;
			min=Double.MAX_VALUE;//更新最小值
			next=k+1;
		}
	}

}
