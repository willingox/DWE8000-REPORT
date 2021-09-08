package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;



public interface IGeneratorStatisticsMonReportService {
	
	 List<DataStatisticsDataVo> listTime(String startDateStr);
	List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID);
	List<GeneratorStatisticsReportVo> list(String  date,Integer smgID);
	List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID);

}
