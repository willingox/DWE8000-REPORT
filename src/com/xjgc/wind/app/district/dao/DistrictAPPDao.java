package com.xjgc.wind.app.district.dao;

import java.util.List;
import java.util.Map;


public interface DistrictAPPDao {
	
	public List getDistrictList(); 	
	
	public List getGenInfoListByDistrictId(String districtId); 
	//获取风电场实时功率数据List<Map>
	public Map getRealtimePowerDataByDistrictId(String dataItem,String districtId);
	//获取风电场历史发电功率数据list（间隔1小时）
	public Map getHisPowerDataByDistrictId(String dataItem,String districtId);
	//获取风电场历史日发电量list
	public Map getHisDayGenWhDataByDistrictId(String dataItem,String districtId);
	//获取风电场历史月发电量list
	public Map getHisMonthGenWhDataByDistrictId(String dataItem,String districtId);
	
	
	public List getGenInfoListByMgId(String mgId); 
	
	public List getGeneratorListByMgId(String mgId); 
	//获取风电场实时功率数据List<Map>
	public Map getRealtimePowerDataByMgId(String dataItem,String mgId);
	//获取风电场历史发电功率数据list（间隔1小时）
	public Map getHisPowerDataByMgId(String dataItem,String mgId);
	//获取风电场历史日发电量list
	public Map getHisDayGenWhDataByMgId(String dataItem,String mgId);
	//获取风电场日收益list
	public Map getDayProfitDataByMgId(String dataItem,String mgId);
	//获取风电场平均风速list
	public Map getHisAvgSpdDataByMgId(String dataItem,String mgId);
	//获取风电场月发电量list
	public Map getTotalGenWhDataByMgId(String dataItem,String mgId);
			
}
