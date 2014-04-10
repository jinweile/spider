package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.SourceSpider;
import com.etaoshi.spider.dao.intf.ISourceSpiderDao;
	
/**
 *SourceSpiderDao is the implementation of ISourceSpiderDao
 */
public class SourceSpiderDao implements ISourceSpiderDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ISourceSpiderDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "SourceSpider-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ISourceSpiderDao.Find
	 */
	public SourceSpider Find(Integer id) throws SQLException {
		String stmtId = "SourceSpider-Find";
		SourceSpider result = (SourceSpider) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ISourceSpiderDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindAll() throws SQLException {
		String stmtId = "SourceSpider-FindAll";
		List<SourceSpider> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> QuickFindAll() throws SQLException {
		String stmtId = "SourceSpider-QuickFindAll";
		List<SourceSpider> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindBySourceid
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindBySourceid(Integer sourceid) throws SQLException {
		String stmtId = "SourceSpider-FindBySourceid";
		List<SourceSpider> result = mapper.queryForList(stmtId, sourceid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindByTemplateid
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "SourceSpider-FindByTemplateid";
		List<SourceSpider> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindBySpiderentryrule
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindBySpiderentryrule(String spiderentryrule) throws SQLException {
		String stmtId = "SourceSpider-FindBySpiderentryrule";
		List<SourceSpider> result = mapper.queryForList(stmtId, spiderentryrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindByJobrule
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindByJobrule(String jobrule) throws SQLException {
		String stmtId = "SourceSpider-FindByJobrule";
		List<SourceSpider> result = mapper.queryForList(stmtId, jobrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindByCreationdate
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "SourceSpider-FindByCreationdate";
		List<SourceSpider> result = mapper.queryForList(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindByRemark
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindByRemark(String remark) throws SQLException {
		String stmtId = "SourceSpider-FindByRemark";
		List<SourceSpider> result = mapper.queryForList(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.FindByIsused
	 */
	@SuppressWarnings("unchecked")
	public List<SourceSpider> FindByIsused(Boolean isused) throws SQLException {
		String stmtId = "SourceSpider-FindByIsused";
		List<SourceSpider> result = mapper.queryForList(stmtId, isused);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.Insert
	 */
	public Integer Insert(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SourceSpider-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ISourceSpiderDao.Update
	 */
	public int Update(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SourceSpider-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ISourceSpiderDao.Delete
	 */
	public int Delete(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SourceSpider-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteBySourceid
	 */
	public int DeleteBySourceid(Integer sourceid) throws SQLException {
		String stmtId = "SourceSpider-DeleteBySourceid";
		int result = mapper.delete(stmtId, sourceid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "SourceSpider-DeleteByTemplateid";
		int result = mapper.delete(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteBySpiderentryrule
	 */
	public int DeleteBySpiderentryrule(String spiderentryrule) throws SQLException {
		String stmtId = "SourceSpider-DeleteBySpiderentryrule";
		int result = mapper.delete(stmtId, spiderentryrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteByJobrule
	 */
	public int DeleteByJobrule(String jobrule) throws SQLException {
		String stmtId = "SourceSpider-DeleteByJobrule";
		int result = mapper.delete(stmtId, jobrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "SourceSpider-DeleteByCreationdate";
		int result = mapper.delete(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		String stmtId = "SourceSpider-DeleteByRemark";
		int result = mapper.delete(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.DeleteByIsused
	 */
	public int DeleteByIsused(Boolean isused) throws SQLException {
		String stmtId = "SourceSpider-DeleteByIsused";
		int result = mapper.delete(stmtId, isused);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderDao.Reload
	 */
	public void Reload(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SourceSpider-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
}
