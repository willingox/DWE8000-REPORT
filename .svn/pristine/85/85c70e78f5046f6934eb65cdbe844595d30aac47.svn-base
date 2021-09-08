package com.xjgc.wind.app.breakdown.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xjgc.wind.app.breakdown.service.BreakdownAppService;
import com.xjgc.wind.app.breakdown.dao.BreakdownAppDao;
import com.xjgc.wind.app.vo.ResponseResults_Map;

public class BreakdownAppServiceImpl implements BreakdownAppService{

	BreakdownAppDao BreakdownAppDao;
	
	
	public BreakdownAppDao getBreakdownAppDao() {
		return BreakdownAppDao;
	}
	
	
	public void setBreakdownAppDao(BreakdownAppDao breakdownAppDao) {
		BreakdownAppDao = breakdownAppDao;
	}


	public String getAllBreakdown(String dataItem) {
		
		List<Map> matrixlist= BreakdownAppDao.getAllBreakdown();
		Map map=new HashMap();
		map.put("Breakdown", matrixlist);
		ResponseResults_Map responseResults=new ResponseResults_Map(dataItem, map);
		return  responseResults.resultsToStr();
		
	}
	
	
	
}
