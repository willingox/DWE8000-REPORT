package com.xjgc.wind.datastatistics.service;

import java.util.List;


import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;



public interface IGeneratorStatisticsDayReportService {

	//
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> list(String  date,Integer smgID);
	List<GeneratorStatisticsReportVo> listyesterday(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);
}
