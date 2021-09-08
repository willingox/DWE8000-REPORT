package com.xjgc.wind.app.generator.dao;

import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.vo.GeneralRowMapper;

public interface GeneratorAppDao {

	public List<Map> getGeneratorInfoById(String id );
	//获取发电设备实时功率数据List<Map>
	//统计一天之内的数据
	public List<Map> getGeneratorPowerRealtimeData(String id);
	//获取发电设备历史数据List<Map>
	//统计一周之内的数据间隔一小时
	public List<Map> getGeneratorHisPowerDataList(String id);
	public List<Map> getGeneratorHisWindSpeedDataList(String id);
	/**
	 * 一个月的数据  间隔是一天
	 */
	public List<Map> getGeneratorHisDayGenWhList(String id);
	/**
	 * 统计一年的数据    时间间隔为一个月
	 * 
	 */
	public List<Map> getGeneratorHisMonthGenWhList(String id);
	
	/**
	 * 获取发电设备所在间隔id
	 * 
	 */
	public String getGeneratorBayId(String id);
	
	/**
	 * 获取发电设备所在间隔中的各个设备的id与名称列表
	 * 
	 */
	public List<Map> getGeneratorBayEquipmentList(String id);
	
	/**
	 * 获取设备遥测数据
	 */
	public List<Map> getEquipmentMeasurePointListInAnalog(String id);
	/**
	 * 获取设备信数据
	 */
	public List<Map> getEquipmentMeasurePointListInStatus(String id);
}
