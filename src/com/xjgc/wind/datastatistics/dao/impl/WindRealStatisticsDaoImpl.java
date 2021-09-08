package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import com.xjgc.wind.datastatistics.dao.IWindRealStatisticsDao;
import com.xjgc.wind.datastatistics.vo.WgstinfoDataVo;



public class WindRealStatisticsDaoImpl extends JdbcDaoSupport implements IWindRealStatisticsDao{

	public List<WgstinfoDataVo> list(){
		
		String sql = "";
		if(isDBMysql()){
			sql += "select b.name as name, " +
					"r.ActPowDaySum,r.ActPowMonthSum,r.ActPowYearSum,r.ActPowSum,r.ActPowSumCsm,r.PLCRunSecSUM,r.WinTurErrSecSUM,r.MaxGenBearTemp,r.GenStatorTemp,r.AccDriPp,r.AccNonDriPp,r.BraOffTimeHour," +
					"r.OilPumpHigCouHour,r.OilPumpCouTimHour,r.GearOilFanRunTim,r.GenWaterPumpTim,r.HydPumpCouTimHour,r.YawMotorLftHour,r.YawMotorRtghHour," +
					"r.CurMonAvlbltRat,r.LasMonAvlbltRat,r.CurYeaAvlbltRat,r.LasYeaAvlbltRat,r.CurMonAvlbltHour,r.CurMonNormTim,r.CurMonRunTim,r.CurMonStopTim,r.CurMonErrEmeTim,r.CurMonSerTim,r.CurMonGridErrTim," +
					"r.AutoResetTimes,r.WinTurStCovCont,r.SerModTimes,r.EmeStoTimes,r.BraCou,r.OilPmpHigCou,r.OilPmpLowCou,r.OilFanRunCoun,r.HydPumpCou,r.YawMotorCWCou,r.YawMotorCCWCou,r.WinTurErrCovCont,r.ConvMaiSwitchCou,r.WinHigErrTimes,r.WinTurCatInCont,r.WinTurArtStpCont" +
					"  from rtwgstinfo r,bay b where r.id=b.id order by r.id  ";
			
		}
		else{
			
		}
		return getJdbcTemplate().query(sql, new BoxQueryDataRowMapper());
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
	
	
class BoxQueryDataRowMapper implements RowMapper{
		
	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		WgstinfoDataVo object = new WgstinfoDataVo();
		    object.setName( rs.getString("name") );
			object.setActPowDaySum( rs.getDouble("ActPowDaySum") );
			object.setActPowMonthSum( rs.getDouble("ActPowMonthSum") );
			object.setActPowYearSum( rs.getDouble("ActPowYearSum") );
			object.setActPowSum( rs.getDouble("ActPowSum") );
			object.setActPowSumCsm( rs.getDouble("ActPowSumCsm") );
			object.setPlcRunSecSUM( rs.getDouble("PLCRunSecSUM") );
			object.setWinTurErrSecSUM( rs.getDouble("WinTurErrSecSUM") );
			object.setMaxGenBearTemp( rs.getDouble("MaxGenBearTemp") );
			object.setGenStatorTemp( rs.getDouble("GenStatorTemp") );
			object.setAccDriPp( rs.getDouble("AccDriPp") );
			object.setAccNonDriPp( rs.getDouble("AccNonDriPp") );
			object.setBraOffTimeHour( rs.getDouble("BraOffTimeHour") );
			object.setOilPumpHigCouHour( rs.getDouble("OilPumpHigCouHour") );
			object.setOilPumpCouTimHour( rs.getDouble("OilPumpCouTimHour") );
			object.setGearOilFanRunTim( rs.getDouble("GearOilFanRunTim") );
			object.setGenWaterPumpTim( rs.getDouble("GenWaterPumpTim") );
			object.setHydPumpCouTimHour( rs.getDouble("HydPumpCouTimHour") );
			object.setYawMotorLftHour( rs.getDouble("YawMotorLftHour") );
			object.setYawMotorRtghHour( rs.getDouble("YawMotorRtghHour") );
			object.setCurMonAvlbltRat( rs.getDouble("CurMonAvlbltRat") );
			object.setLasMonAvlbltRat( rs.getDouble("LasMonAvlbltRat") );
			object.setCurYeaAvlbltRat( rs.getDouble("CurYeaAvlbltRat") );
			object.setLasYeaAvlbltRat( rs.getDouble("LasYeaAvlbltRat") );
			object.setCurMonAvlbltHour( rs.getDouble("CurMonAvlbltHour") );
			object.setCurMonNormTim( rs.getDouble("CurMonNormTim") );
			object.setCurMonRunTim( rs.getDouble("CurMonRunTim") );
			object.setCurMonStopTim( rs.getDouble("CurMonStopTim") );
			object.setCurMonErrEmeTim( rs.getDouble("CurMonErrEmeTim") );
			object.setCurMonSerTim( rs.getDouble("CurMonSerTim") );
			object.setCurMonGridErrTim( rs.getDouble("CurMonGridErrTim") );
			object.setAutoResetTimes( rs.getDouble("AutoResetTimes") );
			object.setWinTurStCovCont( rs.getDouble("WinTurStCovCont") );
			object.setSerModTimes( rs.getDouble("SerModTimes") );
			object.setEmeStoTimes( rs.getDouble("EmeStoTimes") );
			object.setBraCou( rs.getDouble("BraCou") );
			object.setOilPmpHigCou( rs.getDouble("OilPmpHigCou") );
			object.setOilPmpLowCou( rs.getDouble("OilPmpLowCou") );
			object.setOilFanRunCoun( rs.getDouble("OilFanRunCoun") );
			object.setHydPumpCou( rs.getDouble("HydPumpCou") );
			object.setYawMotorCWCou( rs.getDouble("YawMotorCWCou") );
			object.setYawMotorCCWCou( rs.getDouble("YawMotorCCWCou") );
			object.setWinTurErrCovCont( rs.getDouble("WinTurErrCovCont") );
			object.setConvMaiSwitchCou( rs.getDouble("ConvMaiSwitchCou") );
			object.setWinHigErrTimes( rs.getDouble("WinHigErrTimes") );
			object.setWinTurCatInCont( rs.getDouble("WinTurCatInCont") );
			object.setWinTurArtStpCont( rs.getDouble("WinTurArtStpCont") );
		
		return object;
	}
}


}
