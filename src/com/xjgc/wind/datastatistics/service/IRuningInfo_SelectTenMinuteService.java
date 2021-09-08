package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_SelectOneMinuteDataForm;

public interface IRuningInfo_SelectTenMinuteService {


	List<DataStatisticsDataVo> BayList(Integer flag);
	List<DataStatisticsDataVo> list(Integer flag);
}
