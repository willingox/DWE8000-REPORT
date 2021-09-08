package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;



import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsMonReportDao;
import com.xjgc.wind.datastatistics.service.IGeneratorStatisticsMonReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;


public class GeneratorStatisticsMonReportServiceImpl implements IGeneratorStatisticsMonReportService {

	IGeneratorStatisticsMonReportDao generatorStatisticsMonReportDao;
	
	
	public IGeneratorStatisticsMonReportDao getGeneratorStatisticsMonReportDao() {
		return generatorStatisticsMonReportDao;
	}

	public void setGeneratorStatisticsMonReportDao(
			IGeneratorStatisticsMonReportDao generatorStatisticsMonReportDao) {
		this.generatorStatisticsMonReportDao = generatorStatisticsMonReportDao;
	}

	
	
	
	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {

		return generatorStatisticsMonReportDao.list(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID){
		return generatorStatisticsMonReportDao.listWind(date,smgID);
	}
	public List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID){
		return generatorStatisticsMonReportDao.listcount(date,smgID);
	}
	
public List<DataStatisticsDataVo> listTime(String startDateStr){
		
		List<DataStatisticsDataVo> resultList=generatorStatisticsMonReportDao.listAva(startDateStr);
		List<DataStatisticsDataVo> notEndList=generatorStatisticsMonReportDao.listHappenNoEnd(startDateStr);
	
		for(int i=0;i<resultList.size();i++){
			DataStatisticsDataVo obj=resultList.get(i);
			for(int j=0;j<notEndList.size();j++){
				DataStatisticsDataVo obj1=notEndList.get(j);
				if(obj.getHappenTime().equals(obj1.getHappenTime()) && obj.getId()==obj1.getId()){
					notEndList.remove(j); break;
				}
			}
		}
		resultList.addAll(notEndList);
		return resultList;
	}
}
