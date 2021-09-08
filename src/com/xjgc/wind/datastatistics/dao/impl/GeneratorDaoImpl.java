package com.xjgc.wind.datastatistics.dao.impl;


import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;




import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IGeneratorDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class GeneratorDaoImpl extends JdbcDaoSupport implements IGeneratorDao {


	
	public List<DataStatisticsDataVo> list() {
   String sql = "select ID, NAME,totalgenwhid from generator order by id";
    
		return getJdbcTemplate().query(sql, new GendataRowMapper());
		
	}
	
	public List<DataStatisticsDataVo> listu(String name,char id) {
		   
		String sql = "";
		if(name.compareTo("YM") == 0)
			sql += "select u.code as name from Accumulator a,MeasUnit u where a.id = "+id+" and a.MeasUnitID=u.id";
		else 
			sql += "select u.code as name from analog a,MeasUnit u where a.id = "+id+" and a.MeasUnitID=u.id";

		  return getJdbcTemplate().query(sql, new GendatauRowMapper());
				
			}
	class GendataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("ID") );
			object.setName1(rs.getString("totalgenwhid"));
			object.setName( rs.getString("NAME") );
			return object;
		}
	}
	
	class GendatauRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName2(rs.getString("name"));
			return object;
		}
	}
}



	
	
