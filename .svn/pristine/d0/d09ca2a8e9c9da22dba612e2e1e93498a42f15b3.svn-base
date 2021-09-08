package com.xjgc.wind.app.rank.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.fr.third.org.quartz.Calendar;
import com.xjgc.wind.app.rank.dao.RankAPPDao;
import com.xjgc.wind.app.vo.OverviewRankDataRowMapper;

public class RankAPPDaoImpl extends JdbcDaoSupport implements RankAPPDao {
	
	public List getDayRankList(String dataItem,Boolean sort){
		String sql = this.getDayRankSql(dataItem, sort);
		return getJdbcTemplate().query(sql, new OverviewRankDataRowMapper());//根据需要替换RowMapper
	
	}
	public List getMonthRankList(String dataItem,Boolean sort){
		String sql = this.getMonthRankSql(dataItem, sort);
		return getJdbcTemplate().query(sql, new OverviewRankDataRowMapper());//根据需要替换RowMapper
	}
	
	public List getYearRankList(String dataItem,Boolean sort){
		String sql = this.getYearRankSql(dataItem, sort);
		return getJdbcTemplate().query(sql, new OverviewRankDataRowMapper());//根据需要替换RowMapper
	}
	
	String  getDayRankSql(String dataItem, Boolean sortDesc){
		String sqlSelectItem="";
		
		String sqlSort="";
		
		//genWh avg_P avg_spd total_genWh capacity
		if(dataItem.equals("genWh"))
		{
			sqlSelectItem="CONCAT(r.todaygenwh,' MWh') as value,r.todaygenwh as orderitem";
		}else if(dataItem.equals("avg_P")){
			
			sqlSelectItem="CONCAT(truncate(r.curp/1000,2),' MW') as value,r.curp as orderitem";
			
		}else if(dataItem.equals("avg_spd")){
			
			sqlSelectItem="CONCAT(w.windvelval,' m/s') as value, w.windvelval as orderitem";
			
		}else if(dataItem.equals("total_genWh")){
			
			sqlSelectItem="CONCAT(truncate(r.totalgenwh/1000,2),' GWh') as value ,r.totalgenwh as orderitem";
			
		}else if(dataItem.equals("capacity")){
			
			sqlSelectItem=" CONCAT(r.totalgencpty/1000,' MWh') as value,r.totalgencpty as orderitem";
			
		}
		
		if(sortDesc)
		{
			sqlSort="desc";
		}else{
			sqlSort="asc";
		}	
		
		String sql=	"select s.id as id,s.name as name,";
			sql+=sqlSelectItem;
			sql+=" from rtgentypestat r,smgsysinfo s,rtweatherdata w ";
			sql+="where r.EQUIPTYPEID=202 and w.smgid=r.smgid and r.smgid=s.id group by r.smgid order by orderitem ";
			sql+=sqlSort;
		System.out.println(sql);
		return sql;
	}
	
	String  getMonthRankSql(String dataItem,Boolean sortDesc){
		String sqlSelectItem="";
		String sqlSort="";
		
		//genWh avg_P avg_spd total_genWh capacity
		if(dataItem.equals("genWh"))
		{
			sqlSelectItem="CONCAT(truncate(sum(his.todaygenwh),2),' MWh') as value,sum(his.todaygenwh) as orderitem";
		}else if(dataItem.equals("avg_P")){
			
			sqlSelectItem="CONCAT(truncate(avg(his.curp/1000),2),' MW') as value, avg(his.curp/1000) as orderitem";
			
		}
		
		if(sortDesc)
		{
			sqlSort="desc";
		}else{
			sqlSort="asc";
		}	
		String curYear = new SimpleDateFormat("yyyy").format(new Date());
		
		String sql=	"select s.id as id,s.name as name,";
			sql+=sqlSelectItem;
			sql+=" from hisgentypestat_"+curYear+" his,smgsysinfo s ";
			sql+="where his.savetime>curdate()-interval 30 day and s.id=his.smgid group by his.smgid order by orderitem ";
			sql+=sqlSort;
		System.out.println(sql);
		if(dataItem.equals("avg_spd"))
		{
			sql="select s.id as id,s.name as name , CONCAT(truncate(avg(h.windvelval),2),'m/s') as value ,avg(h.windvelval) as orderitem" +
					"from generator g, hisweatherdata_2016 h,smgsysinfo s " +
					"where h.windvelval<=30 and h.savetime>curdate()-interval 30 day  and s.id=g.mgid and h.equipid=g.id " +
					"group by g.mgid order by  orderitem ";
			sql+=sqlSort;
		}
		return sql;
	}
	
	String  getYearRankSql(String dataItem,Boolean sortDesc){
		String sqlSelectItem="";
		String sqlSort="";
		
		//genWh avg_P avg_spd total_genWh capacity
		if(dataItem.equals("genWh"))
		{
			sqlSelectItem="CONCAT(truncate(sum(his.todaygenwh),2),' MWh') as value,sum(his.todaygenwh) as orderitem";
		}else if(dataItem.equals("avg_P")){
			
			sqlSelectItem="CONCAT(truncate(avg(his.curp/1000),2),' MW') as value,avg(his.curp/1000) as orderitem ";
			
		}
		
		if(sortDesc)
		{
			sqlSort="desc";
		}else{
			sqlSort="asc";
		}	
		String curYear = new SimpleDateFormat("yyyy").format(new Date());
		
		String sql=	"select s.id as id,s.name as name,";
			sql+=sqlSelectItem;
			sql+=" from hisgentypestat_"+curYear+" his,smgsysinfo s ";
			sql+="where his.savetime>curdate()-interval 1 year and s.id=his.smgid group by his.smgid order by orderitem ";
			sql+=sqlSort;
		System.out.println(sql);
		if(dataItem.equals("avg_spd"))
		{
			sql="select s.id as id,s.name as name , CONCAT(truncate(avg(h.windvelval),2),'m/s') as value,avg(h.windvelval) as orderitem " +
					"from generator g, hisweatherdata_2016 h,smgsysinfo s " +
					"where h.windvelval<=30 and h.savetime>curdate()-interval 1 year  and s.id=g.mgid and h.equipid=g.id " +
					"group by g.mgid order by  orderitem  ";
			sql+=sqlSort;
		}
		return sql;
	}
}
