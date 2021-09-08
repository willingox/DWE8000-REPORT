package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;







import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;





import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneMinuteDao;
import com.xjgc.wind.datastatistics.dao.IRuningInfo_OneMinuteReportDao;
import com.xjgc.wind.datastatistics.dao.impl.RuningInfo_SelectOneDayDaoImpl.RuningInfo_SelectOneDayDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;



public class RuningInfo_OneMinuteReportDaoImpl extends JdbcDaoSupport implements IRuningInfo_OneMinuteReportDao{
	
	int len=0;
	public List<DataStatisticsDataVo> listVal() {
		   String sql = "select columndes ,columnname  from bussclmncfg  where webshowflag = 1 "; 
	    	
				sql += " and busstblcfgid=1";
			
	return getJdbcTemplate().query(sql, new RuningInfo_SelectOneMinuteReportDataRowMapper());
	
}
	class RuningInfo_SelectOneMinuteReportDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setColumndes( rs.getString("columnname") );
			object.setColumnName( rs.getString("columndes") );
			return object;
		}
	}
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,String str,String[]  check_value,int flag,int yearIf,int pageNo,int pageSize){
		len=0;
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
		int startYear=startCalendar.get(Calendar.YEAR);//寮�骞翠唤
		int endYear=endCalendar.get(Calendar.YEAR);//缁撴潫骞翠唤
		len = check_value.length;
		String[] arr =null;
		String sql = "";
	
			if (yearIf==1) {
			sql += "select b.name as name, h.savetime as savetime ";
			if(len != 0)
			{
			 for(int i=0;i<len;i++){
					sql += ",h."+check_value[i]+" as cal"+i;
				 }
			 }
			
			if(str.length()>11){
				sql += " from hiswgimptinfo_"+startYear+" h,bay b where h.id=b.id and h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
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
				sql += " from hiswgimptinfo_"+startYear+" h,bay b,equipment e,generator g,smgsysinfo sm where h.id=b.id and h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
				sql +=" and b.id=e.bayid and e.id=g.id and g.mgid=sm.id and ";

 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			}
				if(flag==2){
					sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='10:00' or right(h.savetime,5)='20:00' or right(h.savetime,5)='30:00' or right(h.savetime,5)='40:00' or right(h.savetime,5)='50:00')";	
				}
				if(flag==3){
					sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='15:00' or right(h.savetime,5)='30:00' or right(h.savetime,5)='45:00') ";	
				}
				if(flag==4){
					sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='30:00') ";	
				}
				if(flag==5){
					sql += " and (right(h.savetime,5)='00:00' ) ";	
				}
				int a=(pageNo-1)*pageSize+1;
				int b=pageNo*pageSize;
				sql += " order by h.id ";	
		}
			if (yearIf==2) {
				sql += "select b.name as name, h.savetime as savetime ";
				if(len != 0)
				{
				 for(int i=0;i<len;i++){
						sql += ",h."+check_value[i]+" as cal"+i;
					 }
				 }
				
				if(str.length()>11){
					sql += " from hiswgimptinfo_"+startYear+" h,bay b where h.id=b.id and h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
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
					sql += " from hiswgimptinfo_"+endYear+" h,bay b,equipment e,generator g,smgsysinfo sm where h.id=b.id and h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
					sql +=" and b.id=e.bayid and e.id=g.id and g.mgid=sm.id and ";

	 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
				}
					if(flag==2){
						sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='10:00' or right(h.savetime,5)='20:00' or right(h.savetime,5)='30:00' or right(h.savetime,5)='40:00' or right(h.savetime,5)='50:00')";	
					}
					if(flag==3){
						sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='15:00' or right(h.savetime,5)='30:00' or right(h.savetime,5)='45:00') ";	
					}
					if(flag==4){
						sql += " and (right(h.savetime,5)='00:00' or right(h.savetime,5)='30:00') ";	
					}
					if(flag==5){
						sql += " and (right(h.savetime,5)='00:00' ) ";	
					}
					int a=(pageNo-1)*pageSize+1;
					int b=pageNo*pageSize;
					sql += " order by h.id ";		
			}
	
		return getJdbcTemplate().query(sql, new RuningInfo_OneMinuteReportDataRowMapper());
	}
	
	

	
	
class RuningInfo_OneMinuteReportDataRowMapper implements RowMapper{
		
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setName( rs.getString("name"));
		object.setSaveTime( rs.getTimestamp("savetime"));
		if(len == 1)
		{
			object.setCalValue0( rs.getDouble("cal0") );
		}
		if(len == 2)
		{
			object.setCalValue0( rs.getDouble("cal0") );
			object.setCalValue1( rs.getDouble("cal1") );
		}
		if(len == 3)
		{
			object.setCalValue0( rs.getDouble("cal0") );
			object.setCalValue1( rs.getDouble("cal1") );
			object.setCalValue2( rs.getDouble("cal2") );
		}
		if(len == 4)
		{
			object.setCalValue0( rs.getDouble("cal0") );
			object.setCalValue1( rs.getDouble("cal1") );
			object.setCalValue2( rs.getDouble("cal2") );
			object.setCalValue3( rs.getDouble("cal3") );
		}
		
		
		return object;
	}
}


}
