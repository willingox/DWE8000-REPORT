package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IPowerScatterDao;
import com.xjgc.wind.datastatistics.service.IPowerScatterService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.PowerScatterDataForm;

public class PowerScatterServiceImpl implements IPowerScatterService {

	IPowerScatterDao powerScatterDao;

	public IPowerScatterDao getPowerScatterDao() {
		return powerScatterDao;
	}
	
	public void setPowerScatterDao(IPowerScatterDao powerScatterDao) {
		this.powerScatterDao = powerScatterDao;
	}
	public List<DataStatisticsDataVo> listgenpwd(Integer equipId) {
		return powerScatterDao.listgenpwd(equipId);
	}
	
	public List<DataStatisticsDataVo> listfre(PowerScatterDataForm queryFilter,Integer equipId) {
		return powerScatterDao.listfre(queryFilter, equipId);
	}
}

