package com.xjgc.wind.datastatistics.service.impl;

import java.util.List;

import com.xjgc.wind.datastatistics.dao.IWindAvailabilityContrastDao;
import com.xjgc.wind.datastatistics.service.IWindAvailabilityContrastService;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public class WindAvailabilityContrastServiceImpl implements IWindAvailabilityContrastService {

	IWindAvailabilityContrastDao windAvailabilityContrastDao;

	public IWindAvailabilityContrastDao getWindAvailabilityContrastDao() {
		return windAvailabilityContrastDao;
	}
	
	public void setWindAvailabilityContrastDao(IWindAvailabilityContrastDao windAvailabilityContrastDao) {
		this.windAvailabilityContrastDao = windAvailabilityContrastDao;
	}

	
	public List<DataStatisticsDataVo> listava(String startDateStr,String endDateStr,String str,int flag) {
		return windAvailabilityContrastDao.listava(startDateStr,endDateStr,str,flag);
	}
	public List<DataStatisticsDataVo> listcount(String startDateStr,String endDateStr,int flag) {
		return windAvailabilityContrastDao.listcount(startDateStr,endDateStr,flag);
	}
	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str) {
		return windAvailabilityContrastDao.list(startDateStr,endDateStr,str);
	}
		
	
	public List<DataStatisticsDataVo> listTime(String startDateStr,String endDateStr,String str){
		
		List<DataStatisticsDataVo> resultList=windAvailabilityContrastDao.list(startDateStr,endDateStr,str);
		List<DataStatisticsDataVo> notEndList=windAvailabilityContrastDao.listHappenNoEnd(startDateStr,endDateStr,str);
	
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
	
	public List<DataStatisticsDataVo> listAllAva(String startDateStr,String endDateStr,int flag){
		return windAvailabilityContrastDao.listAllAva(startDateStr,endDateStr,flag);
	}
}
