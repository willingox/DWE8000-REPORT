package com.xjgc.wind.datastatistics.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;







import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.datastatistics.dao.IRuningInfo_SelectOneDayDao;
import com.xjgc.wind.datastatistics.vo.DataStatisticsDataVo;




public class RuningInfo_SelectOneDayDaoImpl extends JdbcDaoSupport implements IRuningInfo_SelectOneDayDao{

	
	
	public List<DataStatisticsDataVo> list(Integer flag) {
		   String sql = "select columndes ,columnname  from bussclmncfg  where webshowflag = 1 "; 
	    	if (flag != null) {
				sql += " and busstblcfgid=" + flag;
			}
	return getJdbcTemplate().query(sql, new RuningInfo_SelectOneDayDataRowMapper());
	
}
	
	public List<DataStatisticsDataVo> BayList(Integer flag) {
		String sqltypeid = "select querycondition from busstblcfg where id =" +flag;
		String typeid=(String) getJdbcTemplate().queryForObject(sqltypeid,new Object[]{},java.lang.String.class);
		String sql = "select id  ,name from bay ";
		if(typeid.length()!=0){
		sql+=" where  " + typeid;
		}
		sql+=" group by id,name order by id";
		return getJdbcTemplate().query(sql, new RuningInfo_SelectOneDayBayDataRowMapper());
		
	}
	

class RuningInfo_SelectOneDayDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setColumndes( rs.getString("columndes") );
		object.setColumnName( rs.getString("columnname") );
		return object;
	}
}
class RuningInfo_SelectOneDayBayDataRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
		DataStatisticsDataVo object = new DataStatisticsDataVo();
		object.setId( rs.getInt("id") );
		object.setName( rs.getString("name") );
		return object;
	}
}
}
