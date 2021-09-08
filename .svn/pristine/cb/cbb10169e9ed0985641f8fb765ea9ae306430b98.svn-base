package com.xjgc.wind.app.station.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.vo.GeneralRowMapper;
import com.xjgc.wind.app.station.dao.StationAppDao;

public class StationAppDaoImpl extends JdbcDaoSupport implements StationAppDao {

	
	
	/**
	 * 获取数据库中的电站坐标 使用第一个电站经纬度
	 * 该版本为单个电站所以应用只用第一个电站的信息
	 */
	
	public List<Map> getStationsLocation() {
		
		String sql="";
		sql+="select "
		+"s.id as id"
		+",s.name as name "
		+",s.latitude as latitude"
		+",s.longitude as longitude"
		+" from smgsysinfo s ";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}
	
	public List<Map> getGeneratorsCapacity() {
		
		String sql="";
		sql+="select "
		 +"cast(rtg.capacity as decimal(10,2)) as capacity"
		+",rtg.id  as id "
		+",truncate(avg(btd.latitude) ,4) as latitude"
		+",truncate(avg(btd.longitude),4) as longitude"
		+",e.name as name"
		+" from rtgenerator rtg ,bayltd  btd,equipment e "
		+" where rtg.id=e.id and e.bayid=btd.bayid "
		+" group by rtg.id";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}

	public List<Map> getGeneratorsCurp() {
		
		String sql="";
		sql+="select "
		 +"cast(rtg.curp as decimal(10,2)) as curp"
		+",rtg.id  as id "
		+",truncate(avg(btd.latitude) ,4) as latitude"
		+",truncate(avg(btd.longitude),4) as longitude"
		+",e.name as name"
		+" from rtgenerator rtg ,bayltd  btd,equipment e "
		+" where rtg.id=e.id and e.bayid=btd.bayid "
		+" group by rtg.id";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}

	public List<Map> getGeneratorsWindSpeed() {
		String sql="";
		sql+="select " 
		+"w.windvelval as windspeed"
		+",e.id as id" 
		+",truncate(avg(b.latitude) ,4) as latitude"
		+",truncate(avg(b.longitude),4) as longitude"
		+",e.name as name"
		+" from bayltd  b,equipment e , rtweatherdata w"
		+" where e.bayid=b.bayid and e.id=w.equipid" 
		+" group by e.id";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}

	public List<Map> getGeneratorsTodaygenwh() {
		String sql="";
		sql+="select "
		 +"cast(g.todaygenwh as decimal(10,2)) as todaygenwh"
		 +",g.id  as id "
		 +",truncate(avg(b.latitude) ,4) as latitude"
		 +",truncate(avg(b.longitude),4) as longitude"
		 +",e.name as name"
		 +" from rtgeneratorst g ,bayltd  b,equipment e" 
		 +" where g.id=e.id and e.bayid=b.bayid"
		 +" group by g.id";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}

	public List<Map> getGeneratorsTotalgenwh() {
		String sql="";
		sql+="select "
		 +"cast(g.totalgenwh as decimal(10,2)) as totalgenwh"
		 +",g.id  as id "
		 +",truncate(avg(b.latitude) ,4) as latitude"
		 +",truncate(avg(b.longitude),4) as longitude"
		 +",e.name as name"
		 +" from rtgeneratorst g ,bayltd  b,equipment e" 
		 +" where g.id=e.id and e.bayid=b.bayid"
		 +" group by g.id";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}


	public List<Map> getGeneratorsCurstate() {
		String sql="";
		sql+="select "
		 +" g.id  as id "
		 +",g.name as name "
		 +",case g.curcmpstate "  
		 +" when 0 then  '停运' " 
		 +" when 1 then  '待机' "
		 +" when 2 then  '全功率运行' "
		 +" when 3 then  '限功率运行' "
		 +" when 4 then  '检修' "
		 +" when 5 then  '故障' "
		 +" when 6 then  '离网' "
		 +" when 7 then  '通讯中断' "
		 +" end as curstate "
		 +",cast(g.curp as decimal(10,2)) as curp "
		 +",cast(gst.todaygenwh as decimal(10,2)) as todaygenwh "
		 +",cast(gst.totalgenwh as decimal(10,2)) as totalgenwh "
		 +" from rtgeneratorst gst , rtgenerator g "
		 +" where g.id=gst.id "
		 +" group by g.id ";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	

}
