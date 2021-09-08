package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class DistrictListDataRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		Map<String, String> dataMap=new HashMap<String, String>();
		dataMap.put("id", rs.getString("id"));
		dataMap.put("name", rs.getString("name"));
		dataMap.put("capacity", rs.getString("capacity"));
		dataMap.put("power", rs.getString("power"));
		dataMap.put("genWh", rs.getString("genWh"));
		dataMap.put("total_genWh", rs.getString("total_genWh"));
		dataMap.put("profit", rs.getString("profit"));
		return dataMap;
	}
	
}