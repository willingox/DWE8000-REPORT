package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IWindRoseDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.WindRoseDataForm;


import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class WindRoseDaoImpl extends JdbcDaoSupport implements IWindRoseDao {

	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}

	}
	

	
	
	public List<DataStatisticsDataVo> list(WindRoseDataForm queryFilter) {
		
		String startDateStr=queryFilter.getStartDateDisp();//开始时间字符串
		String endDateStr=queryFilter.getEndDateDisp();//结束时间字符串
		//Integer equipid = queryFilter.getEquipId();
		Integer id = queryFilter.getId();
		
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
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
		int endYear=endCalendar.get(Calendar.YEAR);//结束年份
		String sql = "";
		
		
			if(startYear==endYear){
				String hisweather="hisweatherdata_"+startYear;
				String hisgenerator="hisgenerator_"+startYear;
				
				sql+=" select count(*) as COUNTS, floor(qwer.winddirval/22.5) as WINDDIRVAL, TRUNCATE(avg(qwer.windvelval),2) as AVGWINDVELVALDU ,TRUNCATE(avg(qwer.curp),2) as POWER ";
				sql+=" from ( ";
				sql+=" 	select (case when hw.winddirval>=348.75 then hw.winddirval-326.25 else hw.winddirval+33.75 end) as winddirval ,hw.windvelval ,hg.curp ";
				sql+=" 	from "+hisweather+" hw,"+hisgenerator+" hg ";
				sql+=" 	where hw.winddirval >=0 and hw.winddirval <=360 and hw.equipid=hg.id and hw.equipid="+id+" and  hw.savetime=hg.savetime and hw.savetime >='"+startDateStr+"' and hw.savetime <='"+endDateStr+"' ) as qwer ";
				sql+=" group by floor(qwer.winddirval/22.5) ";
			}else{
				String hisweather="hisweatherdata_"+startYear;
				String hisgenerator="hisgenerator_"+startYear;
				String hisweather2="hisweatherdata_"+endYear;
				String hisgenerator2="hisgenerator_"+endYear;
				
				sql+=" select count(*) as COUNTS, floor(qwer.winddirval/22.5) as WINDDIRVAL, TRUNCATE(avg(qwer.windvelval),2) as AVGWINDVELVALDU ,TRUNCATE(avg(qwer.curp),2) as POWER ";
				sql+=" from ( ";
				sql+=" 	(select (case when hw.winddirval>=348.75 then hw.winddirval-326.25 else hw.winddirval+33.75 end) as winddirval ,hw.windvelval ,hg.curp ";
				sql+=" 	from "+hisweather+" hw,"+hisgenerator+" hg ";
				sql+=" 	where hw.winddirval >=0 and hw.winddirval <=360  and hw.equipid=hg.id and hw.equipid="+id+" and  hw.savetime=hg.savetime and hw.savetime >='"+startDateStr+"' and hw.savetime <='"+endDateStr+"') union ";
				sql+=" 	(select (case when hw.winddirval>=348.75 then hw.winddirval-326.25 else hw.winddirval+33.75 end) as winddirval ,hw.windvelval ,hg.curp ";
				sql+=" 	from "+hisweather2+" hw,"+hisgenerator2+" hg ";
				sql+=" 	where hw.winddirval >=0 and hw.winddirval <=360  and hw.equipid=hg.id and hw.equipid="+id+" and  hw.savetime=hg.savetime and hw.savetime >='"+startDateStr+"' and hw.savetime <='"+endDateStr+"' )";
				sql+="  ) as qwer ";
				sql+=" group by floor(qwer.winddirval/22.5) ";
				
			}
		
		
		
		return getJdbcTemplate().query(sql, new WindRoseDataRowMapper());
	}
	
	
	class WindRoseDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setAvgWindVelvalDu( rs.getFloat("AVGWINDVELVALDU") );								//
			object.setWindDirVal( rs.getString("WINDDIRVAL") );											//
			//object.setFrequency1( rs.getDouble("FREQUENCY") );													//频率			
			object.setPower( rs.getFloat("POWER") );                                                    //
			//object.setAvgWindVelval(rs.getFloat("AVGWINDVELVAL")*rs.getFloat("FREQUENCY"));             		//风能
			object.setCounts(rs.getInt("COUNTS"));                                                      //
			return object;
		}
	}




	
}
	
