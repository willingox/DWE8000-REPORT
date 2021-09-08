package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;






import com.xjgc.wind.datastatistics.dao.IRuningInfo_TenMinuteDao;
import com.xjgc.wind.datastatistics.dao.IRuningInfo_TenMinuteReportDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_TenMinuteReportService;
import com.xjgc.wind.datastatistics.service.IRuningInfo_TenMinuteService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.DataStatisticsReportDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_TenMinuteDataForm;

public class RuningInfo_TenMinuteReportServiceImpl implements IRuningInfo_TenMinuteReportService {
	IRuningInfo_TenMinuteReportDao runingInfo_TenMinuteReportDao;

	public IRuningInfo_TenMinuteReportDao getRuningInfo_TenMinuteReportDao() {
		return runingInfo_TenMinuteReportDao;
	}
	
	public void setRuningInfo_TenMinuteReportDao(IRuningInfo_TenMinuteReportDao runingInfo_TenMinuteReportDao) {
		this.runingInfo_TenMinuteReportDao = runingInfo_TenMinuteReportDao;
	}
	
	

	public List<DataStatisticsReportDataVo> list(String startDateDisp,String endDateDisp,String str,String[]  check_value,int flag,int yearIf,int pageNo,int pageSize) {
		return runingInfo_TenMinuteReportDao.list(startDateDisp,endDateDisp,str,check_value,flag,yearIf,pageNo,pageSize);
	}
	public List<DataStatisticsReportDataVo> listVal() {
		return runingInfo_TenMinuteReportDao.listVal();
	}

}
