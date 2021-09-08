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


import com.xjgc.wind.datastatistics.dao.IWindMeaInfoMonitorDao;
import com.xjgc.wind.datastatistics.vo.WgmeasinfoDataVo;



public class WindMeaInfoMonitorDaoImpl extends JdbcDaoSupport implements IWindMeaInfoMonitorDao{

	public List<WgmeasinfoDataVo> list(){
		
		String sql = "";
		if(isDBMysql()){
			sql += "select b.name as name, " +
					"r.rctPowGri as rctPowGri,r.meanRotorSpeed as meanRotorSpeed," +
					"r.positionActual1 as  positionActual1, r.batVoltageUdc1 as batVoltageUdc1,r.bladeTorque1 as bladeTorque1,r.motorTemp1 as motorTemp1,r.cabTemp1 as cabTemp1,r. moduleTemp1 as moduleTemp1, r. voltageUdc1  as voltageUdc1, r.heaterSinkTemp1 as heaterSinkTemp1," +
					"r.positionActual2 as  positionActual2, r.batVoltageUdc2 as batVoltageUdc2,r.bladeTorque2 as bladeTorque2,r.motorTemp2 as motorTemp2,r.cabTemp2 as cabTemp2,r. moduleTemp2 as moduleTemp2, r. voltageUdc2  as voltageUdc2, r.heaterSinkTemp2 as heaterSinkTemp2," +
					"r.positionActual3 as  positionActual3, r.batVoltageUdc3 as batVoltageUdc3,r.bladeTorque3 as bladeTorque3,r.motorTemp3 as motorTemp3,r.cabTemp3 as cabTemp3,r. moduleTemp3 as moduleTemp3, r. voltageUdc3  as voltageUdc3, r.heaterSinkTemp3 as heaterSinkTemp3," +
					"r.gearOilPumPres as gearOilPumPres,r.gearOilInPres as gearOilInPres,r.gearOilInTemp as gearOilInTemp,r.geaOilTankTemp as geaOilTankTemp,r.geaOilHeaTemp as geaOilHeaTemp,r.geaBeaTemp1 as geaBeaTemp1,r.geaBeaTemp2 as geaBeaTemp2,r.geaBeaTemp3 as geaBeaTemp3,r.maxGeaBeaTemp as maxGeaBeaTemp," +
					"r.genBearDETemp as genBearDETemp,r.genBearNDETemp as genBearNDETemp,r.genWatInTemp as genWatInTemp,r.genTempU as genTempU,r.genTempV as genTempV,r.genTempW as genTempW," +
					"r.powMeaSorCurI as powMeaSorCurI,r.powMeaLinFrq as powMeaLinFrq,r.actPowAuxiliary as actPowAuxiliary,r.meanGscTemp as meanGscTemp,r.meanLscTemp as meanLscTemp,r.winSpeTur0 as winSpeTur0,r.nacPsnErrDem as nacPsnErrDem,r.outsideTemp as outsideTemp,r.nacBoxOutsideTemp as nacBoxOutsideTemp," + 
					"r.yawCalspeed as yawCalspeed,r.meanTBCInTemp as meanTBCInTemp,r.meanNC310InTemp as meanNC310InTemp,r.meanTBCOutTemp as meanTBCOutTemp,r.powMeaPf as powMeaPf,r.powRat as powRat,r.CableTwistTotal as CableTwistTotal,r.volU12 as volU12,r.volU23 as volU23,r.volU31 as volU31,r.curI1 as curI1,r.curI2 as curI2,r.curI3 as curI3," +
	                "r.powLmtHMIWPC as powLmtHMIWPC,r.rePowLmtHMIWPC as rePowLmtHMIWPC,r.speLmtHMIWPC as speLmtHMIWPC,r.winSpeVal0 as winSpeVal0,r.winSpeVal1 as winSpeVal1,r.auxTransforTemp as auxTransforTemp,r.actPowGri as actPowGri,r.windSpeed as windSpeed,r.winDirNor as winDirNor,r.meanGenSpeed as meanGenSpeed" +
					"  from rtwgmeasinfo r,bay b where r.id=b.id order by r.id  ";
			
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
		WgmeasinfoDataVo object = new WgmeasinfoDataVo();
		    object.setName( rs.getString("name") );
			object.setRctPowGri( rs.getDouble("rctPowGri") );
			object.setMeanRotorSpeed( rs.getDouble("meanRotorSpeed") );
			object.setPositionActual1( rs.getDouble("positionActual1") );
			object.setBatVoltageUdc1( rs.getDouble("batVoltageUdc1") );
			object.setBladeTorque1( rs.getDouble("bladeTorque1") );
			object.setMotorTemp1( rs.getDouble("motorTemp1") );
			object.setCabTemp1( rs.getDouble("cabTemp1") );
			object.setModuleTemp1( rs.getDouble("moduleTemp1") );
			object.setVoltageUdc1( rs.getDouble("voltageUdc1") );
			object.setHeaterSinkTemp1( rs.getDouble("heaterSinkTemp1") );
			object.setPositionActual2( rs.getDouble("positionActual2") );
			object.setBatVoltageUdc2( rs.getDouble("batVoltageUdc2") );
			object.setBladeTorque2( rs.getDouble("bladeTorque2") );
			object.setMotorTemp2( rs.getDouble("motorTemp2") );
			object.setCabTemp2( rs.getDouble("cabTemp2") );
			object.setModuleTemp2( rs.getDouble("moduleTemp2") );
			object.setVoltageUdc2( rs.getDouble("voltageUdc2") );
			object.setHeaterSinkTemp2( rs.getDouble("heaterSinkTemp2") );
			object.setPositionActual3( rs.getDouble("positionActual3") );
			object.setBatVoltageUdc3( rs.getDouble("batVoltageUdc3") );
			object.setBladeTorque3( rs.getDouble("bladeTorque3") );
			object.setMotorTemp3( rs.getDouble("motorTemp3") );
			object.setCabTemp3( rs.getDouble("cabTemp3") );
			object.setModuleTemp3( rs.getDouble("moduleTemp3") );
			object.setVoltageUdc3( rs.getDouble("voltageUdc3") );
			object.setHeaterSinkTemp3( rs.getDouble("heaterSinkTemp3") );
			object.setGearOilPumPres( rs.getDouble("gearOilPumPres") );
			object.setGearOilInPres( rs.getDouble("gearOilInPres") );
			object.setGearOilInTemp( rs.getDouble("gearOilInTemp") );
			object.setGeaOilTankTemp( rs.getDouble("geaOilTankTemp") );
			object.setGeaOilHeaTemp( rs.getDouble("geaOilHeaTemp") );
			object.setGeaBeaTemp1( rs.getDouble("geaBeaTemp1") );
			object.setGeaBeaTemp2( rs.getDouble("geaBeaTemp2") );
			object.setGeaBeaTemp3( rs.getDouble("geaBeaTemp3") );
			object.setMaxGeaBeaTemp( rs.getDouble("maxGeaBeaTemp") );
			object.setGenBearDETemp( rs.getDouble("genBearDETemp") );
			object.setGenBearNDETemp( rs.getDouble("genBearNDETemp") );
			object.setGenWatInTemp( rs.getDouble("genWatInTemp") );
			object.setGenTempU( rs.getDouble("genTempU") );
			object.setGenTempV( rs.getDouble("genTempV") );
			object.setGenTempW( rs.getDouble("genTempW") );
			object.setPowMeaSorCurI( rs.getDouble("powMeaSorCurI") );
			object.setPowMeaLinFrq( rs.getDouble("powMeaLinFrq") );
			object.setActPowAuxiliary( rs.getDouble("actPowAuxiliary") );
			object.setMeanGscTemp( rs.getDouble("meanGscTemp") );
			object.setMeanLscTemp( rs.getDouble("meanLscTemp") );
			object.setWinSpeTur0( rs.getDouble("winSpeTur0") );
			object.setNacPsnErrDem( rs.getDouble("nacPsnErrDem") );
			object.setOutsideTemp( rs.getDouble("outsideTemp") );
			object.setNacBoxOutsideTemp( rs.getDouble("maxGeaBeaTemp") );
			object.setYawCalspeed( rs.getDouble("yawCalspeed") );
			object.setMeanTBCInTemp( rs.getDouble("meanTBCInTemp") );
			object.setMeanNC310InTemp( rs.getDouble("meanNC310InTemp") );
			object.setMeanTBCOutTemp( rs.getDouble("meanTBCOutTemp") );
			object.setPowMeaPf( rs.getDouble("powMeaPf") );
			object.setPowRat( rs.getDouble("powRat") );
			object.setCableTwistTotal( rs.getDouble("cableTwistTotal") );
			object.setVolU12( rs.getDouble("volU12") );
			object.setVolU23( rs.getDouble("volU23") );
			object.setVolU31( rs.getDouble("volU31") );
			object.setCurI1( rs.getDouble("curI1") );
			object.setCurI2( rs.getDouble("curI2") );
			object.setCurI3( rs.getDouble("curI3") );
			object.setPowLmtHMIWPC( rs.getDouble("powLmtHMIWPC") );
			object.setRePowLmtHMIWPC( rs.getDouble("rePowLmtHMIWPC") );
			object.setSpeLmtHMIWPC( rs.getDouble("speLmtHMIWPC") );
			object.setWinSpeVal0( rs.getDouble("winSpeVal0") );
			object.setWinSpeVal1( rs.getDouble("winSpeVal1") );
			object.setAuxTransforTemp( rs.getDouble("auxTransforTemp") );
			object.setActPowGri( rs.getDouble("actPowGri") );
			object.setWindSpeed( rs.getDouble("windSpeed") );
			object.setWinDirNor( rs.getDouble("winDirNor") );
			object.setMeanGenSpeed( rs.getDouble("meanGenSpeed") );
			
		return object;
	}
}


}
