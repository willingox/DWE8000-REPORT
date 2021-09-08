package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


	public class webViewDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			Map<String, String> dataMap=new HashMap<String, String>();
			dataMap.put("xAxis", rs.getString("xAxis").replace(" ", "\n"));
			dataMap.put("data", rs.getString("data"));
			return dataMap;
		}
	}
