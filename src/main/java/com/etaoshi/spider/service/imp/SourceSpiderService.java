package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ISourceSpiderDao;

import com.etaoshi.spider.model.SourceSpider;
import com.etaoshi.spider.service.intf.ISourceSpiderService;
	
/**
 *SourceSpiderService is the implementation of ISourceSpiderService
 */
public class SourceSpiderService implements ISourceSpiderService {
	
	private ISourceSpiderDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setSourceSpiderDao(ISourceSpiderDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ISourceSpiderService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ISourceSpiderService.Find
	 */
	public SourceSpider Find(Integer id) throws SQLException {
		SourceSpider result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ISourceSpiderService.FindAll
	 */
	public List<SourceSpider> FindAll() throws SQLException {
		List<SourceSpider> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.QuickFindAll
	 */
	public List<SourceSpider> QuickFindAll() throws SQLException {
		List<SourceSpider> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindBySourceid
	 */
	public List<SourceSpider> FindBySourceid(Integer sourceid) throws SQLException {
		List<SourceSpider> result = dao.FindBySourceid(sourceid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindByTemplateid
	 */
	public List<SourceSpider> FindByTemplateid(Integer templateid) throws SQLException {
		List<SourceSpider> result = dao.FindByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindBySpiderentryrule
	 */
	public List<SourceSpider> FindBySpiderentryrule(String spiderentryrule) throws SQLException {
		List<SourceSpider> result = dao.FindBySpiderentryrule(spiderentryrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindByJobrule
	 */
	public List<SourceSpider> FindByJobrule(String jobrule) throws SQLException {
		List<SourceSpider> result = dao.FindByJobrule(jobrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindByCreationdate
	 */
	public List<SourceSpider> FindByCreationdate(Date creationdate) throws SQLException {
		List<SourceSpider> result = dao.FindByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindByRemark
	 */
	public List<SourceSpider> FindByRemark(String remark) throws SQLException {
		List<SourceSpider> result = dao.FindByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.FindByIsused
	 */
	public List<SourceSpider> FindByIsused(Boolean isused) throws SQLException {
		List<SourceSpider> result = dao.FindByIsused(isused);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.Insert
	 */
	public Integer Insert(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ISourceSpiderService.Update
	 */
	public int Update(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ISourceSpiderService.Delete
	 */
	public int Delete(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteBySourceid
	 */
	public int DeleteBySourceid(Integer sourceid) throws SQLException {
		int result = dao.DeleteBySourceid(sourceid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		int result = dao.DeleteByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteBySpiderentryrule
	 */
	public int DeleteBySpiderentryrule(String spiderentryrule) throws SQLException {
		int result = dao.DeleteBySpiderentryrule(spiderentryrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteByJobrule
	 */
	public int DeleteByJobrule(String jobrule) throws SQLException {
		int result = dao.DeleteByJobrule(jobrule);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		int result = dao.DeleteByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		int result = dao.DeleteByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.DeleteByIsused
	 */
	public int DeleteByIsused(Boolean isused) throws SQLException {
		int result = dao.DeleteByIsused(isused);
		return result;
	}
	
	/**
	 *Implements ISourceSpiderService.Reload
	 */
	public void Reload(SourceSpider obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
