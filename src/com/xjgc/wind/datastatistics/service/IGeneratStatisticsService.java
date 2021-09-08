package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratStatisticsDataForm;


public interface IGeneratStatisticsService {

	//
	
	List<DataStatisticsDataVo> listpw(GeneratStatisticsDataForm queryFilter);
	List<DataStatisticsDataVo> listgenwh(GeneratStatisticsDataForm queryFilter);
	List<DataStatisticsDataVo> listsunlight(GeneratStatisticsDataForm queryFilter);

}
