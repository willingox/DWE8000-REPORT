package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDataForm;


public interface IWindGenwhService {
	
	
	List<DataStatisticsDataVo> listTime(String startDateDisp,String endDateDisp,int flag);
	List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,int flag);
	List<DataStatisticsDataVo> listd(WindGenwhDataForm queryFilter);
	List<DataStatisticsDataVo> listu(String name,char id);
	List<DataStatisticsDataVo> listDetailed(String equipId,String startDateDisp,String endDateDisp,int flag);
}
