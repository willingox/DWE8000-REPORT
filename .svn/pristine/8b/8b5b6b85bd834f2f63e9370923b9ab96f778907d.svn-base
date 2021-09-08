package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;




import com.xjgc.wind.datastatistics.dao.IRuningInfo_SelectOneDayDao;
import com.xjgc.wind.datastatistics.service.IRuningInfo_SelectOneDayService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;

public class RuningInfo_SelectOneDayServiceImpl implements IRuningInfo_SelectOneDayService {
	IRuningInfo_SelectOneDayDao runingInfo_SelectOneDayDao;


	
	public IRuningInfo_SelectOneDayDao getRuningInfo_SelectOneDayDao() {
		return runingInfo_SelectOneDayDao;
	}

	public void setRuningInfo_SelectOneDayDao(
			IRuningInfo_SelectOneDayDao runingInfo_SelectOneDayDao) {
		this.runingInfo_SelectOneDayDao = runingInfo_SelectOneDayDao;
	}

	public List<DataStatisticsDataVo> BayList(Integer flag){
		return runingInfo_SelectOneDayDao.BayList(flag);
	}
	
	public List<DataStatisticsDataVo> list(Integer flag){
		return runingInfo_SelectOneDayDao.list(flag);
	}


}
