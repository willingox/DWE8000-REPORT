package com.xjgc.wind.app.station.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.station.dao.StationAppDao;
import com.xjgc.wind.app.station.service.StationAppService;
import com.xjgc.wind.app.util.UnitAnalyse;
import com.xjgc.wind.app.vo.ResponseResults_Map;

public class StationAppServiceImpl implements StationAppService{

	StationAppDao stationAppDao;
	
	public StationAppDao getStationAppDao() {
		return stationAppDao;
	}

	public void setStationAppDao(StationAppDao stationAppDao) {
		this.stationAppDao = stationAppDao;
	}

	public String getGeneratorsCapacityStr() {
		List generatorsCapacityList = stationAppDao.getGeneratorsCapacity();
		List stationlocationlist=stationAppDao.getStationsLocation();
		handleList(generatorsCapacityList);
		Map map = new HashMap();
		map.put("list",generatorsCapacityList);
		map.put("location", stationlocationlist.get(0));
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);
		
		
		return responseResults.resultsToStr();
	}

	public String getGeneratorsCurpStr() {
		List generatorsCurpList= stationAppDao.getGeneratorsCurp();
		List stationlocationlist=stationAppDao.getStationsLocation();
		handleList(generatorsCurpList);
		Map map = new HashMap();
		map.put("list",generatorsCurpList);
		map.put("location", stationlocationlist.get(0));
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);

		return responseResults.resultsToStr();
	}

	public String getGeneratorsWindSpeedStr() {
		List generatorsWindSpeedList= stationAppDao.getGeneratorsWindSpeed();
		List stationlocationlist=stationAppDao.getStationsLocation();
		handleList(generatorsWindSpeedList);
		Map map = new HashMap();
		map.put("list",generatorsWindSpeedList);
		map.put("location", stationlocationlist.get(0));
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);
		return responseResults.resultsToStr();
	}

	public String getGeneratorsTodaygenwhStr() {
		List generatorsTodaygenwhList= stationAppDao.getGeneratorsTodaygenwh();
		List stationlocationlist=stationAppDao.getStationsLocation();
		handleList(generatorsTodaygenwhList);
		Map map = new HashMap();
		map.put("list",generatorsTodaygenwhList);
		map.put("location", stationlocationlist.get(0));
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);

		return responseResults.resultsToStr();
	}

	public String getGeneratorsTotalgenwhStr() {
		List generatorsTotalgenwhList= stationAppDao.getGeneratorsTotalgenwh();
		List stationlocationlist=stationAppDao.getStationsLocation();
		handleList(generatorsTotalgenwhList);
		Map map = new HashMap();
		map.put("list",generatorsTotalgenwhList);
		map.put("location", stationlocationlist.get(0));
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);

		return responseResults.resultsToStr();
	}

	
	private void handleList(List list){
		for(int i=0;i<list.size();i++){
			Map _map=(Map)list.get(i);
			if(_map.get("capacity")!=null){
				String str=(String)_map.get("capacity");
				_map.put("strValue", UnitAnalyse.powerFormat(str)+UnitAnalyse.powerFormatUnit(str));
			}else if(_map.get("curp")!=null){
				String str=(String)_map.get("curp");
				_map.put("strValue", UnitAnalyse.powerFormat(str)+UnitAnalyse.powerFormatUnit(str));
			}else if(_map.get("windspeed")!=null){
				String str=(String)_map.get("windspeed");
				_map.put("strValue",str+" m/s" );
			}else if(_map.get("todaygenwh")!=null){
				String str=(String)_map.get("todaygenwh");
				_map.put("strValue", UnitAnalyse.generationFormat(str)+UnitAnalyse.generationFormatUnit(str));
			}else if(_map.get("totalgenwh")!=null){
				String str=(String)_map.get("totalgenwh");
				_map.put("strValue", UnitAnalyse.generationFormat(str)+UnitAnalyse.generationFormatUnit(str));
			}
		}
	}
	
	
	
	public String  getGeneratorsCurstateStr(){
		List generatorsCurstateList= stationAppDao.getGeneratorsCurstate();
		handleCurstateList(generatorsCurstateList);
		Map map = new HashMap();
		map.put("list",generatorsCurstateList);
		ResponseResults_Map responseResults = new ResponseResults_Map(
				"stationInfo", map);

		return responseResults.resultsToStr();
	}
	
	private void handleCurstateList(List list){
		
		for(int i=0;i<list.size();i++){
			Map _map=(Map)list.get(i);
			_map.put("curp", UnitAnalyse.powerFormat((String)_map.get("curp"))+UnitAnalyse.powerFormatUnit((String)_map.get("curp")));
			_map.put("todaygenwh", UnitAnalyse.generationFormat((String)_map.get("todaygenwh"))
					+UnitAnalyse.generationFormatUnit((String)_map.get("todaygenwh")));
			_map.put("totalgenwh", UnitAnalyse.generationFormat((String)_map.get("totalgenwh"))
					+UnitAnalyse.generationFormatUnit((String)_map.get("totalgenwh")));
		}
	}
	
	
	
}
