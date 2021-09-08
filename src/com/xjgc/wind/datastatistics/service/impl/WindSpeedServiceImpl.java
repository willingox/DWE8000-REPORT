package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindSpeedDao;
import com.xjgc.wind.datastatistics.service.IWindSpeedService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;

public class WindSpeedServiceImpl implements IWindSpeedService{

	IWindSpeedDao windSpeedDao;

	public IWindSpeedDao getWindSpeedDao() {
		return windSpeedDao;
	}

	public void setWindSpeedDao(IWindSpeedDao windSpeedDao) {
		this.windSpeedDao = windSpeedDao;
	}
	
	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str,int flag){
		return windSpeedDao.list(startDateStr,endDateStr,str,flag);
	}
	
	public List<DataStatisticsDataVo> listavg(String startDateStr,String endDateStr,int flag){
		return windSpeedDao.listavg(startDateStr,endDateStr,flag);
	}
	public List<DataStatisticsDataVo> listcount(String startDateStr,String endDateStr,int flag){
		return windSpeedDao.listcount(startDateStr,endDateStr,flag);
	}
	public List<DataStatisticsDataVo> listMax(String startDateStr,String endDateStr,int flag){
		return windSpeedDao.listMax(startDateStr,endDateStr, flag);
	}
	public List<DataStatisticsDataVo> listMin(String startDateStr,String endDateStr,int flag){
		return windSpeedDao.listMin(startDateStr,endDateStr,flag);
	}
	public List<DataStatisticsDataVo> listMaxTime(String startDateStr,String endDateStr,int id,int flag){
		return windSpeedDao.listMaxTime(startDateStr,endDateStr,id,flag);
	}
	public List<DataStatisticsDataVo> listMinTime(String startDateStr,String endDateStr,int id,int flag){
		return windSpeedDao.listMinTime(startDateStr,endDateStr,id,flag);
	}
	
}
