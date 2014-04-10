package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ITemplateDao;

import com.etaoshi.spider.model.Template;
import com.etaoshi.spider.service.intf.ITemplateService;
	
/**
 *TemplateService is the implementation of ITemplateService
 */
public class TemplateService implements ITemplateService {
	
	private ITemplateDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setTemplateDao(ITemplateDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ITemplateService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ITemplateService.Find
	 */
	public Template Find(Integer id) throws SQLException {
		Template result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ITemplateService.FindAll
	 */
	public List<Template> FindAll() throws SQLException {
		List<Template> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ITemplateService.QuickFindAll
	 */
	public List<Template> QuickFindAll() throws SQLException {
		List<Template> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ITemplateService.FindByDatamodelid
	 */
	public List<Template> FindByDatamodelid(Integer datamodelid) throws SQLException {
		List<Template> result = dao.FindByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateService.FindByName
	 */
	public List<Template> FindByName(String name) throws SQLException {
		List<Template> result = dao.FindByName(name);
		return result;
	}
	
	/**
	 *Implements ITemplateService.FindByCreationdate
	 */
	public List<Template> FindByCreationdate(Date creationdate) throws SQLException {
		List<Template> result = dao.FindByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ITemplateService.FindByRemark
	 */
	public List<Template> FindByRemark(String remark) throws SQLException {
		List<Template> result = dao.FindByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ITemplateService.Insert
	 */
	public Integer Insert(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ITemplateService.Update
	 */
	public int Update(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ITemplateService.Delete
	 */
	public int Delete(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ITemplateService.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		int result = dao.DeleteByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ITemplateService.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		int result = dao.DeleteByName(name);
		return result;
	}
	
	/**
	 *Implements ITemplateService.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		int result = dao.DeleteByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ITemplateService.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		int result = dao.DeleteByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ITemplateService.Reload
	 */
	public void Reload(Template obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
