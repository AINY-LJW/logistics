package com.logistics.feign.provider.service;

import java.util.List;
import java.util.Map;

import com.logistics.feign.provider.domain.OrderVO;
import com.logistics.util.EasyUIDataGridResult;

/**
 * 
 * 简述部分: 订单Service
 * 
 * Copyright: 版权所有 (c) JOIN-CHEER
 * Company: 久其
 *
 * @author lijiawen
 * @version 2020年4月23日
 */
public interface OrderService {
	List<OrderVO> selectAll();
	
	/**
	 * huoqu所有订单
	 * TODO
	 * @param 
	 * @return EasyUIDataGridResult
	 */
	public EasyUIDataGridResult getAllOrder(int pageNum, int pageSize, String legalperson, String industry,
			String companyName, String uid);
}
