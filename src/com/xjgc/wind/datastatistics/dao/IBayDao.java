package com.xjgc.wind.datastatistics.dao;


import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public interface IBayDao {


	List<DataStatisticsDataVo> list();
	List<DataStatisticsDataVo> listSmg();
}
