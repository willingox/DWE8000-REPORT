package com.xjgc.wind.app.push.service;

import java.util.List;
import java.util.Map;

public interface PushAppService {

	//获取所有故障
	public List<Map>  getAllBreakdown( );
	
	//获取特定时间段的内的故障
	public List<Map>  getBreakdownByTime(String startTime ,String endTime);
	
	//获取特定时间段内上次状态是故障
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime);
}
