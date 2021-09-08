package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDetailedDataForm;

public interface IWindGenwhDetailedService {

	List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,int flag,int id);
	List<DataStatisticsDataVo> listMax(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listTable(String maxtime);
}
