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
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;




import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IWindAvailabilityContrastDao;
import com.xjgc.wind.datastatistics.dao.impl.FaultQueryDaoImpl.faultqueryDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindAvailabilityContrastDaoImpl extends JdbcDaoSupport implements IWindAvailabilityContrastDao {
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}

	}
	public List<DataStatisticsDataVo> list(String startDateStr,String endDateStr,String str) {

		
		
		Integer type = 5;
		
		Date startDate=null;
		Date endDate=null;
		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
	//	String str=queryFilter.getStr();
	    String[] arr =null;
	
		String sql = " ";
		/*TO_DATE('"+startTime+"' ,'yyyy-mm-dd hh24:mi:ss')*/
		if(isDBMysql()){
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874)";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
				
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
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
					
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			
			  		  
			  			
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id    ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;
				sql += "select * from( ";
				sql += "(select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  		
		  		  
		  			
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
						
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += " ) union (select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1 ,e.id as equipId,e.bayid as bayId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  		
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql += "and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+ "' and h.lastvaltime <='"+endDateStr+"' ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  		
			  		  
			  		
					sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  ) )as qwer order by equipId ";
		
			}
		}else{
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  		
		  			
					
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
						sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
						sql +=" and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}
				
					sql += "  order by e.id ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;
				sql += "select * from( ";
				sql += "(select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1 ,e.id as equipId,e.bayid as bayId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
				
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += " ) union (select h.lastvaltime as happenTime,h.curdatatime as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
				
					
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
						sql += "and s.equipid = e.id and h.lastvalue = st.value and h.lastvaltime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.lastvaltime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
						
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			
				  			
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}
					sql += "  ) )as qwer order by equipId ";
		
			}
		}
		return getJdbcTemplate().query(sql, new WindAvailabilityContrast1DataRowMapper());

	}

	
	public List<DataStatisticsDataVo> listHappenNoEnd(String startDateStr,String endDateStr,String str) {
		
		
		Date startDate=null;
		Date endDate=null;
		Integer type = 5;
		Calendar startCalendar=Calendar.getInstance();
		Calendar endCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
			endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
			startCalendar.setTime(startDate);
			endCalendar.setTime(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
		 
			String[] arr =null;
		String sql = " ";
		/*TO_DATE('"+startTime+"' ,'yyyy-mm-dd hh24:mi:ss')*/
		if(isDBMysql()){
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId  ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql +=" and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
		  		  
		  			
			
				
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
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql +=" and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
				
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			
			  		  
			  			
					sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id ";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;

				sql += "select * from( (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
			
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  		
		  		  
		  		
				
				
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
						sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
						sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
					
						if(type == 2)
				  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
						 if(type == 5||type == 6)
				  			  sql += " and  st.statetype = "+type;
				  		  
				  			
				  		  
				  			
						
						sql +=" and e.id=g.id and g.mgid=sm.id and ";

			 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
			 				
			 			}

				sql += "  ) union (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  

		  		
			

				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql += "and s.equipid = e.id and h.curvalue = st.value   and h.curdatatime>='"+startDateStr+ "' and h.curdatatime <='"+endDateStr+"' ";
					
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			
				
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  ) )as qwer order by equipId";
		
			}
		}else{
			if (startYear == endYear) {
				String tablename = "hisstatus_" + startYear;
				sql += "select h.curdatatime as happenTime, null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId ";
				if(str.length()>11){
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql +=" and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
		  		
				

				
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
					sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql +=" and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += "  order by e.id";
				
			} else {
				String tablename1 = "hisstatus_" + startYear;
				String tablename2 = "hisstatus_" + endYear;

				sql += "select * from ( (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename1;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  			
			  		  
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
				sql += "  ) union (select h.curdatatime as happenTime,null as removetime,s.name as name,s.id as id,st.name as curcmpState,e.name as name1,e.id as equipId,e.bayid as bayId from "
						+ tablename2;
				if(str.length()>11){
				sql += " h,status s,equipment e,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
				if(type == 2)
		  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
				 if(type == 5||type == 6)
		  			  sql += " and  st.statetype = "+type;
		  		  
		  			
		  		  
		  		
				
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
					sql += " h,status s,equipment e,generator g,smgsysinfo sm,stvaltype st where h.statusid = s.id and s.meastypeid = st.meastypeid  and  (st.meastypeid=20811 or st.meastypeid=20874) ";
					sql += "and s.equipid = e.id and h.curvalue = st.value and h.curdatatime>=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.curdatatime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') ";
					if(type == 2)
			  			  sql += " and s.meastypeid=20873 and st.statetype = "+type;
					 if(type == 5||type == 6)
			  			  sql += " and  st.statetype = "+type;
			  		  
			  		
			  		  
			  		
		 			sql +=" and e.id=g.id and g.mgid=sm.id and ";

		 				sql +="sm.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
		 				
		 			}
					sql += " ) )as qwer  order by equipId ";
		
			}
		}
		return getJdbcTemplate().query(sql, new WindAvailabilityContrast1DataRowMapper());
		
	}
	
	class WindAvailabilityContrast1DataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setHappenTime(rs.getTimestamp("happenTime"));
			object.setRemoveTime(rs.getTimestamp("removetime"));
			object.setName(rs.getString("name"));
			object.setName2(rs.getString("curcmpState"));
			object.setName1(rs.getString("name1"));
			object.setId(rs.getInt("id"));
			object.setEquipId(rs.getInt("equipId"));
			object.setBayId(rs.getInt("bayId"));
			//object.setCount(rs.getDouble("id"));
			return object;
		}
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
  

		return getJdbcTemplate().query(sql, new WindAvailabilityContrastCountDataRowMapper());
		
	}
	
	
	public List<DataStatisticsDataVo> listAllAva(String startDateStr,String endDateStr,int flag) {
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
  	 if (flag==1) {
  	   
  		 String tablename ="hiswgstinfo_"+startYear;
  		 
  		sql += "select h.id as id,(1-((max(h.winturerrsecsum)-min(h.winturerrsecsum))/((DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24)))*100 as availability";
  		 
  		sql += " from "+tablename+" h where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59'  ";
  	
 		sql +=" group by h.id";
  	   }
  	 if (flag==2) {
    	   
  		 String tablename ="hiswgstinfo_"+endYear;
  		 
   		sql += "select h.id as id,(1-((max(h.winturerrsecsum)-min(h.winturerrsecsum))/((DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24)))*100 as availability";
   		 
   		sql += " from "+tablename+" h where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59' ";
   	
  		sql +=" group by h.id";
  	   
  	 }

		return getJdbcTemplate().query(sql, new WindAvailabilityContrastAllDataRowMapper());
		
	}
	
	
	
	public List<DataStatisticsDataVo> listava(String startDateStr,String endDateStr,String str,int flag) {
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
  	 if (flag==1) {
  	   
  		 String tablename ="hiswgstinfo_"+startYear;
  		 
  		sql += "select h.id as id,b.name as name,(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24 as hour,max(h.winturerrsecsum)-min(h.winturerrsecsum) as faultTime,(1-((max(h.winturerrsecsum)-min(h.winturerrsecsum))/((DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24)))*100 as availability,";
  		 if(str.length()>11){
  		sql += "(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24-(max(h.winturerrsecsum)-min(h.winturerrsecsum)) as avaTime from "+tablename+" h,bay b where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59' ";
  		sql += " and h.id=b.id";
 		
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
 			sql += "(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24-(max(h.winturerrsecsum)-min(h.winturerrsecsum)) as avaTime from "+tablename+" h,bay b,equipment e,generator g,smgsysinfo s where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59' ";
 	  		sql += " and h.id=b.id";
 			sql +=" and e.bayid=b.id and e.id=g.id and g.mgid=s.id  and ";

 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
 				
 			}
 		sql +=" group by h.id";
  	   }
  	 if (flag==2) {
    	   
  		 String tablename ="hiswgstinfo_"+endYear;
  		 
  		sql += "select h.id as id,b.name as name,(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24 as hour,max(h.winturerrsecsum)-min(h.winturerrsecsum) as faultTime,(1-((max(h.winturerrsecsum)-min(h.winturerrsecsum))/((DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24)))*100 as availability,";
  		 if(str.length()>11){
  		sql += "(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24-(max(h.winturerrsecsum)-min(h.winturerrsecsum)) as avaTime from "+tablename+" h,bay b where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59' ";
  		sql += " and h.id=b.id";
 		
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
 			sql += "(DATEDIFF('"+endDateStr+"','"+startDateStr+"')+1)*24-(max(h.winturerrsecsum)-min(h.winturerrsecsum)) as avaTime from "+tablename+" h,bay b,equipment e,generator g,smgsysinfo s where h.savetime >='"+startDateStr+" 00:00:00'  and h.savetime <='"+endDateStr+" 23:59:59' ";
 	  		sql += " and h.id=b.id";
 			sql +=" and e.bayid=b.id and e.id=g.id and g.mgid=s.id  and ";

 				sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
 				
 			}
 		sql +=" group by h.id";
  	   }
  	   
  	   
  

		return getJdbcTemplate().query(sql, new WindAvailabilityContrastDataRowMapper());
		
	}
	
	class WindAvailabilityContrastAllDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("id") );
			object.setAvailability(rs.getDouble("availability"));
			return object;
		}
	}
	class WindAvailabilityContrastDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("id") );
			object.setName( rs.getString("name") );
			object.setFaultTime(rs.getDouble("faultTime"));
			object.setAvaTime(rs.getDouble("avaTime"));
			object.setHour(rs.getDouble("hour"));
			object.setAvailability(rs.getDouble("availability"));
			return object;
		}
	}
	class WindAvailabilityContrastCountDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("id") );
			
			return object;
		}
	}
	

}



	
	
