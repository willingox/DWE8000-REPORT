package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.dao.IWindGenwhDetailedDao;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IWindGenwhDetailedService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class WindGenwhDetailedServiceImpl implements IWindGenwhDetailedService {

	IWindGenwhDetailedDao windGenwhDetailedDao;

	public IWindGenwhDetailedDao getWindGenwhDetailedDao() {
		return windGenwhDetailedDao;
	}
	
	public void setWindGenwhDetailedDao(IWindGenwhDetailedDao windGenwhDetailedDao) {
		this.windGenwhDetailedDao = windGenwhDetailedDao;
	}

	
	public List<DataStatisticsDataVo> listMax(String startDateStr,String endDateStr,int flag) {
		return windGenwhDetailedDao.listMax(startDateStr,endDateStr,flag);
	}
	
	public List<DataStatisticsDataVo> listTable(String maxtime) {
		return windGenwhDetailedDao.listTable(maxtime);
	}
	public List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,int flag,int id) {
		return windGenwhDetailedDao.listWind(startDateStr,endDateStr,flag,id);
	}
	
}
