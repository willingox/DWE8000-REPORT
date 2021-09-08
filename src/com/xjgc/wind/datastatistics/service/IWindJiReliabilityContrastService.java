package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm;

public interface IWindJiReliabilityContrastService {

	public List<DataStatisticsDataVo> list(String str);
	List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,String str,int flag);
	
	List<DataStatisticsDataVo> listrandhour(String startDateStr,String endDateStr,String str,int flag);
	
}
