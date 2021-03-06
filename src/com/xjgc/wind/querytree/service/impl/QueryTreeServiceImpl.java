package com.xjgc.wind.querytree.service.impl;

import java.util.List;

import com.xjgc.wind.querytree.dao.IQueryTreeDao;
import com.xjgc.wind.querytree.service.IQueryTreeService;
import com.xjgc.wind.querytree.vo.ResultVO;

public class QueryTreeServiceImpl implements IQueryTreeService {
	
	IQueryTreeDao queryTreeDao;
	
	public IQueryTreeDao getQueryTreeDao() {
		return queryTreeDao;
	}

	public void setQueryTreeDao(IQueryTreeDao queryTreeDao) {
		this.queryTreeDao = queryTreeDao;
	}

	public List<ResultVO> listStationByClass(Integer classId) {
		return getQueryTreeDao().listStationByClass(classId);
	}

	public List<ResultVO> listEuTypeByStation(Integer stationId) {
		return getQueryTreeDao().listEuTypeByStation(stationId);
	}

	public List<ResultVO> listEuByStationAndType(Integer stationId,
			String typeIds) {
		return getQueryTreeDao().listEuByStationAndType(stationId, typeIds);
	}

	public List<ResultVO> listEuitemClassByType(Integer typeId) {
		return getQueryTreeDao().listEuitemClassByType(typeId);
	}

	public List<ResultVO> listEuitemByEu(Integer euId) {
		return getQueryTreeDao().listEuitemByEu(euId);
	}

	public List<ResultVO> listEuitemByClass(Integer stationId,Integer classId) {
		return getQueryTreeDao().listEuitemByClass(stationId,classId);
	}

	public int getEuitemCountByName(String strFilter) {
		return getQueryTreeDao().getEuitemCountByName(strFilter);
	}

	public List<ResultVO> listEuitemByName(String strFilter) {
		return getQueryTreeDao().listEuitemByName(strFilter);
	}

	/**
	 * ��������
	 * @author hb 2016-5-16
	 *
	 */
	public List<ResultVO> listDistrict() {
		return getQueryTreeDao().listDistrict();
	}
	
	public List<ResultVO> getMicrogridList() {
		return getQueryTreeDao().getMicrogridList();
	}
	
	public List<ResultVO> getGeneratorList(String parentId) {
		return getQueryTreeDao().getGeneratorList(parentId);
	}
	public List<ResultVO> getBayList(String parentId,int id) {
		return getQueryTreeDao().getBayList(parentId,id);
	}
	public List<ResultVO> getBayList(String parentId) {
		return getQueryTreeDao().getBayList(parentId);
	}
	public List<ResultVO> getBayOrEquip() {
		return getQueryTreeDao().getBayOrEquip();
	}
	public List<ResultVO> getMesurePointSubList() {
		return getQueryTreeDao().getMesurePointSubList();
	}
	
	public List<ResultVO> getMesurePointList(String parentId ) {
		return getQueryTreeDao().getMesurePointList(parentId);
	}
}
