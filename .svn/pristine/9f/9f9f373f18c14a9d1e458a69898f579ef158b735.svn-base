package com.xjgc.wind.app.station.dao;

import java.util.List;
import java.util.Map;

public interface StationAppDao {

	
	//所有电站的坐标默认只存一个也只去第一个
	List<Map> getStationsLocation();
	//所有风机的装机容量与坐标值
	List<Map>  getGeneratorsCapacity();
	//所有风机的发电功率与坐标值
	List<Map>  getGeneratorsCurp();
	//所有风机的当前风速与坐标值
	List<Map>  getGeneratorsWindSpeed();
	//所有风机的今日发电量与坐标值
	List<Map>  getGeneratorsTodaygenwh();
	//所有风机的累计发电量与坐标值
	List<Map>  getGeneratorsTotalgenwh();
	//所有风机的状态信息
	List<Map> getGeneratorsCurstate();
	
}
