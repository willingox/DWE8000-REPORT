package com.xjgc.wind.datastatistics.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.vo.GeneralRowMapper;
import com.xjgc.wind.datastatistics.dao.IWindGerRtMonitorDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;



public class WindGerRtMonitorDaoImpl extends JdbcDaoSupport implements IWindGerRtMonitorDao{
	
	
	// 统计当前电站的设备运行状态
	public List<Map> getGeneratorCurState() {
		String sql = "";

		sql += "select ";
		sql += "sum(case when curcmpstate=0  then 1 else 0 end ) as  \"stop\" ,"; 
		sql += "sum(case when curcmpstate=1  then 1 else 0 end ) as  \"gridOnStandby\" ,"; 
		sql += "sum(case when curcmpstate=2  then 1 else 0 end ) as  \"fullRun\" ,";
		sql += "sum(case when curcmpstate=3  then 1 else 0 end ) as  \"limitRun\" ,";
		sql += "sum(case when curcmpstate=4  then 1 else 0 end ) as  \"overhaul\" ,";
		sql += "sum(case when curcmpstate=5  then 1 else 0 end ) as  \"breakdown\" ,";
		sql += "sum(case when curcmpstate=6  then 1 else 0 end ) as  \"gridOff\" ";
		sql += "from rtgenerator  ";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	
	// 统计当前电站的功率，天气状态
	public List<Map> getGeneratorCurState1() {
		String sql = "";
		
		sql += " select smg.curp as \"curp\" , avg(w.windvelval) as \"sunlightval\" , avg(w.tempval) as \"tempval\" ";
		sql += " from   rtgentypestat smg , rtweatherdata w ";
		sql += " where smg.smgid=w.smgid ";
		sql += " group by smg.smgid ";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
		
	
	// 统计当前电站的今日发电量，今日上网电量，今日最大发电功率和发生时间
	public List<Map> getGeneratorCurState2() {
		String sql = "";
		sql += " select smg.todayupnetwh as \"todayupnetwh\" ,smg.todaygenwh as \"todaygenwh\", smg.maxgenpw  as \"maxgenpw\" ,smg.maxgenptime as \"maxgenptime\" ";
		sql += " from   rtsmgsysinfost smg "; 
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	
	
	public List<DataStatisticsDataVo> generatorCurStateList(){
		String sql = "";
		sql += "select g.id as id ,g.name as name , w.WindSpeed as WindSpeed ,w.MeanGenSpeed as MeanGenSpeed ,g.curp as curp ,g.todaygenwh as todaygenwh , g.curcmpstate as curcmpstate ";                     
		sql += "from rtgenerator g,bay b,equipment e, rtwgimptinfo w "; 
		sql += "where g.id=e.id and e.bayid=b.id and b.id=w.id order by g.id "; 
		return getJdbcTemplate().query(sql, new GeneratorDataRowMapper());
	}
	
	
	public List<DataStatisticsDataVo> generatorCurStateList(int argetType,
			int sortType,int stateType) {
		String p1 = "";
		if (1==argetType){
			p1="g.curp";
		}else{
			p1="g.todaygenwh";
		}
		
		String p2 = "";
		if (1==sortType){
			p2="desc";
		}else{
			p2="asc";
		}
		
		String p3 = "";
		if (-1==stateType){
			p3="";
		}else{
			p3=" and g.curcmpstate="+stateType;
		}
		
		
		String sql = "";
		sql += " select g.id as id ,g.name as name ,  g.CURP as curp ,g.todaygenwh as todaygenwh , w.WindSpeed as WindSpeed ,w.MeanGenSpeed as MeanGenSpeed , g.curcmpstate as curcmpstate ";                     
		sql += " from rtgenerator g,bay b,equipment e, rtwgimptinfo w "; 
		sql += " where g.id=e.id and e.bayid=b.id and b.id=w.id "+p3; 
		sql += " order by "+p1+" "+p2;
		
		return getJdbcTemplate().query(sql, new GeneratorDataRowMapper());
	}
	
	
	class GeneratorDataRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId(rs.getInt("id"));
			object.setName(rs.getString("name"));
			object.setCurp(rs.getDouble("curp"));
			object.setTodayGenwh(rs.getDouble("todaygenwh"));
			object.setCurcmpState(rs.getInt("curcmpstate"));
			object.setWindSpeed(rs.getString("windSpeed"));
			object.setMeanGenSpeed(rs.getString("meanGenSpeed"));
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


