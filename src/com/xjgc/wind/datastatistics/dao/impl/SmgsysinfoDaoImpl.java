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
import com.xjgc.wind.datastatistics.dao.ISmgsysinfoDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;


import com.xjgc.wind.util.DateFormatUtil;


public class SmgsysinfoDaoImpl extends JdbcDaoSupport implements ISmgsysinfoDao {


	
	public List<DataStatisticsDataVo> list() {
   String sql = "select statetype,name from stvaltype where statetype = 5 or statetype = 6 group by statetype";
    
		return getJdbcTemplate().query(sql, new smgsysinfoDataRowMapper());
		
	}
	class smgsysinfoDataRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
			DataStatisticsDataVo object = new DataStatisticsDataVo();
			object.setName(rs.getString("name"));
			object.setId( rs.getInt("statetype") );
			return object;
		}
	}
	
}



	
	
