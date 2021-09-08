package com.xjgc.wind.app.district.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.app.district.dao.DistrictAPPDao;
import com.xjgc.wind.app.vo.DistrictListDataRowMapper;
import com.xjgc.wind.app.vo.GeneratorListByMgIdDataRowMapper;
import com.xjgc.wind.app.vo.OverviewGeneralInfoRowMapper;
import com.xjgc.wind.app.vo.OverviewRankDataRowMapper;
import com.xjgc.wind.app.vo.webViewDataRowMapper;

public class DistrictAPPDaoImpl extends JdbcDaoSupport implements DistrictAPPDao {
	
	public List getDistrictList(){
	
		String sql ="select d.id as id ,d.name as name,truncate(sum(r.totalgencpty/1000),0) as capacity,truncate(sum(r.curp/1000),2) as power,truncate(sum(r.todaygenwh),2) as genWh,truncate(sum(r.totalgenwh/1000),2) as total_genWh, truncate(sum(r.todaygenwh*0.91*0.5/10),2) as profit from district d ,smgsysinfo s,rtgentypestat r where s.districtid=d.id and r.smgid=s.id and equiptypeid='202' group by d.id  order by d.id";
		return getJdbcTemplate().query(sql, new DistrictListDataRowMapper());//根据需要替换RowMapper

	}
	/**
	 *以下为地区基本信息和曲线先关函数 
	 */
	public List getGenInfoListByDistrictId(String districtId){
		String sql ="";
		sql+="select ";
		sql+="truncate(sum(rt.curp)/1000,2) as power, ";
		sql+="truncate(sum(rt.todaygenwh),2) as todaygenwh, ";
		sql+="truncate(rand()*15,2) as windspd,";
		sql+="concat(";
		sql+="		truncate(sum(rt.rungencpty)/1000,0),'/',";
		sql+="		truncate(sum(rt.totalgencpty)/1000,0),' MW','-',";
		sql+="		truncate(";
		sql+="			(sum(rt.rungencpty)/sum(rt.totalgencpty)*100),0";
		sql+="		)";
		sql+="	) as '容量(运行/装机)', ";
		sql+="concat(sum(rt.runnumb),'-',CEILING(sum(rt.runnumb)/ sum(rt.totalgennumb)*100)) as 运行台数,";
		sql+="concat(sum(rt.repairnum),'-',CEILING(sum(rt.repairnum)/ sum(rt.totalgennumb)*100)) as 检修台数,";
		sql+="concat(truncate(sum(rt.todaygenwh),2),' MW','-',CEILING(sum(rt.todaygenwh)*1000/(sum(rt.totalgencpty)*24))) as 今日发电量, ";
		sql+="concat(truncate(sum(rt.totalgenwh)/1000,2),' MW','-',CEILING(sum(rt.totalgenwh)*1000/sum(rt.totalgencpty)/24/365)) as 累计发电量";
		sql+=" from rtgentypestat rt, smgsysinfo smg  where rt.smgid=smg.id and smg.districtid='" +
				districtId +
				"' group by smg.districtid";
		
		
		/*sql+="select"; 
		sql+=" truncate(sum(rt.curp)/1000,2) as power,";
		sql+=" truncate(sum(rt.todaygenwh),2) as todaygenwh,";
		sql+=" truncate(rand()*15,2) as windspd,";
		//sql+=" count(rt.todaygenwh) as 风场个数,";
		sql+=" concat(truncate(sum(rt.totalgencpty)/1000,0),' MW') as capacity, ";
		sql+=" sum(rt.totalgennumb) as totalnum,";
		sql+=" concat(truncate(sum(rt.rungencpty)/1000,0),' MW') as 运行容量, ";
		sql+=" sum(rt.runnumb) as 运行台数, ";
		sql+=" sum(rt.repairnum) as 检修台数, ";
		sql+=" concat(truncate(sum(rt.todaygenwh),2),' MW') as 今日发电量,";
		sql+=" concat(truncate(sum(rt.todaygenwh),2)*0.9,' MW') as 昨日发电量,";
		sql+=" concat(truncate(sum(rt.todaygenwh),2)*24,' MW') as 月发电量,";
		sql+=" concat(truncate(sum(rt.totalgenwh)/1000,2),' GW') as 累计发电量";
		sql+=" from rtgentypestat  rt, smgsysinfo smg ";
		sql+=" where rt.smgid=smg.id and ";
		sql+="smg.districtid='";
		sql+=districtId;
		sql+="' group by smg.districtid";*/
		System.out.println(sql);
		//由于rowmapper返回list，所以genInfoRowMapper返回的是List<List<Map>>,外层List只包含一个List，所以需要get(0),并在前面加List强制转换
		return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);//根据需要替换RowMapper

	}
	
	
	//获取风电场实时功率数据List<Map>
		public Map getRealtimePowerDataByDistrictId(String dataItem,String districtId){
			
			/*String sql ="select left(his.savetime,16) as xAxis ,left(sum(his.curp)/1000,4) as data from hisgenerator_2016 his ,generator g,smgsysinfo smg  where his.id=g.id and g.mgid=smg.id and smg.districtid="
					 	+districtId
						+" and  his.savetime >= now()-interval 24*14 hour and his.savetime <=now()-interval 24*13 hour group by  his.savetime order by his.savetime  ";
			*/
			String sql ="select left(his.savetime,16) as xAxis ,left(sum(his.curp)/1000,4) as data from hisgenerator_2016 his ,generator g,smgsysinfo smg  where his.id=g.id and g.mgid=smg.id and smg.districtid=";
					sql+=districtId;
					sql+=" and  his.savetime >= now()-interval 24 hour and his.savetime <=now() group by  his.savetime   ";
			
			List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
			Map dataMap=new HashMap();
			dataMap.put("webViewDataList", dataList);
			return dataMap;
		}
		//获取风电场历史发电功率数据list（间隔1小时）
		public Map getHisPowerDataByDistrictId(String dataItem,String districtId){
			String sql ="select his.SAVETIME as xAxis,truncate(SUM(his.CURP)/1000,2) as data from hisgenerator_2016 his,generator g,smgsysinfo smg WHERE his.id=g.id and g.mgid=smg.id and smg.districtid=" +
					districtId+" and  right(his.SAVETIME,5)='00:00'  and his.savetime>now()-interval 24*7 hour group by DATE_FORMAT(his.savetime,'%Y-%m-%d %H:' ) ORDER BY  his.SAVETIME";
			List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
			Map dataMap=new HashMap();
			dataMap.put("webViewDataList", dataList);
			return dataMap;
		}
		//获取风电场历史日发电量list
		public Map getHisDayGenWhDataByDistrictId(String dataItem,String districtId){
			String sql ="select left(his.SAVETIME,10) as xAxis,truncate(SUM(his.TODAYGENWH)/1000,2) as data  from hisgentypestat_2016 his,smgsysinfo smg where his.smgid=smg.id and smg.districtid=" +
					districtId+" and his.savetime>now()-interval 30 day group by his.savetime ORDER BY his.SAVETIME";
			List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
			Map dataMap=new HashMap();
			dataMap.put("webViewDataList", dataList);
			return dataMap;
		}
		//获取风电场历史月发电量list
		public Map getHisMonthGenWhDataByDistrictId(String dataItem,String districtId){
			String sql ="select left(his.SAVETIME,7) as xAxis,truncate(SUM(his.TODAYGENWH),2) as data  from hisgentypestat_2016 his,smgsysinfo smg where his.smgid=smg.id and smg.districtid=" +
					districtId+"  group by DATE_FORMAT(his.savetime,'%Y-%m') ORDER BY his.SAVETIME";
			List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
			Map dataMap=new HashMap();
			dataMap.put("webViewDataList", dataList);
			return dataMap;
		}
		/**
		 *一下为风电场基本信息和曲线先关函数 
		 */
		public List getGenInfoListByMgId(String mgId){
			String sql ="";
			sql+=	"select smg.name as title," +
					"'中国' as 地址," +
					"concat(truncate(rt.totalgencpty/1000,0),' MW') as 装机容量," +
					"rt.runnumb as 运行台数," +
					"concat(truncate(curp/1000,2),' MW') as 当前功率 , " +
					"concat(truncate(25*rand(),2),' m/s') as 平均风速 , " +
					"concat(truncate(todaygenwh,2),' MW') as 今日发电量," +
					"concat(truncate(totalgenwh/1000,2),' GW') as 累计发电量 " +
					"from rtgentypestat rt,smgsysinfo smg " +
					"where rt.smgid=smg.id " +
					"and smgid=" +
					mgId;
			
			System.out.println(sql);
			//由于rowmapper返回list，所以genInfoRowMapper返回的是List<List<Map>>,外层List只包含一个List，所以需要get(0),并在前面加List强制转换
			return (List)getJdbcTemplate().query(sql, new OverviewGeneralInfoRowMapper()).get(0);//根据需要替换RowMapper

		}
		
		public List getGeneratorListByMgId(String mgId){
			String sql ="select rt.name as 风机," +
					"rt.curcmpstate as 运行状态," +
					"concat(truncate(rt.curp/1000,2),' MW') as 发电功率 ," +
					"concat(truncate(rt.todaygenwh,2),' MW') as 今日发电量," +
					"concat(truncate(rt.totalgenwh/1000,2),' GW') as 累计发电量 " +
					"from rtgenerator rt " +
					"where rt.mgid="+mgId;
			return getJdbcTemplate().query(sql, new GeneratorListByMgIdDataRowMapper());//根据需要替换RowMapper
		} 
		
		//获取风电场实时功率数据List<Map>
			public Map getRealtimePowerDataByMgId(String dataItem,String mgId){
				
				String sql ="select left(his.savetime,16) as xAxis ,left(sum(his.curp)/1000,4) as data from hisgenerator_2016 his ,generator g,smgsysinfo smg  where his.id=g.id and g.mgid=" +
						mgId+" and  his.savetime >= now()-interval 24*14 hour and his.savetime <=now()-interval 24*13 hour group by  his.savetime order by his.savetime  ";
				/*String sql ="select left(his.savetime,16) as xAxis ,left(sum(his.curp)/1000,4) as data from hisgenerator_2016 his ,generator g,smgsysinfo smg  where his.id=g.id and g.mgid=smg.id and smg.districtid=";
						sql+=districtId;
						sql+=" and  his.savetime >= now()-interval 24 hour and his.savetime <=now() group by  his.savetime   ";
				*/
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
			//获取风电场历史发电功率数据list（间隔1小时）
			public Map getHisPowerDataByMgId(String dataItem,String mgId){
				String sql ="select left(his.SAVETIME,16) as xAxis,truncate(avg(his.windvelval),2) as data from hisweatherdata_2016 his where his.windvelval<30 and his.smgid=" +
						mgId +
						" group by DATE_FORMAT(his.savetime,'%Y-%m-%d %H:' ) order by xAxis asc";
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
			//获取风电场历史日发电量list
			public Map getHisDayGenWhDataByMgId(String dataItem,String mgId){
				String sql ="select left(his.SAVETIME,10) as xAxis,truncate(SUM(his.TODAYGENWH)/1000,2) as data  from hisgentypestat_2016 his,smgsysinfo smg where his.smgid=smg.id and smg.id=" +
						mgId+" and his.savetime>now()-interval 30 day group by his.savetime ORDER BY his.SAVETIME";
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
			
			//获取风电场历史日发电量list
			public Map getDayProfitDataByMgId(String dataItem,String mgId){
				String sql ="select left(his.SAVETIME,10) as xAxis,truncate(SUM(his.TODAYGENWH)/10000*0.5,2) as data  from hisgentypestat_2016 his,smgsysinfo smg where his.smgid=smg.id and smg.id=" +
						mgId+" and his.savetime>now()-interval 30 day group by his.savetime ORDER BY his.SAVETIME";
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
			
			

			//获取风电场平均风速list
			public Map getHisAvgSpdDataByMgId(String dataItem,String mgId){
				String sql ="select left(his.SAVETIME,16) as xAxis,truncate(avg(his.windvelval),2) as data from hisweatherdata_2016 his where his.windvelval<30 and his.smgid=" +
						mgId +
						" and  his.savetime>now()-interval 30 day group by DATE_FORMAT(his.savetime,'%Y-%m-%d' ) order by xAxis asc";
				System.out.println(sql);
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
			
			//获取风电场平均风速list
			public Map getTotalGenWhDataByMgId(String dataItem,String mgId){
				String sql ="select left(his.SAVETIME,7) as xAxis,truncate(SUM(his.totalgenwh)/1000,2) as data  from hisgentypestat_2016 his where his.smgid=" +
						mgId +
						" and his.savetime>now()-interval 1 year group by DATE_FORMAT(his.savetime,'%Y-%m' ) ORDER BY his.SAVETIME";
				List dataList= getJdbcTemplate().query(sql, new webViewDataRowMapper());//根据需要替换RowMapper
				Map dataMap=new HashMap();
				dataMap.put("webViewDataList", dataList);
				return dataMap;
			}
		
}
