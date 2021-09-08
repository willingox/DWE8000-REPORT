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
import com.xjgc.wind.datastatistics.dao.ILossEleReportDao;
import com.xjgc.wind.datastatistics.dao.IWindPlcReportDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class LossEleReportDaoImpl extends JdbcDaoSupport implements ILossEleReportDao {


	
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
		  	   	String tablename ="hiswgmeasinfo_"+startYear;
		  	 
		  	   	sql +="select b.name as name,max(h.gridErrStopHour)-min(h.gridErrStopHour) as gridErrStopHour,"
		  	   			+ "max(h.weaErrStopHour)-min(h.weaErrStopHour) as weaErrStopHour,"
		  	   			+ "max(h.hMIStopHour)-min(h.hMIStopHour) as hMIStopHour, max(h.remoteStopHour)-min(h.remoteStopHour) as remoteStopHour,"
		  	   			+ "max(h.errBreakHour)-min(h.errBreakHour) as errBreakHour,max(h.powLimHour)-min(h.powLimHour) as powLimHour,"
		  	   			+ "((max(h.gridErrStopHour)-min(h.gridErrStopHour))+(max(h.weaErrStopHour)-min(h.weaErrStopHour))+(max(h.hMIStopHour)-min(h.hMIStopHour))"
		  	   			+ "+(max(h.remoteStopHour)-min(h.remoteStopHour))+(max(h.errBreakHour)-min(h.errBreakHour))+(max(h.powLimHour)-min(h.powLimHour))) as hoursSum,"
		  	   			+ "max(h.gridErrPowSum)-min(h.gridErrPowSum) as gridErrPowSum,"
		  	   			+ "max(h.weaErrPowSum)-min(h.weaErrPowSum) as weaErrPowSum ,"
		  	   			+ "max(h.hMIStopPowSum)-min(h.hMIStopPowSum) as hMIStopPowSum ,"
		  	   			+ "max(h.remoteStopPowSum)-min(h.remoteStopPowSum) as remoteStopPowSum ,"
		  	   			+ "max(h.errBreakPowSum)-min(h.errBreakPowSum) as errBreakPowSum ,"
		  	   			+ "max(h.powLimPowSum)-min(h.powLimPowSum) as powLimPowSum ,"
		  	   		+ "((max(h.gridErrPowSum)-min(h.gridErrPowSum))+(max(h.weaErrPowSum)-min(h.weaErrPowSum))+(max(h.hMIStopPowSum)-min(h.hMIStopPowSum))"
	  	   			+ "+(max(h.remoteStopPowSum)-min(h.remoteStopPowSum))+(max(h.errBreakPowSum)-min(h.errBreakPowSum))+(max(h.powLimPowSum)-min(h.powLimPowSum))) as lossGenSum,"
		  	   			+ "max(h.hiddenPow)-min(h.hiddenPow) as hiddenPow ,"
		  	   			+ "(max(h.Reserve1)-min(h.Reserve1))/(max(h.hiddenPow)-min(h.hiddenPow)) as capAva,"
		  	   			+ "(max(h.hiddenPow)-min(h.hiddenPow))/g.CAPACITY as avaHours"
		  	   			+ " from "+tablename;
		  	  if(str.length()>11){
		  	   	sql +=" h,bay b,generator g,equipment e  where  h.SAVETIME >='"+startDateDisp+"' and h.SAVETIME <='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id and b.id=h.id";
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
		  		sql +=" h,bay b,generator g,equipment e ,smgsysinfo s where h.SAVETIME >='"+startDateDisp+"' and h.SAVETIME <='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id and b.id=h.id";
			  	sql +="  and g.mgid=s.id  and ";
		        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
						
					}
				sql +=" group by h.id";
		  	   	}
		  	if(flag == 2){
		  	   	String tablename ="hiswgmeasinfo_"+endYear;
		  	  sql +="select b.name as name,max(h.gridErrStopHour)-min(h.gridErrStopHour) as gridErrStopHour,"
		  	   			+ "max(h.weaErrStopHour)-min(h.weaErrStopHour) as weaErrStopHour,"
		  	   			+ "max(h.hMIStopHour)-min(h.hMIStopHour) as hMIStopHour, max(h.remoteStopHour)-min(h.remoteStopHour) as remoteStopHour,"
		  	   			+ "max(h.errBreakHour)-min(h.errBreakHour) as errBreakHour,max(h.powLimHour)-min(h.powLimHour) as powLimHour,"
		  	   			+ "((max(h.gridErrStopHour)-min(h.gridErrStopHour))+(max(h.weaErrStopHour)-min(h.weaErrStopHour))+(max(h.hMIStopHour)-min(h.hMIStopHour))"
		  	   			+ "+(max(h.remoteStopHour)-min(h.remoteStopHour))+(max(h.errBreakHour)-min(h.errBreakHour))+(max(h.powLimHour)-min(h.powLimHour))) as hoursSum,"
		  	   			+ "max(h.gridErrPowSum)-min(h.gridErrPowSum) as gridErrPowSum,"
		  	   			+ "max(h.weaErrPowSum)-min(h.weaErrPowSum) as weaErrPowSum ,"
		  	   			+ "max(h.hMIStopPowSum)-min(h.hMIStopPowSum) as hMIStopPowSum ,"
		  	   			+ "max(h.remoteStopPowSum)-min(h.remoteStopPowSum) as remoteStopPowSum ,"
		  	   			+ "max(h.errBreakPowSum)-min(h.errBreakPowSum) as errBreakPowSum ,"
		  	   			+ "max(h.powLimPowSum)-min(h.powLimPowSum) as powLimPowSum ,"
		  	   		+ "((max(h.gridErrPowSum)-min(h.gridErrPowSum))+(max(h.weaErrPowSum)-min(h.weaErrPowSum))+(max(h.hMIStopPowSum)-min(h.hMIStopPowSum))"
	  	   			+ "+(max(h.remoteStopPowSum)-min(h.remoteStopPowSum))+(max(h.errBreakPowSum)-min(h.errBreakPowSum))+(max(h.powLimPowSum)-min(h.powLimPowSum))) as lossGenSum,"
		  	   			+ "max(h.hiddenPow)-min(h.hiddenPow) as hiddenPow ,"
		  	   			+ "(max(h.Reserve1)-min(h.Reserve1))/(max(h.hiddenPow)-min(h.hiddenPow)) as capAva,"
		  	   			+ "(max(h.hiddenPow)-min(h.hiddenPow))/g.CAPACITY as avaHours"
		  	   			+ " from "+tablename;
		  	  if(str.length()>11){
		  	   	sql +=" h,bay b,generator g,equipment e  where  h.SAVETIME >='"+startDateDisp+"' and h.SAVETIME <='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id and b.id=h.id";
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
		  		sql +=" h,bay b,generator g,equipment e ,smgsysinfo s where h.SAVETIME >='"+startDateDisp+"' and h.SAVETIME <='"+endDateDisp+"' and h.id=e.bayid and e.id=g.id and b.id=h.id";
			  	sql +="  and g.mgid=s.id  and ";
		        sql +="s.districtid=" +Integer.parseInt(str.substring(str.lastIndexOf("-")+1,str.length()));
						
					}
				sql +=" group by h.id";
		  	   	}
		  	  
		return getJdbcTemplate().query(sql, new LossEleReportRowMapper());
		
	}
	

	class LossEleReportRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));
			object.setHoursSum( rs.getDouble("hoursSum"));
			object.setLossGenSum( rs.getDouble("lossGenSum"));
			object.setGridErrStopHour( rs.getDouble("gridErrStopHour"));
			object.setWeaErrStopHour( rs.getDouble("weaErrStopHour"));
			object.setHmiStopHour( rs.getDouble("hMIStopHour"));
			object.setRemoteStopHour( rs.getDouble("remoteStopHour"));
			object.setErrBreakHour( rs.getDouble("errBreakHour"));
			object.setPowLimHour( rs.getDouble("powLimHour"));
			object.setGridErrPowSum( rs.getDouble("gridErrPowSum"));
			object.setWeaErrPowSum( rs.getDouble("weaErrPowSum"));
			object.setHmiStopPowSum( rs.getDouble("hMIStopPowSum"));
			object.setRemoteStopPowSum( rs.getDouble("remoteStopPowSum"));
			object.setErrBreakPowSum( rs.getDouble("errBreakPowSum"));
			object.setPowLimPowSum( rs.getDouble("powLimPowSum"));
			object.setHiddenPow( rs.getDouble("hiddenPow"));
			object.setCapAva( rs.getDouble("capAva"));
			object.setAvaHours( rs.getDouble("avaHours"));
			
			
		
			return object;
		}
	}
	
	
}



	
	
