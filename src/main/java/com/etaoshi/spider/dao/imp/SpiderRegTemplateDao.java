package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.SpiderRegTemplate;
import com.etaoshi.spider.dao.intf.ISpiderRegTemplateDao;
	
/**
 *SpiderRegTemplateDao is the implementation of ISpiderRegTemplateDao
 */
public class SpiderRegTemplateDao implements ISpiderRegTemplateDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ISpiderRegTemplateDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "SpiderRegTemplate-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ISpiderRegTemplateDao.Find
	 */
	public SpiderRegTemplate Find(Integer id) throws SQLException {
		String stmtId = "SpiderRegTemplate-Find";
		SpiderRegTemplate result = (SpiderRegTemplate) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ISpiderRegTemplateDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindAll() throws SQLException {
		String stmtId = "SpiderRegTemplate-FindAll";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> QuickFindAll() throws SQLException {
		String stmtId = "SpiderRegTemplate-QuickFindAll";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindByTemplateid
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindByTemplateid";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindByParentid
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindByParentid(Integer parentid) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindByParentid";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, parentid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindByTypeid
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindByTypeid(Integer typeid) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindByTypeid";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, typeid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindBySpiderreg
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindBySpiderreg(String spiderreg) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindBySpiderreg";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, spiderreg);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindBySpiderorderby
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindBySpiderorderby(Integer spiderorderby) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindBySpiderorderby";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, spiderorderby);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindByIsspiderentry
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindByIsspiderentry(Integer isspiderentry) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindByIsspiderentry";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, isspiderentry);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.FindByEntryrule
	 */
	@SuppressWarnings("unchecked")
	public List<SpiderRegTemplate> FindByEntryrule(String entryrule) throws SQLException {
		String stmtId = "SpiderRegTemplate-FindByEntryrule";
		List<SpiderRegTemplate> result = mapper.queryForList(stmtId, entryrule);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.Insert
	 */
	public Integer Insert(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderRegTemplate-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.Update
	 */
	public int Update(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderRegTemplate-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.Delete
	 */
	public int Delete(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderRegTemplate-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteByTemplateid";
		int result = mapper.delete(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteByParentid
	 */
	public int DeleteByParentid(Integer parentid) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteByParentid";
		int result = mapper.delete(stmtId, parentid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteByTypeid
	 */
	public int DeleteByTypeid(Integer typeid) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteByTypeid";
		int result = mapper.delete(stmtId, typeid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteBySpiderreg
	 */
	public int DeleteBySpiderreg(String spiderreg) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteBySpiderreg";
		int result = mapper.delete(stmtId, spiderreg);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteBySpiderorderby
	 */
	public int DeleteBySpiderorderby(Integer spiderorderby) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteBySpiderorderby";
		int result = mapper.delete(stmtId, spiderorderby);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteByIsspiderentry
	 */
	public int DeleteByIsspiderentry(Integer isspiderentry) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteByIsspiderentry";
		int result = mapper.delete(stmtId, isspiderentry);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.DeleteByEntryrule
	 */
	public int DeleteByEntryrule(String entryrule) throws SQLException {
		String stmtId = "SpiderRegTemplate-DeleteByEntryrule";
		int result = mapper.delete(stmtId, entryrule);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateDao.Reload
	 */
	public void Reload(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "SpiderRegTemplate-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
}
