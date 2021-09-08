package com.xjgc.wind.datastatistics.dao;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.PowerScatterDataForm;

public interface IPowerScatterDao {

	//

	List<DataStatisticsDataVo> listgenpwd(Integer equipId);
	List<DataStatisticsDataVo> listfre(PowerScatterDataForm queryFilter,Integer equipId);
	
	
}

