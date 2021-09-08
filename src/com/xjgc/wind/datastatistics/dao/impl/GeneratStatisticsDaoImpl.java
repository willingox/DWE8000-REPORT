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
import java.util.Properties;





import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IGeneratStatisticsDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.GeneratStatisticsDataForm;


import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YearFormatUtil;
import com.xjgc.wind.util.YearMonthFormatUtil;


public class GeneratStatisticsDaoImpl extends JdbcDaoSupport implements IGeneratStatisticsDao {
	
	
	public List<DataStatisticsDataVo> listpw(GeneratStatisticsDataForm queryFilter) {
   
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//选择时间对应年份
		int startYear=startCalendar.get(Calendar.YEAR);
		
		String tableHisGenerator ="hisgenerator_"+startYear;
		String tableHisWeather ="hisweatherdata_"+startYear;
		String sql = "";
		if(isDBMysql()){
			sql +="select h.savetime as savetime, sum(h.curp) as curp,(select max(windvelval) from ";
			sql += tableHisWeather +" where savetime = h.savetime  ) as windvelval from ";
	    	sql += tableHisGenerator+" h where  DATE_FORMAT(h.savetime,'%Y-%m-%d' )=DATE_FORMAT('"+startDateStr+"','%Y-%m-%d' )";
	    	sql +=" group by h.savetime order by h.savetime";
		}else{
			sql +="select h.savetime as savetime, sum(h.curp) as curp,(select max(windvelval) from ";
			sql += tableHisWeather +" where savetime = h.savetime  ) as windvelval from ";
	    	sql += tableHisGenerator+" h where TO_CHAR(h.savetime,'yyyy-mm-dd')='"+startDateStr+"'" ;
	    	sql +=" group by h.savetime order by h.savetime";
		}
		return getJdbcTemplate().query(sql, new pwDataRowMapper());
	}
	
	public List<DataStatisticsDataVo> listsunlight(GeneratStatisticsDataForm queryFilter) {
	    
		String date1 = queryFilter.getStartDateDisp();

 		Date startDate = null;
 		if (StringUtils.isNotBlank(date1)) 
 			{
 			try {
 				startDate = DateFormatUtil.get().parse(date1);
 			} catch (ParseException e) {
 				startDate = null;
 				e.printStackTrace();
 			}
 		}
 	
 		String date11 = date1;
 	    Calendar   calendar   =   new   GregorianCalendar(); 
 	    calendar.setTime(startDate); 
 	    date1 = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
 	    date11 = new SimpleDateFormat("yyyy").format(calendar.getTime());
 	    calendar.add(Calendar.DATE, 1);
 	    String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
 	    Properties p = new Properties();
 	    try{
 	    	String path = this.getClass().getResource("/").getPath();
 	    	path=path.substring(1, path.indexOf("classes"));
 	    	p.load(new FileInputStream(path+"db.properties")); 
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }    
 	   String driver = p.getProperty("db.driver"); 
 	   String sql = "";
 	   if(driver.equals("oracle.jdbc.driver.OracleDriver")){
 	   }
 	   else if(driver.equals("com.mysql.jdbc.Driver")){
 		   String tablename ="hisweatherdata_"+date11;
 		   sql +="select savetime, windvelval from "+tablename+" where DATE_FORMAT(savetime,'%Y-%m-%d' )>='";
 		   sql +=date1 + "' and DATE_FORMAT(savetime,'%Y-%m-%d' )<'"+date+"' and equipid = 0";
 	   }
		return getJdbcTemplate().query(sql, new sunDataRowMapper());
	}
	
	
	public List<DataStatisticsDataVo> listgenwh(GeneratStatisticsDataForm queryFilter) {
		//选择时间
		String startDateStr=queryFilter.getStartDateDisp();
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//选择时间对应年份
		int startYear=startCalendar.get(Calendar.YEAR);
		
		String tableHisGeneratorst ="hisgeneratorst_"+startYear;
		String sql = "";
		if(isDBMysql()){
	    	sql +=" select h.todaygenwh as todaygenwh,g.name as name from "+tableHisGeneratorst+" h,generator g where h.id = g.id ";
	    	sql +=" and DATE_FORMAT(h.savetime,'%Y-%m-%d')='"+startDateStr+"'";
		}else{
			sql +=" select h.todaygenwh as todaygenwh,g.name as name from "+tableHisGeneratorst+" h,generator g where h.id = g.id ";
	    	sql +=" and TO_CHAR(h.savetime,'yyyy-mm-dd')='"+startDateStr+"'";
		}
		
		return getJdbcTemplate().query(sql, new genwhDataRowMapper());
	}
	
	class pwDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setSaveTime(rs.getTimestamp("savetime"));
			object.setCurp( rs.getDouble("curp") );
			object.setWindVelval(rs.getString("windvelval"));
			object.setSunLightVal(rs.getDouble("windvelval"));
			return object;
		}
	}
	
	class genwhDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));	
			object.setTodayGenwh( rs.getDouble("todaygenwh") );
			return object;
		}
	}
	
	class sunDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setSaveTime(rs.getTimestamp("savetime"));
			object.setSunLightVal( rs.getDouble("windvelval") );
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