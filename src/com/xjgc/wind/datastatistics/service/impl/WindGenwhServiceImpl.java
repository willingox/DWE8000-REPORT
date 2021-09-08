package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindGenwhDao;
import com.xjgc.wind.datastatistics.service.IWindGenwhService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDataForm;

public class WindGenwhServiceImpl implements IWindGenwhService{
	
	IWindGenwhDao windGenwhDao;

	public IWindGenwhDao getWindGenwhDao() {
		return windGenwhDao;
	}

	public void setWindGenwhDao(IWindGenwhDao windGenwhDao) {
		this.windGenwhDao = windGenwhDao;
	}

	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,int flag) {
		return windGenwhDao.list(startDateStr,endDateStr,flag);
	}
	
	public List<DataStatisticsDataVo> listd(WindGenwhDataForm queryFilter) {
		return windGenwhDao.listd(queryFilter);
	}
	
	public List<DataStatisticsDataVo> listu(String name,char id) {
		return windGenwhDao.listu( name, id);
	}
	public List<DataStatisticsDataVo> listDetailed(String equipId,String startDateDisp,String endDateDisp,int flag) {
		return windGenwhDao.listDetailed(equipId,startDateDisp,endDateDisp,flag);
	}
	public List<DataStatisticsDataVo> listTime(String startDateDisp,String endDateDisp,int flag) {
		return windGenwhDao.listTime(startDateDisp,endDateDisp,flag);
	}

}
