package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IMeasclassDao;
import com.xjgc.wind.datastatistics.service.IMeasclassService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class MeasclassServiceImpl implements IMeasclassService {

	IMeasclassDao measclassDao;

	public IMeasclassDao getMeasclassDao() {
		return measclassDao;
	}
	
	public void setMeasclassDao(IMeasclassDao measclassDao) {
		this.measclassDao = measclassDao;
	}

	
	public List<DataStatisticsDataVo> list() {
		return measclassDao.list();
	}
	
	
	
}
