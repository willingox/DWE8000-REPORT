package com.xjgc.wind.querytree.service;

import java.util.List;

import com.xjgc.wind.querytree.vo.ResultVO;

public interface IQueryTreeService {

	//��ѯ��ȡ��վ/������Ϣ
	List<ResultVO> listStationByClass(Integer classId);

	//�������/�����������
	List<ResultVO> listEuTypeByStation(Integer stationId);

	//���/�����ܺ�: typeIds ���id֮���Զ���(,)�ָ�
	List<ResultVO> listEuByStationAndType(Integer stationId, String typeIds);

	//�豸����/����������
	List<ResultVO> listEuitemClassByType(Integer typeId);

	//�豸/�������
	List<ResultVO> listEuitemByEu(Integer euId);

	//�豸/�������
	List<ResultVO> listEuitemByClass(Integer stationId,Integer classId);
	
	//�豸/�������ģ�����
	int getEuitemCountByName(String strFilter);

	List<ResultVO> listEuitemByName(String strFilter);
	/**
	 * ��������
	 * @author hb 2016-5-16
	 *
	 */
	
	//��ѯ��ȡ��������list
	List<ResultVO> listDistrict();
	//��ѯ��ȡdistrictId������microgrid
	List<ResultVO> getMicrogridList();
	//��ѯ��ȡmicrogrid������geneator
	List<ResultVO> getGeneratorList(String parentId);
	List<ResultVO> getBayList(String parentId,int id);
	List<ResultVO> getBayList(String parentId);
	//获取是bayid还是equipid
	List<ResultVO> getBayOrEquip();
	//��ѯ��ȡgeneator������mesurePoindSub
	List<ResultVO> getMesurePointSubList();
	//��ѯ��ȡmesurePoindSub������mesurePoint
	List<ResultVO> getMesurePointList(String parentId);
}
