package com.logistics.feign.provider.service;

import com.logistics.util.EasyUIDataGridResult;

/**
 * 
 * 简述部分:农产品service
 * 
 *
 * @author lijiawen
 * @version 2020年4月22日
 */
public interface ProductService {
	/**
	 * 获取所有
	 * TODO
	 * @param 
	 * @return EasyUIDataGridResult
	 */
	public EasyUIDataGridResult getAllProduct(int pageNum, int pageSize,String legalperson,String industry,String companyName,String other);
}
