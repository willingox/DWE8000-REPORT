package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsDayReportDao;
import com.xjgc.wind.datastatistics.dao.impl.WindSpeedDaoImpl.WindSpeedCountDataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindSpeedDataForm;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YearFormatUtil;

public class GeneratorStatisticsDayReportDaoImpl extends JdbcDaoSupport implements IGeneratorStatisticsDayReportDao{

	
	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {
		
		
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//年份
		
		String tablename1 = "hiswgmeasinfo_" + year;
		String tablename2 = "hiswgstinfo_" + year;
		
		String sql="";
		if(isDBMysql()){
			
			sql+=" select ";
			sql+=" b.name as name, "; //name
			sql+=" stinfo.ID as id, "; //id 
			sql+=" max(measinfo.windspeed) as max_windspeed, "; //风速
			sql+=" avg(measinfo.windspeed) as avg_windspeed, "; //风速
			sql+=" min(measinfo.windspeed) as min_windspeed, "; //风速 
			sql+=" max(measinfo.ActPowGri) as max_P, "; //有功
			sql+=" avg(measinfo.ActPowGri) as avg_P, "; //有功
			sql+=" min(measinfo.ActPowGri) as min_P, ";  //有功
			sql+=" max(measinfo.RctPowGri) as max_Q, "; //无功
			sql+=" avg(measinfo.RctPowGri) as avg_Q, "; //无功
			sql+=" min(measinfo.RctPowGri) as min_Q, "; //无功
			sql+=" max(measinfo.OutsideTemp) as max_Temp, "; //环境温度
			sql+=" avg(measinfo.OutsideTemp) as avg_Temp, "; //环境温度
			sql+=" min(measinfo.OutsideTemp) as min_Temp, "; //环境温度
			
			
			sql+=" stinfo.YawMotorLftHour as LftHour, ";//左偏航总时间
			sql+=" stinfo.YawMotorRtghHour as RtghHour, ";//右偏航总时间
			sql+=" stinfo.YawMotorLftHour+stinfo.YawMotorRtghHour as YawHour, "; //偏航总时间
			sql+=" stinfo.YawMotorCWCou as LftYawMotorCWCou, "; //左偏航总次数
			sql+=" stinfo.YawMotorCCWCou as RtghYawMotorCCWCou, "; //右偏航总次数
			sql+=" stinfo.YawMotorCWCou+stinfo.YawMotorCCWCou as YawCWCou, "; //偏航总次数
			
			sql+=" stinfo.ActPowDaySum as daygenwh, "; //日发电量
			sql+=" stinfo.WinTurStCovCont as WinTurStCovCont, "; //停机总次数
			sql+=" stinfo.SerModTimes as SerModTimes, "; //维护次数
			sql+=" stinfo.WinTurErrCovCont as WinTurErrCovCont, "; //故障停机次数
			sql+=" stinfo.ConvMaiSwitchCou as ConvMaiSwitchCou, "; //并网次数
			sql+=" stinfo.WinHigErrTimes as WinHigErrTimes, ";  //大风停机次数
			sql+=" stinfo.WinTurCatInCont as WinTurCatInCont, "; //切入次数
			sql+=" stinfo.WinTurArtStpCont as WinTurArtStpCont "; //人工停机次数
			
			//sql+=" stinfo.ActPowMonthSum as monthgenwh, ";
			//sql+=" stinfo.ActPowYearSum as yeargenwh, ";
			//sql+=" stinfo.CurMonAvlbltRat as MonAvlbltRat, ";
			//sql+=" stinfo.CurYeaAvlbltRat as YeaAvlbltRat, ";
			//sql+=" stinfo.CurMonAvlbltHour as MonAvlbltHour, ";
			//sql+=" stinfo.CurMonNormTim as MonNormTim, ";
			//sql+=" stinfo.CurMonRunTim as MonRunTim, ";
			//sql+=" stinfo.CurMonStopTim as MonStopTim, ";
			//sql+=" stinfo.CurMonErrEmeTim as MonErrEmeTim, ";
			//sql+=" stinfo.CurMonSerTim as MonSerTim, ";
			//sql+=" stinfo.CurMonGridErrTim as MonGridErrTim, ";

			sql+=" FROM "+tablename1+" measinfo,"+tablename2+" stinfo,bay b ,equipment e,generator g ";
			sql+=" WHERE measinfo.ID = stinfo.ID and DATE_FORMAT(measinfo.savetime,'%Y-%m-%d' )=DATE_FORMAT(stinfo.savetime,'%Y-%m-%d' )  and b.id=stinfo.ID and b.id=e.bayid and e.id=g.id  ";
			sql+=" and DATE_FORMAT(measinfo.savetime,'%Y-%m-%d' )=DATE_FORMAT('"+date+"','%Y-%m-%d' ) ";
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql+=" GROUP BY stinfo.ID order by stinfo.ID";
				
		}else{
			
		}
		
		return getJdbcTemplate().query(sql, new DataRowMapper());
	}
	//查询日期前一天的次数
public List<GeneratorStatisticsReportVo> listyesterday(String date,Integer smgID) {
		
		
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
	
		
		try {
			getDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		calendar.add(Calendar.DATE,-1);
		String YesterDayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		int year=calendar.get(Calendar.YEAR);//年份
		
		String tablename = "hiswgstinfo_" + year;
		
		String sql="";
		if(isDBMysql()){
			
			sql+=" select ";
			sql+=" stinfo.ID as id, "; //id 

			sql+=" stinfo.YawMotorLftHour as LftHour, ";//左偏航总时间
			sql+=" stinfo.YawMotorRtghHour as RtghHour, ";//右偏航总时间
			sql+=" stinfo.YawMotorLftHour+stinfo.YawMotorRtghHour as YawHour, "; //偏航总时间
			sql+=" stinfo.YawMotorCWCou as LftYawMotorCWCou, "; //左偏航总次数
			sql+=" stinfo.YawMotorCCWCou as RtghYawMotorCCWCou, "; //右偏航总次数
			sql+=" stinfo.YawMotorCWCou+stinfo.YawMotorCCWCou as YawCWCou, "; //偏航总次数
			
			sql+=" stinfo.WinTurStCovCont as WinTurStCovCont, "; //停机总次数
			sql+=" stinfo.SerModTimes as SerModTimes, "; //维护次数
			sql+=" stinfo.WinTurErrCovCont as WinTurErrCovCont, "; //故障停机次数
			sql+=" stinfo.ConvMaiSwitchCou as ConvMaiSwitchCou, "; //并网次数
			sql+=" stinfo.WinHigErrTimes as WinHigErrTimes, ";  //大风停机次数
			sql+=" stinfo.WinTurCatInCont as WinTurCatInCont, "; //切入次数
			sql+=" stinfo.WinTurArtStpCont as WinTurArtStpCont "; //人工停机次数

			sql+=" FROM "+tablename+" stinfo,equipment e,generator g";
			sql+=" where DATE_FORMAT(stinfo.savetime,'%Y-%m-%d' )=DATE_FORMAT('"+YesterDayDate+"','%Y-%m-%d' ) and stinfo.id=e.bayid and e.id=g.id ";
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql+=" order BY stinfo.ID ";
				
		}else{
			
		}
		
		return getJdbcTemplate().query(sql, new DataYesterdayRowMapper());
	}

//有效风时数
public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID) {
	
	
	Date getDate=null;
	Calendar calendar=Calendar.getInstance();
	try {
		getDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		calendar.setTime(getDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int year=calendar.get(Calendar.YEAR);//年份
	
	String tablename = "hiswgimptinfo_" + year;
	/*String sql="";
	if(isDBMysql()){
		
		String sqlwind =" select b.id as id,min(g.weatherval) as minWeatherval,  max(g.weatherval) as maxWeatherval  from   genpwd g ,bay b,equipment e,generator ge where g.genpwd>0 and e.bayid=b.id and ge.genmodelid=g.genmodelid and e.id=ge.id  group by b.id order by b.id ";
		List rows= getJdbcTemplate().queryForList(sqlwind);
		for(int i=0;i<rows.size();i++){
			Map windrows=(Map) rows.get(i);
			sql +="select count(id)/60 as hours , id from " +tablename;
			sql +=" where id="+windrows.get("id")+" and WindSpeed>="+windrows.get("minWeatherval")+" and WindSpeed<="+windrows.get("maxWeatherval")+"  and DATE_FORMAT(savetime,'%Y-%m-%d' ) = DATE_FORMAT('"+date+"','%Y-%m-%d' )  group by id ";
		if(i<rows.size()-1){
			sql +=" union ";
		}
		
		}
		
	}*/
	String sql="";
	if(isDBMysql()){
		
		sql +="select count(h.id)/60 as hours , h.id from " +tablename;
		sql +=" h,equipment e,generator g where  h.WindSpeed>=3 and h.WindSpeed<=20  and DATE_FORMAT(h.savetime,'%Y-%m-%d' ) = DATE_FORMAT('"+date+"','%Y-%m-%d' ) and h.id=e.bayid and e.id=g.id ";
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1";
		}
			
			sql+="	group by h.id ";
		
	}
	else{
		
	}
	
	return getJdbcTemplate().query(sql, new WindDataRowMapper());
}

//统计运行的风机台数
public List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID) {
	Date getDate=null;
	Calendar calendar=Calendar.getInstance();
	try {
		getDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		calendar.setTime(getDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int year=calendar.get(Calendar.YEAR);//年份
	
	String tablename = "hisgenerator_" + year;
	   String sql = "";
	 
		 
		sql += "select h.ID from "+tablename+" h ,generator g where h.netstate=1 ";
		sql +=" and  h.id=g.id and h.savetime >='"+date +" 00:00:00'  and h.savetime <='"+date+"  23:59:59'" ;
		if(smgID!=0){
			sql+=" and g.mgid= "+smgID;
		}
		if(smgID==0){
			sql+=" and g.mgid=1 ";
		}
		sql +=" group by h.id";
	  

	return getJdbcTemplate().query(sql, new WindCountDataRowMapper());
	
}
class WindCountDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
		object.setId( rs.getInt("id") );
		
		return object;
	}
}
class WindDataRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
		
		object.setHours(rs.getDouble("hours"));
		object.setId(rs.getInt("id"));
		

		return object;
	}
}
class DataYesterdayRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
		
		object.setLftHour(rs.getDouble("LftHour"));
		object.setRtghHour(rs.getDouble("RtghHour"));
		object.setYawHour(rs.getDouble("YawHour"));
		object.setLftYawMotorCWCou(rs.getInt("LftYawMotorCWCou"));
		object.setRtghYawMotorCCWCou(rs.getInt("RtghYawMotorCCWCou"));
		object.setYawCWCou(rs.getInt("YawCWCou"));
		object.setWinTurStCovCont(rs.getInt("WinTurStCovCont"));
		object.setSerModTimes(rs.getInt("SerModTimes"));
		object.setWinTurErrCovCont(rs.getInt("WinTurErrCovCont"));
		object.setConvMaiSwitchCou(rs.getInt("ConvMaiSwitchCou"));
		object.setWinHigErrTimes(rs.getInt("WinHigErrTimes"));
		object.setWinTurCatInCont(rs.getInt("WinTurCatInCont"));
		object.setWinTurArtStpCont(rs.getInt("WinTurArtStpCont"));

		return object;
	}
}

	class DataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
			object.setName(rs.getString("name"));
			object.setId(rs.getInt("id"));
			
			
			object.setMax_windspeed(rs.getDouble("max_windspeed"));
			object.setAvg_windspeed(rs.getDouble("avg_windspeed"));
			object.setMin_windspeed(rs.getDouble("min_windspeed"));
			object.setMax_P(rs.getDouble("max_P"));
			object.setAvg_P(rs.getDouble("avg_P"));
			object.setMin_P(rs.getDouble("min_P"));
			object.setMax_Q(rs.getDouble("max_Q"));
			object.setAvg_Q(rs.getDouble("avg_Q"));
			object.setMin_Q(rs.getDouble("min_Q"));
			object.setMax_Temp(rs.getDouble("max_Temp"));
			object.setAvg_Temp(rs.getDouble("avg_Temp"));
			object.setMin_Temp(rs.getDouble("min_Temp"));
			object.setLftHour(rs.getDouble("LftHour"));
			object.setRtghHour(rs.getDouble("RtghHour"));
			object.setYawHour(rs.getDouble("YawHour"));
			object.setLftYawMotorCWCou(rs.getInt("LftYawMotorCWCou"));
			object.setRtghYawMotorCCWCou(rs.getInt("RtghYawMotorCCWCou"));
			object.setYawCWCou(rs.getInt("YawCWCou"));
			object.setDaygenwh(rs.getDouble("daygenwh"));
			object.setWinTurStCovCont(rs.getInt("WinTurStCovCont"));
			object.setSerModTimes(rs.getInt("SerModTimes"));
			object.setWinTurErrCovCont(rs.getInt("WinTurErrCovCont"));
			object.setConvMaiSwitchCou(rs.getInt("ConvMaiSwitchCou"));
			object.setWinHigErrTimes(rs.getInt("WinHigErrTimes"));
			object.setWinTurCatInCont(rs.getInt("WinTurCatInCont"));
			object.setWinTurArtStpCont(rs.getInt("WinTurArtStpCont"));

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
