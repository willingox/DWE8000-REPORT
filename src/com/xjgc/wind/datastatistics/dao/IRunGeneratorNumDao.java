package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.PowerScatterDataForm;


public interface IRunGeneratorNumDao {
	List<DataStatisticsDataVo> list();
	
}