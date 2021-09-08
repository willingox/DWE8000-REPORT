package com.xjgc.wind.datastatistics.service;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;

public interface IWindPlcReportService {

	
	List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,int flag);
}
