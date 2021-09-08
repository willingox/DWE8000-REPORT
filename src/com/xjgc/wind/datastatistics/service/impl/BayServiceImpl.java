package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IBayDao;
import com.xjgc.wind.datastatistics.service.IBayService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class BayServiceImpl implements IBayService {

	IBayDao bayDao;

	public IBayDao getBayDao() {
		return bayDao;
	}
	
	public void setBayDao(IBayDao bayDao) {
		this.bayDao = bayDao;
	}

	
	public List<DataStatisticsDataVo> list() {
		return bayDao.list();
	}
	public List<DataStatisticsDataVo> listSmg() {
		return bayDao.listSmg();
	}
	
	
	
}
