package com.xjgc.wind.app.district.service;

import java.util.List;
import java.util.Map;

public interface DistrictAPPService {
	//获取district列表
	public String getDistrictListStr(String dataItem); 
	//按地区id获取地区的统计信息
	public String getGenInfoStrByDistrictId(String dataItem,String districtId); 
	//获取风电场实时功率数据List<Map>
	String  getRealtimePowerDataStrByDistrictId(String dataItem,String districtId);
	//获取风电场历史发电功率数据list（间隔1小时）
	String  getHisPowerDataStrByDistrictId(String dataItem,String districtId);
	//获取风电场历史日发电量list
	String getHisDayGenWhStrByDistrictId(String dataItem,String districtId);
	//获取风电场历史月发电量list
	String  getHisMonthGenWhStrByDistrictId(String dataItem,String districtId);
	
	String getGenInfoStrByMgId(String dataItem,String mgId); 
	
	String getGeneratorListStrByMgId(String dataItem,String mgId); 
	//获取风电场实时功率数据List<Map>
	String  getRealtimePowerDataStrByMgId(String dataItem,String mgId);
	//获取风电场历史发电功率数据list（间隔1小时）
	String  getHisPowerDataStrByMgId(String dataItem,String mgId);
	//获取风电场历史日发电量list
	String getHisDayGenWhStrByMgId(String dataItem,String mgId);
	//获取风电场日收益list
	String getDayProfitStrByMgId(String dataItem,String mgId);
	//获取风电场平均风速list
	String getHisAvgSpdStrByMgId(String dataItem,String mgId);
	//获取风电场月电量list
	String getTotalGenWhStrByMgId(String dataItem,String mgId);
	
	
}
