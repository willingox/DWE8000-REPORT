package com.xjgc.wind.app.rank.dao;

import java.util.List;


public interface RankAPPDao {
	
	public List getDayRankList(String dataItem,Boolean sortDesc);
	public List getMonthRankList(String dataItem,Boolean sortDesc);
	public List getYearRankList(String dataItem,Boolean sortDesc);
	
}
