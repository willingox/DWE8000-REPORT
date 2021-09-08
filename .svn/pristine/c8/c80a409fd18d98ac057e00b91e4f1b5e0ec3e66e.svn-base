package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class GeneratorListByMgIdDataRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		Map columnMap=new HashMap();
		ResultSetMetaData rsmd = rs.getMetaData() ; 
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{	
			columnMap.put( rsmd.getColumnLabel(i), rs.getString(i));
		}
		return columnMap;
	}
	
}