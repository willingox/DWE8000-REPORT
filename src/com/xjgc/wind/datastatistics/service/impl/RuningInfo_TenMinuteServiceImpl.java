package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.xjgc.wind.datastatistics.dao.IRuningInfo_TenMinuteDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_TenMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_TenMinuteDataForm;

public class RuningInfo_TenMinuteServiceImpl implements IRuningInfo_TenMinuteService {
	IRuningInfo_TenMinuteDao runingInfo_TenMinuteDao;

	public IRuningInfo_TenMinuteDao getRuningInfo_TenMinuteDao() {
		return runingInfo_TenMinuteDao;
	}
	
	public void setRuningInfo_TenMinuteDao(IRuningInfo_TenMinuteDao runingInfo_TenMinuteDao) {
		this.runingInfo_TenMinuteDao = runingInfo_TenMinuteDao;
	}
	
	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,ArrayList<String> check_id,ArrayList<ArrayList<String>> check_value,int flag,int yearIf) {
		return runingInfo_TenMinuteDao.list(startDateDisp,endDateDisp,check_id,check_value,flag,yearIf);
	}
	


}
