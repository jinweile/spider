package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ITestUrlDao;

import com.etaoshi.spider.model.TestUrl;
import com.etaoshi.spider.service.intf.ITestUrlService;
	
/**
 *TestUrlService is the implementation of ITestUrlService
 */
public class TestUrlService implements ITestUrlService {
	
	private ITestUrlDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setTestUrlDao(ITestUrlDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ITestUrlService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ITestUrlService.Find
	 */
	public TestUrl Find(Integer id) throws SQLException {
		TestUrl result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ITestUrlService.QuickFind
	 */
	public TestUrl QuickFind(Integer id) throws SQLException {
		TestUrl result = dao.QuickFind(id);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.FindAll
	 */
	public List<TestUrl> FindAll() throws SQLException {
		List<TestUrl> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ITestUrlService.QuickFindAll
	 */
	public List<TestUrl> QuickFindAll() throws SQLException {
		List<TestUrl> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ITestUrlService.FindByTemplateid
	 */
	public List<TestUrl> FindByTemplateid(Integer templateid) throws SQLException {
		List<TestUrl> result = dao.FindByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.QuickFindByTemplateid
	 */
	public List<TestUrl> QuickFindByTemplateid(Integer templateid) throws SQLException {
		List<TestUrl> result = dao.QuickFindByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.FindByTesturl
	 */
	public List<TestUrl> FindByTesturl(String testurl) throws SQLException {
		List<TestUrl> result = dao.FindByTesturl(testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.QuickFindByTesturl
	 */
	public List<TestUrl> QuickFindByTesturl(String testurl) throws SQLException {
		List<TestUrl> result = dao.QuickFindByTesturl(testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.FindByParentid
	 */
	public List<TestUrl> FindByParentid(Integer parentid) throws SQLException {
		List<TestUrl> result = dao.FindByParentid(parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.QuickFindByParentid
	 */
	public List<TestUrl> QuickFindByParentid(Integer parentid) throws SQLException {
		List<TestUrl> result = dao.QuickFindByParentid(parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.Insert
	 */
	public Integer Insert(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ITestUrlService.Update
	 */
	public int Update(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ITestUrlService.Delete
	 */
	public int Delete(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ITestUrlService.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		int result = dao.DeleteByTemplateid(templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.DeleteByTesturl
	 */
	public int DeleteByTesturl(String testurl) throws SQLException {
		int result = dao.DeleteByTesturl(testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.DeleteByParentid
	 */
	public int DeleteByParentid(Integer parentid) throws SQLException {
		int result = dao.DeleteByParentid(parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlService.Reload
	 */
	public void Reload(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
}
