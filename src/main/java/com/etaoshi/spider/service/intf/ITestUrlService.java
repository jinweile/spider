package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ITestUrlService is the Service interface for com.etaoshi.spider.model.TestUrl
 */
public interface ITestUrlService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a TestUrl instance by the primary key value
	 */
	TestUrl Find(Integer id) throws SQLException;

	/**
	 *Finds a TestUrl instance by the primary key value without Lob columns loaded
	 */
	TestUrl QuickFind(Integer id) throws SQLException;
	
	/**
	 *Finds all TestUrl instances.
	 */
	List<TestUrl> FindAll() throws SQLException;
	
	/**
	 *Finds all TestUrl instances without Lob columns loaded.
	 */
	List<TestUrl> QuickFindAll() throws SQLException;
	
	/**
	 *Finds TestUrl instances by Templateid value.
	 */
	List<TestUrl> FindByTemplateid(Integer templateid) throws SQLException;
	
	/**
	 *Finds TestUrl instances by Templateid value without Lob columns loaded.
	 */
	List<TestUrl> QuickFindByTemplateid(Integer templateid) throws SQLException;
	
	/**
	 *Finds TestUrl instances by Testurl value.
	 */
	List<TestUrl> FindByTesturl(String testurl) throws SQLException;
	
	/**
	 *Finds TestUrl instances by Testurl value without Lob columns loaded.
	 */
	List<TestUrl> QuickFindByTesturl(String testurl) throws SQLException;
	
	/**
	 *Finds TestUrl instances by Parentid value.
	 */
	List<TestUrl> FindByParentid(Integer parentid) throws SQLException;
	
	/**
	 *Finds TestUrl instances by Parentid value without Lob columns loaded.
	 */
	List<TestUrl> QuickFindByParentid(Integer parentid) throws SQLException;
	
	/**
	 *Inserts a new TestUrl instance into underlying database table.
	 */
	Integer Insert(TestUrl obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a TestUrl instance.
	 */
	int Update(TestUrl obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a TestUrl instance.
	 */
	int Delete(TestUrl obj) throws SQLException;
	
    /**
	 *Deletes TestUrl instances by TestUrl.Templateid.
	 */
	int DeleteByTemplateid(Integer templateid) throws SQLException;
	
    /**
	 *Deletes TestUrl instances by TestUrl.Testurl.
	 */
	int DeleteByTesturl(String testurl) throws SQLException;
	
    /**
	 *Deletes TestUrl instances by TestUrl.Parentid.
	 */
	int DeleteByParentid(Integer parentid) throws SQLException;
	
	/**
	 *Reload the underlying database record of a TestUrl instance.
	 */
	void Reload(TestUrl obj) throws SQLException;
	
}
