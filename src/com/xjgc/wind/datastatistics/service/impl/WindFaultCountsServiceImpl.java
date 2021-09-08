package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindFaultCountsDao;
import com.xjgc.wind.datastatistics.service.IWindFaultCountsService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindFaultCountsDataForm;

public class WindFaultCountsServiceImpl implements IWindFaultCountsService{

	IWindFaultCountsDao windFaultCountsDao;

	public IWindFaultCountsDao getWindFaultCountsDao() {
		return windFaultCountsDao;
	}

	public void setWindFaultCountsDao(IWindFaultCountsDao windFaultCountsDao) {
		this.windFaultCountsDao = windFaultCountsDao;
	}
	
	public List<DataStatisticsDataVo> list(WindFaultCountsDataForm queryFilter){
		
		List<DataStatisticsDataVo> resultList=windFaultCountsDao.list(queryFilter);
		List<DataStatisticsDataVo> notEndList=windFaultCountsDao.listHappenNoEnd(queryFilter);
	
		for(int i=0;i<resultList.size();i++){
			DataStatisticsDataVo obj=resultList.get(i);
			for(int j=0;j<notEndList.size();j++){
				DataStatisticsDataVo obj1=notEndList.get(j);
				if(obj.getHappenTime().equals(obj1.getHappenTime()) && obj.getId()==obj1.getId()){
					notEndList.remove(j); break;
				}
			}
		}
		resultList.addAll(notEndList);
		return resultList;
	}
}
