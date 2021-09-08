package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;


import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsDayReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsDayReportService;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public class GeneratorStatisticsDayReportServiceImpl implements IGeneratorStatisticsDayReportService {

	IGeneratorStatisticsDayReportDao generatorStatisticsDayReportDao;
	
	
	
	public IGeneratorStatisticsDayReportDao getGeneratorStatisticsDayReportDao() {
		return generatorStatisticsDayReportDao;
	}



	public void setGeneratorStatisticsDayReportDao(
			IGeneratorStatisticsDayReportDao generatorStatisticsDayReportDao) {
		this.generatorStatisticsDayReportDao = generatorStatisticsDayReportDao;
	}



	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {

		return generatorStatisticsDayReportDao.list(date,smgID);
	}

	public List<GeneratorStatisticsReportVo> listyesterday(String date,Integer smgID){
		return generatorStatisticsDayReportDao.listyesterday(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID){
		return generatorStatisticsDayReportDao.listWind(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID){
		return generatorStatisticsDayReportDao.listcount(date,smgID);
	}
	
}
