package com.xjgc.wind.app.overview.service;

import java.util.List;
import java.util.Map;


public interface OverviewAPPService {
	
		//��ȡ���Ż�����Ϣ
		String  getGeneralInfoStr();
		//��ȡ��糡ʵʱ��������List<Map>
		String  getPowerRealtimeData();
	
		//��ȡ��糡��ʷ���繦������list�����1Сʱ��
		String  getHisPowerDataStr();
		
		//��ȡ��糡��ʷ����list�����1Сʱ��
		String getHisWindSpeedDataStr();
		
		//��ȡ��糡��ʷ�շ�����list
		String getHisDayGenWhStr();
		
		//��ȡ��糡��ʷ�·�����list
		String  getHisMonthGenWhStr();
		

}
