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
	
	
	//��ȡ���й���
	public List<Map>  getAllBreakdown(){
		
		return pushAppDao.getAllBreakdown();
	}
	
	//��ȡ�ض�ʱ��ε��ڵĹ���
	public List<Map>  getBreakdownByTime(String startTime ,String endTime){
		return pushAppDao.getBreakdownByTime(startTime ,endTime);
	}
	
	//��ȡ�ض�ʱ������ϴ�״̬�ǹ���
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime){
		return pushAppDao.getLastStatusBreakdownByTime(startTime ,endTime);
	}
	
}




