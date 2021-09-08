package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindPowScatterDao;
import com.xjgc.wind.datastatistics.service.IWindPowScatterService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindPowScatterDataForm;

public class WindPowScatterServiceImpl implements IWindPowScatterService{

	IWindPowScatterDao windPowScatterDao;

	public IWindPowScatterDao getWindPowScatterDao() {
		return windPowScatterDao;
	}

	public void setWindPowScatterDao(IWindPowScatterDao windPowScatterDao) {
		this.windPowScatterDao = windPowScatterDao;
	}
	
	public List<DataStatisticsDataVo> list(WindPowScatterDataForm queryFilter,Integer equipId){
		return windPowScatterDao.list(queryFilter,equipId);
	}
}
