package com.xjgc.wind.app.station.service;

import java.util.List;
import java.util.Map;

public interface StationAppService {


	//���з����װ������������ֵ
	String  getGeneratorsCapacityStr();
	//���з���ķ��繦��������ֵ
	String  getGeneratorsCurpStr();
	//���з���ĵ�ǰ����������ֵ
	String  getGeneratorsWindSpeedStr();
	//���з���Ľ��շ�����������ֵ
	String  getGeneratorsTodaygenwhStr();
	//���з�����ۼƷ�����������ֵ
	String  getGeneratorsTotalgenwhStr();
			
	String  getGeneratorsCurstateStr();

}
