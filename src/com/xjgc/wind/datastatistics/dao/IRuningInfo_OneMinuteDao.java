package com.xjgc.wind.datastatistics.dao;

import java.util.ArrayList;
import java.util.List;

import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;

public interface IRuningInfo_OneMinuteDao {

	
	List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,ArrayList<String> check_id,ArrayList<ArrayList<String>> check_value,int flag,int yearIf);
	
}
