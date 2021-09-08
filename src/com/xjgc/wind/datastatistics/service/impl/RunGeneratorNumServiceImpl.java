package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IRunGeneratorNumDao;
import com.xjgc.wind.datastatistics.service.IRunGeneratorNumService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class RunGeneratorNumServiceImpl implements IRunGeneratorNumService {

	IRunGeneratorNumDao runGeneratorNumDao;

	public IRunGeneratorNumDao getRunGeneratorNumDao() {
		return runGeneratorNumDao;
	}
	
	public void setRunGeneratorNumDao(IRunGeneratorNumDao runGeneratorNumDao) {
		this.runGeneratorNumDao = runGeneratorNumDao;
	}

	
	public List<DataStatisticsDataVo> list() {
		return runGeneratorNumDao.list();
	}
	
	
	
}