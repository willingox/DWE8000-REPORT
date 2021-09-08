package com.xjgc.wind.datastatistics.service;

import java.util.List;
import java.util.Map;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;




public interface IWindGerRtMonitorService {
	
	public Map getStates();
	public List<DataStatisticsDataVo> generatorCurStateList();
	public List<DataStatisticsDataVo> generatorCurStateList(int argetType, int sortType,int stateType);
	
}
