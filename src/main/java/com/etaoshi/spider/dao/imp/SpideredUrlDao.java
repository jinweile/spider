package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.SpideredUrl;
import com.etaoshi.spider.dao.intf.ISpideredUrlDao;
	
/**
 *SpideredUrlDao is the implementation of ISpideredUrlDao
 */
public class SpideredUrlDao implements ISpideredUrlDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ISpideredUrlDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "SpideredUrl-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ISpideredUrlDao.Find
	 */
	public SpideredUrl Find(Integer id) throws SQLException {
		String stmtId = "SpideredUrl-Find";
		SpideredUrl result = (SpideredUrl) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ISpideredUrlDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpideredUrl> FindAll() throws SQLException {
		String stmtId = "SpideredUrl-FindAll";
		List<SpideredUrl> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpideredUrl> QuickFindAll() throws SQLException {
		String stmtId = "SpideredUrl-QuickFindAll";
		List<SpideredUrl> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.FindByDomain
	 */
	@SuppressWarnings("unchecked")
	public List<SpideredUrl> FindByDomain(String domain) throws SQLException {
		String stmtId = "SpideredUrl-FindByDomain";
		List<SpideredUrl> result = mapper.queryForList(stmtId, domain);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.FindByUrl
	 */
	@SuppressWarnings("unchecked")
	public List<SpideredUrl> FindByUrl(String url) throws SQLException {
		String stmtId = "SpideredUrl-FindByUrl";
		List<SpideredUrl> result = mapper.queryForList(stmtId, url);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.FindBySpidereddate
	 */
	@SuppressWarnings("unchecked")
	public List<SpideredUrl> FindBySpidereddate(Date spidereddate) throws SQLException {
		String stmtId = "SpideredUrl-FindBySpidereddate";
		List<SpideredUrl> result = mapper.queryForList(stmtId, spidereddate);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.Insert
	 */
	public Integer Insert(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpideredUrl-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ISpideredUrlDao.Update
	 */
	public int Update(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpideredUrl-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ISpideredUrlDao.Delete
	 */
	public int Delete(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpideredUrl-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ISpideredUrlDao.DeleteByDomain
	 */
	public int DeleteByDomain(String domain) throws SQLException {
		String stmtId = "SpideredUrl-DeleteByDomain";
		int result = mapper.delete(stmtId, domain);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.DeleteByUrl
	 */
	public int DeleteByUrl(String url) throws SQLException {
		String stmtId = "SpideredUrl-DeleteByUrl";
		int result = mapper.delete(stmtId, url);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.DeleteBySpidereddate
	 */
	public int DeleteBySpidereddate(Date spidereddate) throws SQLException {
		String stmtId = "SpideredUrl-DeleteBySpidereddate";
		int result = mapper.delete(stmtId, spidereddate);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlDao.Reload
	 */
	public void Reload(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpideredUrl-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
}
