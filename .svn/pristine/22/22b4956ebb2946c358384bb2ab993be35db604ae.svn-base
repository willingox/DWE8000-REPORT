package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class GeneralRowMapper implements RowMapper{

	
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		Map columnMap=new HashMap();
		ResultSetMetaData rsmd = arg0.getMetaData() ; 
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
			columnMap.put( rsmd.getColumnLabel(i), arg0.getString(i));
		}
		return columnMap;
	}
	
}
