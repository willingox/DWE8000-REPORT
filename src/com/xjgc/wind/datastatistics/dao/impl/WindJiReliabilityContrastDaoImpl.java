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

import com.xjgc.wind.datastatistics.dao.IWindJiReliabilityContrastDao;
import com.xjgc.wind.datastatistics.dao.impl.GeneratorDaoImpl.GendataRowMapper;
import com.xjgc.wind.datastatistics.dao.impl.GeneratorStatisticsDayReportDaoImpl.WindDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindJiReliabilityContrastDataForm; 
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindJiReliabilityContrastDaoImpl extends JdbcDaoSupport implements IWindJiReliabilityContrastDao {

	public List<DataStatisticsDataVo> list(String str) {
		String[] arr =null;
		   String sql = "select g.ID as id,g.name as name from generator g ";
		   if(str.length()>11){
			 arr = str.split(",");
		 
		   sql +=" where (";
			for(int i=0;i<arr.length;i++){
				sql +="g.id=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
				if(i!=arr.length-1){
					sql +=" or ";	
				}
				if(i==arr.length-1){
					sql +=" ) ";	
				}
			  }
			}
		   if(str.length()<11){
		  		sql +="   ,smgsysinfo s where  ";
			  	sql +="   g.mgid=s.id  and ";
		        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
						
					}
				sql +=" group by g.id";
				return getJdbcTemplate().query(sql, new ReliabilityGenSelectdataRowMapper());
				
			}
	class ReliabilityGenSelectdataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("ID") );
			object.setName( rs.getString("name") );
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
	
	public List<DataStatisticsDataVo> listrandhour(String startDateStr,String endDateStr,String str,int flag) {
	    
		
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
  	String[] arr =null;
  	
  	  if(flag == 1){
  	   	String tablename ="hisgenerator_"+startYear;
  	   	sql +="select h.id as id,((max(h.totalgenwh)-min(h.totalgenwh))*1000)/g.capacity as hour,g.name as name,max(h.totalgenwh)-min(h.totalgenwh) as genwh, g.capacity as capacity from "+tablename;
  	  if(str.length()>11){
  	   	sql +=" h,generator g where  h.savetime >='"+startDateStr+"' and h.savetime <='"+endDateStr+"' and h.id = g.id and h.totalgenwh>0";
  	  arr = str.split(",");
		sql +=" and (";
		for(int i=0;i<arr.length;i++){
			sql +="h.id=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
			if(i!=arr.length-1){
				sql +=" or ";	
			}
			if(i==arr.length-1){
				sql +=" ) ";	
			}
		}
		}
  	 if(str.length()<11){
  		sql +=" h,generator g ,smgsysinfo s where  h.savetime >='"+startDateStr+"' and h.savetime <='"+endDateStr+"' and h.id = g.id and h.totalgenwh>0";
	  	sql +="  and g.mgid=s.id  and ";
        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
				
			}
		sql +=" group by h.id";
  	   	}
  	if(flag == 2){
  	   	String tablename ="hisgenerator_"+endYear;
  	 	sql +="select h.id as id,((max(h.totalgenwh)-min(h.totalgenwh))*1000)/g.capacity as hour,g.name as name,max(h.totalgenwh)-min(h.totalgenwh) as genwh, g.capacity as capacity from "+tablename;
    	  if(str.length()>11){
    	   	sql +=" h,generator g where  h.savetime >='"+startDateStr+"' and h.savetime <='"+endDateStr+"' and h.id = g.id and h.totalgenwh>0";
    	  arr = str.split(",");
  		sql +=" and (";
  		for(int i=0;i<arr.length;i++){
  			sql +="h.id=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
  			if(i!=arr.length-1){
  				sql +=" or ";	
  			}
  			if(i==arr.length-1){
  				sql +=" ) ";	
  			}
  		}
  		}
    	 if(str.length()<11){
    		sql +=" h,generator g ,smgsysinfo s where  h.savetime >='"+startDateStr+"' and h.savetime <='"+endDateStr+"' and h.id = g.id and h.totalgenwh>0";
  	  	sql +="  and g.mgid=s.id  and ";
          sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
  				
  			}
  		sql +=" group by h.id";
  	   	
  	   	}
  	  
  	 
		return getJdbcTemplate().query(sql, new WindJiReliabilityContrastDataRowMapper());
		
	}
	//有效风时数
	public List<DataStatisticsDataVo> listWind(String startDateStr,String endDateStr,String str,int flag) {
		
		
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
		String sql="";
		String[] arr =null;
		
		if(flag == 1){
		String tablename = "hiswgimptinfo_" + startYear;	
		sql +="select count(h.id)/60 as windHours , e.id as id from " +tablename;
		if(str.length()>11){
		sql +=" h,equipment e,generator g where g.id=e.id and h.WindSpeed>=3 and h.WindSpeed<=20  and e.bayid=h.id and h.savetime>='"+startDateStr+"' and h.savetime<='"+endDateStr+"'  ";
		arr = str.split(",");
			sql +=" and (";
			for(int i=0;i<arr.length;i++){
				sql +="e.id=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
				if(i!=arr.length-1){
					sql +=" or ";	
				}
				if(i==arr.length-1){
					sql +=" ) ";	
				}
			}
			}
         if(str.length()<11){
		 	  		sql +=" h,equipment e,generator g ,smgsysinfo s where  h.WindSpeed>=3 and h.WindSpeed<=20  and e.bayid=h.id and h.savetime>='"+startDateStr+"' and h.savetime<='"+endDateStr+"'  ";
		 			sql +=" and e.bayid=h.id and e.id=g.id and g.mgid=s.id  and ";

		 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
		 		sql +=" group by h.id";
		}
		
		if(flag == 2){
			String tablename = "hiswgimptinfo_" + endYear;	
			sql +="select count(h.id)/60 as windHours , e.id as id from " +tablename;
			if(str.length()>11){
			sql +=" h,equipment e,generator g where g.id=e.id and h.WindSpeed>=3 and h.WindSpeed<=20  and e.bayid=h.id and h.savetime>='"+startDateStr+"' and h.savetime<='"+endDateStr+"'  ";
			arr = str.split(",");
				sql +=" and (";
				for(int i=0;i<arr.length;i++){
					sql +="e.id=" +Integer.parseInt(arr[i].substring(arr[i].lastIndexOf("-")+1,arr[i].length()));
					if(i!=arr.length-1){
						sql +=" or ";	
					}
					if(i==arr.length-1){
						sql +=" ) ";	
					}
				}
				}
	         if(str.length()<11){
			 	  		sql +=" h,equipment e,generator g ,smgsysinfo s where  h.WindSpeed>=3 and h.WindSpeed<=20  and e.bayid=h.id and h.savetime>='"+startDateStr+"' and h.savetime<='"+endDateStr+"'  ";
			 			sql +=" and e.bayid=h.id and e.id=g.id and g.mgid=s.id  and ";

			 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}
			 		sql +=" group by h.id";
		
			}
		
		return getJdbcTemplate().query(sql, new WindJiReliabilityContrastWindHourDataRowMapper());
	}
	class WindJiReliabilityContrastWindHourDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			
			object.setWindHours(rs.getDouble("windHours"));
			object.setId(rs.getInt("id"));
			

			return object;
		}
	}
	
	class WindJiReliabilityContrastDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId(rs.getInt("id"));
			object.setName( rs.getString("name") );
			object.setHour( rs.getDouble("hour") );
			object.setTotalGenwh(rs.getDouble("genwh"));
			object.setCapacity(rs.getDouble("capacity"));
			return object;
		}
	}
	
}



	
	
