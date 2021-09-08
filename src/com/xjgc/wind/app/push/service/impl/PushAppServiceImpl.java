package com.xjgc.wind.app.push.service.impl;

import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.push.dao.PushAppDao;
import com.xjgc.wind.app.push.service.PushAppService;

public class PushAppServiceImpl implements PushAppService{

	PushAppDao pushAppDao;

	public PushAppDao getPushAppDao() {
		return pushAppDao;
	}

	public void setPushAppDao(PushAppDao pushAppDao) {
		this.pushAppDao = pushAppDao;
	}
	
	
	//获取所有故障
	public List<Map>  getAllBreakdown(){
		
		return pushAppDao.getAllBreakdown();
	}
	
	//获取特定时间段的内的故障
	public List<Map>  getBreakdownByTime(String startTime ,String endTime){
		return pushAppDao.getBreakdownByTime(startTime ,endTime);
	}
	
	//获取特定时间段内上次状态是故障
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime){
		return pushAppDao.getLastStatusBreakdownByTime(startTime ,endTime);
	}
	
}




