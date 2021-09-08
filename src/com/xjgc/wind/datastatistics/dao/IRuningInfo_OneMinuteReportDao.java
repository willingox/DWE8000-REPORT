package com.xjgc.wind.datastatistics.dao;

import java.util.ArrayList;
import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;

public interface IRuningInfo_OneMinuteReportDao {

	List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,String[]  check_value,int flag,int yearIf,int pageNo,int pageSize);

	List<DataStatisticsDataVo> listVal();
}
