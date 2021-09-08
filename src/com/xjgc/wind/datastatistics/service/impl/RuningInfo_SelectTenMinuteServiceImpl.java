package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;


import com.xjgc.wind.datastatistics.dao.IRuningInfo_SelectTenMinuteDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_SelectTenMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_SelectTenMinuteDataForm;

public class RuningInfo_SelectTenMinuteServiceImpl implements IRuningInfo_SelectTenMinuteService {
	IRuningInfo_SelectTenMinuteDao runingInfo_SelectTenMinuteDao;

	public IRuningInfo_SelectTenMinuteDao getRuningInfo_SelectTenMinuteDao() {
		return runingInfo_SelectTenMinuteDao;
	}
	
	public void setRuningInfo_SelectTenMinuteDao(IRuningInfo_SelectTenMinuteDao runingInfo_SelectTenMinuteDao) {
		this.runingInfo_SelectTenMinuteDao = runingInfo_SelectTenMinuteDao;
	}
	
	public List<DataStatisticsDataVo> BayList(Integer flag){
		return runingInfo_SelectTenMinuteDao.BayList(flag);
	}
	
	public List<DataStatisticsDataVo> list(Integer flag){
		return runingInfo_SelectTenMinuteDao.list(flag);
	}


}
