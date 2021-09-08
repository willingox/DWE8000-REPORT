package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindJiReliabilityContrastDao;
import com.xjgc.wind.datastatistics.service.IWindJiReliabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm;

public class WindJiReliabilityContrastServiceImpl implements IWindJiReliabilityContrastService {

	IWindJiReliabilityContrastDao windJiReliabilityContrastDao;

	public IWindJiReliabilityContrastDao getWindJiReliabilityContrastDao() {
		return windJiReliabilityContrastDao;
	}
	
	public void setWindJiReliabilityContrastDao(IWindJiReliabilityContrastDao windJiReliabilityContrastDao) {
		this.windJiReliabilityContrastDao = windJiReliabilityContrastDao;
	}

	public List<DataStatisticsDataVo> list(String str) {
		return windJiReliabilityContrastDao.list(str);
	    }
	public List<DataStatisticsDataVo> listrandhour(String startDateStr,String endDateStr,String str,int flag) {
	return windJiReliabilityContrastDao.listrandhour(startDateStr,endDateStr,str,flag);
    }
	public List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,String str,int flag){
		return windJiReliabilityContrastDao.listWind(startDateStr,endDateStr,str,flag);
	}
}
