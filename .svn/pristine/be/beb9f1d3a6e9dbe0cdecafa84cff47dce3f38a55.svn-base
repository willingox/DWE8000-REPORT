package com.xjgc.wind.datastatistics.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.datastatistics.dao.IWindGerRtMonitorDao;
import com.xjgc.wind.datastatistics.service.IWindGerRtMonitorService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class WindGerRtMonitorServiceImpl implements IWindGerRtMonitorService{
	
	IWindGerRtMonitorDao windGerRtMonitorDao;

	public IWindGerRtMonitorDao getWindGerRtMonitorDao() {
		return windGerRtMonitorDao;
	}

	public void setWindGerRtMonitorDao(IWindGerRtMonitorDao windGerRtMonitorDao) {
		this.windGerRtMonitorDao = windGerRtMonitorDao;
	}
	
	public Map getStates(){
		Map result=new HashMap();
		Map map=null;
		List<Map> list=null;
		list=windGerRtMonitorDao.getGeneratorCurState();
		if(0!=list.size()){
			map=list.get(0);
			result.putAll(map);
		}
		list=windGerRtMonitorDao.getGeneratorCurState1();
		if(0!=list.size()){
			map=list.get(0);
			result.putAll(map);
		}
		list=windGerRtMonitorDao.getGeneratorCurState2();
		if(0!=list.size()){
			map=list.get(0);
			result.putAll(map);
		}
		return result;
	}
	
	
	public List<DataStatisticsDataVo> generatorCurStateList(){
		
		List<DataStatisticsDataVo> list=null;
		list =windGerRtMonitorDao.generatorCurStateList();
		return list;
	}

	
	public List<DataStatisticsDataVo> generatorCurStateList(int argetType,
			int sortType,int stateType) {
		List<DataStatisticsDataVo> list=null;
		
		
		list =windGerRtMonitorDao.generatorCurStateList(argetType,sortType, stateType);
		return list;
	}
	
	
	
	
}
