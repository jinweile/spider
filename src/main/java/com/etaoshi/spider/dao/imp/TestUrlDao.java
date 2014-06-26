package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.TestUrl;
import com.etaoshi.spider.dao.intf.ITestUrlDao;
	
/**
 *TestUrlDao is the implementation of ITestUrlDao
 */
public class TestUrlDao implements ITestUrlDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements ITestUrlDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "TestUrl-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements ITestUrlDao.Find
	 */
	public TestUrl Find(Integer id) throws SQLException {
		String stmtId = "TestUrl-Find";
		TestUrl result = (TestUrl) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements ITestUrlDao.QuickFind
	 */
	public TestUrl QuickFind(Integer id) throws SQLException {
		String stmtId = "TestUrl-QuickFind";
		TestUrl result = (TestUrl) mapper.queryForObject(stmtId, id);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> FindAll() throws SQLException {
		String stmtId = "TestUrl-FindAll";
		List<TestUrl> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> QuickFindAll() throws SQLException {
		String stmtId = "TestUrl-QuickFindAll";
		List<TestUrl> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.FindByTemplateid
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> FindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "TestUrl-FindByTemplateid";
		List<TestUrl> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.QuickFindByTemplateid
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> QuickFindByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "TestUrl-QuickFindByTemplateid";
		List<TestUrl> result = mapper.queryForList(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.FindByTesturl
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> FindByTesturl(String testurl) throws SQLException {
		String stmtId = "TestUrl-FindByTesturl";
		List<TestUrl> result = mapper.queryForList(stmtId, testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.QuickFindByTesturl
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> QuickFindByTesturl(String testurl) throws SQLException {
		String stmtId = "TestUrl-QuickFindByTesturl";
		List<TestUrl> result = mapper.queryForList(stmtId, testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.FindByParentid
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> FindByParentid(Integer parentid) throws SQLException {
		String stmtId = "TestUrl-FindByParentid";
		List<TestUrl> result = mapper.queryForList(stmtId, parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.QuickFindByParentid
	 */
	@SuppressWarnings("unchecked")
	public List<TestUrl> QuickFindByParentid(Integer parentid) throws SQLException {
		String stmtId = "TestUrl-QuickFindByParentid";
		List<TestUrl> result = mapper.queryForList(stmtId, parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.Insert
	 */
	public Integer Insert(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TestUrl-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements ITestUrlDao.Update
	 */
	public int Update(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TestUrl-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements ITestUrlDao.Delete
	 */
	public int Delete(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TestUrl-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements ITestUrlDao.DeleteByTemplateid
	 */
	public int DeleteByTemplateid(Integer templateid) throws SQLException {
		String stmtId = "TestUrl-DeleteByTemplateid";
		int result = mapper.delete(stmtId, templateid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.DeleteByTesturl
	 */
	public int DeleteByTesturl(String testurl) throws SQLException {
		String stmtId = "TestUrl-DeleteByTesturl";
		int result = mapper.delete(stmtId, testurl);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.DeleteByParentid
	 */
	public int DeleteByParentid(Integer parentid) throws SQLException {
		String stmtId = "TestUrl-DeleteByParentid";
		int result = mapper.delete(stmtId, parentid);
		return result;
	}
	
	/**
	 *Implements ITestUrlDao.Reload
	 */
	public void Reload(TestUrl obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "TestUrl-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
}
