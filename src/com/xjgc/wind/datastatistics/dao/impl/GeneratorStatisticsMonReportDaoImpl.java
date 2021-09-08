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

import com.xjgc.wind.datastatistics.dao.IGeneratorStatisticsMonReportDao;
import com.xjgc.wind.datastatistics.dao.impl.GeneratorStatisticsDayReportDaoImpl.WindCountDataRowMapper;
import com.xjgc.wind.datastatistics.dao.impl.GeneratorStatisticsDayReportDaoImpl.WindDataRowMapper;
import com.xjgc.wind.datastatistics.dao.impl.WindAvailabilityContrastDaoImpl.WindAvailabilityContrast1DataRowMapper;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.vo.GeneratorStatisticsReportVo;
import com.xjgc.wind.datastatistics.web.form.WindAvailabilityContrastDataForm;

public class GeneratorStatisticsMonReportDaoImpl extends JdbcDaoSupport implements IGeneratorStatisticsMonReportDao{

	public List<DataStatisticsDataVo> listAva(String startDateStr) {

		
		
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
	
	
		String sql = " ";
		
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,h.curdatatime as removetime,e.bayid as id ";
				
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+"-01 00:00:00' and h.lastvaltime <='"+startDateStr+"-30 23:59:59'";
				 sql += " and  st.statetype = 5";
					sql += "  order by e.bayid   ";
				
		
		return getJdbcTemplate().query(sql, new DataRowAvaMapper());

	}
	class DataRowAvaMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setHappenTime(rs.getTimestamp("happenTime"));
			object.setRemoveTime(rs.getTimestamp("removetime"));
			object.setId(rs.getInt("id"));
			return object;
		}
	}
	public List<DataStatisticsDataVo> listHappenNoEnd(String startDateStr) {
	
		
		Date startDate=null;
		Calendar startCalendar=Calendar.getInstance();
		try {
			startDate=new SimpleDateFormat("yyyy-MM").parse(startDateStr);
			startCalendar.setTime(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int startYear=startCalendar.get(Calendar.YEAR);//开始年份
	
	
		String sql = " ";
		
				String tablename = "hisstatus_" + startYear;
				sql += "select h.lastvaltime as happenTime,null as removetime,e.bayid as id ";
				
				sql +=" from "+tablename+" h,status s,equipment e,stvaltype st ";
				sql +=" where h.statusid = s.id and s.meastypeid = st.meastypeid and  (st.meastypeid=20811 or st.meastypeid=20874) ";
				sql +=" and s.equipid = e.id and h.lastvalue = st.value   and h.lastvaltime>='"+startDateStr+"-01 00:00:00' and h.lastvaltime <='"+startDateStr+"-30 23:59:59'";
				 sql += " and  st.statetype = 5";
					sql += "  order by e.bayid   ";
				
		
		return getJdbcTemplate().query(sql, new DataRowAvaMapper());
		
	}
	public List<GeneratorStatisticsReportVo> list(String date,Integer smgID) {
		
		
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy-MM").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//楠炵繝鍞�
		
		String tablename1 = "hiswgmeasinfo_" + year;
		String tablename2 = "hiswgstinfo_" + year;
		
		String sql="";
		if(isDBMysql()){
			
			sql+=" select ";
			sql+=" b.name as name, "; //name
			sql+=" stinfo.ID as id, "; //id 
			sql+=" max(measinfo.windspeed) as max_windspeed, "; //妞嬪酣锟�
			sql+=" avg(measinfo.windspeed) as avg_windspeed, "; //妞嬪酣锟�
			sql+=" min(measinfo.windspeed) as min_windspeed, "; //妞嬪酣锟� 
			sql+=" max(measinfo.ActPowGri) as max_P, "; //閺堝濮�
			sql+=" avg(measinfo.ActPowGri) as avg_P, "; //閺堝濮�
			sql+=" min(measinfo.ActPowGri) as min_P, ";  //閺堝濮�
			sql+=" max(measinfo.RctPowGri) as max_Q, "; //閺冪姴濮�
			sql+=" avg(measinfo.RctPowGri) as avg_Q, "; //閺冪姴濮�
			sql+=" min(measinfo.RctPowGri) as min_Q, "; //閺冪姴濮�
			sql+=" max(measinfo.OutsideTemp) as max_Temp, "; //閻滎垰顣ㄥ〒鈺佸
			sql+=" avg(measinfo.OutsideTemp) as avg_Temp, "; //閻滎垰顣ㄥ〒鈺佸
			sql+=" min(measinfo.OutsideTemp) as min_Temp, "; //閻滎垰顣ㄥ〒鈺佸
			
			
			sql+=" max(stinfo.YawMotorLftHour)-min(stinfo.YawMotorLftHour) as LftHour, ";//瀹革箑浜搁懜顏咃拷閺冨爼妫�
			sql+=" max(stinfo.YawMotorRtghHour)-min(stinfo.YawMotorRtghHour) as RtghHour, ";//閸欏啿浜搁懜顏咃拷閺冨爼妫�
			sql+=" (max(stinfo.YawMotorLftHour)-min(stinfo.YawMotorLftHour))+ (max(stinfo.YawMotorRtghHour)-min(stinfo.YawMotorRtghHour)) as YawHour, "; //閸嬪繗鍩呴幀缁樻闂傦拷
			sql+=" max(stinfo.YawMotorCWCou)-min(stinfo.YawMotorCWCou) as LftYawMotorCWCou, "; //瀹革箑浜搁懜顏咃拷濞嗏剝鏆�
			sql+=" max(stinfo.YawMotorCCWCou)-min(stinfo.YawMotorCCWCou) as RtghYawMotorCCWCou, "; //閸欏啿浜搁懜顏咃拷濞嗏剝鏆�
			sql+=" (max(stinfo.YawMotorCWCou)-min(stinfo.YawMotorCWCou))+(max(stinfo.YawMotorCCWCou)-min(stinfo.YawMotorCCWCou)) as YawCWCou, "; //閸嬪繗鍩呴幀缁橆偧閺侊拷
			
			//sql+=" stinfo.ActPowDaySum as daygenwh, "; //閺冦儱褰傞悽鐢稿櫤
			sql+=" max(stinfo.ActPowMonthSum) as monthgenwh, "; //閺堝牆褰傞悽鐢稿櫤
			//sql+=" max(stinfo.ActPowYearSum) as yeargenwh, "; //楠炴潙褰傞悽鐢稿櫤
			sql+=" max(stinfo.WinTurStCovCont)-min(stinfo.WinTurStCovCont) as WinTurStCovCont, "; //閸嬫粍婧�閹粯顐奸弫锟�
			sql+=" max(stinfo.SerModTimes)-min(stinfo.SerModTimes) as SerModTimes, "; //缂佸瓨濮㈠▎鈩冩殶
			sql+=" max(stinfo.WinTurErrCovCont)-min(stinfo.WinTurErrCovCont) as WinTurErrCovCont, "; //閺佸懘娈伴崑婊勬簚濞嗏剝鏆�
			sql+=" max(stinfo.ConvMaiSwitchCou)-min(stinfo.ConvMaiSwitchCou) as ConvMaiSwitchCou, "; //楠炲墎缍夊▎鈩冩殶
			sql+=" max(stinfo.WinHigErrTimes)-min(stinfo.WinHigErrTimes) as WinHigErrTimes, ";  //婢堆囶棑閸嬫粍婧�濞嗏剝鏆�
			sql+=" max(stinfo.WinTurCatInCont)-min(stinfo.WinTurCatInCont) as WinTurCatInCont, "; //閸掑洤鍙嗗▎鈩冩殶
			sql+=" max(stinfo.WinTurArtStpCont)-min(stinfo.WinTurArtStpCont) as WinTurArtStpCont , "; //娴滃搫浼愰崑婊勬簚濞嗏剝鏆�
			
			sql+=" max(stinfo.CurMonAvlbltRat)*100 as MonAvlbltRat, "; //褰撴湀鍙埄鐢ㄧ巼
			sql+=" min(stinfo.CurMonAvlbltHour) as MonAvlbltHour, ";//鏈堝彲鍒╃敤灏忔椂鏁�
			sql+=" max(stinfo.CurMonNormTim) as MonNormTim, "; //鏈堣繍琛屽皬鏃舵暟
			sql+=" max(stinfo.CurMonRunTim) as MonRunTim, "; //鏈堝彂鐢靛皬鏃舵暟
			sql+=" max(stinfo.CurMonStopTim) as MonStopTim, "; //鏈堝仠鏈哄皬鏃舵暟
			sql+=" max(stinfo.CurMonErrEmeTim) as MonErrEmeTim, ";//鏈堟晠闅滃皬鏃舵暟
			sql+=" max(stinfo.CurMonSerTim) as MonSerTim, "; //鏈堢淮鎶ゅ皬鏃舵暟
			sql+=" max(stinfo.CurMonGridErrTim) as MonGridErrTim "; //鐢电綉鏈堟晠闅滄椂闂�

			sql+=" FROM "+tablename1+" measinfo,"+tablename2+" stinfo,bay b ,equipment e,generator g  ";
			sql+=" WHERE stinfo.CurMonAvlbltHour>0 and measinfo.ID = stinfo.ID and b.id=stinfo.ID and b.id=e.bayid and e.id=g.id  and DATE_FORMAT(measinfo.savetime,'%Y-%m' )=DATE_FORMAT(stinfo.savetime,'%Y-%m' )";
			sql+=" and DATE_FORMAT(measinfo.savetime,'%Y-%m' )='"+date+"' ";
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql+=" GROUP BY stinfo.ID ";
				
		}else{
			
		}
		
		return getJdbcTemplate().query(sql, new DataRowMapper());
	}
	
	//閺堝鏅ユ搴㈡閺侊拷
	public List<GeneratorStatisticsReportVo> listWind(String date,Integer smgID) {
		
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy-MM").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//楠炵繝鍞�
		
		String tablename = "hiswgimptinfo_" + year;
		/*String sql="";
		if(isDBMysql()){
			
			String sqlwind =" select b.id as id,min(g.weatherval) as minWeatherval,  max(g.weatherval) as maxWeatherval  from   genpwd g ,bay b,equipment e,generator ge where g.genpwd>0 and e.bayid=b.id and ge.genmodelid=g.genmodelid and e.id=ge.id  group by b.id order by b.id ";
			List rows= getJdbcTemplate().queryForList(sqlwind);
			for(int i=0;i<rows.size();i++){
				Map windrows=(Map) rows.get(i);
				sql +="select count(id)/60 as hours , id from " +tablename;
				sql +=" where id="+windrows.get("id")+" and WindSpeed>="+windrows.get("minWeatherval")+" and WindSpeed<="+windrows.get("maxWeatherval")+"  and DATE_FORMAT(savetime,'%Y-%m' ) = '"+date+"'  group by id ";
			if(i<rows.size()-1){
				sql +=" union ";
			}
			
			}
			
		}*/
		String sql="";
		if(isDBMysql()){
			
			sql +="select count(h.id)/60 as hours , h.id from " +tablename;
				sql +=" h,equipment e,generator g where  h.WindSpeed>=3 and h.WindSpeed<=20  and DATE_FORMAT(h.savetime,'%Y-%m' ) = '"+date+"' and h.id=e.bayid and e.id=g.id   ";
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
	
	//缁熻杩愯鐨勯鏈哄彴鏁�
	public List<GeneratorStatisticsReportVo> listcount(String date,Integer smgID) {
		Date getDate=null;
		Calendar calendar=Calendar.getInstance();
		try {
			getDate=new SimpleDateFormat("yyyy-MM").parse(date);
			calendar.setTime(getDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int year=calendar.get(Calendar.YEAR);//骞翠唤
		
		String tablename = "hisgenerator_" + year;
		   String sql = "";
		 
			 
			sql += "select h.ID from "+tablename+" h ,generator g where h.netstate=1 ";
			sql +=" and  h.id=g.id and DATE_FORMAT(h.savetime,'%Y-%m' ) = '"+date+"'" ;
			if(smgID!=0){
				sql+=" and g.mgid= "+smgID;
			}
			if(smgID==0){
				sql+=" and g.mgid=1 ";
			}
			sql +=" group by h.id";
		  

		return getJdbcTemplate().query(sql, new WindCountMonDataRowMapper());
		
	}
	class WindCountMonDataRowMapper implements RowMapper {

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
			object.setLftYawMotorCWCou(rs.getDouble("LftYawMotorCWCou"));
			object.setRtghYawMotorCCWCou(rs.getDouble("RtghYawMotorCCWCou"));
			object.setYawCWCou(rs.getDouble("YawCWCou"));
			object.setMonthgenwh(rs.getDouble("monthgenwh"));
			object.setWinTurStCovCont(rs.getDouble("WinTurStCovCont"));
			object.setSerModTimes(rs.getDouble("SerModTimes"));
			object.setWinTurErrCovCont(rs.getDouble("WinTurErrCovCont"));
			object.setConvMaiSwitchCou(rs.getDouble("ConvMaiSwitchCou"));
			object.setWinHigErrTimes(rs.getDouble("WinHigErrTimes"));
			object.setWinTurCatInCont(rs.getDouble("WinTurCatInCont"));
			object.setWinTurArtStpCont(rs.getDouble("WinTurArtStpCont"));
			object.setMonAvlbltRat(rs.getDouble("MonAvlbltRat"));
			object.setMonAvlbltHour(rs.getDouble("MonAvlbltHour"));
			object.setMonNormTim(rs.getDouble("MonNormTim"));
			object.setMonRunTim(rs.getDouble("MonRunTim"));
			object.setMonStopTim(rs.getDouble("MonStopTim"));
			object.setMonErrEmeTim(rs.getDouble("MonErrEmeTim"));
			object.setMonSerTim(rs.getDouble("MonSerTim"));
			object.setMonGridErrTim(rs.getDouble("MonGridErrTim"));
			

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
