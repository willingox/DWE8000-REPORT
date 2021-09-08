package com.xjgc.wind.datastatistics.service;

import java.util.List;



import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;



public interface IGeneratorStatisticsReportService {
	List<GeneratorStatisticsReportVo> list();
	List<GeneratorStatisticsReportVo> listcount(String startDateDisp,String endDateDisp,Integer smgID,int flag);
	List<GeneratorStatisticsReportVo> list(String startDateDisp,String endDateDisp,Integer smgID,int flag);
	List<GeneratorStatisticsReportVo> listWind(String startDateDisp,String endDateDisp,Integer smgID,int flag);
}
