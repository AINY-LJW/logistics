package com.logistics.feign.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.logistics.feign.provider.domain.CoordinateDomain;

/**
 * 
 * 简述部分:坐标
 * 
 * @author helantian
 * @version 2020年4月21日
 */
@Mapper
public interface CoordinateDomainMapper {
	@Select("SELECT * FROM coordinate")
	List<CoordinateDomain> selectAll();
}
