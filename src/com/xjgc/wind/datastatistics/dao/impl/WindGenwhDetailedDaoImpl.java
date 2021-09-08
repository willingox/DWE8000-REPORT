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

import com.xjgc.wind.datastatistics.dao.IOperatRecordDao;
import com.xjgc.wind.datastatistics.dao.IWindGenwhDetailedDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.OperatRecordDataForm;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDetailedDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class WindGenwhDetailedDaoImpl extends JdbcDaoSupport implements IWindGenwhDetailedDao{
public List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,int flag,int id){
		
		Date startDate = null;
 		Date endDate = null;
 		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
  	  String sql = "";
  	  if(flag == 1)
  	  {
  		  String tablename = "hiswgimptinfo_"+startYear;
  		sql += "select max(ActPowGri) as maxCurp, b.name as name,";
  		sql += " (select SAVETIME  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and id="+id+") and savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' limit 1) as maxtime,(select WindSpeed  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and id="+id+") limit 1) as maxWind";
  		sql += " from "+tablename+" h ,bay b where h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' and h.id=b.id and  h.id="+id;
  	  }
  	  if(flag == 2)
  	  {
  		  String tablename = "hiswgimptinfo_"+endYear;
  		sql += "select max(ActPowGri) as maxCurp, b.name as name,";
  		sql += " (select SAVETIME  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and id="+id+") and savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' limit 1) as maxtime,(select WindSpeed  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and id="+id+") limit 1) as maxWind";
  		sql += " from "+tablename+" h ,bay b where h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' and h.id=b.id and  h.id="+id;
  	  }
  	

  	 return getJdbcTemplate().query(sql, new WindGenwhDataRowMapper());
	}
public List<DataStatisticsDataVo> listMax(String startDateStr,String endDateStr,int flag){
		
		Date startDate = null;
 		Date endDate = null;
 		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
  	  String sql = "";
  	  if(flag == 1)
  	  {
  		  String tablename = "hiswgimptinfo_"+startYear;
  		sql += "select  h.savetime as maxtime,h.ActPowGri as maxCurp,h.WindSpeed as maxWind from (select savetime,sum(ActPowGri) as ActPowGri,max(WindSpeed) as WindSpeed  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' GROUP BY savetime) h";
  		sql += " where h.ActPowGri=(select  max(h.ActPowGri) from (select savetime,sum(ActPowGri) as ActPowGri  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' GROUP BY savetime) h)";
  		/*sql += "select max(ActPowGri) as maxCurp,";
  		sql += " (select SAVETIME  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"') limit 1) as maxtime,(select WindSpeed  from "+tablename+" where ActPowGri=(select max(ActPowGri)  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' ) limit 1) as maxWind";
  		sql += " from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"'";*/
  	  }
  	  if(flag == 2)
  	  {
  		  String tablename = "hiswgimptinfo_"+endYear;
  		sql += "select  h.savetime as maxtime,h.ActPowGri as maxCurp,h.WindSpeed as maxWind from (select savetime,sum(ActPowGri) as ActPowGri,max(WindSpeed) as WindSpeed  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' GROUP BY savetime) h";
  		sql += " where h.ActPowGri=(select  max(h.ActPowGri) from (select savetime,sum(ActPowGri) as ActPowGri  from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' GROUP BY savetime) h)";
  	  }
  	

  	 return getJdbcTemplate().query(sql, new WindGenwhMaxDataRowMapper());
	}

	public List<DataStatisticsDataVo> listTable(String maxtime) {
		   
		Date startDate = null;
 		Date endDate = null;
 		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(maxtime);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
  	  String sql = "";
  	  
  		  String tablename = "hiswgimptinfo_"+startYear;
  		sql += "select b.name as name,h.ActPowGri as curp,h.SAVETIME as SAVETIME,";
  		sql += "  h.WindSpeed as WindSpeed ";
  		sql += " from "+tablename+" h,bay b";
  		sql += " where h.id=b.id and h.SAVETIME= '"+maxtime+"'";
  	 

  	  	 return getJdbcTemplate().query(sql, new WindGenwhTableDataRowMapper());
  		}

	
	class WindGenwhMaxDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setMaxTime(rs.getString("Maxtime"));
			object.setMaxWind(rs.getDouble("MaxWind"));
			object.setMaxCurp(rs.getDouble("maxCurp"));
			return object;
		}
	}
	class WindGenwhDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));
			object.setMaxTime(rs.getString("Maxtime"));
			object.setMaxWind(rs.getDouble("MaxWind"));
			object.setMaxCurp(rs.getDouble("maxCurp"));
			return object;
		}
	}
	class WindGenwhTableDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setSaveTime(rs.getTimestamp("saveTime"));
			object.setCurp(rs.getDouble("curp"));
			object.setWindSpeed(rs.getString("WindSpeed"));
			object.setName(rs.getString("name"));
			return object;
		}
	}
	
	
}
