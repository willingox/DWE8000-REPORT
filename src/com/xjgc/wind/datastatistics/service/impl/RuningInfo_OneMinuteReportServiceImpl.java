package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;





import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneMinuteDao;
import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneMinuteReportDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneMinuteReportService;
import com.xjgc.wind.datastatistics.service.IRuningInfo_OneMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_OneMinuteDataForm;

public class RuningInfo_OneMinuteReportServiceImpl implements IRuningInfo_OneMinuteReportService {
	IRuningInfo_OneMinuteReportDao runingInfo_OneMinuteReportDao;

	public IRuningInfo_OneMinuteReportDao getRuningInfo_OneMinuteReportDao() {
		return runingInfo_OneMinuteReportDao;
	}
	
	public void setRuningInfo_OneMinuteReportDao(IRuningInfo_OneMinuteReportDao runingInfo_OneMinuteReportDao) {
		this.runingInfo_OneMinuteReportDao = runingInfo_OneMinuteReportDao;
	}
	
	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,String[]  check_value,int flag,int yearIf,int pageNo,int pageSize) {
		return runingInfo_OneMinuteReportDao.list(startDateDisp,endDateDisp,str,check_value,flag,yearIf,pageNo,pageSize);
	}
	public List<DataStatisticsDataVo> listVal() {
		return runingInfo_OneMinuteReportDao.listVal();
	}

}
