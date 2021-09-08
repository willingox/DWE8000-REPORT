package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IGeneratStatisticsDao;
import com.xjgc.wind.datastatistics.service.IGeneratStatisticsService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratStatisticsDataForm;


public class GeneratStatisticsServiceImpl implements IGeneratStatisticsService {

	IGeneratStatisticsDao generatStatisticsDao;

	public IGeneratStatisticsDao getGeneratStatisticsDao() {
		return generatStatisticsDao;
	}
	
	public void setGeneratStatisticsDao(IGeneratStatisticsDao generatStatisticsDao) {
		this.generatStatisticsDao = generatStatisticsDao;
	}

	
	public List<DataStatisticsDataVo> listpw(GeneratStatisticsDataForm queryFilter) {
		return generatStatisticsDao.listpw(queryFilter);
	}
	public List<DataStatisticsDataVo> listgenwh(GeneratStatisticsDataForm queryFilter) {
		return generatStatisticsDao.listgenwh(queryFilter);
	}
	public List<DataStatisticsDataVo> listsunlight(GeneratStatisticsDataForm queryFilter) {
		return generatStatisticsDao.listsunlight(queryFilter);
	}
}