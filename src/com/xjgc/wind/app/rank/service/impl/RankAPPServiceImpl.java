package com.xjgc.wind.app.rank.service.impl;





import com.bjxj.usermgr.util.JsonUtils;
import com.xjgc.wind.app.rank.dao.RankAPPDao;
import com.xjgc.wind.app.rank.service.RankAPPService;
import com.xjgc.wind.app.vo.ResponseResults;

public class RankAPPServiceImpl implements RankAPPService{
	
	RankAPPDao rankAPPDao;

	public RankAPPDao getRankAPPDao() {
		return rankAPPDao;
	}

	public void setRankAPPDao(RankAPPDao rankAPPDao) {
		this.rankAPPDao = rankAPPDao;
	}

	public String getDayRankStr(String method,String dataItem,Boolean sort){
		//Gson gson=new Gson();
		ResponseResults respResults=new ResponseResults(dataItem,rankAPPDao.getDayRankList(dataItem, sort));
		//JsonArray testArray=gson.fromJson(gson.toJson(respResults.getResultsList()),JsonArray.class);
		return respResults.resultsToStr();
	}
	
	public String getMonthRankStr(String method,String dataItem,Boolean sort){
		
		ResponseResults respResults=new ResponseResults(dataItem,rankAPPDao.getMonthRankList(dataItem, sort));
		return respResults.resultsToStr();
	
	}
	
public String getYearRankStr(String method,String dataItem,Boolean sort){
		
		ResponseResults respResults=new ResponseResults(dataItem,rankAPPDao.getYearRankList(dataItem, sort));
		return respResults.resultsToStr();
	
	}
}
