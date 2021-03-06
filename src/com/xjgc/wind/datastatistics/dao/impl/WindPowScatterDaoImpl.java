package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IWindPowScatterDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindPowScatterDataForm;

public class WindPowScatterDaoImpl extends JdbcDaoSupport implements IWindPowScatterDao{
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}
	}
	
	public List<DataStatisticsDataVo> list(WindPowScatterDataForm queryFilter,Integer equipId){
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
		if (isDBMysql()==true) {
			if(startYear == endYear){
	        	String tablenamew ="hisweatherdata_"+startYear;
	        	String tablenameg ="hisgenerator_"+startYear;
	       
	        	sql +=" select h1.WINDVELVAL as WINDVELVAL1, g1.curp as POWER  ";
	        	sql +=" from "+tablenamew+" h1, "+tablenameg+" g1 ,genpwd ge,generator g";
	        	sql +=" where   g1.curcmpstate = 2 and h1.equipid=g1.id and g.id=g1.id and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >='"+startDateStr+"' and h1.savetime <='"+endDateStr+"'  ";
				
			}
	    	else{
	        	String tablenamew1 ="hisweatherdata_"+startYear;
	        	String tablenameg1 ="hisgenerator_"+startYear;
	        	String tablenamew2 ="hisweatherdata_"+endYear;
	        	String tablenameg2 ="hisgenerator_"+endYear;
	        	
	        	sql +=" ( select h1.WINDVELVAL as WINDVELVAL1,g1.curp as POWER  ";
	        	sql +=" from "+tablenamew1+" h1, "+tablenameg1+" g1 ,genpwd ge,generator g";
	        	sql +=" where  g1.curcmpstate = 2 and h1.equipid=g1.id and g.id=g1.id and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >='"+startDateStr+"' and h1.savetime <='"+endDateStr+"'  ) union all ";

	        	sql +=" (select h1.WINDVELVAL as WINDVELVAL1,g1.curp as POWER  ";
	        	sql +=" from "+tablenamew2+" h1, "+tablenameg2+" g1 ,genpwd ge,generator g";
	        	sql +=" where   g1.curcmpstate = 2 and h1.equipid=g1.id and g.id=g1.id and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >='"+startDateStr+"' and h1.savetime <='"+endDateStr+"'  )";
	        
	    	}
		}
		
		if (isDBMysql()==false) {
			if(startYear == endYear){
	        	String tablenamew ="hisweatherdata_"+startYear;
	        	String tablenameg ="hisgenerator_"+startYear;
	        	
	        	sql +=" select h1.WINDVELVAL as WINDVELVAL1, g1.curp as POWER  ";
	        	sql +=" from "+tablenamew+" h1, "+tablenameg+" g1 ,genpwd ge,generator g";
	        	sql +=" where  g1.curcmpstate = 2 and h1.equipid=g1.id and g.id=g1.id and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h1.savetime <=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss')  ";
	        	
	    	}
	    	else{
	        	String tablenamew1 ="hisweatherdata_"+startYear;
	        	String tablenameg1 ="hisgenerator_"+startYear;
	        	String tablenamew2 ="hisweatherdata_"+endYear;
	        	String tablenameg2 ="hisgenerator_"+endYear;
	        	sql +=" ( select h1.WINDVELVAL as WINDVELVAL1,g1.curp as POWER  ";
	        	sql +=" from "+tablenamew1+" h1, "+tablenameg1+" g1 ,genpwd ge,generator g";
	        	sql +=" where   g1.curcmpstate = 2 and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and h1.equipid=g1.id and g.id=g1.id and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h1.savetime <=o_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss')  ) union all ";
	        	
	        	sql +=" (select h1.WINDVELVAL as WINDVELVAL1,g1.curp as POWER  ";
	        	sql +=" from "+tablenamew2+" h1, "+tablenameg2+" g1 ,genpwd ge,generator g";
	        	sql +=" where   g1.curcmpstate = 2 and ge.WEATHERVAL*2=floor(h1.WINDVELVAL/0.5) and h1.equipid=g1.id and g.id=g1.id and g.genmodelid=ge.genmodelid and g1.curp>=ge.genpwd*ge.lowerLimits and g1.curp<=ge.genpwd*ge.upperlimits";
	        	if (equipId != null) {
	        		sql += " and h1.equipid=" + equipId;
	        	}
	        	sql +=" and h1.savetime=g1.savetime and h1.savetime >=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss') and h1.savetime <=to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss')  )";
	    	}
		}
		return getJdbcTemplate().query(sql, new WindPowScatterDataRowMapper());
	}
	
	
	class WindPowScatterDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setWindVelval1( rs.getFloat("WINDVELVAL1") );
			object.setPower( rs.getFloat("POWER") );
			return object;
		}
	}
	
}
