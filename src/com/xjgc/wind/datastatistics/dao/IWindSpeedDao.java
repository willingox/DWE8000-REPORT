package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;

public interface IWindSpeedDao {
	List<DataStatisticsDataVo> listcount(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str,int flag);
	List<DataStatisticsDataVo> listavg(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listMax(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listMin(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listMaxTime(String startDateStr,String endDateStr,int id,int flag);
	List<DataStatisticsDataVo> listMinTime(String startDateStr,String endDateStr,int id,int flag);
}
