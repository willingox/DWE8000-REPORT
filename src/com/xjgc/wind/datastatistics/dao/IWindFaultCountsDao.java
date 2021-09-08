package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindFaultCountsDataForm;

public interface IWindFaultCountsDao {

	List<DataStatisticsDataVo> list(WindFaultCountsDataForm queryFilter);
	List<DataStatisticsDataVo> listHappenNoEnd(WindFaultCountsDataForm queryFilter) ;
}
