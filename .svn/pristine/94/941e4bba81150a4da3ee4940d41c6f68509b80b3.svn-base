package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;


import com.xjgc.wind.datastatistics.dao.IRuningInfo_SelectOneMinuteDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_SelectOneMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_SelectOneMinuteDataForm;

public class RuningInfo_SelectOneMinuteServiceImpl implements IRuningInfo_SelectOneMinuteService {
	IRuningInfo_SelectOneMinuteDao runingInfo_SelectOneMinuteDao;

	public IRuningInfo_SelectOneMinuteDao getRuningInfo_SelectOneMinuteDao() {
		return runingInfo_SelectOneMinuteDao;
	}
	
	public void setRuningInfo_SelectOneMinuteDao(IRuningInfo_SelectOneMinuteDao runingInfo_SelectOneMinuteDao) {
		this.runingInfo_SelectOneMinuteDao = runingInfo_SelectOneMinuteDao;
	}
	
	public List<DataStatisticsDataVo> BayList(Integer flag){
		return runingInfo_SelectOneMinuteDao.BayList(flag);
	}
	
	public List<DataStatisticsDataVo> list(Integer flag){
		return runingInfo_SelectOneMinuteDao.list(flag);
	}


}
