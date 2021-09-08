package com.xjgc.wind.datastatistics.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.ISmgMonthInfoDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.SmgMonthInfoDataForm;

public class SmgMonthInfoDaoImpl extends JdbcDaoSupport implements ISmgMonthInfoDao{
	
	public List<DataStatisticsDataVo> listtimegen(SmgMonthInfoDataForm queryFilter){
		
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM").parse(startDateStr);
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
			sql += "select sum(h.todaygenwh) as todaygenwh,h.savetime as savetime from "+tableName+" h";
			sql += " where  DATE_FORMAT(h.savetime,'%Y-%m')= '"+startDateStr+"' ";
			sql +=" group by DATE_FORMAT(h.savetime,'%Y-%m-%d') order by h.savetime ";
		}else{
			sql += "select sum(h.todaygenwh) as todaygenwh,TO_CHAR(h.savetime,'yyyy-mm-dd') as savetime from "+tableName+" h";
			sql += " where TO_CHAR(h.savetime,'yyyy-mm')= '"+startDateStr+"' ";
			sql +=" group by TO_CHAR(h.savetime,'yyyy-mm-dd')  ";
		}
		
		return getJdbcTemplate().query(sql, new timegenDataRowMapper());
	}
	
	public List<DataStatisticsDataVo> listnamegen(SmgMonthInfoDataForm queryFilter){
		
		
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//选择时间对应年份
		int startYear=startCalendar.get(Calendar.YEAR);
		
		String tableName ="hisgeneratorst_"+startYear;
		String sql = "";
		
		if(isDBMysql()){
			sql += "select sum(h.todaygenwh) as todaygenwh,g.name as name from "+tableName+" h,generator g";
			sql += " where h.id = g.id and DATE_FORMAT(h.savetime,'%Y-%m')= '"+startDateStr+"' ";
			sql +=" group by h.id ";
		}
		else{
			sql += "select sum(h.todaygenwh) as todaygenwh,g.name as name from "+tableName+" h,generator g";
			sql += " where h.id = g.id and TO_CHAR(h.savetime,'yyyy-mm')= '"+startDateStr+"' ";
			sql +=" group by h.id ,g.name";
		}
		
		return getJdbcTemplate().query(sql, new namegenDataRowMapper());
	}

	class timegenDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setTodayGenwh(rs.getDouble("todaygenwh"));
			//object.setSaveTime(rs.getTimestamp("savetime"));
			object.setSaveTime(rs.getDate("savetime"));
		
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
