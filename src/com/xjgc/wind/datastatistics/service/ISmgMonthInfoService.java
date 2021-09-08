package com.xjgc.wind.datastatistics.service;

import java.util.List;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgMonthInfoDataForm;

public interface ISmgMonthInfoService {

	List<DataStatisticsDataVo> listtimegen(SmgMonthInfoDataForm queryFilter);
	List<DataStatisticsDataVo> listnamegen(SmgMonthInfoDataForm queryFilter);
}
