package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public interface IGeneratorStatisticsYearReportDao {

	
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> list(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> listHour(String date,Integer smgID);
}
