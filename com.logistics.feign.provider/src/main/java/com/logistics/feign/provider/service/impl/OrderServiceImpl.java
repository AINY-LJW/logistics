package com.logistics.feign.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.logistics.feign.provider.dao.OrderMapper;
import com.logistics.feign.provider.domain.OrderVO;
import com.logistics.feign.provider.service.OrderService;
import com.logistics.util.EasyUIDataGridResult;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public List<OrderVO> selectAll() {
		return orderMapper.getAll();
	}
	@Override
	public EasyUIDataGridResult getAllOrder(int pageNum, int pageSize, String legalperson, String industry,
			String companyName, String uid) {
		PageMethod.startPage(pageNum, pageSize);;
		List<OrderVO> list = orderMapper.getAll();
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<OrderVO> pageInfo = new PageInfo<>(list);
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

}
