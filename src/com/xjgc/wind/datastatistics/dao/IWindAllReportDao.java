package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAllReportDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public interface IWindAllReportDao {

	List<DataStatisticsDataVo> list(WindAllReportDataForm queryFilter);
	
	List<DataStatisticsDataVo> listHappenNoEnd(WindAllReportDataForm queryFilter);
	

}
