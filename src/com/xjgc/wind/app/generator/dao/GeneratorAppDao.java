package com.xjgc.wind.app.generator.dao;

import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.vo.GeneralRowMapper;

public interface GeneratorAppDao {

	public List<Map> getGeneratorInfoById(String id );
	//��ȡ�����豸ʵʱ��������List<Map>
	//ͳ��һ��֮�ڵ�����
	public List<Map> getGeneratorPowerRealtimeData(String id);
	//��ȡ�����豸��ʷ����List<Map>
	//ͳ��һ��֮�ڵ����ݼ��һСʱ
	public List<Map> getGeneratorHisPowerDataList(String id);
	public List<Map> getGeneratorHisWindSpeedDataList(String id);
	/**
	 * һ���µ�����  �����һ��
	 */
	public List<Map> getGeneratorHisDayGenWhList(String id);
	/**
	 * ͳ��һ�������    ʱ����Ϊһ����
	 * 
	 */
	public List<Map> getGeneratorHisMonthGenWhList(String id);
	
	/**
	 * ��ȡ�����豸���ڼ��id
	 * 
	 */
	public String getGeneratorBayId(String id);
	
	/**
	 * ��ȡ�����豸���ڼ���еĸ����豸��id�������б�
	 * 
	 */
	public List<Map> getGeneratorBayEquipmentList(String id);
	
	/**
	 * ��ȡ�豸ң������
	 */
	public List<Map> getEquipmentMeasurePointListInAnalog(String id);
	/**
	 * ��ȡ�豸������
	 */
	public List<Map> getEquipmentMeasurePointListInStatus(String id);
}
