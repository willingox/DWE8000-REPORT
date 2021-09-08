package com.xjgc.wind.app.district.dao;

import java.util.List;
import java.util.Map;


public interface DistrictAPPDao {
	
	public List getDistrictList(); 	
	
	public List getGenInfoListByDistrictId(String districtId); 
	//��ȡ��糡ʵʱ��������List<Map>
	public Map getRealtimePowerDataByDistrictId(String dataItem,String districtId);
	//��ȡ��糡��ʷ���繦������list�����1Сʱ��
	public Map getHisPowerDataByDistrictId(String dataItem,String districtId);
	//��ȡ��糡��ʷ�շ�����list
	public Map getHisDayGenWhDataByDistrictId(String dataItem,String districtId);
	//��ȡ��糡��ʷ�·�����list
	public Map getHisMonthGenWhDataByDistrictId(String dataItem,String districtId);
	
	
	public List getGenInfoListByMgId(String mgId); 
	
	public List getGeneratorListByMgId(String mgId); 
	//��ȡ��糡ʵʱ��������List<Map>
	public Map getRealtimePowerDataByMgId(String dataItem,String mgId);
	//��ȡ��糡��ʷ���繦������list�����1Сʱ��
	public Map getHisPowerDataByMgId(String dataItem,String mgId);
	//��ȡ��糡��ʷ�շ�����list
	public Map getHisDayGenWhDataByMgId(String dataItem,String mgId);
	//��ȡ��糡������list
	public Map getDayProfitDataByMgId(String dataItem,String mgId);
	//��ȡ��糡ƽ������list
	public Map getHisAvgSpdDataByMgId(String dataItem,String mgId);
	//��ȡ��糡�·�����list
	public Map getTotalGenWhDataByMgId(String dataItem,String mgId);
			
}
