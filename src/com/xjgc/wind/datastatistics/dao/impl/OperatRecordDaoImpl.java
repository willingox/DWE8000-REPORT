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

import com.xjgc.wind.datastatistics.dao.IOperatRecordDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.OperatRecordDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class OperatRecordDaoImpl extends JdbcDaoSupport implements IOperatRecordDao{

	public List<DataStatisticsDataVo> list(OperatRecordDataForm queryFilter){

 		
		Integer equipid = queryFilter.getEquipId();//闂撮殧id
		
		String startDateStr=queryFilter.getStartDateDisp();//寮�鏃堕棿瀛楃涓�
		String endDateStr=queryFilter.getEndDateDisp();//缁撴潫鏃堕棿瀛楃涓�
		
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
		int startYear=startCalendar.get(Calendar.YEAR);//寮�骞翠唤
		int endYear=endCalendar.get(Calendar.YEAR);//缁撴潫骞翠唤
		String sql = "";
		if(isDBMysql()){
			
			sql +=" select h.objdesc as name2,h.DESCRIPTION as DESCRIPTION,h.thetime as savetime,h.actuser as name1,b.name as name ";
			sql +=" from hisalarm h ,control c,bay b  ";
			sql +=" where h.thetime >= '"+startDateStr+"'  and h.thetime <='"+endDateStr+"' and h.objtype ='control' and h.alarmid >=401 and h.alarmid <= 414 and c.id = h.objid and c.bayid=b.id";
	  		if(equipid != null){
	  			sql += " and b.id = "+equipid;
	  		}
			
		}else{
			sql +=" select h.objdesc as name2,h.thetime as savetime,h.actuser as name1,b.name as name ";
			sql +=" from hisalarm h ,control c,bay b  ";
			sql +=" where h.thetime >=TO_DATE('"+startDateStr+"' ,'yyyy-mm-dd hh24:mi:ss')  and h.thetime <=TO_DATE('"+endDateStr+"' ,'yyyy-mm-dd hh24:mi:ss') and h.objtype ='control' and h.alarmid >=401 and h.alarmid <= 414 and c.id = h.objid and c.bayid=b.id";
	  		if(equipid != null){
	  			sql += " and b.id = "+equipid;
	  		}
		}
  	  	
  		return getJdbcTemplate().query(sql,new operatrecordDataRowMapper());
  		
	}
	
	class operatrecordDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));
			object.setName2(rs.getString("name2"));
			object.setName1(rs.getString("name1"));
			object.setDescription(rs.getString("DESCRIPTION"));
			object.setSaveTime(rs.getTimestamp("savetime"));
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
