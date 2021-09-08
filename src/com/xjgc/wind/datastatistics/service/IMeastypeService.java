package com.xjgc.wind.datastatistics.service;


import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public interface IMeastypeService {

	//
	
	List<DataStatisticsDataVo> list(Integer measclassId);
	
}
