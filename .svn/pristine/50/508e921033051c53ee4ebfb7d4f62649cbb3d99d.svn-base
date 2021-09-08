package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IOperatRecordDao;
import com.xjgc.wind.datastatistics.service.IOperatRecordService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.OperatRecordDataForm;

public class OperatRecordServiceImpl implements IOperatRecordService{
	
	IOperatRecordDao operatRecordDao;

	
	public IOperatRecordDao getOperatRecordDao() {
		return operatRecordDao;
	}


	public void setOperatRecordDao(IOperatRecordDao operatRecordDao) {
		this.operatRecordDao = operatRecordDao;
	}


	public List<DataStatisticsDataVo> list(OperatRecordDataForm queryFilter){
		return operatRecordDao.list(queryFilter);
	}

}
