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

import com.xjgc.wind.datastatistics.dao.IBayDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class BayDaoImpl extends JdbcDaoSupport implements IBayDao {


	
	public List<DataStatisticsDataVo> list() {
   String sql = "select ID, NAME from bay where typeid=2 or typeid=11 order by id";
    
		return getJdbcTemplate().query(sql, new BaydataRowMapper());
		
	}
	public List<DataStatisticsDataVo> listSmg() {
		   String sql = "select ID, NAME from smgsysinfo  order by id";
		    
				return getJdbcTemplate().query(sql, new BaydataRowMapper());
				
			}
	
	class BaydataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setId( rs.getInt("ID") );
			object.setName( rs.getString("NAME") );
			return object;
		}
	}
	
}



	
	
