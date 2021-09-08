package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneMinuteDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;

public class RuningInfo_OneMinuteServiceImpl implements IRuningInfo_OneMinuteService {
	IRuningInfo_OneMinuteDao runingInfo_OneMinuteDao;

	public IRuningInfo_OneMinuteDao getRuningInfo_OneMinuteDao() {
		return runingInfo_OneMinuteDao;
	}
	
	public void setRuningInfo_OneMinuteDao(IRuningInfo_OneMinuteDao runingInfo_OneMinuteDao) {
		this.runingInfo_OneMinuteDao = runingInfo_OneMinuteDao;
	}
	
	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,ArrayList<String> check_id,ArrayList<ArrayList<String>> check_value,int flag,int yearIf) {
		return runingInfo_OneMinuteDao.list(startDateDisp,endDateDisp,check_id,check_value,flag,yearIf);
	}
	


}
