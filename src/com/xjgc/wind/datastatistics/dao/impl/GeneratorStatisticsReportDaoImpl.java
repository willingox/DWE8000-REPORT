package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsReportDao;
import com.xjgc.wind.datastatistics.dao.impl.BayDaoImpl.BaydataRowMapper;
import com.xjgc.wind.datastatistics.dao.impl.WindAvailabilityContrastDaoImpl.WindAvailabilityContrastCountDataRowMapper;
import com.xjgc.wind.datastatistics.dao.impl.WindSpeedDaoImpl.WindSpeedCountDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class GeneratorStatisticsReportDaoImpl extends JdbcDaoSupport implements IGeneratorStatisticsReportDao{


	public List<GeneratorStatisticsReportVo> list() {
		   String sql = "select ID, NAME from bay where typeid=2 or typeid=11 order by id";
		    
				return getJdbcTemplate().query(sql, new ReportBaydataRowMapper());
				
			}
	class ReportBaydataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
			object.setId( rs.getInt("ID") );
			object.setName( rs.getString("NAME") );
			return object;
		}
	}
	//统计有功功率、风速、风机发电量、故障时间、可利用率	
	public List<GeneratorStatisticsReportVo> list(String startDateDisp,String endDateDisp,Integer smgID,int flag) {
		
		
		Date startDate=null;
		Date endDate=null;
		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd ").parse(startDateDisp);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateDisp);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//年份
		int endYear=endCalendar.get(Calendar.YEAR);//年份
		//int diff=daysDiff(startDate,endDate);
		String sql="";
	
		
			if(flag==1){
				String tablename = "hiswgmeasinfo_" + startYear;
			sql+=" select ";
			sql+=" b.name as name, "; //name
			sql+=" h.ID as id, "; //id 
			sql+=" max(h.windspeed) as max_windspeed, "; //风速
			sql+=" avg(h.windspeed) as avg_windspeed, "; //风速
			sql+=" min(h.windspeed) as min_windspeed, "; //风速 
			sql+=" max(h.ActPowGri) as max_P, "; //有功
			sql+=" avg(h.ActPowGri) as avg_P, "; //有功
			sql+=" min(h.ActPowGri) as min_P, ";  //有功
			sql+=" max(h.Reserve1)-min(h.Reserve1) as totalgenwh, ";  //发电量
			sql+=" max(h.Reserve2)-min(h.Reserve2) as winturerrsec";  //故障时间
			
			sql+=" FROM "+tablename+" h,bay b ,equipment e,generator g ";
			sql+=" WHERE   b.id=h.ID and b.id=e.bayid and e.id=g.id  ";
			sql+=" and h.savetime>='"+startDateDisp+"' and  h.savetime<'"+endDateDisp+"'  ";
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql+=" GROUP BY h.id  ";
			
		}
	
		
		if(flag==2){
			String tablename = "hiswgmeasinfo_" + endYear;
			
				
				sql+=" select ";
				sql+=" b.name as name, "; //name
				sql+=" h.ID as id, "; //id 
				sql+=" max(h.windspeed) as max_windspeed, "; //风速
				sql+=" avg(h.windspeed) as avg_windspeed, "; //风速
				sql+=" min(h.windspeed) as min_windspeed, "; //风速 
				sql+=" max(h.ActPowGri) as max_P, "; //有功
				sql+=" avg(h.ActPowGri) as avg_P, "; //有功
				sql+=" min(h.ActPowGri) as min_P, ";  //有功
				sql+=" max(h.Reserve1)-min(h.Reserve1) as totalgenwh, ";  //发电量
				sql+=" max(h.Reserve2)-min(h.Reserve2) as winturerrsec ";  //故障时间
				
				sql+=" FROM "+tablename+" h,bay b ,equipment e,generator g ";
				sql+=" WHERE   b.id=h.ID and b.id=e.bayid and e.id=g.id  ";
				sql+=" and h.savetime>='"+startDateDisp+"' and  h.savetime<'"+endDateDisp+"'  ";
				if(smgID!=0){
					sql+=" and g.mgid= "+smgID;
				}
				if(smgID==0){
					sql+=" and g.mgid=1 ";
				}
				sql+=" GROUP BY h.id  ";
					
			}
			
		
		return getJdbcTemplate().query(sql, new DataRowMapper());
	}
	
//有效风时数
public List<GeneratorStatisticsReportVo> listWind(String startDateDisp,String endDateDisp,Integer smgID,int flag) {
	
	
	Date startDate=null;
	Date endDate=null;
	Calendar startCalendar=Calendar.getInstance();
	Calendar endCalendar=Calendar.getInstance();
	try {
		startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateDisp);
		startCalendar.setTime(startDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateDisp);
		endCalendar.setTime(endDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int startYear=startCalendar.get(Calendar.YEAR);//年份
	int endYear=endCalendar.get(Calendar.YEAR);//年份
	String sql="";
	if(flag==1){
	String tablename = "hiswgimptinfo_" + startYear;
	
		
		sql +="select count(h.id)/60 as hours , h.id from " +tablename;
		sql +=" h,equipment e,generator g where  h.WindSpeed>=3 and h.WindSpeed<=20  and h.savetime>='"+startDateDisp+"' and h.savetime<='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id ";
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1";
		}
			
			sql+="	group by h.id   ";
		
	}
	
	if(flag==2){
      String tablename = "hiswgimptinfo_" + endYear;
	
		
		sql +="select count(h.id)/60 as hours , h.id from " +tablename;
		sql +=" h,equipment e,generator g where  h.WindSpeed>=3 and h.WindSpeed<=20  and h.savetime>='"+startDateDisp+"' and h.savetime<='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id ";
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1";
		}
			
			sql+="	group by  h.id ";
		}
	return getJdbcTemplate().query(sql, new WindDataRowMapper());
}

//统计运行的风机台数
public List<GeneratorStatisticsReportVo> listcount(String startDateDisp,String endDateDisp,Integer smgID,int flag) {
	Date startDate=null;
	Date endDate=null;
	Calendar startCalendar=Calendar.getInstance();
	Calendar endCalendar=Calendar.getInstance();
	try {
		startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateDisp);
		startCalendar.setTime(startDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateDisp);
		endCalendar.setTime(endDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int startYear=startCalendar.get(Calendar.YEAR);//年份
	int endYear=endCalendar.get(Calendar.YEAR);//年份
	String sql = "";
	if(flag==1){
	String tablename = "hisgenerator_" + startYear;
	   
	 
		 
		sql += "select h.ID from "+tablename+" h ,generator g where h.netstate=1 ";
		sql +=" and  h.id=g.id and h.savetime >='"+startDateDisp +" 00:00:00'  and h.savetime <='"+endDateDisp+" 23:59:59'" ;
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1 ";
		}
		sql +=" group by h.id ";
	}
	if(flag==2){
		String tablename = "hisgenerator_" + endYear;
		   
		 
		 
		sql += "select h.ID from "+tablename+" h ,generator g where h.netstate=1 ";
		sql +=" and  h.id=g.id and h.savetime >='"+startDateDisp +" 00:00:00'  and h.savetime <='"+endDateDisp+" 23:59:59'" ;
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1 ";
		}
		
		sql +=" group by h.id ";
	}
	return getJdbcTemplate().query(sql, new WindCountDataRowMapper());
	
}





class WindCountDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
		object.setId( rs.getInt("id") );
		
		return object;
	}
}
class WindDataRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
		
		object.setHours(rs.getDouble("hours"));
		object.setId(rs.getInt("id"));
		

		return object;
	}
}


	class DataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
			object.setName(rs.getString("name"));
			object.setId(rs.getInt("id"));
			
			object.setMax_windspeed(rs.getDouble("max_windspeed"));
			object.setAvg_windspeed(rs.getDouble("avg_windspeed"));
			object.setMin_windspeed(rs.getDouble("min_windspeed"));
			object.setMax_P(rs.getDouble("max_P"));
			object.setAvg_P(rs.getDouble("avg_P"));
			object.setMin_P(rs.getDouble("min_P"));
			object.setDaygenwh(rs.getDouble("TotalGenwh"));
			object.setWinTurErrSec(rs.getDouble("WinTurErrSec"));
			
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
