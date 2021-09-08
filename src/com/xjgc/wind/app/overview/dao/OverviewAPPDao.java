package com.xjgc.wind.app.overview.dao;

import java.util.List;
import java.util.Map;

public interface OverviewAPPDao {
	//��ȡ��糡ʵʱ��������List<Map>
	List<Map>  getGeneralInfoData();
	List<Map>  getGeneralInfoData2();
	List<Map> getGeneratorCurState();
	//��ȡ��糡ʵʱ��������List<Map>
	List<Map>  getPowerRealtimeData();
	//��ȡ��糡��ʷ���繦������list�����1Сʱ��
	List<Map>  getHisPowerDataList();
	
	//��ȡ��糡��ʷ����ֵ�����1Сʱ��
	List<Map>  getHisWindSpeedDataList();
	//��ȡ��糡��ʷ�շ�����list
	List<Map>  getHisDayGenWhList();
	//��ȡ��糡��ʷ�·�����list
	List<Map>  getHisMonthGenWhList();
	
}
