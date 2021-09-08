package com.xjgc.wind.datastatistics.dao;


import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public interface IWindPlcReportDao {


	List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,int flag);
	
}
