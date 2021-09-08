package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public interface IGeneratorStatisticsMonReportDao {

	
	List<DataStatisticsDataVo> listAva(String startDateStr);
	List<DataStatisticsDataVo> listHappenNoEnd(String startDateStr);
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> list(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);
}
