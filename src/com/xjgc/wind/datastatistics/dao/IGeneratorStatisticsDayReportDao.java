package com.xjgc.wind.datastatistics.dao;

import java.util.List;


import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public interface IGeneratorStatisticsDayReportDao {

	
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);			//运行的风机编号
	List<GeneratorStatisticsReportVo> list(String date,Integer smgID);				//日报表主数据
	List<GeneratorStatisticsReportVo> listyesterday(String date,Integer smgID);		//日报表昨日数据
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);			//有效风时数数据
}
