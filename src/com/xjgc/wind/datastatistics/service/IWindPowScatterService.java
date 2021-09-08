package com.xjgc.wind.datastatistics.service;

import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindPowScatterDataForm;

public interface IWindPowScatterService {
	
	List<DataStatisticsDataVo> list(WindPowScatterDataForm queryFilter,Integer equipId);

}
