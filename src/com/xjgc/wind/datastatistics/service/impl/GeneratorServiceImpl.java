package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.service.IGeneratorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class GeneratorServiceImpl implements IGeneratorService {

	IGeneratorDao generatorDao;

	public IGeneratorDao getGeneratorDao() {
		return generatorDao;
	}
	
	public void setGeneratorDao(IGeneratorDao generatorDao) {
		this.generatorDao = generatorDao;
	}

	
	public List<DataStatisticsDataVo> list() {
		return generatorDao.list();
	}
	
	public List<DataStatisticsDataVo> listu(String name,char id) {
		return generatorDao.listu(name,id);
	}
	
}
