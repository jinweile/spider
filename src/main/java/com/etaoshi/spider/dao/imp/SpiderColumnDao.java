package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.SpiderColumn;
import com.etaoshi.spider.model.Template;
import com.etaoshi.spider.dao.intf.ISpiderColumnDao;
	
/**
 *SpiderColumnDao is the implementation of ISpiderColumnDao
 */
public class SpiderColumnDao implements ISpiderColumnDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ISpiderColumnDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "SpiderColumn-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ISpiderColumnDao.Find
	 */
	public SpiderColumn Find(Integer id) throws SQLException {
		String stmtId = "SpiderColumn-Find";
		SpiderColumn result = (SpiderColumn) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ISpiderColumnDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindAll() throws SQLException {
		String stmtId = "SpiderColumn-FindAll";
		List<SpiderColumn> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> QuickFindAll() throws SQLException {
		String stmtId = "SpiderColumn-QuickFindAll";
		List<SpiderColumn> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByDatamodelid
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "SpiderColumn-FindByDatamodelid";
		List<SpiderColumn> result = mapper.queryForList(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByColumnename
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByColumnename(String columnename) throws SQLException {
		String stmtId = "SpiderColumn-FindByColumnename";
		List<SpiderColumn> result = mapper.queryForList(stmtId, columnename);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByColumncname
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByColumncname(String columncname) throws SQLException {
		String stmtId = "SpiderColumn-FindByColumncname";
		List<SpiderColumn> result = mapper.queryForList(stmtId, columncname);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByColumntype
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByColumntype(Integer columntype) throws SQLException {
		String stmtId = "SpiderColumn-FindByColumntype";
		List<SpiderColumn> result = mapper.queryForList(stmtId, columntype);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByColumnlen
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByColumnlen(Integer columnlen) throws SQLException {
		String stmtId = "SpiderColumn-FindByColumnlen";
		List<SpiderColumn> result = mapper.queryForList(stmtId, columnlen);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByColumnaccuracy
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByColumnaccuracy(Integer columnaccuracy) throws SQLException {
		String stmtId = "SpiderColumn-FindByColumnaccuracy";
		List<SpiderColumn> result = mapper.queryForList(stmtId, columnaccuracy);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByCreationdate
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "SpiderColumn-FindByCreationdate";
		List<SpiderColumn> result = mapper.queryForList(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.FindByRemark
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByRemark(String remark) throws SQLException {
		String stmtId = "SpiderColumn-FindByRemark";
		List<SpiderColumn> result = mapper.queryForList(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.Insert
	 */
	public Integer Insert(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderColumn-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderColumnDao.Update
	 */
	public int Update(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderColumn-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderColumnDao.Delete
	 */
	public int Delete(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderColumn-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByDatamodelid";
		int result = mapper.delete(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByColumnename
	 */
	public int DeleteByColumnename(String columnename) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByColumnename";
		int result = mapper.delete(stmtId, columnename);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByColumncname
	 */
	public int DeleteByColumncname(String columncname) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByColumncname";
		int result = mapper.delete(stmtId, columncname);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByColumntype
	 */
	public int DeleteByColumntype(Integer columntype) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByColumntype";
		int result = mapper.delete(stmtId, columntype);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByColumnlen
	 */
	public int DeleteByColumnlen(Integer columnlen) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByColumnlen";
		int result = mapper.delete(stmtId, columnlen);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByColumnaccuracy
	 */
	public int DeleteByColumnaccuracy(Integer columnaccuracy) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByColumnaccuracy";
		int result = mapper.delete(stmtId, columnaccuracy);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByCreationdate";
		int result = mapper.delete(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		String stmtId = "SpiderColumn-DeleteByRemark";
		int result = mapper.delete(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnDao.Reload
	 */
	public void Reload(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderColumn-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
	/**
	 * 新增字段
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int Alter(Map map) throws SQLException {
		if (map == null) throw new NullPointerException("map");
		String stmtId = "SpiderColumn-Alter";
		return mapper.update(stmtId, map);
	}
	
	/**
	 * 根据模版id获取列列表
	 * @param templateid
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderColumn> FindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "SpiderColumn-FindByTemplateid";
		List<SpiderColumn> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 * 获取所有列哈希表
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, String> FindAllMap() throws SQLException {
		String stmtId = "SpiderColumn-FindAll";
		Map<Integer, String> result = mapper.queryForMap(stmtId, null, "id", "columnename");
		return result;
	}
	
	/**
	 * 插入数据到数据模型创建的表中
	 * @param value
	 * @throws SQLException
	 */
	public void InsertIntoDataModel(String sql) throws SQLException {
		String stmtId = "SpiderColumn-InsertIntoDataModel";
		mapper.insert(stmtId, sql);
	}
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	public List getDynamicTable(String sql) throws SQLException {
		String stmtId = "SpiderColumn-getDynamicTable";
		List result = mapper.queryForList(stmtId, sql);
		return result;
	}
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	public Integer getDynamicTableCount(String tablename) throws SQLException {
		String stmtId = "SpiderColumn-getDynamicTableCount";
		Integer result = (Integer)mapper.queryForObject(stmtId, tablename);
		return result;
	}
	
}
