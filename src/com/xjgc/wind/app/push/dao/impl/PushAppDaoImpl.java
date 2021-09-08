package com.xjgc.wind.app.push.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.xjgc.wind.app.push.dao.PushAppDao;
import com.xjgc.wind.app.vo.GeneralRowMapper;

public class PushAppDaoImpl extends JdbcDaoSupport implements PushAppDao{
		
	public List<Map> getAllBreakdown() {
		
		String sql="";
		sql+="select  s.id, concat(s.name,' ',v.name) as gzname ,b.name as fzname,n.name as dzname ,ifnull(s.CurDataTime ,0) as datatime";
		sql+=" from rtstatus s, meastype y, StValType v,bay b,station n";
		sql+=" where s.meastypeid=y.id and v.meastypeid = y.id and v.statetype=5 and b.id=s.bayid and s.stationid=n.id and s.curvalue=v.value ";
		sql+=" order by dzname";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	
	
	public List<Map> getBreakdownByTime(String startTime, String endTime) {
		
		String sql="";
		sql+="select  s.id, concat(s.name,' ',v.name) as gzname ,b.name as fzname,n.name as dzname ,ifnull(s.CurDataTime ,0) as datatime";
		sql+=" from rtstatus s, meastype y, StValType v,bay b,station n";
		sql+=" where s.meastypeid=y.id and v.meastypeid = y.id and v.statetype=5 and b.id=s.bayid and s.stationid=n.id and s.curvalue=v.value and s.appshowflag=1 and s.CurDataTime>='"+startTime+"' and s.curDataTime<='"+endTime+"'";                                                       
		sql+=" order by dzname";
		
		
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	
	public List<Map> getLastStatusBreakdownByTime(String startTime ,String endTime){
		
		String sql="";
		sql+="select  s.id, concat(s.name,' ',v.name) as gzname ,b.name as fzname,n.name as dzname ,ifnull(s.lastvaltime ,0) as datatime";
		sql+=" from rtstatus s, meastype y, StValType v,bay b,station n";
		sql+=" where s.meastypeid=y.id and v.meastypeid = y.id and v.statetype=5 and b.id=s.bayid and s.stationid=n.id and s.lastvalue=v.value and s.appshowflag=1 and s.lastvaltime>='"+startTime+"' and s.lastvaltime<='"+endTime+"'";                                                       
		sql+=" order by dzname";
		
		
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	}
	
	
	
}
