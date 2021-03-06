package com.xjgc.wind.querytree.dao;

import java.util.List;

import com.xjgc.wind.querytree.vo.ResultVO;

public interface IQueryTreeDao {

	//查询获取厂站/建筑信息
	List<ResultVO> listStationByClass(Integer classId);


	//间隔类型/分类耗能类型
	List<ResultVO> listEuTypeByStation(Integer stationId);

	//间隔/分类能耗: typeIds 多个id之类以逗号(,)分隔
	List<ResultVO> listEuByStationAndType(Integer stationId, String typeIds);
	
	//设备大类/分类分项类别
	List<ResultVO> listEuitemClassByType(Integer typeId);

	//设备/分项耗能
	List<ResultVO> listEuitemByEu(Integer euId);


	//设备/分项耗能
	List<ResultVO> listEuitemByClass(Integer stationId,Integer classId);
	
	//设备/分项耗能模糊检索
	int getEuitemCountByName(String strFilter);
	
	List<ResultVO> listEuitemByName(String strFilter);
	
	
	//查询获取区域列表
	List<ResultVO> listDistrict();
	//查询获取districtId下所有microgrid
	List<ResultVO> getMicrogridList();
	//查询获取microgrid下所有geneator
	List<ResultVO> getGeneratorList(String parentId);
	//查询获取microgrid下所有bay
	List<ResultVO> getBayList(String parentId,int id);
	List<ResultVO> getBayList(String parentId);
	//获取是bayid还是equipid
	List<ResultVO> getBayOrEquip();
	//查询获取geneator下所有mesurePoindSub
	List<ResultVO> getMesurePointSubList();
	//查询获取mesurePoindSub下所有mesurePoint
	List<ResultVO> getMesurePointList(String parentId);

}
