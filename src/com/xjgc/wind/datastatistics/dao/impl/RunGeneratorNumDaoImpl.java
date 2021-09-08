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
import com.xjgc.wind.datastatistics.dao.IRunGeneratorNumDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class RunGeneratorNumDaoImpl extends JdbcDaoSupport implements IRunGeneratorNumDao {


	
	public List<DataStatisticsDataVo> list() {
		String sql = "";
		
	    sql +="select runnumb as count ";
			sql += " from rtgentypestat   ";
	    	
	   
    
		return getJdbcTemplate().query(sql, new GendataRowMapper());
		
	}
	
	
	class GendataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setCount( rs.getInt("count") );
			return object;
		}
	}
	
}