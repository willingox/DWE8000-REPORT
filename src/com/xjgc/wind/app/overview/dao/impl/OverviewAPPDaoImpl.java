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
			sql +=" sum(totalgencpty) as ��װ������,";
			sql +=" sum(totalgennumb) as ��װ��̨��,";

			sql +=" cast(sum(totalgenwh) as decimal(10,2)) as �ۼƷ����� ,";
			sql +=" concat(cast(sum(totalgenwh)*3.6/10000*2.6 as decimal(10,2)),' t') as '�ۼƼ�����'";
			sql +=" from rtgentypestat ";
			//System.out.println(sql);
			
			//����rowmapper����list������genInfoRowMapper���ص���List<List<Map>>,���Listֻ����һ��List��������Ҫget(0),����ǰ���Listǿ��ת��
			return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		
		}
		
		
		/**
		 *ͳ�Ƶ�ǰ���е�վ������״̬
		 *
		 */
		public List<Map> getGeneratorCurState(){
			String sql = "";

			sql+="select "; 
			sql+="sum(case when curcmpstate=2 or curcmpstate=3 then 1 else 0 end ) as '����',"; ; 
			sql+="sum(case when curcmpstate=1 then 1 else 0 end )  as '����',"; 
			sql+="sum(case when curcmpstate=0 or curcmpstate=6 then 1 else 0 end ) as  'ͣ��',";
			sql+="sum(case when curcmpstate=5 then 1 else 0 end ) as '����',";
			sql+="sum(case when curcmpstate=7 then 1 else 0 end ) as 'ͨ���ж�',";
			sql+="sum(case when curcmpstate=4 then 1 else 0 end ) as '����'";
			sql+="from rtgenerator";
			return (List<Map>)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		}
		
		
		
		/**
		 * ���ر���ͳ����Ϣ
		 * @return
		 */
		public List<Map> getGeneralInfoData2(){
			
			String sql = "";
			sql +="select ";
			sql +=" concat(truncate(avg(rtweather.windvelval) ,2),' m/s') as 'ƽ������'"; 
			sql +=", cast(rtsmgsys.todaygenwh as decimal(10,2)) as '���շ�����'"; 
			sql +=", cast(rtsmgsys.todayupnetwh as decimal(10,2)) as '������������'"; 
			sql +=", cast(smg.genprice*rtsmgsys.todayupnetwh as decimal(10,2)) as '��������'";  
			sql +="from rtsmgsysinfost rtsmgsys , smgsysinfo smg ,rtweatherdata  rtweather "; 
			sql +="where smg.id=rtsmgsys.id  and smg.id=rtweather.smgid ";  
			sql +=" group by smg.id"; 
			//����rowmapper����list������genInfoRowMapper���ص���List<List<Map>>,���Listֻ����һ��List��������Ҫget(0),����ǰ���Listǿ��ת��
			return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);
		}
		
		
		//��ȡ��糡ʵʱ��������List<Map>
		//ͳ��һ��֮�ڵ�����
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
		
		
		//��ȡ��糡��ʷ����List<Map>
		//ͳ��һ��֮�ڵ����ݼ��һСʱ
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
		 * һ���µ�����  �����һ��
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
		 * ͳ�Ƽ����µ�����    ʱ����Ϊһ����
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



