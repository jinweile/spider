package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.TemplateDataModel;
import com.etaoshi.spider.dao.intf.ITemplateDataModelDao;
	
/**
 *TemplateDataModelDao is the implementation of ITemplateDataModelDao
 */
public class TemplateDataModelDao implements ITemplateDataModelDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ITemplateDataModelDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "TemplateDataModel-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ITemplateDataModelDao.Find
	 */
	public TemplateDataModel Find(Integer id) throws SQLException {
		String stmtId = "TemplateDataModel-Find";
		TemplateDataModel result = (TemplateDataModel) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ITemplateDataModelDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<TemplateDataModel> FindAll() throws SQLException {
		String stmtId = "TemplateDataModel-FindAll";
		List<TemplateDataModel> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<TemplateDataModel> QuickFindAll() throws SQLException {
		String stmtId = "TemplateDataModel-QuickFindAll";
		List<TemplateDataModel> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.FindByTemplateid
	 */
	@SuppressWarnings("unchecked")
	public List<TemplateDataModel> FindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "TemplateDataModel-FindByTemplateid";
		List<TemplateDataModel> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.FindByDatamodelid
	 */
	@SuppressWarnings("unchecked")
	public List<TemplateDataModel> FindByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "TemplateDataModel-FindByDatamodelid";
		List<TemplateDataModel> result = mapper.queryForList(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.Insert
	 */
	public Integer Insert(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TemplateDataModel-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDataModelDao.Update
	 */
	public int Update(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TemplateDataModel-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDataModelDao.Delete
	 */
	public int Delete(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TemplateDataModel-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ITemplateDataModelDao.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "TemplateDataModel-DeleteByTemplateid";
		int result = mapper.delete(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		String stmtId = "TemplateDataModel-DeleteByDatamodelid";
		int result = mapper.delete(stmtId, datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelDao.Reload
	 */
	public void Reload(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TemplateDataModel-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
	/**
	 * 更新模版数据模型关系
	 * @param params
	 * @throws SQLException
	 */
	public void UpdateByTemplateidAndDataModelid(Map params) throws SQLException {
		if (params == null) throw new NullPointerException("params");
		String stmtId = "TemplateDataModel-UpdateByTemplateidAndDataModelid";
		mapper.update(stmtId, params);
	}
	
}
