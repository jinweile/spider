package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.Source;
import com.etaoshi.spider.dao.intf.ISourceDao;
	
/**
 *SourceDao is the implementation of ISourceDao
 */
public class SourceDao implements ISourceDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ISourceDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "Source-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ISourceDao.Find
	 */
	public Source Find(Integer id) throws SQLException {
		String stmtId = "Source-Find";
		Source result = (Source) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ISourceDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<Source> FindAll() throws SQLException {
		String stmtId = "Source-FindAll";
		List<Source> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISourceDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<Source> QuickFindAll() throws SQLException {
		String stmtId = "Source-QuickFindAll";
		List<Source> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISourceDao.FindByName
	 */
	@SuppressWarnings("unchecked")
	public List<Source> FindByName(String name) throws SQLException {
		String stmtId = "Source-FindByName";
		List<Source> result = mapper.queryForList(stmtId, name);
		return result;
	}
	
	/**
	 *Implements ISourceDao.FindByUrl
	 */
	@SuppressWarnings("unchecked")
	public List<Source> FindByUrl(String url) throws SQLException {
		String stmtId = "Source-FindByUrl";
		List<Source> result = mapper.queryForList(stmtId, url);
		return result;
	}
	
	/**
	 *Implements ISourceDao.FindByIsused
	 */
	@SuppressWarnings("unchecked")
	public List<Source> FindByIsused(Boolean isused) throws SQLException {
		String stmtId = "Source-FindByIsused";
		List<Source> result = mapper.queryForList(stmtId, isused);
		return result;
	}
	
	/**
	 *Implements ISourceDao.FindByRemark
	 */
	@SuppressWarnings("unchecked")
	public List<Source> FindByRemark(String remark) throws SQLException {
		String stmtId = "Source-FindByRemark";
		List<Source> result = mapper.queryForList(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISourceDao.Insert
	 */
	public Integer Insert(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Source-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ISourceDao.Update
	 */
	public int Update(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Source-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ISourceDao.Delete
	 */
	public int Delete(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Source-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ISourceDao.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		String stmtId = "Source-DeleteByName";
		int result = mapper.delete(stmtId, name);
		return result;
	}
	
	/**
	 *Implements ISourceDao.DeleteByUrl
	 */
	public int DeleteByUrl(String url) throws SQLException {
		String stmtId = "Source-DeleteByUrl";
		int result = mapper.delete(stmtId, url);
		return result;
	}
	
	/**
	 *Implements ISourceDao.DeleteByIsused
	 */
	public int DeleteByIsused(Boolean isused) throws SQLException {
		String stmtId = "Source-DeleteByIsused";
		int result = mapper.delete(stmtId, isused);
		return result;
	}
	
	/**
	 *Implements ISourceDao.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		String stmtId = "Source-DeleteByRemark";
		int result = mapper.delete(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISourceDao.Reload
	 */
	public void Reload(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Source-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
}
