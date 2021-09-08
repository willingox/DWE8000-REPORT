package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public interface IFaultQueryDao {

	List<DataStatisticsDataVo> list(FaultQueryDataForm queryFilter);
	
	List<DataStatisticsDataVo> listHappenNoEnd(FaultQueryDataForm queryFilter);
	

}
