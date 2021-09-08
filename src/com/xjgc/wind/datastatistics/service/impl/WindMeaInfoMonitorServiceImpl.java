package com.xjgc.wind.datastatistics.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.xjgc.wind.datastatistics.dao.IWindMeaInfoMonitorDao;
import com.xjgc.wind.datastatistics.service.IWindMeaInfoMonitorService;
import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;

public class WindMeaInfoMonitorServiceImpl implements IWindMeaInfoMonitorService {
	IWindMeaInfoMonitorDao windMeaInfoMonitorDao;

	public IWindMeaInfoMonitorDao getWindMeaInfoMonitorDao() {
		return windMeaInfoMonitorDao;
	}
	
	public void setWindMeaInfoMonitorDao(IWindMeaInfoMonitorDao windMeaInfoMonitorDao) {
		this.windMeaInfoMonitorDao = windMeaInfoMonitorDao;
	}
	
	
	public List<WgmeasinfoDataVo> list() {
		return windMeaInfoMonitorDao.list();
	}
	


}
