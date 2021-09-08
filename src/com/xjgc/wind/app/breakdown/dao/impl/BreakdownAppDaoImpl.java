package com.xjgc.wind.app.breakdown.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.breakdown.dao.BreakdownAppDao;
import com.xjgc.wind.app.vo.GeneralRowMapper;

public class BreakdownAppDaoImpl extends JdbcDaoSupport implements BreakdownAppDao {


	public List<Map> getAllBreakdown() {
		// TODO Auto-generated method stub
		/*//分别是：故障点ID，故障名称，方阵名称，电站名称，发生时间
		String sql="";
		sql+="select  s.id,s.name as gzname,b.name as fzname,n.name as dzname ,ifnull(s.CurDataTime ,0) as datatime"; 
		sql+=" from status s, meastype y, StValType v,bay b,station n"; 
		sql+=" where s.meastypeid=y.id and v.meastypeid = y.id and v.statetype=5 and b.id=s.bayid and s.stationid=n.id  and s.curvalue=v.value";
		sql+=" order by dzname";*/
		String sql="";
		sql+="select  s.id, concat(s.name,' ',v.name) as gzname ,b.name as fzname,n.name as dzname ,ifnull(s.CurDataTime ,0) as datatime";
		sql+=" from rtstatus s, meastype y, StValType v,bay b,station n";
		sql+=" where s.meastypeid=y.id and v.meastypeid = y.id and v.statetype=5 and b.id=s.bayid and s.stationid=n.id and s.curvalue=v.value ";
		sql+=" order by dzname";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
	
	}
	
}
