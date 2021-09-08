package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;



import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsYearReportDao;
import com.xjgc.wind.datastatistics.dao.impl.GeneratorStatisticsMonReportDaoImpl.DataRowMapper;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;

public class GeneratorStatisticsYearReportDaoImpl extends JdbcDaoSupport implements IGeneratorStatisticsYearReportDao{

	
	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {
        
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//骞翠唤
		
		String tablename1 = "hiswgmeasinfo_" + year;
		String tablename2 = "hiswgstinfo_" + year;
		
		String sql="";
		if(isDBMysql()){
			if(isTableExistMysql(tablename1)){
				sql+=" select ";
				sql+=" b.name as name, "; //name
				sql+=" stinfo.ID as id, "; //id 
				sql+=" max(measinfo.windspeed) as max_windspeed, "; //椋庨�
				sql+=" avg(measinfo.windspeed) as avg_windspeed, "; //椋庨�
				sql+=" min(measinfo.windspeed) as min_windspeed, "; //椋庨� 
				sql+=" max(measinfo.ActPowGri) as max_P, "; //鏈夊姛
				sql+=" avg(measinfo.ActPowGri) as avg_P, "; //鏈夊姛
				sql+=" min(measinfo.ActPowGri) as min_P, ";  //鏈夊姛
				sql+=" max(measinfo.RctPowGri) as max_Q, "; //鏃犲姛
				sql+=" avg(measinfo.RctPowGri) as avg_Q, "; //鏃犲姛
				sql+=" min(measinfo.RctPowGri) as min_Q, "; //鏃犲姛
				sql+=" max(measinfo.OutsideTemp) as max_Temp, "; //鐜娓╁害
				sql+=" avg(measinfo.OutsideTemp) as avg_Temp, "; //鐜娓╁害
				sql+=" min(measinfo.OutsideTemp) as min_Temp, "; //鐜娓╁害
				
				
				sql+=" max(stinfo.YawMotorLftHour) as LftHour, ";//宸﹀亸鑸�鏃堕棿
				sql+=" max(stinfo.YawMotorRtghHour) as RtghHour, ";//鍙冲亸鑸�鏃堕棿
				sql+=" max(stinfo.YawMotorLftHour+stinfo.YawMotorRtghHour) as YawHour, "; //鍋忚埅鎬绘椂闂�
				sql+=" max(stinfo.YawMotorCWCou) as LftYawMotorCWCou, "; //宸﹀亸鑸�娆℃暟
				sql+=" max(stinfo.YawMotorCCWCou) as RtghYawMotorCCWCou, "; //鍙冲亸鑸�娆℃暟
				sql+=" max(stinfo.YawMotorCWCou+stinfo.YawMotorCCWCou) as YawCWCou, "; //鍋忚埅鎬绘鏁�
				
				//sql+=" stinfo.ActPowDaySum as daygenwh, "; //鏃ュ彂鐢甸噺
				//sql+=" max(stinfo.ActPowMonthSum) as monthgenwh, "; //鏈堝彂鐢甸噺
				sql+=" max(stinfo.ActPowYearSum) as yeargenwh, "; //骞村彂鐢甸噺
				sql+=" max(stinfo.WinTurStCovCont) as WinTurStCovCont, "; //鍋滄満鎬绘鏁�
				sql+=" max(stinfo.SerModTimes) as SerModTimes, "; //缁存姢娆℃暟
				sql+=" max(stinfo.WinTurErrCovCont) as WinTurErrCovCont, "; //鏁呴殰鍋滄満娆℃暟
				sql+=" max(stinfo.ConvMaiSwitchCou) as ConvMaiSwitchCou, "; //骞剁綉娆℃暟
				sql+=" max(stinfo.WinHigErrTimes) as WinHigErrTimes, ";  //澶ч鍋滄満娆℃暟
				sql+=" max(stinfo.WinTurCatInCont) as WinTurCatInCont, "; //鍒囧叆娆℃暟
				sql+=" max(stinfo.WinTurArtStpCont) as WinTurArtStpCont , "; //浜哄伐鍋滄満娆℃暟
				
				
				sql+=" max(stinfo.CurYeaAvlbltRat) as YeaAvlbltRat ";  //骞村彲鍒╃敤鐜�
			
				sql+=" FROM "+tablename1+" measinfo,"+tablename2+" stinfo,bay b,equipment e,generator g   ";
				sql+=" WHERE measinfo.ID = stinfo.ID and b.id=stinfo.ID and b.id=e.bayid and e.id=g.id ";
				if(smgID!=0){
					sql+=" and g.mgid= "+smgID;
				}
				if(smgID==0){
					sql+=" and g.mgid=1 ";
				}
				sql+=" GROUP BY stinfo.ID ";
			}else{
				return null;
			}
		}else{
			
			
		}
		
		return getJdbcTemplate().query(sql, new DataRowMapper());
	}
public List<GeneratorStatisticsReportVo> listHour(String date,Integer smgID) {
		
		
	Date getDate=null;
	Calendar calendar=Calendar.getInstance();
	try {
		getDate=new SimpleDateFormat("yyyy-MM").parse(date);
		calendar.setTime(getDate);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int year=calendar.get(Calendar.YEAR);
	
	String tablename = "hiswgstinfo_" + year;
		
		String sql="";
		if(isDBMysql()){
			
			sql+="select h.id as id, h.CurMonAvlbltHour as CurMonAvlbltHour FROM "+tablename+" h,bay b ,equipment e,generator g";
			sql+=" where h.savetime=(select max(savetime)  ";
			sql+=" from "+tablename+ "  where DATE_FORMAT(savetime,'%Y-%m' )='"+date+"'  ) "; 
			sql+="  and  b.id=h.ID and b.id=e.bayid and e.id=g.id  ";
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql+=" GROUP BY h.ID ";
				
		}else{
			
		}
		
		return getJdbcTemplate().query(sql, new DataHourRowMapper());
	}
	//鏈夋晥椋庢椂鏁�
	public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID) {
		
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//骞翠唤
		
		String tablename = "hiswgimptinfo_" + year;
		/*String sql="";
		if(isDBMysql()){
			
			String sqlwind =" select b.id as id,min(g.weatherval) as minWeatherval,  max(g.weatherval) as maxWeatherval  from   genpwd g ,bay b,equipment e,generator ge where g.genpwd>0 and e.bayid=b.id and ge.genmodelid=g.genmodelid and e.id=ge.id  group by b.id order by b.id ";
			List rows= getJdbcTemplate().queryForList(sqlwind);
			for(int i=0;i<rows.size();i++){
				Map windrows=(Map) rows.get(i);
				sql +="select count(id)/60 as hours , id from " +tablename;
				sql +=" where id="+windrows.get("id")+" and WindSpeed>="+windrows.get("minWeatherval")+" and WindSpeed<="+windrows.get("maxWeatherval")+"   group by id ";
			if(i<rows.size()-1){
				sql +=" union ";
			}
			
			}
			
		}else{
			
		}*/
		String sql="";
		if(isDBMysql()){
			
			sql +="select count(h.id)/60 as hours , h.id from " +tablename;
				sql +=" h,equipment e,generator g where  h.WindSpeed>=3 and h.WindSpeed<=20  and h.id=e.bayid and e.id=g.id  ";
				if(smgID!=0){
					sql+=" and g.mgid= "+smgID;
				}
				if(smgID==0){
					sql+=" and g.mgid=1 ";
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
			getDate=new SimpleDateFormat("yyyy").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//年份
		
		String tablename = "hisgenerator_" + year;
		   String sql = "";
		 
			 
			sql += "select h.ID from "+tablename+" h ,generator g where h.netstate=1 ";
			sql +=" and  h.id=g.id " ;
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql +=" group by h.id";
		  

		return getJdbcTemplate().query(sql, new WindCountYearDataRowMapper());
		
	}
	class WindCountYearDataRowMapper implements RowMapper {

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
	class DataHourRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			GeneratorStatisticsReportVo object = new GeneratorStatisticsReportVo();
			
			object.setMonAvlbltHour(rs.getDouble("CurMonAvlbltHour"));
			object.setId(rs.getInt("id"));
			

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
			object.setYeargenwh(rs.getDouble("yeargenwh"));
			object.setWinTurStCovCont(rs.getInt("WinTurStCovCont"));
			object.setSerModTimes(rs.getInt("SerModTimes"));
			object.setWinTurErrCovCont(rs.getInt("WinTurErrCovCont"));
			object.setConvMaiSwitchCou(rs.getInt("ConvMaiSwitchCou"));
			object.setWinHigErrTimes(rs.getInt("WinHigErrTimes"));
			object.setWinTurCatInCont(rs.getInt("WinTurCatInCont"));
			object.setWinTurArtStpCont(rs.getInt("WinTurArtStpCont"));
			object.setYeaAvlbltRat(rs.getInt("YeaAvlbltRat"));
			

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
	
	private boolean isTableExistMysql(String tableName) {

		JdbcTemplate jdbcTemplate=getJdbcTemplate();
		String bdUrl=((BasicDataSource) jdbcTemplate.getDataSource()).getUrl();
	    String dbName=bdUrl.substring(bdUrl.indexOf("://")+3,bdUrl.indexOf("?"));
	    dbName=dbName.substring(dbName.indexOf("/")+1);
		
		String sql = "SELECT count(*) as  num FROM information_schema.TABLES WHERE table_name ='"
				+ tableName + "' and table_schema='" + dbName + "'";
		//List list = getJdbcTemplate().query(sql, new GeneralRowMapper());
		int num=jdbcTemplate.queryForInt(sql);
		//Map map = (Map) list.get(0);
		//String num = (String) map.get("num");
		//System.out.println(num);
		if (0==num) {
			return false;
		} else {
			return true;
		}
	}
	
	
	private boolean isTableExistOracle(String tableName) {
		/*鏌愪釜鐢ㄦ埛涓嬫槸鍚︽湁鏌愪釜琛紵
		select count(*) from dba_tables where owner = 'USER_NAME' and table_name = 'TABLE_NAME';*/
		//褰撳墠鐢ㄦ埛杩炴帴杩炴帴涓嬫槸鍚︽湁鏌愪釜琛紵
		String sql = "SELECT count(*) as  \"num\" FROM user_tables WHERE table_name ='"
				+ tableName + "'";
		int num=getJdbcTemplate().queryForInt(sql);
		//List list = getJdbcTemplate().query(sql, new GeneralRowMapper());
		//Map map = (Map) list.get(0);
		//String num = (String) map.get("num");
		//System.out.println(num);
		if (0==num) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
