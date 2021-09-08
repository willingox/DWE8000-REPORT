package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.dao.ILossEleReportDao;
import com.xjgc.wind.datastatistics.dao.IWindPlcReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.service.ILossEleReportService;
import com.xjgc.wind.datastatistics.service.IWindPlcReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class LossEleReportServiceImpl implements ILossEleReportService {

	ILossEleReportDao lossEleReportDao;

	public ILossEleReportDao getLossEleReportDao() {
		return lossEleReportDao;
	}
	
	public void setLossEleReportDao(ILossEleReportDao lossEleReportDao) {
		this.lossEleReportDao = lossEleReportDao;
	}

	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,int flag) {
		return lossEleReportDao.list(startDateDisp,endDateDisp,str,flag);
	}
	

	
}
