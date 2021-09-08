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

import com.xjgc.wind.datastatistics.dao.IPowerScatterDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.PowerScatterDataForm;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class PowerScatterDaoImpl extends JdbcDaoSupport implements
		IPowerScatterDao {
	
	private boolean isDBMysql(){
		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String driver=((BasicDataSource) jdbcTemplate.getDataSource()).getDriverClassName();
		if(driver.equals("com.mysql.jdbc.Driver")){
			return true;
		}else{
			return false;
		}

	}

	public List<DataStatisticsDataVo> listgenpwd(Integer equipId) {

		String sql = "";
		sql += "select  ge.weatherval as windvelval, ge.configure as configure,ge.genpwd as genpwd ,ge.weight as weight from genpwd ge ,generator g where ge.genmodelid=g.genmodelid ";
		if (equipId != null) {
			sql += " and g.id=" + equipId;
		}
		return getJdbcTemplate().query(sql, new PowerWindgenpwdRowMapper());
	}

	

	class PowerWindgenpwdRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
		
			object.setAvgWindVelval(rs.getDouble("windvelval"));
			object.setGenpwd(rs.getBigDecimal("genpwd"));
			object.setWeight(rs.getDouble("weight"));
			object.setConfigure(rs.getInt("configure"));
			return object;
		}
	}

	class PowerWindfreRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			
			object.setAvgWindVelval(rs.getDouble("windvelval"));
			object.setPwdCurp(rs.getBigDecimal("curp"));
			
			return object;
		}
	}

	
	

   public List<DataStatisticsDataVo> listfre(PowerScatterDataForm queryFilter,
		   Integer equipId){
   
		String startDateStr = queryFilter.getStartDateDisp();
		String endDateStr = queryFilter.getEndDateDisp();
		
		Date startDate = queryFilter.getStartDate();
		Date endDate = queryFilter.getEndDate();
		
		String starYear;
		String endYear;
		starYear = new SimpleDateFormat("yyyy").format(startDate);
		endYear = new SimpleDateFormat("yyyy").format(endDate);
		
		String sql = "";
		if (isDBMysql()==true) {
			if (starYear.equals(endYear)) {
				String tableWeather = "hisweatherdata_" + starYear;
				String tableGenerator = "hisgenerator_" + starYear;
				sql += "select floor(hw.WINDVELVAL/0.5) as windvelval ,avg(hg.curp)  as curp from ";
				sql += tableWeather
						+ " hw,"
						+ tableGenerator
						+ " hg  ,genpwd ge ,generator g where  hw.savetime >='"
						+ startDateStr
						+ "' and hw.savetime < '"
						+ endDateStr
						+ "' and hw.savetime = hg.savetime and hw.windvelval >=2 and hw.windvelval <25  and hg.curp>20 and hg.curcmpstate = 2 and hg.id=hw.equipid " //and hg.curcmpstate = 2
						+ " and g.id=hg.id and g.genmodelid=ge.genmodelid and ge.WEATHERVAL*2=floor(hw.WINDVELVAL/0.5) and hg.curp>=ge.genpwd*ge.lowerLimit and hg.curp<=ge.genpwd*ge.upperlimit   ";
				if (equipId != null) {
					sql += " and hg.id=" + equipId;
				}
				sql += " group by floor( hw.WINDVELVAL/0.5) ";
				
			}else {  
				String tableWeather = "hisweatherdata_" + starYear;
				String tableGenerator = "hisgenerator_" + starYear;
				String tableWeather2 = "hisweatherdata_" + endYear;
				String tableGenerator2 = "hisgenerator_" + endYear;
		
				sql += " select floor(hw.WINDVELVAL/0.5) as windvelval ,truncate(avg(hg.curp),3)  as curp ";
				sql += " from genpwd ge ,generator g, ";
				sql += " ((select savetime,windvelval,equipid  from  "+tableWeather+" where savetime >='"+startDateStr+"' and savetime < '"+endDateStr+"') union (select savetime,windvelval,equipid  from  "+tableWeather2+" where savetime >='"+startDateStr+"' and savetime < '"+endDateStr+"') ) as hw, ";
				sql += " ((select savetime,id,curcmpstate,curp from  "+tableGenerator+" where savetime >='"+startDateStr+"' and savetime < '"+endDateStr+"')  union (select savetime,id,curcmpstate,curp from  "+tableGenerator2+" where savetime >='"+startDateStr+"' and savetime < '"+endDateStr+"') ) as hg ";
				sql += " where hw.savetime = hg.savetime and hw.windvelval >=2 and hw.windvelval <25  and hg.curp>20 and hg.curcmpstate = 2 and hg.id=hw.equipid   ";
				sql += " and g.id=hg.id and g.genmodelid=ge.genmodelid and ge.WEATHERVAL*2=floor(hw.WINDVELVAL/0.5) and hg.curp>=ge.genpwd*ge.lowerLimit  and hg.curp<=ge.genpwd*ge.upperlimit  ";
				if (equipId != null) {
					sql += " and hg.id=" + equipId;
				}
				sql += " group by floor( hw.WINDVELVAL/0.5)  ";
				
			}
		} 
		 if (isDBMysql()==false) {
			if (starYear.equals(endYear)) {
				String tableWeather = "hisweatherdata_" + starYear;
				String tableGenerator = "hisgenerator_" + starYear;
				sql += "select floor(hw.WINDVELVAL/0.5) as windvelval ,avg(hg.curp)  as curp from ";
				sql += tableWeather
						+ " hw,"
						+ tableGenerator
						+ " hg  ,genpwd ge ,generator g  where  hw.savetime>=to_date('"+startDateStr+"','yyyy-mm-dd hh24:mi:ss')"
						
						+ " and hw.savetime < =to_date('"+endDateStr+"','yyyy-mm-dd hh24:mi:ss')"
						+ " and hw.savetime = hg.savetime and hw.windvelval >=2 and hw.windvelval <25  and hg.curp>20 and hg.curcmpstate = 2 and hg.id=hw.equipid " //and hg.curcmpstate = 2
						+ " and g.id=hg.id and g.genmodelid=ge.genmodelid and ge.WEATHERVAL*2=floor(hw.WINDVELVAL/0.5) and hg.curp>=ge.genpwd*ge.lowerLimit  and hg.curp<=ge.genpwd*ge.upperlimit  ";
				if (equipId != null) {
					sql += " and hg.id=" + equipId;
				}
				sql += " group by floor( hw.WINDVELVAL/0.5) ";
			}else {
				String tableWeather = "hisweatherdata_" + starYear;
				String tableGenerator = "hisgenerator_" + starYear;
				String tableWeather2 = "hisweatherdata_" + endYear;
				String tableGenerator2 = "hisgenerator_" + endYear;
				
				sql += " select floor(hw.WINDVELVAL/0.5) as windvelval ,truncate(avg(hg.curp),3)  as curp ";
				sql += " from genpwd ge ,generator g, ";
				sql += " ((select savetime,windvelval,equipid  from  "+tableWeather+" where savetime >=to_date('"+startDateStr+"') and savetime < to_date('"+endDateStr+"')  ) union (select savetime,windvelval,equipid  from  "+tableWeather2+" where savetime >=to_date('"+startDateStr+"') and savetime < to_date('"+endDateStr+"')  ) ) as hw, ";
				sql += " ((select savetime,id,curcmpstate,curp from  "+tableGenerator+" where savetime >='to_date("+startDateStr+"') and savetime < to_date('"+endDateStr+"') )  union (select savetime,id,curcmpstate,curp from  "+tableGenerator2+" where savetime >=to_date('"+startDateStr+"') and savetime < to_date('"+endDateStr+"') ) ) as hg ";
				sql += " where hw.savetime = hg.savetime and hw.windvelval >=2 and hw.windvelval <25  and hg.curp>20 and hg.curcmpstate = 2 and hg.id=hw.equipid   ";
				sql += " and g.id=hg.id and g.genmodelid=ge.genmodelid and ge.WEATHERVAL*2=floor(hw.WINDVELVAL/0.5) and hg.curp>=ge.genpwd*ge.lowerLimit  and hg.curp<=ge.genpwd*ge.upperlimit   ";
				if (equipId != null) {
					sql += " and hg.id=" + equipId;
				}
				sql += " group by floor( hw.WINDVELVAL/0.5)  ";
			}
		
		} 

		return getJdbcTemplate().query(sql, new PowerWindfreRowMapper());
	}

	
	
}


