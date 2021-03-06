package com.xjgc.wind.querytree.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.xjgc.wind.querytree.dao.IQueryTreeDao;
import com.xjgc.wind.querytree.vo.ResultVO;

public class QueryTreeDaoImpl extends JdbcDaoSupport implements
		IQueryTreeDao {

	public List<ResultVO> listStationByClass(Integer classId) {
		String sql = "select s.id sId, s.name sName from station s";
		if (null != classId) 
			sql += ", roottype r where s.typeid=r.id and r.rootclassid="
				+ classId;
		sql += " order by s.id";
		
		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("sId");
				String name = rs.getString("sName");
				return new ResultVO(id, name);
			}

		} );
	}

	/**
	 * @author hb 2016-5-16
	 * 获取包含id，name,count的List<ResultVO>
	 */
	private List<ResultVO> getTreeChildrenList(String sql)
	{
		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				//System.out.println(rs.getFetchSize());
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer child = rs.getInt("child");
				return new ResultVO(id, name,child);
			}

		} );
	}
	
	public List<ResultVO> listDistrict() {
		//String sql = "select s.id sId, s.name sName from district s";
		//sql += " order by s.id";
		String sql = "select d.id as id ,d.name as name, count(s.districtID) as child from district d, smgsysinfo s where s.districtID =d.id group by d.id,d.name order by d.id";
		return this.getTreeChildrenList(sql);
	}

	public List<ResultVO> getMicrogridList() {
		String sql = "select s.id as id,s.name as name,count(g.mgID) as child from smgsysinfo s left join generator g on g.mgID=s.id  ";
		sql+=" group by s.id,s.name order by s.id";
		
		return this.getTreeChildrenList(sql);
	}

	//考虑到为站内监控系统，所以把所属电站id去掉
	public List<ResultVO> getGeneratorList(String parentId) {
		//String sql = "select g.id as id ,g.name as name ,count(b.id) as child from generator g left join busstblcfg b on 1 where g.MGID=";
		//String sql = "select g.id as id ,g.name as name, 0 as child from generator g where g.MGID=";
		//sql+=parentId.substring(parentId.lastIndexOf('-')+1);
		//sql+=" group by g.id,g.name order by g.id";
		String sql = "select g.id as id ,g.name as name, 0 as child from generator g where g.MGID=";
		sql+= parentId.substring(parentId.lastIndexOf('-')+1);
		sql+=" group by g.id,g.name order by g.id";
		return this.getTreeChildrenList(sql);
	}
	/**
	 * @author zlj 2017-4-10
	 * 增加间隔的List<ResultVO>
	 */
	//考虑到为站内监控系统，所以把所属电站id去掉
public List<ResultVO> getBayList(String parentId) {
	
		String sql = "select b.id as id ,b.name as name, 0 as child from bay b, generator g,equipment e where  b.id=e.bayid and e.id=g.id  and (b.typeid=2 or b.typeid=11) and g.mgid= ";
		sql+= parentId.substring(parentId.lastIndexOf('-')+1);
		sql+=" group by b.id";
		return this.getTreeChildrenList(sql);
		
	}
public List<ResultVO> getBayList(String parentId,int id) {
	String sqltypeid = "select querycondition from busstblcfg where id =" +id;
	String typeid=(String) getJdbcTemplate().queryForObject(sqltypeid,new Object[]{},java.lang.String.class);
	String sql = "select id  ,name , 0 as child from bay where stationid=";
	sql+=parentId.substring(parentId.lastIndexOf('-')+1);
	if(typeid.length()!=0){
	sql+=" and " + typeid;
	}
	sql+=" group by id,name order by id";
	return this.getTreeChildrenList(sql);
	
}
	/**
	 * @author zlj 2017-7-20
	 * 获取树列表的第三级是bayid还是equipid
	 */

	public List<ResultVO> getBayOrEquip() {
		
		String sql = "select  ID as id ,suptablename as name,  0 as child from busstblcfg order by id";
				
		return this.getTreeChildrenList(sql);
	}
	public List<ResultVO> getMesurePointSubList() {
		String sql = "select tb.id as id, tb.tabledes as name,count(col.id) as child from  busstblcfg tb left join bussclmncfg col on  col.busstblcfgid=tb.id and col.queryenable=1  where tb.queryenable=1  group by tb.id";
		//System.out.println(sql);
		return this.getTreeChildrenList(sql);
	}
	
	public List<ResultVO> getMesurePointList(String parentId) {
		String sql = "select id as id,columndes as name,0 as child  from  bussclmncfg where queryenable=1  and busstblcfgid=";
		sql+=parentId.substring(parentId.lastIndexOf('-')+1);
		//System.out.println(sql);
		return this.getTreeChildrenList(sql);
	}

	
	public List<ResultVO> listEuTypeByStation(Integer stationId) {
		String sql = "select v.id vId, v.name vName from roottype v, bay b where v.id=b.typeid";
		if (null != stationId) 
			sql += " and b.stationid=" + stationId;
		sql += " group by b.typeid order by b.typeid";

		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("vId");
				String name = rs.getString("vName");
				return new ResultVO(id, name);
			}
			
		});
	}

	public List<ResultVO> listEuByStationAndType(Integer stationId,
			String typeIds) {
		String sql = "select id, name from bay where 1=1";
		if (null != stationId)
			sql += " and stationid=" + stationId;
		if (StringUtils.isNotBlank(typeIds)) 
			sql += " and typeid in (" + typeIds + ")";
		sql += " order by id";

		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new ResultVO(id, name);
			}
			
		});
	}

	public List<ResultVO> listEuitemClassByType(Integer typeId) {
		String sql = "select id, name from equipclass where 1=1";
		if (null != typeId) 
			sql += " and type=" + typeId;
		sql += " order by id";
		
		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNo) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new ResultVO(id, name);
			}
			
		});
	}

	public List<ResultVO> listEuitemByEu(Integer euId) {
		String sql = "select id, name from equipment where 1=1";
		if (null != euId)
			sql += " and bayid=" + euId;
		sql += " order by id";

		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new ResultVO(id, name);
			}

		});
	}

	public List<ResultVO> listEuitemByClass(Integer stationId,Integer classId) {
		String sql = "select e.id id, e.name name from equipment e,bay b where 1=1 and e.bayid=b.id " +
				"and b.stationid="+stationId;
		if (null != classId)
			sql += " and e.equipclassid=" + classId;
		sql += " order by e.id";

		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new ResultVO(id, name);
			}

		});
	}

	public int getEuitemCountByName(String strFilter) {
		String sql = "select count(*) from equipment where 1=1";
		if (StringUtils.isNotBlank(strFilter))
			sql += " and name like '" + strFilter + "%'";

		return getJdbcTemplate().queryForInt(sql);
	}

	public List<ResultVO> listEuitemByName(String strFilter) {
		String sql = "select id, name from equipment where 1=1";
		if (StringUtils.isNotBlank(strFilter))
			sql += " and name like '" + strFilter + "%'";
		sql += " order by id";

		return getJdbcTemplate().query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				return new ResultVO(id, name);
			}

		});
	}

}
