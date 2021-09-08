package com.xjgc.wind.app.district.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.district.dao.DistrictAPPDao;
import com.xjgc.wind.app.district.service.DistrictAPPService;
import com.xjgc.wind.app.vo.ResponseResults;
import com.xjgc.wind.app.vo.ResponseResults_Map;

public class DistrictAPPServiceImpl implements DistrictAPPService{
	
	DistrictAPPDao districtAPPDao;

	public DistrictAPPDao getDistrictAPPDao() {
		return districtAPPDao;
	}

	public void setDistrictAPPDao(DistrictAPPDao districtAPPDao) {
		this.districtAPPDao = districtAPPDao;
	}



	public String getDistrictListStr(String dataItem){
		
		ResponseResults respResults=new ResponseResults(dataItem,districtAPPDao.getDistrictList());
		return respResults.resultsToStr();
	
	}
	
	public String getGenInfoStrByDistrictId(String dataItem,String districtId){
		List genInfoList=districtAPPDao.getGenInfoListByDistrictId(districtId);
		System.out.println(genInfoList.toString());
		Map map=new HashMap();
		map.put(((Map)genInfoList.get(0)).get("name"),((Map)genInfoList.get(0)).get("value"));
		map.put(((Map)genInfoList.get(1)).get("name"),((Map)genInfoList.get(1)).get("value"));
		map.put(((Map)genInfoList.get(2)).get("name"),((Map)genInfoList.get(2)).get("value"));

		List<Map> gridList=genInfoList.subList(3, genInfoList.size());
		for(int i=0;i<gridList.size();i++)
		{
			//Map itemMap=gridList.get(i);
			String totalValue=gridList.get(i).get("value").toString();
			gridList.get(i).put("value",totalValue.substring(0,totalValue.indexOf('-')));
			gridList.get(i).put("progress",totalValue.substring(totalValue.indexOf('-')+1));
			
		}
		map.put("gridList",gridList);
		System.out.println(gridList.toString());
		ResponseResults_Map respResults=new ResponseResults_Map(dataItem,map);
		return respResults.resultsToStr();
	
	}

	//获取实时功率曲线
	public String getRealtimePowerDataStrByDistrictId(String dataItem,String districtId){
		ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getRealtimePowerDataByDistrictId(dataItem,districtId));
		return respResults.resultsToStr();
	}
	
	//获取风电场历史发电功率数据list（间隔1小时）
	public String  getHisPowerDataStrByDistrictId(String dataItem,String districtId){
		ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisPowerDataByDistrictId(dataItem,districtId));
		return respResults.resultsToStr();
	}
	//获取风电场历史日发电量list
	public String getHisDayGenWhStrByDistrictId(String dataItem,String districtId){
		ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisDayGenWhDataByDistrictId(dataItem,districtId));
		return respResults.resultsToStr();
	}
	//获取风电场历史月发电量list
	public String  getHisMonthGenWhStrByDistrictId(String dataItem,String districtId){
		ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisMonthGenWhDataByDistrictId(dataItem,districtId));
		return respResults.resultsToStr();
	}

	//获取风场基本信息
		public String getGenInfoStrByMgId(String dataItem,String mgId){
			List genInfoList=districtAPPDao.getGenInfoListByMgId(mgId);
			Map map=new HashMap();
			
			for(int i=0;i<4;i++)
				{
					map.put(((Map)genInfoList.get(i)).get("name"),((Map)genInfoList.get(i)).get("value"));
				}
			map.put("gridList", genInfoList.subList(4,genInfoList.size()));
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,map);
			return respResults.resultsToStr();
		
		}
		
		//获取风场基本信息
			public String getGeneratorListStrByMgId(String dataItem,String mgId){
				List generatorList=districtAPPDao.getGeneratorListByMgId(mgId);
				Map map=new HashMap();
				
				map.put(dataItem,generatorList);
				
				ResponseResults_Map respResults=new ResponseResults_Map(dataItem,map);
				return respResults.resultsToStr();
			
			}
	//获取实时功率曲线
		public String getRealtimePowerDataStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getRealtimePowerDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
		
		//获取风电场历史发电功率数据list（间隔1小时）
		public String  getHisPowerDataStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisPowerDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
		//获取风电场历史日发电量list
		public String getHisDayGenWhStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisDayGenWhDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
		//获取风电场历史日发电量list
		public String getDayProfitStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisDayGenWhDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
		//获取风电场平均风速list
		public String getHisAvgSpdStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getHisAvgSpdDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
		//获取风电场历史日发电量list
		public String getTotalGenWhStrByMgId(String dataItem,String mgId){
			ResponseResults_Map respResults=new ResponseResults_Map(dataItem,districtAPPDao.getTotalGenWhDataByMgId(dataItem,mgId));
			return respResults.resultsToStr();
		}
}
