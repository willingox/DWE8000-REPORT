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

import com.xjgc.wind.datastatistics.dao.IRuningInfo_SelectTenMinuteDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;
import com.xjgc.wind.datastatistics.web.form.RuningInfo_SelectTenMinuteDataForm;
import com.xjgc.wind.querytree.vo.ResultVO;
import com.xjgc.wind.util.DateFormatUtil;
import com.xjgc.wind.util.YMDHMSUtil;
import com.xjgc.wind.util.YearFormatUtil;


public class RuningInfo_SelectTenMinuteDaoImpl extends JdbcDaoSupport implements IRuningInfo_SelectTenMinuteDao{

	
	
	public List<DataStatisticsDataVo> list(Integer flag) {
		   String sql = "select columndes ,columnname  from bussclmncfg  where webshowflag = 1 "; 
	    	if (flag != null) {
				sql += " and busstblcfgid=" + flag;
			}
	return getJdbcTemplate().query(sql, new RuningInfo_SelectTenMinuteDataRowMapper());
	
}
	
	public List<DataStatisticsDataVo> BayList(Integer flag) {
		String sqltypeid = "select querycondition from busstblcfg where id =" +flag;
		String typeid=(String) getJdbcTemplate().queryForObject(sqltypeid,new Object[]{},java.lang.String.class);
		String sql = "select id  ,name from bay ";
		if(typeid.length()!=0){
		sql+=" where  " + typeid;
		}
		sql+=" group by id,name order by id";
		return getJdbcTemplate().query(sql, new RuningInfo_SelectTenMinuteBayDataRowMapper());
		
	}
	

class RuningInfo_SelectTenMinuteDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setColumndes( rs.getString("columndes") );
		object.setColumnName( rs.getString("columnname") );
		return object;
	}
}
class RuningInfo_SelectTenMinuteBayDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setId( rs.getInt("id") );
		object.setName( rs.getString("name") );
		return object;
	}
}
}
