package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class MapAPPDataRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		
	/*	String str="select r.todaygenwh as todaygenwh ���շ�������,r.totalgenwh/1000 as totalgenwh �ۼƷ�����,"
				+ "r.totalgencpty/1000 as totalgencpty װ������,"
		+ "avg(w.windvelval)as windvelval ����,  s.longitude as longitude ����,s.latitude as latitude γ��,"
		+ "r.curp/1000 as curp ����,(r.curp/r.totalgencpty)*100 as availability,s.name as name ��վ�� "
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
