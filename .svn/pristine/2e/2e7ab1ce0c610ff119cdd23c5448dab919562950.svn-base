package com.xjgc.wind.app.overview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.overview.dao.OverviewAPPDao;
import com.xjgc.wind.app.overview.service.OverviewAPPService;
import com.xjgc.wind.app.util.UnitAnalyse;
import com.xjgc.wind.app.vo.ResponseResults;
import com.xjgc.wind.app.vo.ResponseResults_Map;

public class OverviewAPPServiceImpl implements OverviewAPPService {

	OverviewAPPDao overviewAPPDao;

	public OverviewAPPDao getOverviewAPPDao() {
		return overviewAPPDao;
	}

	public void setOverviewAPPDao(OverviewAPPDao overviewAPPDao) {
		this.overviewAPPDao = overviewAPPDao;
	}

	public String getGeneralInfoStr() {
		List generalInfoList = overviewAPPDao.getGeneralInfoData();
		List generatorStatusList=overviewAPPDao.getGeneratorCurState();
		List generalInfoList2 = overviewAPPDao.getGeneralInfoData2();
		
		// System.out.println(monthGenWhList.toString());
		//总览页面的下边几个指标数据
		//List gridList = generalInfoList.subList(4, generalInfoList.size());
		List gridList = generalInfoList2;
		gridList.add(generalInfoList.get(4));
		gridList.add(generalInfoList.get(5));
		//总装机容量和总装机台数
		List topRightList = generalInfoList.subList(2, 4);
	
		Map map = new HashMap();
		//表盘当前值、量程、单位
		String _totalgencpy=UnitAnalyse.powerFormat((String)((Map) generalInfoList.get(1)).get("value"));
		String _unit=UnitAnalyse.powerFormatUnit((String)((Map) generalInfoList.get(1)).get("value"));
		String _curp=UnitAnalyse.handlePowerWithUnit((String)((Map) generalInfoList.get(0)).get("value"),_unit);
		//表盘的当前值、量程、单位
		map.put(((Map) generalInfoList.get(0)).get("name"),
				//((Map) generalInfoList.get(0)).get("value"));
				_curp);
		map.put(((Map) generalInfoList.get(1)).get("name"),
				//((Map) generalInfoList.get(1)).get("value"));
				_totalgencpy);
		map.put("unit",_unit);
		//运行状态信息
		
		
		handleTopRightList(topRightList);
		handleGridList(gridList);
		map.put("generatorStatusList", generatorStatusList);      
		map.put("topRightList", topRightList);
		map.put("gridList", gridList);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"generalInfo", map);

		return responseResults.resultsToStr();
		
	}

	
	/**
	 * 整理gridList列表中value的格式与单位
	 * @param list
	 */
	private void handleTopRightList(List list){
		for (int i=0; i<list.size();i++){
			Map _map=(Map)list.get(i);
		    String _name=(String)_map.get("name");
		    //System.out.println(_map.get("value"));
		    if(null!=_map.get("value")){
				if(_name.equals("总装机容量")){
					_map.put("value", UnitAnalyse.powerFormat((String)_map.get("value"))+" "+UnitAnalyse.powerFormatUnit((String)_map.get("value")));
				}
		    }		
		}
	}
	/**
	 * 整理gridList列表中value的格式与单位
	 * @param list
	 */
	private void handleGridList(List list){
		
		for (int i=0; i<list.size();i++){
			Map _map=(Map)list.get(i);
		    String _name=(String)_map.get("name");
		    //System.out.println(_map.get("value"));
		    if(null!=_map.get("value")){
				if(_name.equals("本日发电量")||_name.equals("本日上网电量")||_name.equals("累计发电量")){
					_map.put("value", UnitAnalyse.generationFormat((String)_map.get("value"))+" "+UnitAnalyse.generationFormatUnit((String)_map.get("value")));
				}else if (_name.equals("本日收益")){
					_map.put("value",UnitAnalyse.profitFormat((String)_map.get("value"))+" "+UnitAnalyse.profitFormatUnit((String)_map.get("value")));
				}
				
		    }		
		}
		
	}
	
	
	
	// 获取风电场实时功率数据List<Map>
	public String getPowerRealtimeData() {

		List list = overviewAPPDao.getPowerRealtimeData();
		double maxValue=getlistMaxValue(list);
		String unit=UnitAnalyse.powerFormatUnit(maxValue);
		//处理list的结果
		UnitAnalyse.handlePowerListWithUnit(list,unit);
		Map map = new HashMap();
		map.put("unit", unit);
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"PowerRealtimeData", map);

		return responseResults.resultsToStr();

	}
	
	
	/**
	 * 获取sql查询返回的最大值
	 * @param list
	 * @return
	 */
	private double getlistMaxValue(List list){
		
		double _result=0;
		for (int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String _getstr=(String)map.get("data");
			double _nowDouble=Double.parseDouble(_getstr);
			if(_nowDouble>_result){
				_result=_nowDouble;
			}
		}
		return _result;
	}
	
	
	

	// 获取风电场功率历史数据字符串
	public String getHisPowerDataStr() {

		List list = overviewAPPDao.getHisPowerDataList();
		double maxValue=getlistMaxValue(list);
		String unit=UnitAnalyse.powerFormatUnit(maxValue);
		//处理list的结果
		UnitAnalyse.handlePowerListWithUnit(list,unit);
		Map map = new HashMap();
		map.put("unit", unit);
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"HisPowerData", map);
		return responseResults.resultsToStr();

	}
	
	// 获取风电场历史数据字符串
	public String getHisWindSpeedDataStr() {

		List list = overviewAPPDao.getHisWindSpeedDataList();
		
		Map map = new HashMap();
		map.put("unit", "m/s");
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"HisWindspeedData", map);
		return responseResults.resultsToStr();

	}
	
	
	// 获取风电场历史数据字符串
	public String getHisDayGenWhStr() {

		List list = overviewAPPDao.getHisDayGenWhList();
		double maxValue=getlistMaxValue(list);
		String unit=UnitAnalyse.generationFormatUnit(maxValue);
		//处理list的结果
		UnitAnalyse.handleGenerationListWithUnit(list,unit);
		Map map = new HashMap();
		map.put("unit", unit);
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"HisDayGenWh", map);
		return responseResults.resultsToStr();

	}

	// 获取风电场历史数据字符串
	public String getHisMonthGenWhStr() {
		List list = overviewAPPDao.getHisMonthGenWhList();
		double maxValue=getlistMaxValue(list);
		String unit=UnitAnalyse.generationFormatUnit(maxValue);
		//处理list的结果
		UnitAnalyse.handleGenerationListWithUnit(list,unit);
		Map map = new HashMap();
		map.put("unit", unit);
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"HisMonthGenWh", map);
		return responseResults.resultsToStr();

	}
	
	
}
