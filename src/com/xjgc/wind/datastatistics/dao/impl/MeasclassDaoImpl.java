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



import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IMeasclassDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;



public class MeasclassDaoImpl extends JdbcDaoSupport implements IMeasclassDao {

	
	
	
	//获取可利用率历史数据列表(不分页)
	
	public List<DataStatisticsDataVo> list() {
   String sql = "select ID, NAME from measclass order by id";
    
		return getJdbcTemplate().query(sql, new DailyReportDataRowMapper());
		
	}
	class DailyReportDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("ID") );
			object.setName( rs.getString("NAME") );
			return object;
		}
	}
	
}



	
	
