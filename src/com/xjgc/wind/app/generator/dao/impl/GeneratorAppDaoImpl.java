package com.xjgc.wind.app.generator.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.generator.dao.GeneratorAppDao;
import com.xjgc.wind.app.station.dao.StationAppDao;
import com.xjgc.wind.app.util.MyDate;
import com.xjgc.wind.app.util.SqlUtil;
import com.xjgc.wind.app.vo.GeneralRowMapper;
import com.xjgc.wind.app.vo.webViewDataRowMapper;

public class GeneratorAppDaoImpl extends JdbcDaoSupport implements GeneratorAppDao {
	
	

	public List<Map> getGeneratorInfoById(String id ) {
		
		String sql="";
		sql+="select "
		+"g.id as id"
		+",g.name as name"
		+",g.capacity as capcity"
		+",case g.curcmpstate "
		+"	when 0 then  '停运' "
		+"	when 1 then  '待机' "
		+"	when 2 then  '全功率运行' "
		+"	when 3 then  '限功率运行' "
		+"	when 4 then  '检修' "
		+"	when 5 then  '故障' "
		+"	when 6 then  '离网' "
		+"	when 7 then  '通讯中断' "
		+"	end as curstate "
		+",w.WINDVELVAL as windspeed"
		+",g.curp as curp"
		+",g.todaygenwh as todaygenwh "
		+" from rtgenerator g , rtweatherdata w"
		+" where g.id=w.equipid and g.id ="+id;
		return getJdbcTemplate().query(sql, new GeneralRowMapper());	
	}
	
	
	//获取发电设备实时功率数据List<Map>
	//统计一天之内的数据
	public List<Map> getGeneratorPowerRealtimeData(String id){
		String _year=MyDate.getYear();
		String _lastYear=MyDate.getLastyearStr();
		String sql = "";
		if(!MyDate.nowTimeInFistDay()||!isTableExist("hisgenerator_"+ _lastYear)){
			sql +="select left(g.savetime,16) as xAxis ";
			sql +=",cast( g.curp as decimal(10,2)) as data ";
			sql +=" from hisgenerator_"+ _year+ " g where g.savetime >= now()-interval 24 hour and g.savetime <=now() and g.id="+id;
	    	//sql +="select left(savetime,16) as xAxis,left(sum(curp)/1000,4) as data from hisgenerator_2016 where savetime >= '2016-05-18 17:15:00' and savetime <='2016-05-19 17:15:00'  group by  savetime ";
		}else{
			sql +="(select left(lg.savetime,16) as xAxis ";
			sql +=",cast(lg.curp as decimal(10,2))as data ";
			sql +=" from hisgentypestat_"+ _lastYear+ " lg where lg.savetime >= now()-interval 24 hour and lg.savetime <=now() and lg.id="+id+") union ";
			sql +="(select left(g.savetime,16) as xAxis ";
			sql +=",cast(g.curp as decimal(10,2)) as data ";
			sql +=" from hisgenerator_"+ _year+ " g where g.savetime >= now()-interval 24 hour and g.savetime <=now() and g.id="+id+")";
		}
		
		return getJdbcTemplate().query(sql, new webViewDataRowMapper());
	}
	
	
	//获取发电设备历史数据List<Map>
	//统计一周之内的数据间隔一小时
	public List<Map> getGeneratorHisPowerDataList(String id){
		String _year=MyDate.getYear();
		String _lastYear=MyDate.getLastyearStr();
		String sql = "";
		if(!MyDate.nowTimeInFistWeek()||!isTableExist("hisgenerator_"+ _lastYear)){
			sql +="select left(g.SAVETIME,16) as xAxis ";
			sql +=",cast( g.CURP as decimal(10,2)) as data ";
			sql +="from hisgenerator_"+ _year+ "  g WHERE  right(g.SAVETIME,5)='00:00' and g.savetime >= now()-interval 7 day and g.id="+id+" ORDER BY g.SAVETIME";
		}else{
			sql +="( select left(lg.SAVETIME,16) as xAxis ";
			sql +=",cast( lg.CURP as decimal(10,2)) as data ";
			sql +="from hisgenerator_"+ _lastYear+ "  lg WHERE  right(lg.SAVETIME,5)='00:00' and lg.savetime >= now()-interval 7 day and lg.id="+id+" ORDER BY lg.SAVETIME ) union";
			sql +="( select left(g.SAVETIME,16) as xAxis ";
			sql +=",cast( g.CURP as decimal(10,2)) as data ";
			sql +="from hisgenerator_"+ _year+ "  g WHERE  right(g.SAVETIME,5)='00:00' and g.savetime >= now()-interval 7 day and g.id="+id+" ORDER BY g.SAVETIME )";
		}
    	
		return getJdbcTemplate().query(sql, new webViewDataRowMapper());
	}
	
		
	public List<Map> getGeneratorHisWindSpeedDataList(String id){
		String _year=MyDate.getYear();
		String _lastYear=MyDate.getLastyearStr();
		String sql = "";
		if(!MyDate.nowTimeInFistWeek()||!isTableExist("hisweatherdata_"+ _lastYear)){
			sql +="select left(SAVETIME,16) as xAxis ";
			sql +=",truncate(windvelval ,2) as data ";
			sql +="from hisweatherdata_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day and equipID="+id+" ORDER BY SAVETIME";
		}else{
			sql +="(select left(SAVETIME,16) as xAxis ";
			sql +=",truncate(windvelval ,2) as data ";
			sql +="from hisweatherdata_"+ _lastYear+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day and equipID="+id+" ORDER BY SAVETIME) union ";
			sql +="( select left(SAVETIME,16) as xAxis ";
			sql +=",truncate(windvelval ,2) as data ";
			sql +="from hisweatherdata_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day and equipID="+id+" ORDER BY SAVETIME)";
		}
		return getJdbcTemplate().query(sql, new webViewDataRowMapper());
	}
	
	
	/**
	 * 一个月的数据  间隔是一天
	 */
	public List<Map> getGeneratorHisDayGenWhList(String id){
		String _year=MyDate.getYear();
		String _lastYear=MyDate.getLastyearStr();
		String sql = "";
    	
		
		if (!MyDate.nowTimeInFistMonth()||!isTableExist("hisgeneratorst_"+ _lastYear)){
			sql +="select left(SAVETIME,10) as xAxis ";
			sql +=",cast(TODAYGENWH as decimal(10,2)) as data ";
			sql +=" from hisgeneratorst_"+ _year +" where savetime> now()-interval 30 day  and id="+id+" ORDER BY SAVETIME";
		}else{
			sql +="(select left(SAVETIME,10) as xAxis ";
			sql +=",cast(TODAYGENWH as decimal(10,2)) as data ";
			sql +=" from hisgeneratorst_"+ _lastYear +" where savetime> now()-interval 30 day and id="+id+" ORDER BY SAVETIME) union ";
			sql +="(select left(SAVETIME,10) as xAxis ";
			sql +=",cast(TODAYGENWH as decimal(10,2)) as data ";
			sql +=" from hisgeneratorst_"+ _year +" where savetime> now()-interval 30 day  and id="+id+" ORDER BY SAVETIME)";
		}
		
		return getJdbcTemplate().query(sql, new webViewDataRowMapper());
		
	}
	
	
	/**
	 * 统计一个月的数据    时间间隔为一个月
	 * 
	 */
	public List<Map> getGeneratorHisMonthGenWhList(String id){
		String _year=MyDate.getYear();
		String _lastYear=MyDate.getLastyearStr();
		String sql = "";
    	
		if(isTableExist("hisgeneratorst_"+ _lastYear)){
		sql +="(select left(SAVETIME,7) as xAxis ";
		sql +=",cast( sum(TODAYGENWH) as decimal(10,2)) as data  ";
		sql +="from hisgeneratorst_"+ _lastYear +"  where savetime> now()-interval 1 year and id="+id+" group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME) union  ";
		sql +="(select left(SAVETIME,7) as xAxis ";
		sql +=",cast( sum(TODAYGENWH) as decimal(10,2)) as data  ";
		sql +="from hisgeneratorst_"+ _year +"  where savetime> now()-interval 1 year and id="+id+" group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME)";
		}else{
			sql +="select left(SAVETIME,7) as xAxis ";
			sql +=",cast( sum(TODAYGENWH) as decimal(10,2)) as data  ";
			sql +="from hisgeneratorst_"+ _year +"  where savetime> now()-interval 1 year and id="+id+" group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME";
		}
		return getJdbcTemplate().query(sql, new webViewDataRowMapper());
	}
	
	
	/**
	 * 获取发电设备所在间隔中的各个设备的id与名称列表
	 * 
	 */
	public List<Map> getGeneratorBayEquipmentList(String id){

		String bayid=getGeneratorBayId(id);
		String sql = "";
		
		sql +="select e.id as id, e.description as description ,e.name as name ";
		sql +="from (select distinct (r.equipid) as eid "; 
		sql +="from rtanalog r where r.bayid="+bayid+" and r.appshowflag=1 ";
		sql +="union ";
		sql +="select distinct (r.equipid) as eid ";
		sql +="from rtstatus r where r.bayid="+bayid+" and r.appshowflag=1)  a, equipment e ";
		sql +="where e.id=a.eid ";
		
		/*sql +="select e.id as id, e.description as description ,e.name as name   sql +="
		sql +="	from equipment e ";
		sql +="	where e.bayid=(select A.bayid ";
		sql +="				from equipment A ";
		sql +="				where A.id =" +id +")";*/
		
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}
	
	
	/**
	 * 获取发电设备所在间隔id
	 * 
	 */
	public String getGeneratorBayId(String id){

		String sql = "";
		sql +="select e.bayid as id ";
		sql +="	from equipment e ";
		sql +="	where e.id="+id;
		Map map=(Map)getJdbcTemplate().query(sql, new GeneralRowMapper()).get(0);
		
		return (String)map.get("id");
	}
	
	
	/**
	 * 
	 */
	public List<Map> getEquipmentMeasurePointListInAnalog(String id){
		
		String sql = "";
		sql +="select a.name as name,a.calvalue as value,(select  u.code from measunit u where a.measunitid=u.id) as unit ";
		sql +=" from rtanalog  a ";
		sql +=" where equipid="+id+" and appshowflag=1 ";
		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}
	
	
	public List<Map> getEquipmentMeasurePointListInStatus(String id){
		
		String sql = "";
		sql +="select a.name as name,a.curvalue as value ";
		sql +=" from rtstatus  a ";
		sql +=" where equipid="+id+" and appshowflag=1 ";

		return getJdbcTemplate().query(sql, new GeneralRowMapper());
		
	}
	
	
	private boolean isTableExist(String tableName){
		
		String sql ="SELECT count(*) as  num FROM information_schema.TABLES WHERE table_name ='"+tableName+"' and table_schema='"+SqlUtil.dbName+"'";
		List list=getJdbcTemplate().query(sql, new GeneralRowMapper());
		Map map=(Map)list.get(0);
		String num=(String)map.get("num");
		System.out.println(num);
		if ("0".equals(num)){return false;}
		else {return true;}
	}
	
	
}
