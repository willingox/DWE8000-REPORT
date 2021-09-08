package com.xjgc.wind.datastatistics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.xjgc.wind.datastatistics.dao.IFaultQueryDao;
import com.xjgc.wind.datastatistics.dao.IWindAllReportDao;
import com.xjgc.wind.datastatistics.service.IFaultQueryService;
import com.xjgc.wind.datastatistics.service.IWindAllReportService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAllReportDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public class WindAllReportServiceImpl implements IWindAllReportService{
	
	IWindAllReportDao windAllReportDao;

	public IWindAllReportDao getWindAllReportDao() {
		return windAllReportDao;
	}

	public void setWindAllReportDao(IWindAllReportDao windAllReportDao) {
		this.windAllReportDao = windAllReportDao;
	}
	
	public List<DataStatisticsDataVo> list(WindAllReportDataForm queryFilter){
		
		List<DataStatisticsDataVo> resultList=windAllReportDao.list(queryFilter);
		List<DataStatisticsDataVo> notEndList=windAllReportDao.listHappenNoEnd(queryFilter);
	
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
