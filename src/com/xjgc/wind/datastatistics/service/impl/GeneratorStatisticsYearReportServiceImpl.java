package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;


import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsYearReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsYearReportService;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public class GeneratorStatisticsYearReportServiceImpl implements IGeneratorStatisticsYearReportService {

	IGeneratorStatisticsYearReportDao generatorStatisticsYearReportDao;
	

	public IGeneratorStatisticsYearReportDao getGeneratorStatisticsYearReportDao() {
		return generatorStatisticsYearReportDao;
	}


	public void setGeneratorStatisticsYearReportDao(
			IGeneratorStatisticsYearReportDao generatorStatisticsYearReportDao) {
		this.generatorStatisticsYearReportDao = generatorStatisticsYearReportDao;
	}
	public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID){
		return generatorStatisticsYearReportDao.listWind(date,smgID);
	}

	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {

		return generatorStatisticsYearReportDao.list(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listHour(String date,Integer smgID) {

		return generatorStatisticsYearReportDao.listHour(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID){
		return generatorStatisticsYearReportDao.listcount(date,smgID);
	}
	
	
}
