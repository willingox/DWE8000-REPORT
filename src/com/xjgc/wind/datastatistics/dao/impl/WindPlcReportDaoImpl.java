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




import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.dao.IWindPlcReportDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class WindPlcReportDaoImpl extends JdbcDaoSupport implements IWindPlcReportDao {


	
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,int flag) {
		Date startDate = null;
 		Date endDate = null;
 		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateDisp);
			endDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateDisp);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);
		int endYear=endCalendar.get(Calendar.YEAR);
		  String sql = "";
		  	String[] arr =null;
		  	
		  	  if(flag == 1){
		  	   	String tablename ="hisstatus_"+startYear;
		  	   	sql +="select g.name as name,st.description as plcState,h.LASTVALTIME as curTime, h.CURDATATIME as endTime  from "+tablename;
		  	  if(str.length()>11){
		  	   	sql +=" h,generator g,status s,stvaltype st where  h.LASTVALTIME >='"+startDateDisp+"' and h.LASTVALTIME <='"+endDateDisp+"' and h.STATUSID = g.standbystid and h.STATUSID=s.id and s.meastypeid=st.meastypeid and h.lastvalue=st.value";
		  	  arr = str.split(",");
				sql +=" and (";
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
		  		sql +=" h,generator g,status s,stvaltype st  ,smgsysinfo s where  h.savetime >='"+startDateDisp+"' and h.savetime <='"+endDateDisp+"' and h.STATUSID = g.standbystid and h.STATUSID=s.id and s.meastypeid=st.meastypeid and h.lastvalue=st.value ";
			  	sql +="  and g.mgid=s.id  and ";
		        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
						
					}
				sql +=" order by g.id";
		  	   	}
		  	if(flag == 2){
		  	   	String tablename ="hisstatus_"+endYear;
		  	   	sql +="select g.name as name,st.description as plcState,h.LASTVALTIME as curTime, h.CURDATATIME as endTime  from "+tablename;
		  	  if(str.length()>11){
		  	   	sql +=" h,generator g,status s,stvaltype st where  h.LASTVALTIME >='"+startDateDisp+"' and h.LASTVALTIME <='"+endDateDisp+"' and h.STATUSID = g.standbystid and h.STATUSID=s.id and s.meastypeid=st.meastypeid and h.lastvalue=st.value";
		  	  arr = str.split(",");
				sql +=" and (";
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
		  		sql +=" h,generator g,status s,stvaltype st  ,smgsysinfo s where  h.savetime >='"+startDateDisp+"' and h.savetime <='"+endDateDisp+"' and h.STATUSID = g.standbystid and h.STATUSID=s.id and s.meastypeid=st.meastypeid and h.lastvalue=st.value ";
			  	sql +="  and g.mgid=s.id  and ";
		        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
						
					}
				sql +=" order by g.id";
		  	   	}
		  	  
		return getJdbcTemplate().query(sql, new WindPlcReportdataRowMapper());
		
	}
	

	class WindPlcReportdataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setCurTime( rs.getTimestamp("curTime"));
			object.setEndTime( rs.getTimestamp("endTime"));
			
			object.setPlcState(rs.getString("plcState"));
			object.setName( rs.getString("name") );
			return object;
		}
	}
	
	
}



	
	
