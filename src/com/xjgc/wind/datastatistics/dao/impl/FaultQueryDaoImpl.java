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

import com.xjgc.wind.datastatistics.dao.IFaultQueryDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.FaultQueryDataForm;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class FaultQueryDaoImpl extends JdbcDaoSupport implements IFaultQueryDao {

	
	//and  st.meastypeid!=20811 plc状态不显示
	public List<DataStatisticsDataVo> list(FaultQueryDataForm queryFilter) {

		String startDateStr=queryFilter.getStartDateDisp();//开始时间字符串
		String endDateStr=queryFilter.getEndDateDisp();//结束时间字符串
		
		Integer type = queryFilter.getAlarmType();
		String faultName = queryFilter.getFaultName();
		
		Date startDate=null;
		Date endDate=null;
		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
		String str=queryFilter.getStr();
	    String[] arr =null;
	
		String sql = " ";
		/*TO_DATE('"+startTime+"' ,'yyyy-mm-dd hh24:mi:ss')*/
		if(isDBMysql()){
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
				
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
					sql +=" from "+tablename+" h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st ";
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
					sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
					
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
			  			
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id, h.lastvaltime  ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;
				sql += "select * from( ";
				sql += "(select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  			
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
						
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			if(faultName != null)
				    			  sql += " and s.name  like '%" + faultName +"%'";
				  		  
				  		
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += " ) union (select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1 ,e.id as equipId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
				
				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
					sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
			  		
					sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  ) )as qwer order by equipId, happenTime ";
		
			}
		}else{
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  			
					
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
						sql +=" from "+tablename+" h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st ";
						sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
						sql +=" and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			if(faultName != null)
				    			  sql += " and s.name  like '%" + faultName +"%'";
				  		  
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}
				
					sql += "  order by e.id, h.lastvaltime ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;
				sql += "select * from( ";
				sql += "(select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1 ,e.id as equipId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		 
				
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			if(faultName != null)
				    			  sql += " and s.name  like '%" + faultName +"%'";
				  		  
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += " ) union (select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  			
				
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  st.meastypeid!=20811 ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			if(faultName != null)
				    			  sql += " and s.name  like '%" + faultName +"%'";
				  		  
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}
					sql += "  ) )as qwer order by equipId, happenTime ";
		
			}
		}
		return getJdbcTemplate().query(sql, new faultqueryDataRowMapper());

	}

	
	public List<DataStatisticsDataVo> listHappenNoEnd(
			FaultQueryDataForm queryFilter) {
		String startDateStr=queryFilter.getStartDateDisp();//开始时间字符串
		String endDateStr=queryFilter.getEndDateDisp();//结束时间字符串
		
		
		Date startDate=null;
		Date endDate=null;
		Integer type = queryFilter.getAlarmType();
		String faultName = queryFilter.getFaultName();
		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
		 String str=queryFilter.getStr();
			String[] arr =null;
		String sql = " ";
		/*TO_DATE('"+startTime+"' ,'yyyy-mm-dd hh24:mi:ss')*/
		if(isDBMysql()){
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql +=" and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  			
			
				
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
					sql +=" from "+tablename+" h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st ";
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
					sql +=" and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
				
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
			  			
					sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id, h.lastvaltime ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;

				sql += "select * from( (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  		
				
				
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
						sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
					
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			if(faultName != null)
				    			  sql += " and s.name  like '%" + faultName +"%'";
				  		  
				  			
						
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += "  ) union (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		
			

				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
					sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
					
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
				
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  ) )as qwer order by equipId, happenTime";
		
			}
		}else{
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.curdatatime as happenTime, null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql +=" and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		
				

				
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
					sql +=" from "+tablename+" h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st ";
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
					sql +=" and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id, h.lastvaltime";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;

				sql += "select * from ( (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
					sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
				sql += "  ) union (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
				sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			if(faultName != null)
		    			  sql += " and s.name  like '%" + faultName +"%'";
		  		  
		  		
				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  st.meastypeid!=20811 ";
					sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			if(faultName != null)
			    			  sql += " and s.name  like '%" + faultName +"%'";
			  		  
			  		
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += " ) )as qwer  order by equipId, happenTime ";
		
			}
		}
		return getJdbcTemplate().query(sql, new faultqueryDataRowMapper());
		
	}
	
	
	
	class faultqueryDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setHappenTime(rs.getTimestamp("happenTime"));
			object.setRemoveTime(rs.getTimestamp("removetime"));
			object.setName(rs.getString("name"));
			object.setName2(rs.getString("curcmpState"));
			object.setName1(rs.getString("name1"));
			object.setId(rs.getInt("id"));
			//object.setCount(rs.getDouble("id"));
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
