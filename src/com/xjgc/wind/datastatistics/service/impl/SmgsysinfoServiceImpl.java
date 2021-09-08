package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.ISmgsysinfoDao;
import com.xjgc.wind.datastatistics.service.ISmgsysinfoService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class SmgsysinfoServiceImpl implements ISmgsysinfoService {

	ISmgsysinfoDao smgsysinfoDao;

	public ISmgsysinfoDao getSmgsysinfoDao() {
		return smgsysinfoDao;
	}
	
	public void setSmgsysinfoDao(ISmgsysinfoDao smgsysinfoDao) {
		this.smgsysinfoDao = smgsysinfoDao;
	}

	
	public List<DataStatisticsDataVo> list() {
		return smgsysinfoDao.list();
	}
	
	
	
}
