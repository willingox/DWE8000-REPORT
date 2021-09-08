package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;


import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsReportService;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;


public class GeneratorStatisticsReportServiceImpl implements IGeneratorStatisticsReportService {

	IGeneratorStatisticsReportDao generatorStatisticsReportDao;
	
	
	
	public IGeneratorStatisticsReportDao getGeneratorStatisticsReportDao() {
		return generatorStatisticsReportDao;
	}



	public void setGeneratorStatisticsReportDao(
			IGeneratorStatisticsReportDao generatorStatisticsReportDao) {
		this.generatorStatisticsReportDao = generatorStatisticsReportDao;
	}
	
	public List<GeneratorStatisticsReportVo> list(){
		return generatorStatisticsReportDao.list();
	}
	public List<GeneratorStatisticsReportVo> list(String startDateDisp,String endDateDisp,Integer smgID,int flag){
		return generatorStatisticsReportDao.list(startDateDisp,endDateDisp,smgID,flag);
	}
	public List<GeneratorStatisticsReportVo> listWind(String startDateDisp,String endDateDisp,Integer smgID,int flag){
		return generatorStatisticsReportDao.listWind(startDateDisp,endDateDisp,smgID,flag);
	}
	public List<GeneratorStatisticsReportVo> listcount(String startDateDisp,String endDateDisp,Integer smgID,int flag){
		return generatorStatisticsReportDao.listcount(startDateDisp,endDateDisp,smgID,flag);
	}



	

}
