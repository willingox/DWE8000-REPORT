package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IMeastypeDao;
import com.xjgc.wind.datastatistics.service.IMeastypeService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


public class MeastypeServiceImpl implements IMeastypeService {

	IMeastypeDao meastypeDao;

	public IMeastypeDao getMeastypeDao() {
		return meastypeDao;
	}
	
	public void setMeastypeDao(IMeastypeDao meastypeDao) {
		this.meastypeDao = meastypeDao;
	}

	
	public List<DataStatisticsDataVo> list(Integer measclassId) {
		return meastypeDao.list(measclassId);
	}
	
	
	
}
