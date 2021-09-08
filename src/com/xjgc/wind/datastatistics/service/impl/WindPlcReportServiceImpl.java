package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.dao.IWindPlcReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.IWindPlcReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class WindPlcReportServiceImpl implements IWindPlcReportService {

	IWindPlcReportDao windPlcReportDao;

	public IWindPlcReportDao getWindPlcReportDao() {
		return windPlcReportDao;
	}
	
	public void setWindPlcReportDao(IWindPlcReportDao windPlcReportDao) {
		this.windPlcReportDao = windPlcReportDao;
	}

	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,int flag) {
		return windPlcReportDao.list(startDateDisp,endDateDisp,str,flag);
	}
	

	
}
