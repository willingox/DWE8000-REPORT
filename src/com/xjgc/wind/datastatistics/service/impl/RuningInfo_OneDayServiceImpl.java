package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;




import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneDayDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class RuningInfo_OneDayServiceImpl implements IRuningInfo_OneDayService {
	IRuningInfo_OneDayDao runingInfo_OneDayDao;




	public IRuningInfo_OneDayDao getRuningInfo_OneDayDao() {
		return runingInfo_OneDayDao;
	}



	public void setRuningInfo_OneDayDao(IRuningInfo_OneDayDao runingInfo_OneDayDao) {
		this.runingInfo_OneDayDao = runingInfo_OneDayDao;
	}



	public void Day(IRuningInfo_OneDayDao runingInfo_OneDayDao) {
		this.runingInfo_OneDayDao = runingInfo_OneDayDao;
	}



	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,ArrayList<String> check_id,ArrayList<ArrayList<String>> check_value,int flag,int yearIf) {
		return runingInfo_OneDayDao.list(startDateDisp,endDateDisp,check_id,check_value,flag,yearIf);
	}
	


}
