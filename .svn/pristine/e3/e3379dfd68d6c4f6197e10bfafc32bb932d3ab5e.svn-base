package com.xjgc.wind.app.push.dao;

import java.util.List;
import java.util.Map;

public interface PushAppDao {

	
	public List<Map> getAllBreakdown();
	
	//获取特定时间段的内的故障
	public List<Map> getBreakdownByTime(String startTime ,String endTime);
	//获取特定时间段内上次状态是故障
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime);
	
}
