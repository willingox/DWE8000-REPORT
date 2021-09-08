package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public interface IWindAvailabilityContrastDao {


List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str);
	
	List<DataStatisticsDataVo> listHappenNoEnd(String startDateStr,String endDateStr,String str);

	List<DataStatisticsDataVo> listcount(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listava(String startDateStr,String endDateStr,String str,int flag);
	List<DataStatisticsDataVo> listAllAva(String startDateStr,String endDateStr,int flag);
}
