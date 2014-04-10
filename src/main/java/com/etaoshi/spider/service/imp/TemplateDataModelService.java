package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ITemplateDataModelDao;

import com.etaoshi.spider.model.TemplateDataModel;
import com.etaoshi.spider.service.intf.ITemplateDataModelService;
	
/**
 *TemplateDataModelService is the implementation of ITemplateDataModelService
 */
public class TemplateDataModelService implements ITemplateDataModelService {
	
	private ITemplateDataModelDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setTemplateDataModelDao(ITemplateDataModelDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ITemplateDataModelService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ITemplateDataModelService.Find
	 */
	public TemplateDataModel Find(Integer id) throws SQLException {
		TemplateDataModel result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ITemplateDataModelService.FindAll
	 */
	public List<TemplateDataModel> FindAll() throws SQLException {
		List<TemplateDataModel> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.QuickFindAll
	 */
	public List<TemplateDataModel> QuickFindAll() throws SQLException {
		List<TemplateDataModel> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.FindByTemplateid
	 */
	public List<TemplateDataModel> FindByTemplateid(Integer templateid) throws SQLException {
		List<TemplateDataModel> result = dao.FindByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.FindByDatamodelid
	 */
	public List<TemplateDataModel> FindByDatamodelid(Integer datamodelid) throws SQLException {
		List<TemplateDataModel> result = dao.FindByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.Insert
	 */
	public Integer Insert(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ITemplateDataModelService.Update
	 */
	public int Update(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ITemplateDataModelService.Delete
	 */
	public int Delete(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ITemplateDataModelService.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		int result = dao.DeleteByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		int result = dao.DeleteByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateDataModelService.Reload
	 */
	public void Reload(TemplateDataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
	/**
	 * 更新模版数据模型关系
	 * @param params
	 * @throws SQLException
	 */
	public void UpdateByTemplateidAndDataModelid(Map params) throws SQLException {
		if (params == null) throw new NullPointerException("params");
		dao.UpdateByTemplateidAndDataModelid(params);
	}
	
}
