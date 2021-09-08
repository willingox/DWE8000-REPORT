package com.xjgc.wind.datastatistics.dao.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.ISmgYearInfoDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgYearInfoDataForm;

public class SmgYearInfoDaoImpl extends JdbcDaoSupport implements ISmgYearInfoDao{

	public List<DataStatisticsDataVo> listtime(SmgYearInfoDataForm queryFilter){
		
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//选择时间对应年份
		int startYear=startCalendar.get(Calendar.YEAR);
		
		String tableName ="hisgeneratorst_"+startYear;
		String sql = "";
		
		if( isDBMysql() ){
			sql +="select sum(todaygenwh),savetime from "+tableName+" where DATE_FORMAT(savetime ,'%Y') ='"+startDateStr;
		 	sql +="' group by DATE_FORMAT(savetime ,'%Y-%m')";
		}else{
			sql +="select sum(todaygenwh),TO_CHAR(savetime,'yyyy-mm') as savetime from "+tableName+" where TO_CHAR(savetime,'yyyy')='"+startDateStr;
		 	sql +="' group by TO_CHAR(savetime,'yyyy-mm')";
		}
 	   
 	  return getJdbcTemplate().query(sql, new timegenDataRowMapper());
	}
	
	public List<DataStatisticsDataVo> listname(SmgYearInfoDataForm queryFilter){
		
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//选择时间对应年份
		int startYear=startCalendar.get(Calendar.YEAR);
		
		String tableName ="hisgeneratorst_"+startYear;
		String sql = "";
		
		if( isDBMysql() ){
			
			sql +="select sum(h.todaygenwh) as todaygenwh,g.name as name from "+tableName+" h,generator g where DATE_FORMAT(h.savetime ,'%Y') ='"+startDateStr;
		 	sql +="' and h.id = g.id group by h.id ";
		}
		else{
			sql +="select sum(h.todaygenwh) as todaygenwh,g.name as name from "+tableName+" h,generator g where TO_CHAR(h.savetime,'yyyy') ='"+startDateStr;
		 	sql +="' and h.id = g.id group by h.id ,g.name";
		}
		
 	   
 	  return getJdbcTemplate().query(sql, new namegenDataRowMapper());
	}
	
	class timegenDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setTodayGenwh(rs.getDouble("sum(todaygenwh)"));
			//object.setSaveTime(rs.getTimestamp("savetime"));
			String str=rs.getString("savetime");
			
			try {
				object.setSaveTime(new SimpleDateFormat("yyyy-MM").parse(str));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return object;
		}
	}
	
	class namegenDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setTodayGenwh(rs.getDouble("todaygenwh"));
			object.setName(rs.getString("name"));
			return object;
		}
	}
	
	
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}
	}
}
