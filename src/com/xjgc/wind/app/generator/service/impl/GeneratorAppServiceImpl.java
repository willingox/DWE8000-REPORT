package com.xjgc.wind.app.generator.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.generator.dao.GeneratorAppDao;
import com.xjgc.wind.app.generator.service.GeneratorAppService;
import com.xjgc.wind.app.util.UnitAnalyse;
import com.xjgc.wind.app.vo.ResponseResults_Map;

public class GeneratorAppServiceImpl implements GeneratorAppService{

	GeneratorAppDao  generatorAppDao;

	public GeneratorAppDao getGeneratorAppDao() {
		return generatorAppDao;
	}

	public void setGeneratorAppDao(GeneratorAppDao generatorAppDao) {
		this.generatorAppDao = generatorAppDao;
	}
	
	public String getGeneratorInfoByIdStr(String id){
		
		List generatorInflist=generatorAppDao.getGeneratorInfoById(id);
		Map map=new HashMap();
		if(generatorInflist.size()!=0){
			map=(Map)generatorInflist.get(0);
			handleGeneratorInfo(map);
		}
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"generalInfo", map);
		return responseResults.resultsToStr();
		
	}
	
	private void handleGeneratorInfo(Map map){
		
		//发电功率  量程、单位、表盘当前值
		String _capcity=UnitAnalyse.powerFormat((String)map.get("capcity"));
		String _capcityUnit=UnitAnalyse.powerFormatUnit((String)map.get("capcity"));
		String _curp=UnitAnalyse.handlePowerWithUnit((String)map.get("curp"),_capcityUnit);
		
		//今日发电量 量程、单位、表盘当前值
		double _dayGeneration=Double.parseDouble(_capcity)*24;
		String _dayGenerationstr=UnitAnalyse.generationFormat(_dayGeneration);
		String _generationUnit=UnitAnalyse.generationFormatUnit(_dayGeneration);
		String _todaygenwh=UnitAnalyse.handleGenerationWithUnit((String)map.get("todaygenwh"), _generationUnit);
		
		map.put("capcity", _capcity);
		map.put("capcityUnit", _capcityUnit);
		map.put("curp", _curp);
		
		map.put("dayGenerationstr", _dayGenerationstr);
		map.put("generationUnit", _generationUnit);
		map.put("todaygenwh", _todaygenwh);
		
	}

	
	// 获取风电场实时功率数据List<Map>
	public String getGeneratorPowerRealtimeDataStr(String id) {

		List list = generatorAppDao.getGeneratorPowerRealtimeData(id);
		double maxValue=UnitAnalyse.getlistMaxValue(list);
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
	
	
	public String getGeneratorHisPowerDataListStr(String id) {

		List list = generatorAppDao.getGeneratorHisPowerDataList(id);
		double maxValue=UnitAnalyse.getlistMaxValue(list);
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
	
	public String getGeneratorHisWindSpeedDataListStr(String id) {

		List list = generatorAppDao.getGeneratorHisWindSpeedDataList(id);
		Map map = new HashMap();
		map.put("unit", "m/s");
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"HisWindSpeed", map);

		return responseResults.resultsToStr();

	}
	
	
	public String getGeneratorHisDayGenWhListStr(String id) {

		List list = generatorAppDao.getGeneratorHisDayGenWhList(id);
		double maxValue=UnitAnalyse.getlistMaxValue(list);
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
	
	
	public String getGeneratorHisMonthGenWhListStr(String id) {

		List list = generatorAppDao.getGeneratorHisMonthGenWhList(id);
		double maxValue=UnitAnalyse.getlistMaxValue(list);
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
	
	public String getGeneratorBayEquipmentListStr(String id){
		
		List list = generatorAppDao.getGeneratorBayEquipmentList(id);
		Map map = new HashMap();
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"equipmentsId", map);
		return responseResults.resultsToStr();
	}
	
	
	
/*	public String getEquipmentsInfoStr(String id){
		
		//结果map
		Map map = new HashMap();
		//结果list
		List<Map> list=new ArrayList<Map>();
		//设备list
		List equipmentList=generatorAppDao.getGeneratorBayEquipmentList(id);
		for(int i=0;i<equipmentList.size();i++){
			Map equipmentMap=(Map)equipmentList.get(i);
			String equipmentId=(String)equipmentMap.get("id");
			List analogList=generatorAppDao.getEquipmentMeasurePointListInAnalog(equipmentId);
			List statusList=generatorAppDao.getEquipmentMeasurePointListInStatus(equipmentId);
			if(0!=analogList.size()||0!=statusList.size()){
				equipmentMap.put("analogList", analogList);
				equipmentMap.put("statusList", statusList);
				list.add(equipmentMap);
			}
		}
		
		map.put("list", list);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"EquipmentsInfo", map);
		return responseResults.resultsToStr();
		
	}*/
	
	public String getEquipmentMeasurePointListInStatusAndAnalogStr(String id){
		
		List statusList = generatorAppDao.getEquipmentMeasurePointListInStatus(id);
		List analogList = generatorAppDao.getEquipmentMeasurePointListInAnalog(id);
		Map map = new HashMap();
		map.put("statusList", statusList);
		map.put("analogList", analogList);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"equipmentInfo", map);
		return responseResults.resultsToStr();
	}
	
	
	
}
