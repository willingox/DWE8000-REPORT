package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.ISmgMonthInfoDao;
import com.xjgc.wind.datastatistics.service.ISmgMonthInfoService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgMonthInfoDataForm;

public class SmgMonthInfoServiceImpl implements ISmgMonthInfoService{

	ISmgMonthInfoDao smgMonthInfoDao;
	
	public ISmgMonthInfoDao getSmgMonthInfoDao() {
		return smgMonthInfoDao;
	}
	public void setSmgMonthInfoDao(ISmgMonthInfoDao smgMonthInfoDao) {
		this.smgMonthInfoDao = smgMonthInfoDao;
	}
	public List<DataStatisticsDataVo> listtimegen(SmgMonthInfoDataForm queryFilter)
	{
		return smgMonthInfoDao.listtimegen(queryFilter);
	}
	public List<DataStatisticsDataVo> listnamegen(SmgMonthInfoDataForm queryFilter)
	{
		return smgMonthInfoDao.listnamegen(queryFilter);
	}
}
