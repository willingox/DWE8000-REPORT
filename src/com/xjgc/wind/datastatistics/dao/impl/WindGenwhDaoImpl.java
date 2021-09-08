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





import com.xjgc.wind.datastatistics.dao.IWindGenwhDao;
import com.xjgc.wind.datastatistics.dao.impl.FaultQueryDaoImpl.faultqueryDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindGenwhDataForm;
import com.xjgc.wind.util.YearFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;

public class WindGenwhDaoImpl extends JdbcDaoSupport implements IWindGenwhDao{
	
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}

	}
	//发电时间
		public List<DataStatisticsDataVo> listTime(String startDateStr,String endDateStr,int flag) {

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
		
			String sql = " ";
			
				if (flag == 1) {
					String tablename = "hiswgimptinfo_" + startYear;
					sql += "select e.id, count(*)/60 as hour";
					
					sql +=" from generator g,equipment e,"+tablename;
					sql +=" h where g.id=e.id and e.bayid=h.id and  h.savetime>='"+startDateStr+ "' and h.savetime <='"+endDateStr+"' ";
					
			  			  sql += " and  h.ActPowGri > 0";
			  		sql += "  group by e.id    ";
					
				} 
				 else if(flag == 2)
			  	  {
		
			  		String tablename = "hiswgimptinfo_" + endYear;
                    sql += "select e.id, count(*)/60 as hour";
					
					sql +=" from generator g,equipment e,"+tablename;
					sql +=" h where g.id=e.id and e.bayid=h.id and  h.savetime>='"+startDateStr+ "' and h.savetime <='"+endDateStr+"' ";
					
			  			  sql += " and  h.ActPowGri > 0";
			  		sql += "  group by e.id    ";
			  	  }
			  	  
			
			return getJdbcTemplate().query(sql, new WindGenwh4DataRowMapper());

		}

		
		
		
		class WindGenwh4DataRowMapper implements RowMapper {
			public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
				DataStatisticsDataVo object = new DataStatisticsDataVo();
				
				object.setId(rs.getInt("id"));
				object.setHour(rs.getDouble("hour"));
				return object;
			}
		}

	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,int flag) {
	
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
  	  if(isDBMysql()==true){
  	  if(flag == 1)
  	  {
  		  String tablename = "hisgenerator_"+startYear;
  		sql = "select g.id as id,g.id as strId,g.name as name,(max(h.totalgenwh)-min(h.totalgenwh)) as totalgenwh from "+tablename;
  		sql +=" h,generator g where  g.id = h.id  and h.totalgenwh>0 and h.savetime >= '"+startDateStr+"' and h.savetime <= '"+endDateStr+"' group by g.id";
  	  }
  	  else if(flag == 2)
  	  {
  		
  		  String tablename = "hisgenerator_"+endYear;
    		sql = "select g.id as id,g.id as strId,g.name as name,(max(h.totalgenwh)-min(h.totalgenwh)) as totalgenwh from "+tablename;
    		sql +=" h,generator g where  g.id = h.id and h.totalgenwh>0 and h.savetime >= '"+startDateStr+"' and h.savetime <= '"+endDateStr+"' group by g.id";
  	  }
  	  }
  	 if(isDBMysql()==false){
  	  	  if(flag == 1)
  	  	  {
  	  		  String tablename = "hisgenerator_"+startYear;
  	  		sql = "select g.id as id,g.id as strId,g.name as name,(max(h.totalgenwh)-min(h.totalgenwh)) as totalgenwh from "+tablename;
  	  		sql +=" h,generator g where g.id = h.id and h.totalgenwh>0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') group by g.id,g.name order by g.id";
  	  	  }
  	  	  else if(flag == 2)
  	  	  {
  	  		
  	  		 String tablename = "hisgenerator_"+endYear;
   	  		sql = "select g.id as id,g.id as strId,g.name as name,(max(h.totalgenwh)-min(h.totalgenwh)) as totalgenwh from "+tablename;
   	  		sql +=" h,generator g where g.id = h.id and h.totalgenwh>0 and h.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss') group by g.id,g.name order by g.id";
  	  		  
  	  		  
  	  		
  	  	  }
  	  	  }
  	  return getJdbcTemplate().query(sql, new windgenwhDataRowMapper());
				
	}
	
	public List<DataStatisticsDataVo> listd(WindGenwhDataForm queryFilter){
		String sql = "select totalgenwhid from generator limit 0,1";
		  return getJdbcTemplate().query(sql, new winddDataRowMapper());
	}
	
	public List<DataStatisticsDataVo> listu(String name,char id){
		
		String sql = "";
		if(name.compareTo("YM") == 0)
			sql += "select u.name as name from Accumulator a,MeasUnit u where a.id = "+id+" and a.MeasUnitID=u.id";
		else 
			sql += "select u.name as name from analog a,MeasUnit u where a.id = "+id+" and a.MeasUnitID=u.id";

		  return getJdbcTemplate().query(sql, new winduDataRowMapper());
	}
	//风机细节展示
public List<DataStatisticsDataVo> listDetailed(String equipId,String startDateDisp,String endDateDisp,int flag) {
		
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
	
  	  if(isDBMysql()==true){
  	  if(flag == 1)
  	  {
  		  String tablename1 = "hisgenerator_"+startYear;
  		  String tablename2 = "hisweatherdata_"+startYear;
  		sql = "select g.name as name,h1.todaygenwh as todaygenwh ,h1.savetime as savetime, h2.windvelval as avgWindVelval from "+tablename1;
  		sql +=" h1,"+tablename2+" h2 ,generator g where  g.id = h1.id  and h1.id = h2.equipid  and h1.savetime=h2.savetime and h1.savetime >= '"+startDateDisp+"' and h1.savetime <= '"+endDateDisp+"' ";
  		if(equipId!=null){
			sql+=" and g.id= "+equipId;
		}
  		sql +=" group by h1.savetime";
  	  }
  	  if(flag == 2)
  	  {
  		
  		 String tablename1 = "hisgenerator_"+endYear;
 		  String tablename2 = "hisweatherdata_"+endYear;
 		sql = "select g.name as name,h1.todaygenwh as todaygenwh ,h1.savetime as savetime, h2.windvelval as avgWindVelval from "+tablename1;
 		sql +=" h1,"+tablename2+" h2 ,generator g where  g.id = h1.id  and h1.id = h2.equipid  and h1.savetime=h2.savetime and h1.savetime >= '"+startDateDisp+"' and h1.savetime <= '"+endDateDisp+"' ";
 		if(equipId!=null){
			sql+=" and g.id= "+equipId;
		}
 		sql +=" group by h1.savetime";
  	  }
  	  }
  	 
  	  	 
  	  return getJdbcTemplate().query(sql, new windgenwhDetailedDataRowMapper());
				
	}
	
class windgenwhDetailedDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();	
		object.setName( rs.getString("name") );
		object.setSaveTime(rs.getTimestamp("saveTime"));
		object.setTodayGenwh(rs.getDouble("todaygenwh"));
		object.setAvgWindVelval(rs.getDouble("avgWindVelval"));
		return object;
	}
}

	class windgenwhDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("id") );		
			object.setName( rs.getString("name") );
			object.setStrId( rs.getString("strId") );
			object.setTotalGenwh(rs.getDouble("totalgenwh"));
			return object;
		}
	}
	
	class winddDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName1(rs.getString("totalgenwhid"));
			return object;
		}
	}
	class winduDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName2(rs.getString("name"));
			return object;
		}
	}


	

}
