package com.xjgc.wind.app.push.service;

import java.util.List;
import java.util.Map;

public interface PushAppService {

	//��ȡ���й���
	public List<Map>  getAllBreakdown( );
	
	//��ȡ�ض�ʱ��ε��ڵĹ���
	public List<Map>  getBreakdownByTime(String startTime ,String endTime);
	
	//��ȡ�ض�ʱ������ϴ�״̬�ǹ���
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime);
}
