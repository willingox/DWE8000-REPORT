package com.xjgc.wind.app.vo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.xjgc.wind.app.rank.dao.RankAPPDao;


public class OverviewGeneralInfoRowMapper implements RowMapper{

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		List<Map> generalInfoList=new ArrayList<Map>();
		ResultSetMetaData rsmd = rs.getMetaData() ; 
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{	
			Map columnMap=new HashMap();
			columnMap.put("name", rsmd.getColumnLabel(i));
			columnMap.put("value", rs.getString(i));
			generalInfoList.add(columnMap);
		}
		return generalInfoList;
	}
	
}
