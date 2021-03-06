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
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;



public class RuningInfo_OneMinuteDaoImpl extends JdbcDaoSupport implements IRuningInfo_OneMinuteDao{
	
	int len=0;
	public List<DataStatisticsDataVo> list(String startDateDisp,String endDateDisp,ArrayList<String> check_id,ArrayList<ArrayList<String>> check_value,int flag,int yearIf){
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
		String sql = "";
		if(isDBMysql()){
			if (yearIf==1) {
			sql += "select h.savetime as savetime ";
			if(check_id.size() != 0)
			{
			 for(int i=0;i<check_id.size();i++){
				for(int j=0;j<check_value.get(i).size();j++){	
					sql += ",(select h"+i+j+"."+check_value.get(i).get(j)+" from hiswgimptinfo_"+startYear+" h"+i+j+" where h"+i+j+".id ="+check_id.get(i)+" and h"+i+j+".savetime = h.savetime) as cal"+len;
				len=len+1;
				 }
			 }
			}
				
				sql += " from hiswgimptinfo_"+startYear+" h where h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
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
				sql += " group by h.savetime ";	
		}
			if (yearIf==2) {
				sql += "select h.savetime as savetime ";
				if(check_id.size() != 0)
				{
				 for(int i=0;i<check_id.size();i++){
					for(int j=0;j<check_value.get(i).size();j++){	
						sql += ",(select h"+i+j+"."+check_value.get(i).get(j)+" from hiswgimptinfo_"+endYear+" h"+i+j+" where h"+i+j+".id ="+check_id.get(i)+" and h"+i+j+".savetime = h.savetime) as cal"+len;
					len=len+1;
					 }
				 }
				}
					
					sql += " from hiswgimptinfo_"+endYear+" h where h.savetime >= '"+startDateDisp+"' and h.savetime <= '"+endDateDisp+"'"; 
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
					sql += " group by h.savetime ";	
			}
		}
		else{
			if (yearIf==1) {
			sql += "select h.savetime as savetime ";
			if(check_id.size() != 0)
			{
			 for(int i=0;i<check_id.size();i++){
				for(int j=0;j<check_value.get(i).size();j++){	
					sql += ",(select h"+i+j+"."+check_value.get(i).get(j)+" from hiswgimptinfo_"+startYear+" h"+i+j+" where h"+i+j+".id ="+check_id.get(i)+" and h"+i+j+".savetime = h.savetime) as cal"+len;
				len=len+1;
				 }
			 }
			}
				
				sql += " from hiswgimptinfo_"+startYear+" h where h.savetime>=to_date('"+startDateDisp+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateDisp+"','yyyy-mm-dd hh24:mi:ss')";
				if(flag==2){
					sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='10:00' or TO_CHAR(savetime,'mi:ss')='20:00' or TO_CHAR(savetime,'mi:ss')='30:00' or TO_CHAR(savetime,'mi:ss')='40:00' or TO_CHAR(savetime,'mi:ss')='50:00')";	
				}
				if(flag==3){
					sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='15:00' or TO_CHAR(savetime,'mi:ss')='30:00' or TO_CHAR(savetime,'mi:ss')='45:00') ";	
				}
				if(flag==4){
					sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='30:00') ";	
				}
				if(flag==5){
					sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' ) ";	
				}
			}
			if (yearIf==2) {
				sql += "select h.savetime as savetime ";
				if(check_id.size() != 0)
				{
				 for(int i=0;i<check_id.size();i++){
					for(int j=0;j<check_value.get(i).size();j++){	
						sql += ",(select h"+i+j+"."+check_value.get(i).get(j)+" from hiswgimptinfo_"+endYear+" h"+i+j+" where h"+i+j+".id ="+check_id.get(i)+" and h"+i+j+".savetime = h.savetime) as cal"+len;
					len=len+1;
					 }
				 }
				}
					
					sql += " from hiswgimptinfo_"+endYear+" h where h.savetime>=to_date('"+startDateDisp+"','yyyy-mm-dd hh24:mi:ss') and h.savetime<=to_date('"+endDateDisp+"','yyyy-mm-dd hh24:mi:ss')";
					if(flag==2){
						sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='10:00' or TO_CHAR(savetime,'mi:ss')='20:00' or TO_CHAR(savetime,'mi:ss')='30:00' or TO_CHAR(savetime,'mi:ss')='40:00' or TO_CHAR(savetime,'mi:ss')='50:00')";	
					}
					if(flag==3){
						sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='15:00' or TO_CHAR(savetime,'mi:ss')='30:00' or TO_CHAR(savetime,'mi:ss')='45:00') ";	
					}
					if(flag==4){
						sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' or TO_CHAR(savetime,'mi:ss')='30:00') ";	
					}
					if(flag==5){
						sql += " and (TO_CHAR(savetime,'mi:ss')='00:00' ) ";	
					}
				}
			
		}
		return getJdbcTemplate().query(sql, new RuningInfo_OneMinuteDataRowMapper());
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
	
	
class RuningInfo_OneMinuteDataRowMapper implements RowMapper{
		
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
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
		if(len == 5)
		{
			object.setCalValue0( rs.getDouble("cal0") );
			object.setCalValue1( rs.getDouble("cal1") );
			object.setCalValue2( rs.getDouble("cal2") );
			object.setCalValue3( rs.getDouble("cal3") );
			object.setCalValue4( rs.getDouble("cal4") );
		}
		
		return object;
	}
}


}
