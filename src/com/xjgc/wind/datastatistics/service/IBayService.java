package com.xjgc.wind.datastatistics.service;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;

public interface IBayService {

	List<DataStatisticsDataVo> listSmg();
	List<DataStatisticsDataVo> list();
}
