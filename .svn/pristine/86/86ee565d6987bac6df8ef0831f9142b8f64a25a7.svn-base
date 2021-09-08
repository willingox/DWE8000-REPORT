package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


public class OverviewRankDataRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		Map<String, String> dataMap=new HashMap<String, String>();
		dataMap.put("id", rs.getString("id"));
		dataMap.put("rankNum", Integer.toString(rs.getRow()));
		dataMap.put("name", rs.getString("name"));
		dataMap.put("value", rs.getString("value"));
		return dataMap;
	}
	
}
