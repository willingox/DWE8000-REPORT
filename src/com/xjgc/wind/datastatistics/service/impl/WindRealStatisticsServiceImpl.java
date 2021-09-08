package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.xjgc.wind.datastatistics.dao.IWindMeaInfoMonitorDao;
import com.xjgc.wind.datastatistics.dao.IWindRealStatisticsDao;
import com.xjgc.wind.datastatistics.service.IWindRealStatisticsService;
import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;
import com.xjgc.wind.datastatistics.vo.WgstinfoDataVo;

public class WindRealStatisticsServiceImpl implements IWindRealStatisticsService {
	IWindRealStatisticsDao windRealStatisticsDao;

	public IWindRealStatisticsDao getWindRealStatisticsDao() {
		return windRealStatisticsDao;
	}
	
	public void setWindRealStatisticsDao(IWindRealStatisticsDao windRealStatisticsDao) {
		this.windRealStatisticsDao = windRealStatisticsDao;
	}
	
	
	public List<WgstinfoDataVo> list() {
		return windRealStatisticsDao.list();
	}

	

}
