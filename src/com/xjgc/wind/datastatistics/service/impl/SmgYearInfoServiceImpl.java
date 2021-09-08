package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.ISmgYearInfoDao;
import com.xjgc.wind.datastatistics.service.ISmgYearInfoService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgYearInfoDataForm;

public class SmgYearInfoServiceImpl implements ISmgYearInfoService{

	ISmgYearInfoDao smgYearInfoDao;

	public ISmgYearInfoDao getSmgYearInfoDao() {
		return smgYearInfoDao;
	}

	public void setSmgYearInfoDao(ISmgYearInfoDao smgYearInfoDao) {
		this.smgYearInfoDao = smgYearInfoDao;
	}
	
	public List<DataStatisticsDataVo> listtime(SmgYearInfoDataForm queryFilter)
	{
		return smgYearInfoDao.listtime(queryFilter);
	}
	public List<DataStatisticsDataVo> listname(SmgYearInfoDataForm queryFilter)
	{
		return smgYearInfoDao.listname(queryFilter);
	}
}
