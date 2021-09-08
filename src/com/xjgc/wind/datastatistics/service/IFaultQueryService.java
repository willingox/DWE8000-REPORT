package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public interface IFaultQueryService {
	
	List<DataStatisticsDataVo> list(FaultQueryDataForm queryFilter);
}
