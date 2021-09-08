package com.xjgc.wind.app.overview.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.overview.dao.OverviewAPPDao;
import com.xjgc.wind.app.util.MyDate;
import com.xjgc.wind.app.util.SqlUtil;
import com.xjgc.wind.app.vo.GeneralRowMapper;
import com.xjgc.wind.app.vo.OverviewGeneralInfoRowMapper;
import com.xjgc.wind.app.vo.webViewDataRowMapper;

public class OverviewAPPDaoImpl extends JdbcDaoSupport implements OverviewAPPDao {
	
		public List<Map>  getGeneralInfoData(){
			String sql = "";
			sql +=" select";
			sql +=" cast(sum(curp) as decimal(10,2)) as curp,";
			sql +=" cast(sum(totalgencpty) as decimal(10,2)) as totalgencpty,";
			sql +=" sum(totalgencpty) as 总装机容量,";
			sql +=" sum(totalgennumb) as 总装机台数,";

			sql +=" cast(sum(totalgenwh) as decimal(10,2)) as 累计发电量 ,";
			sql +=" concat(cast(sum(totalgenwh)*3.6/10000*2.6 as decimal(10,2)),' t') as '累计减排量'";
			sql +=" from rtgentypestat ";
			//System.out.println(sql);
			
			//由于rowmapper返回list，所以genInfoRowMapper返回的是List<List<Map>>,外层List只包含一个List，所以需要get(0),并在前面加List强制转换
			return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		
		}
		
		
		/**
		 *统计当前所有电站的运行状态
		 *
		 */
		public List<Map> getGeneratorCurState(){
			String sql = "";

			sql+="select "; 
			sql+="sum(case when curcmpstate=2 or curcmpstate=3 then 1 else 0 end ) as '运行',"; ; 
			sql+="sum(case when curcmpstate=1 then 1 else 0 end )  as '待机',"; 
			sql+="sum(case when curcmpstate=0 or curcmpstate=6 then 1 else 0 end ) as  '停运',";
			sql+="sum(case when curcmpstate=5 then 1 else 0 end ) as '故障',";
			sql+="sum(case when curcmpstate=7 then 1 else 0 end ) as '通信中断',";
			sql+="sum(case when curcmpstate=4 then 1 else 0 end ) as '检修'";
			sql+="from rtgenerator";
			return (List<Map>)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		}
		
		
		
		/**
		 * 返回本日统计信息
		 * @return
		 */
		public List<Map> getGeneralInfoData2(){
			
			String sql = "";
			sql +="select ";
			sql +=" concat(truncate(avg(rtweather.windvelval) ,2),' m/s') as '平均风速'"; 
			sql +=", cast(rtsmgsys.todaygenwh as decimal(10,2)) as '本日发电量'"; 
			sql +=", cast(rtsmgsys.todayupnetwh as decimal(10,2)) as '本日上网电量'"; 
			sql +=", cast(smg.genprice*rtsmgsys.todayupnetwh as decimal(10,2)) as '本日收益'";  
			sql +="from rtsmgsysinfost rtsmgsys , smgsysinfo smg ,rtweatherdata  rtweather "; 
			sql +="where smg.id=rtsmgsys.id  and smg.id=rtweather.smgid ";  
			sql +=" group by smg.id"; 
			//由于rowmapper返回list，所以genInfoRowMapper返回的是List<List<Map>>,外层List只包含一个List，所以需要get(0),并在前面加List强制转换
			return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		}
		
		
		//获取风电场实时功率数据List<Map>
		//统计一天之内的数据
		public List<Map> getPowerRealtimeData(){
			String _year=MyDate.getYear();
			String _lastYear=MyDate.getLastyearStr();
			String sql = "";
			if(!MyDate.nowTimeInFistDay()||!isTableExist("hisgentypestat_"+_lastYear)){
				sql +="select left(savetime,16) as xAxis ";
				sql +=",cast( sum(curp) as decimal(10,2)) as data ";
				sql +=" from hisgentypestat_"+ _year+ " where savetime >= now()-interval 24 hour and savetime <=now() group by  savetime";
		    	//sql +="select left(savetime,16) as xAxis,left(sum(curp)/1000,4) as data from hisgenerator_2016 where savetime >= '2016-05-18 17:15:00' and savetime <='2016-05-19 17:15:00'  group by  savetime ";
			}else{
				sql +="(select left(savetime,16) as xAxis ";
				sql +=",cast(sum(curp) as decimal(10,2))as data ";
				sql +=" from hisgentypestat_"+ _lastYear+ " where savetime >= now()-interval 24 hour and savetime <=now() group by  savetime) union ";
				sql +="(select left(savetime,16) as xAxis ";
				sql +=",cast(sum(curp) as decimal(10,2)) as data ";
				sql +=" from hisgentypestat_"+ _year+ " where savetime >= now()-interval 24 hour and savetime <=now() group by  savetime)";
			}
			
			return getJdbcTemplate().query(sql, new webViewDataRowMapper());
		}
		
		
		//获取风电场历史数据List<Map>
		//统计一周之内的数据间隔一小时
		public List<Map> getHisPowerDataList(){
			String _year=MyDate.getYear();
			String _lastYear=MyDate.getLastyearStr();
			String sql = "";
			if(!MyDate.nowTimeInFistWeek()||!isTableExist("hisgentypestat_"+_lastYear)){
				sql +="select left(SAVETIME,16) as xAxis ";
				sql +=",cast( SUM(CURP) as decimal(10,2)) as data ";
				sql +="from hisgentypestat_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME";
			}else{
				sql +="(select left(SAVETIME,16) as xAxis ";
				sql +=",cast( SUM(CURP) as decimal(10,2)) as data ";
				sql +="from hisgentypestat_"+ _lastYear+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME) union ";
				sql +="(select left(SAVETIME,16) as xAxis ";
				sql +=",cast( SUM(CURP) as decimal(10,2)) as data ";
				sql +="from hisgentypestat_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME)";
			}
	    	
			return getJdbcTemplate().query(sql, new webViewDataRowMapper());
		}
		
		
		public List<Map> getHisWindSpeedDataList(){
			String _year=MyDate.getYear();
			String _lastYear=MyDate.getLastyearStr();
			String sql = "";
			if(!MyDate.nowTimeInFistWeek()||!isTableExist("hisweatherdata_"+_lastYear)){
				sql +="select left(SAVETIME,16) as xAxis ";
				sql +=",truncate( AVG(windvelval) ,2) as data ";
				sql +="from hisweatherdata_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME";
			}else{
				sql +="(select left(SAVETIME,16) as xAxis ";
				sql +=",truncate( AVG(windvelval) ,2) as data ";
				sql +="from hisweatherdata_"+ _lastYear+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME) union ";
				sql +="(select left(SAVETIME,16) as xAxis ";
				sql +=",truncate( AVG(windvelval) ,2) as data ";
				sql +="from hisweatherdata_"+ _year+ " WHERE  right(SAVETIME,5)='00:00' and savetime >= now()-interval 7 day group by DATE_FORMAT(savetime,'%Y-%m-%d %H:' ) ORDER BY SAVETIME)";	
			}
			return getJdbcTemplate().query(sql, new webViewDataRowMapper());
		}
		
		
		/**
		 * 一个月的数据  间隔是一天
		 */
		public List<Map> getHisDayGenWhList(){
			String _year=MyDate.getYear();
			String _lastYear=MyDate.getLastyearStr();
			String sql = "";
	    	
			
			if (!MyDate.nowTimeInFistMonth()||!isTableExist("hissmgsysinfost_"+_lastYear)){
				sql +="select left(SAVETIME,10) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data ";
				sql +=" from hissmgsysinfost_"+ _year +" where savetime> now()-interval 30 day group by savetime ORDER BY SAVETIME";
			}else{
				sql +="(select left(SAVETIME,10) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data ";
				sql +=" from hissmgsysinfost_"+ _lastYear +" where savetime> now()-interval 30 day group by savetime ORDER BY SAVETIME) union ";
				sql +="(select left(SAVETIME,10) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data ";
				sql +=" from hissmgsysinfost_"+ _year +" where savetime> now()-interval 30 day group by savetime ORDER BY SAVETIME)";
			}
			
			
			return getJdbcTemplate().query(sql, new webViewDataRowMapper());
		}
		
		
		/**
		 * 统计几个月的数据    时间间隔为一个月
		 * 
		 */
		public List<Map> getHisMonthGenWhList(){
			String _year=MyDate.getYear();
			String _lastYear=MyDate.getLastyearStr();
			String sql = "";
	    	
			if (isTableExist("hissmgsysinfost_"+_lastYear)){
				sql +="(select left(SAVETIME,7) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data  ";
				sql +="from hissmgsysinfost_"+ _lastYear +"  where savetime> now()-interval 1 year group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME) union  ";
				sql +="(select left(SAVETIME,7) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data  ";
				sql +="from hissmgsysinfost_"+ _year +"  where savetime> now()-interval 1 year group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME)";
			}else{
				sql +="select left(SAVETIME,7) as xAxis ";
				sql +=",cast( SUM(TODAYGENWH) as decimal(10,2)) as data  ";
				sql +="from hissmgsysinfost_"+ _year +"  where savetime> now()-interval 1 year group by DATE_FORMAT(savetime,'%Y-%m') ORDER BY SAVETIME";
			}
			
			return getJdbcTemplate().query(sql, new webViewDataRowMapper());
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



