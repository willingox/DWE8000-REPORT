package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class MapAPPDataRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		
	/*	String str="select r.todaygenwh as todaygenwh 今日发发电量,r.totalgenwh/1000 as totalgenwh 累计发电量,"
				+ "r.totalgencpty/1000 as totalgencpty 装机容量,"
		+ "avg(w.windvelval)as windvelval 风速,  s.longitude as longitude 经度,s.latitude as latitude 纬度,"
		+ "r.curp/1000 as curp 功率,(r.curp/r.totalgencpty)*100 as availability,s.name as name 场站名 "
		+ "from rtgentypestat r,smgsysinfo s,rtweatherdata w "
		+ "where r.EQUIPTYPEID=202 and w.smgid=r.smgid and r.smgid=s.id group by r.smgid";*/
		
		Map<String, String> dataMap=new HashMap<String, String>();
		dataMap.put("name", rs.getString("name"));
		dataMap.put("todaygenwh", rs.getString("todaygenwh"));
		dataMap.put("totalgenwh", rs.getString("totalgenwh"));
		dataMap.put("totalgencpty", rs.getString("totalgencpty"));
		dataMap.put("windvelval", rs.getString("windvelval"));
		dataMap.put("longitude", rs.getString("longitude"));
		dataMap.put("latitude", rs.getString("latitude"));
		dataMap.put("curp", rs.getString("curp"));
		dataMap.put("availability", rs.getString("availability"));
		dataMap.put("id", rs.getString("id"));
		
		return dataMap;
	}
	
}
