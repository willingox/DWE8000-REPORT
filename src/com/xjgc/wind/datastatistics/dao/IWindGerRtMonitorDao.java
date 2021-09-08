package com.xjgc.wind.datastatistics.dao;

import java.util.List;
import java.util.Map;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;

public interface IWindGerRtMonitorDao {

	// 统计当前电站的设备运行状态
	public List<Map> getGeneratorCurState();
	// 统计当前电站的功率，最大发电功率和天气状态
	public List<Map> getGeneratorCurState1();
	// 统计当前电站的今日发电量，今日上网电量，今日最大发电功率和发生时间
	public List<Map> getGeneratorCurState2();
	
	public List<DataStatisticsDataVo> generatorCurStateList();
	
	public List<DataStatisticsDataVo> generatorCurStateList(int argetType, int sortType,int stateType);

	
}
