package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.Template;
import com.etaoshi.spider.dao.intf.ITemplateDao;
	
/**
 *TemplateDao is the implementation of ITemplateDao
 */
public class TemplateDao implements ITemplateDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ITemplateDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "Template-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ITemplateDao.Find
	 */
	public Template Find(Integer id) throws SQLException {
		String stmtId = "Template-Find";
		Template result = (Template) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ITemplateDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<Template> FindAll() throws SQLException {
		String stmtId = "Template-FindAll";
		List<Template> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<Template> QuickFindAll() throws SQLException {
		String stmtId = "Template-QuickFindAll";
		List<Template> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.FindByDatamodelid
	 */
	@SuppressWarnings("unchecked")
	public List<Template> FindByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "Template-FindByDatamodelid";
		List<Template> result = mapper.queryForList(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.FindByName
	 */
	@SuppressWarnings("unchecked")
	public List<Template> FindByName(String name) throws SQLException {
		String stmtId = "Template-FindByName";
		List<Template> result = mapper.queryForList(stmtId, name);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.FindByCreationdate
	 */
	@SuppressWarnings("unchecked")
	public List<Template> FindByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "Template-FindByCreationdate";
		List<Template> result = mapper.queryForList(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.FindByRemark
	 */
	@SuppressWarnings("unchecked")
	public List<Template> FindByRemark(String remark) throws SQLException {
		String stmtId = "Template-FindByRemark";
		List<Template> result = mapper.queryForList(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.Insert
	 */
	public Integer Insert(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Template-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDao.Update
	 */
	public int Update(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Template-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDao.Delete
	 */
	public int Delete(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Template-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDao.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "Template-DeleteByDatamodelid";
		int result = mapper.delete(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		String stmtId = "Template-DeleteByName";
		int result = mapper.delete(stmtId, name);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "Template-DeleteByCreationdate";
		int result = mapper.delete(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		String stmtId = "Template-DeleteByRemark";
		int result = mapper.delete(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements ITemplateDao.Reload
	 */
	public void Reload(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "Template-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}

}
