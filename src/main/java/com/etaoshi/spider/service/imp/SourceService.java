package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ISourceDao;

import com.etaoshi.spider.model.Source;
import com.etaoshi.spider.service.intf.ISourceService;
	
/**
 *SourceService is the implementation of ISourceService
 */
public class SourceService implements ISourceService {
	
	private ISourceDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setSourceDao(ISourceDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ISourceService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ISourceService.Find
	 */
	public Source Find(Integer id) throws SQLException {
		Source result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ISourceService.FindAll
	 */
	public List<Source> FindAll() throws SQLException {
		List<Source> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ISourceService.QuickFindAll
	 */
	public List<Source> QuickFindAll() throws SQLException {
		List<Source> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ISourceService.FindByName
	 */
	public List<Source> FindByName(String name) throws SQLException {
		List<Source> result = dao.FindByName(name);
		return result;
	}
	
	/**
	 *Implements ISourceService.FindByUrl
	 */
	public List<Source> FindByUrl(String url) throws SQLException {
		List<Source> result = dao.FindByUrl(url);
		return result;
	}
	
	/**
	 *Implements ISourceService.FindByIsused
	 */
	public List<Source> FindByIsused(Boolean isused) throws SQLException {
		List<Source> result = dao.FindByIsused(isused);
		return result;
	}
	
	/**
	 *Implements ISourceService.FindByRemark
	 */
	public List<Source> FindByRemark(String remark) throws SQLException {
		List<Source> result = dao.FindByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISourceService.Insert
	 */
	public Integer Insert(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ISourceService.Update
	 */
	public int Update(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ISourceService.Delete
	 */
	public int Delete(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ISourceService.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		int result = dao.DeleteByName(name);
		return result;
	}
	
	/**
	 *Implements ISourceService.DeleteByUrl
	 */
	public int DeleteByUrl(String url) throws SQLException {
		int result = dao.DeleteByUrl(url);
		return result;
	}
	
	/**
	 *Implements ISourceService.DeleteByIsused
	 */
	public int DeleteByIsused(Boolean isused) throws SQLException {
		int result = dao.DeleteByIsused(isused);
		return result;
	}
	
	/**
	 *Implements ISourceService.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		int result = dao.DeleteByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISourceService.Reload
	 */
	public void Reload(Source obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
