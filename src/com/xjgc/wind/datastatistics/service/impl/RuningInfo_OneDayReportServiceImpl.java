package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;






import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneDayDao;
import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneDayReportDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayReportService;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneDayService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.DataStatisticsReportDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneDayDataForm;

public class RuningInfo_OneDayReportServiceImpl implements IRuningInfo_OneDayReportService {
	IRuningInfo_OneDayReportDao runingInfo_OneDayReportDao;

	public IRuningInfo_OneDayReportDao getRuningInfo_OneDayReportDao() {
		return runingInfo_OneDayReportDao;
	}
	
	public void setRuningInfo_OneDayReportDao(IRuningInfo_OneDayReportDao runingInfo_OneDayReportDao) {
		this.runingInfo_OneDayReportDao = runingInfo_OneDayReportDao;
	}
	
	

	public List<DataStatisticsReportDataVo> list(String startDateDisp,String endDateDisp,String str,String[]  check_value,int flag,int yearIf,int pageNo,int pageSize) {
		return runingInfo_OneDayReportDao.list(startDateDisp,endDateDisp,str,check_value,flag,yearIf,pageNo,pageSize);
	}
	public List<DataStatisticsReportDataVo> listVal() {
		return runingInfo_OneDayReportDao.listVal();
	}

	
	
	
	
	
}
