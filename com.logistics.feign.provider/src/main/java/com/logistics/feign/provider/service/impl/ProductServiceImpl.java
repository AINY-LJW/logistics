package com.logistics.feign.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.logistics.feign.provider.dao.ProductMapper;
import com.logistics.feign.provider.domain.Product;
import com.logistics.feign.provider.domain.ProductExample;
import com.logistics.feign.provider.service.ProductService;
import com.logistics.util.EasyUIDataGridResult;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper productMapper;
	@Override
	public EasyUIDataGridResult getAllProduct(int pageNum, int pageSize, String legalperson, String industry,
			String companyName, String uid) {
		PageMethod.startPage(pageNum, pageSize);
		ProductExample example = new ProductExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.
		List<Product> allComments = productMapper.selectByExample(example);
		EasyUIDataGridResult dataGridResult = new EasyUIDataGridResult();
		PageInfo<Product> pageInfo = new PageInfo<>(allComments);
		dataGridResult.setRows(allComments);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

}
