package com.xjgc.wind.datastatistics.dao;

import java.util.List;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgYearInfoDataForm;

public interface ISmgYearInfoDao {
 
	List<DataStatisticsDataVo> listtime(SmgYearInfoDataForm queryFiler);
	List<DataStatisticsDataVo> listname(SmgYearInfoDataForm queryFiler);
}
