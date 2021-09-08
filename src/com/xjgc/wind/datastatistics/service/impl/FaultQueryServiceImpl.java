package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IFaultQueryDao;
import com.xjgc.wind.datastatistics.service.IFaultQueryService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public class FaultQueryServiceImpl implements IFaultQueryService{
	
	IFaultQueryDao faultQueryDao;

	public IFaultQueryDao getFaultQueryDao() {
		return faultQueryDao;
	}

	public void setFaultQueryDao(IFaultQueryDao faultQueryDao) {
		this.faultQueryDao = faultQueryDao;
	}
	
	public List<DataStatisticsDataVo> list(FaultQueryDataForm queryFilter){
		
		List<DataStatisticsDataVo> resultList=faultQueryDao.list(queryFilter);
		List<DataStatisticsDataVo> notEndList=faultQueryDao.listHappenNoEnd(queryFilter);
	
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
