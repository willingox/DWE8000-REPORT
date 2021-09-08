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

import com.xjgc.wind.datastatistics.dao.IWindSpeedDao;
import com.xjgc.wind.datastatistics.dao.impl.WindAvailabilityContrastDaoImpl.WindAvailabilityContrastCountDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YearFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;

public class WindSpeedDaoImpl  extends JdbcDaoSupport implements IWindSpeedDao {
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}

	}
	//风机最大风速时刻
public List<DataStatisticsDataVo> listMaxTime(String startDateStr,String endDateStr,int id,int flag){
		
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
  	if (isDBMysql()==true) {
  	  if(flag == 1)
  	  {
  		  String tablename = "hisweatherdata_"+startYear;
  		sql = "select equipid as id ,SAVETIME as maxTime, windvelval as maxWind  "
  				+ "from "+tablename+" where windvelval=(select max(windvelval)  from "+tablename;
  		sql+=" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' ";
 		
  			sql+=" and   equipid="+id+" ) and  savetime >='"+startDateStr+"' and "
  					+ "savetime <= '"+endDateStr+"' and equipid="+id+" limit 1 " ;
  	  }
  	  if(flag == 2)
  	  {
  		  String tablename = "hisweatherdata_"+endYear;
  		sql = "select equipid as id ,SAVETIME as maxTime, windvelval as maxWind from "+tablename+" where windvelval=(select max(windvelval)  from "+tablename;
  		sql+=" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' ";
 		
  			sql+=" and   equipid="+id+" ) and savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and equipid="+id+" limit 1 " ;
  	  }
  	}
	
	  	
  	 return getJdbcTemplate().query(sql, new windMaxTimeDataRowMapper());
	}
//风机最小风速时刻
public List<DataStatisticsDataVo> listMinTime(String startDateStr,String endDateStr,int id,int flag){
	
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
	if (isDBMysql()==true) {
	  if(flag == 1)
	  {
		  String tablename = "hisweatherdata_"+startYear;
		sql = "select equipid as id ,SAVETIME as minTime , windvelval as minWind from "+tablename+" where windvelval=(select min(windvelval)  from "+tablename;
		sql+=" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' ";
		
			sql+=" and   equipid="+id+" ) and savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and equipid="+id+" limit 1 " ;
	  }
	  if(flag == 2)
	  {
		  String tablename = "hisweatherdata_"+endYear;
		  sql = "select equipid as id ,SAVETIME as minTime, windvelval as minWind  from "+tablename+" where windvelval=(select min(windvelval)  from "+tablename;
			sql+=" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' ";
			
				sql+=" and   equipid="+id+" ) and savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"' and equipid="+id+" limit 1 " ;
	  }
	}

  	
	 return getJdbcTemplate().query(sql, new windMinTimeDataRowMapper());
}
	//统计运行的风机台数
		public List<DataStatisticsDataVo> listcount(String startDateStr,String endDateStr,int flag) {
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
	  	 if (isDBMysql()==true) {
	  	   if(flag == 1){
	  		 String tablename ="hisgenerator_"+startYear;
	  		 
	  		sql += "select ID from "+tablename+" where netstate=1 ";
	  		sql +=" and savetime >='"+startDateStr+" 00:00:00'  and savetime <='"+endDateStr+" 23:59:59'";
	 		
	 		sql +=" group by id";
	  	   }
	  	 if(flag == 2){
	  		 String tablename ="hisgenerator_"+endYear;
	  		 
	  		sql += "select ID from "+tablename+" where netstate=1 ";
	  		sql +=" and savetime >='"+startDateStr+" 00:00:00'  and savetime <='"+endDateStr+" 23:59:59'";
	 		
	 		sql +=" group by id";
	  	   }
	  	   }
	  

			return getJdbcTemplate().query(sql, new WindSpeedCountDataRowMapper());
			
		}
	
		public List<DataStatisticsDataVo> listavg(String startDateStr,String endDateStr,int flag){
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
	  	 if (isDBMysql()==true) {
	  	   if(flag == 1)
	    	  {
	    		  String tablename = "hisweatherdata_"+startYear;
	    		sql = "select avg(h.windvelval) as windvelval from "+tablename;
	    		sql +=" h,generator g  where h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
	    		
	    			sql+="and h.equipid<>0 and h.equipid=g.id group by h.equipid order by h.equipid";
	    	  }
	    	if(flag == 2)
	    	  {
	    		
	    		 String tablename = "hisweatherdata_"+endYear;
	     		sql = "select avg(h.windvelval) as windvelval from "+tablename;
	     		sql +=" h,generator g  where h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
	     		
	     			sql+="and h.equipid<>0 and h.equipid=g.id group by h.equipid order by h.equipid";
	    	  }
	    	}	
	  	if (isDBMysql()==false) {
	    	  if(flag == 1)
	    	  {
	    		  String tablename = "hisweatherdata_"+startYear;
	    		sql = "select avg(h.windvelval) as windvelval from "+tablename;
	    		sql +=" h,generator g where  h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
	    		
	    			sql+="and h.equipid<>0 and h.equipid=g.id group by h.equipid ";
	    	  }
	    	  else if(flag == 2)
	    	  {
	    		
	    		  String tablename = "hisweatherdata_"+endYear;
	      		sql = "select avg(h.windvelval) as windvelval from "+tablename;
	      		sql +=" h,generator g where  h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
	      		
	      			sql+="and h.equipid<>0 and h.equipid=g.id group by h.equipid";
	    		
	    	  }
	  	}
	  	 
	  	 return getJdbcTemplate().query(sql, new windspeedavgDataRowMapper());
	  	 
		}
	
	
	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str,int flag){
		
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
		String[] arr =null;
  	  String sql = "";
  	if (isDBMysql()==true) {
  	  if(flag == 1)
  	  {
  		  String tablename = "hisweatherdata_"+startYear;
  		sql = "select h.equipid as id ,avg(h.windvelval) as windvelval,max(h.windvelval) as maxval, min(h.windvelval) as minval,g.name as name from "+tablename;
  		if(str.length()>11){
  		sql +=" h,generator g  where g.id = h.equipid and h.equipid > 0 and h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
  		
 			arr = str.split(",");
 			sql +=" and (";
 			for(int i=0;i<arr.length;i++){
 				sql +="h.equipid=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
 				if(i!=arr.length-1){
 					sql +=" or ";	
 				}
 				if(i==arr.length-1){
 					sql +=" ) ";	
 				}
 			}
 		}
  		if(str.length()<11){
  			sql +=" h,generator g ,smgsysinfo s where g.id = h.equipid and h.equipid > 0 and h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
 			sql +=" and g.mgid=s.id and ";

 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
 				
 			}
 		
  			sql+=" group by h.equipid order by h.equipid ";
  	  }
  	  if(flag == 2)
  	  {
  		  String tablename = "hisweatherdata_"+endYear;
  		sql = "select h.equipid as id ,avg(h.windvelval) as windvelval,max(h.windvelval) as maxval, min(h.windvelval) as minval,g.name as name from "+tablename;
  		if(str.length()>11){
  		sql +=" h,generator g  where g.id = h.equipid and h.equipid > 0 and h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
  		
 			arr = str.split(",");
 			sql +=" and (";
 			for(int i=0;i<arr.length;i++){
 				sql +="h.equipid=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
 				if(i!=arr.length-1){
 					sql +=" or ";	
 				}
 				if(i==arr.length-1){
 					sql +=" ) ";	
 				}
 			}
 		}
  		if(str.length()<11){
  			sql +=" h,generator g ,smgsysinfo s where g.id = h.equipid and h.equipid > 0 and h.savetime >='"+startDateStr+"' and h.savetime <= '"+endDateStr+"' " ;
 			sql +=" and g.mgid=s.id and ";

 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
 				
 			}
 		
  			sql+=" group by h.equipid order by h.equipid";
  	  }
  	}
	if (isDBMysql()==false) {
	  	  if(flag == 1)
	  	  {
	  		  String tablename = "hisweatherdata_"+startYear;
	  		sql = "select h.equipid as id ,avg(h.windvelval) as windvelval,max(h.windvelval) as maxval, min(h.windvelval) as minval,g.name as name from "+tablename;
	  		if(str.length()>11){
	  		sql +=" h,generator g  where g.id = h.equipid and h.equipid > 0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
	  		
	 			arr = str.split(",");
	 			sql +=" and (";
	 			for(int i=0;i<arr.length;i++){
	 				sql +="h.equipid=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
	 				if(i!=arr.length-1){
	 					sql +=" or ";	
	 				}
	 				if(i==arr.length-1){
	 					sql +=" ) ";	
	 				}
	 			}
	 			}
	  		if(str.length()<11){
	  			sql +=" h,generator g ,smgsysinfo s where g.id = h.equipid and h.equipid > 0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
	 			sql +=" and g.mgid=s.id and ";

	 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
	 				
	 			}
	  			sql+=" group by h.equipid,g.name ";
	  	  }
	     if(flag == 2)
	  	  {
	  		
	    	  String tablename = "hisweatherdata_"+endYear;
		  		sql = "select h.equipid as id ,avg(h.windvelval) as windvelval,max(h.windvelval) as maxval, min(h.windvelval) as minval,g.name as name from "+tablename;
		  		if(str.length()>11){
		  		sql +=" h,generator g  where g.id = h.equipid and h.equipid > 0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
		  		
		 			arr = str.split(",");
		 			sql +=" and (";
		 			for(int i=0;i<arr.length;i++){
		 				sql +="h.equipid=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
		 				if(i!=arr.length-1){
		 					sql +=" or ";	
		 				}
		 				if(i==arr.length-1){
		 					sql +=" ) ";	
		 				}
		 			}
		 			}
		  		if(str.length()<11){
		  			sql +=" h,generator g ,smgsysinfo s where g.id = h.equipid and h.equipid > 0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') " ;
		 			sql +=" and g.mgid=s.id and ";

		 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
		  			sql+=" group by h.equipid,g.name ";
	  	  }
	  	}
  	 return getJdbcTemplate().query(sql, new windspeedDataRowMapper());
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
  	if (isDBMysql()==true) {
  	  if(flag == 1)
  	  {
  		  String tablename = "hisweatherdata_"+startYear;
  		sql += "select max(windvelval) as maxWind,";
  		sql += " (select SAVETIME  from "+tablename+" where windvelval=(select max(windvelval)  from "+tablename+"  where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"') limit 1) as maxtime ";
  		sql += " from "+tablename +" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"'";
  	  }
  	  if(flag == 2)
  	  {
  		  String tablename = "hisweatherdata_"+endYear;
    		sql += "select max(windvelval) as maxWind,";
      		sql += " (select SAVETIME  from "+tablename+" where windvelval=(select max(windvelval)  from "+tablename+"  where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"') limit 1) as maxtime ";
      		sql += " from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"'";
  	  }
  	}

  	 return getJdbcTemplate().query(sql, new windMaxDataRowMapper());
	}
public List<DataStatisticsDataVo> listMin(String startDateStr,String endDateStr,int flag){
	
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
	if (isDBMysql()==true) {
	  if(flag == 1)
	  {
		  String tablename = "hisweatherdata_"+startYear;
		sql += "select min(windvelval) as minWind,";
		sql += " (select SAVETIME  from "+tablename+" where windvelval=(select min(windvelval)  from "+tablename+"  where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"') limit 1) as mintime ";
		sql += " from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"'";
	  }
	  if(flag == 2)
	  {
		  String tablename = "hisweatherdata_"+endYear;
		sql += "select min(windvelval) as minWind,";
  		sql += " (select SAVETIME  from "+tablename+" where windvelval=(select min(windvelval)  from "+tablename+"  where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"') limit 1) as mintime ";
  		sql += " from "+tablename+" where savetime >='"+startDateStr+"' and savetime <= '"+endDateStr+"'";
	  }
	}

	 return getJdbcTemplate().query(sql, new windMinDataRowMapper());
}	
class windMaxTimeDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setMaxTime(rs.getString("maxtime"));
		object.setId(rs.getInt("id"));
		object.setMaxWind(rs.getDouble("MaxWind"));
		return object;
	}
}	
class windMinTimeDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setMinTime(rs.getString("mintime"));
		object.setId(rs.getInt("id"));
		object.setMinWind(rs.getDouble("MinWind"));
		return object;
	}
}
class windMinDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setMinTime(rs.getString("mintime"));
		object.setMinWind(rs.getDouble("minWind"));
		return object;
	}
}
class windMaxDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setMaxTime(rs.getString("Maxtime"));
		object.setMaxWind(rs.getDouble("MaxWind"));
		return object;
	}
}


class windspeedDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));
			object.setId( rs.getInt("id") );
			object.setAvgWindVelval(rs.getDouble("windvelval"));
			object.setMaxData(rs.getDouble("maxval"));
			object.setMinData(rs.getDouble("minval"));
			return object;
		}
	}
	class windspeedavgDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setAvgWindVelval(rs.getDouble("windvelval"));
		
			return object;
		}
	}
	class WindSpeedCountDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("id") );
			
			return object;
		}
	}

}
