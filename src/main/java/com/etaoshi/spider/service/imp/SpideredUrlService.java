package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ISpideredUrlDao;

import com.etaoshi.spider.model.SpideredUrl;
import com.etaoshi.spider.service.intf.ISpideredUrlService;
	
/**
 *SpideredUrlService is the implementation of ISpideredUrlService
 */
public class SpideredUrlService implements ISpideredUrlService {
	
	private ISpideredUrlDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setSpideredUrlDao(ISpideredUrlDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ISpideredUrlService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ISpideredUrlService.Find
	 */
	public SpideredUrl Find(Integer id) throws SQLException {
		SpideredUrl result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ISpideredUrlService.FindAll
	 */
	public List<SpideredUrl> FindAll() throws SQLException {
		List<SpideredUrl> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.QuickFindAll
	 */
	public List<SpideredUrl> QuickFindAll() throws SQLException {
		List<SpideredUrl> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.FindByDomain
	 */
	public List<SpideredUrl> FindByDomain(String domain) throws SQLException {
		List<SpideredUrl> result = dao.FindByDomain(domain);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.FindByUrl
	 */
	public List<SpideredUrl> FindByUrl(String url) throws SQLException {
		List<SpideredUrl> result = dao.FindByUrl(url);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.FindBySpidereddate
	 */
	public List<SpideredUrl> FindBySpidereddate(Date spidereddate) throws SQLException {
		List<SpideredUrl> result = dao.FindBySpidereddate(spidereddate);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.Insert
	 */
	public Integer Insert(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ISpideredUrlService.Update
	 */
	public int Update(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ISpideredUrlService.Delete
	 */
	public int Delete(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ISpideredUrlService.DeleteByDomain
	 */
	public int DeleteByDomain(String domain) throws SQLException {
		int result = dao.DeleteByDomain(domain);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.DeleteByUrl
	 */
	public int DeleteByUrl(String url) throws SQLException {
		int result = dao.DeleteByUrl(url);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.DeleteBySpidereddate
	 */
	public int DeleteBySpidereddate(Date spidereddate) throws SQLException {
		int result = dao.DeleteBySpidereddate(spidereddate);
		return result;
	}
	
	/**
	 *Implements ISpideredUrlService.Reload
	 */
	public void Reload(SpideredUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
