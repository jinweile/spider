package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ISpiderRegTemplateDao;

import com.etaoshi.spider.model.SpiderRegTemplate;
import com.etaoshi.spider.service.intf.ISpiderRegTemplateService;
	
/**
 *SpiderRegTemplateService is the implementation of ISpiderRegTemplateService
 */
public class SpiderRegTemplateService implements ISpiderRegTemplateService {
	
	private ISpiderRegTemplateDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setSpiderRegTemplateDao(ISpiderRegTemplateDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ISpiderRegTemplateService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ISpiderRegTemplateService.Find
	 */
	public SpiderRegTemplate Find(Integer id) throws SQLException {
		SpiderRegTemplate result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ISpiderRegTemplateService.FindAll
	 */
	public List<SpiderRegTemplate> FindAll() throws SQLException {
		List<SpiderRegTemplate> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.QuickFindAll
	 */
	public List<SpiderRegTemplate> QuickFindAll() throws SQLException {
		List<SpiderRegTemplate> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindByTemplateid
	 */
	public List<SpiderRegTemplate> FindByTemplateid(Integer templateid) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindByParentid
	 */
	public List<SpiderRegTemplate> FindByParentid(Integer parentid) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindByParentid(parentid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindByTypeid
	 */
	public List<SpiderRegTemplate> FindByTypeid(Integer typeid) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindByTypeid(typeid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindBySpiderreg
	 */
	public List<SpiderRegTemplate> FindBySpiderreg(String spiderreg) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindBySpiderreg(spiderreg);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindBySpiderorderby
	 */
	public List<SpiderRegTemplate> FindBySpiderorderby(Integer spiderorderby) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindBySpiderorderby(spiderorderby);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindByIsspiderentry
	 */
	public List<SpiderRegTemplate> FindByIsspiderentry(Integer isspiderentry) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindByIsspiderentry(isspiderentry);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.FindByEntryrule
	 */
	public List<SpiderRegTemplate> FindByEntryrule(String entryrule) throws SQLException {
		List<SpiderRegTemplate> result = dao.FindByEntryrule(entryrule);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.Insert
	 */
	public Integer Insert(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateService.Update
	 */
	public int Update(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateService.Delete
	 */
	public int Delete(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		int result = dao.DeleteByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteByParentid
	 */
	public int DeleteByParentid(Integer parentid) throws SQLException {
		int result = dao.DeleteByParentid(parentid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteByTypeid
	 */
	public int DeleteByTypeid(Integer typeid) throws SQLException {
		int result = dao.DeleteByTypeid(typeid);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteBySpiderreg
	 */
	public int DeleteBySpiderreg(String spiderreg) throws SQLException {
		int result = dao.DeleteBySpiderreg(spiderreg);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteBySpiderorderby
	 */
	public int DeleteBySpiderorderby(Integer spiderorderby) throws SQLException {
		int result = dao.DeleteBySpiderorderby(spiderorderby);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteByIsspiderentry
	 */
	public int DeleteByIsspiderentry(Integer isspiderentry) throws SQLException {
		int result = dao.DeleteByIsspiderentry(isspiderentry);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.DeleteByEntryrule
	 */
	public int DeleteByEntryrule(String entryrule) throws SQLException {
		int result = dao.DeleteByEntryrule(entryrule);
		return result;
	}
	
	/**
	 *Implements ISpiderRegTemplateService.Reload
	 */
	public void Reload(SpiderRegTemplate obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
