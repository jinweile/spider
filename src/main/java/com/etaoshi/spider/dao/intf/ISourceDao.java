package com.etaoshi.spider.dao.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ISourceDao is the DAO interface for com.etaoshi.spider.model.Source
 */
public interface ISourceDao {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a Source instance by the primary key value
	 */
	Source Find(Integer id) throws SQLException;

	/**
	 *Finds all Source instances.
	 */
	List<Source> FindAll() throws SQLException;
	
	/**
	 *Finds all Source instances without Lob columns loaded.
	 */
	List<Source> QuickFindAll() throws SQLException;
	
	/**
	 *Finds Source instances by Name value.
	 */
	List<Source> FindByName(String name) throws SQLException;
	
	/**
	 *Finds Source instances by Url value.
	 */
	List<Source> FindByUrl(String url) throws SQLException;
	
	/**
	 *Finds Source instances by Isused value.
	 */
	List<Source> FindByIsused(Boolean isused) throws SQLException;
	
	/**
	 *Finds Source instances by Remark value.
	 */
	List<Source> FindByRemark(String remark) throws SQLException;
	
	/**
	 *Inserts a new Source instance into underlying database table.
	 */
	Integer Insert(Source obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a Source instance.
	 */
	int Update(Source obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a Source instance.
	 */
	int Delete(Source obj) throws SQLException;
	
    /**
	 *Deletes Source instances by Source.Name.
	 */
	int DeleteByName(String name) throws SQLException;
	
    /**
	 *Deletes Source instances by Source.Url.
	 */
	int DeleteByUrl(String url) throws SQLException;
	
    /**
	 *Deletes Source instances by Source.Isused.
	 */
	int DeleteByIsused(Boolean isused) throws SQLException;
	
    /**
	 *Deletes Source instances by Source.Remark.
	 */
	int DeleteByRemark(String remark) throws SQLException;
	
	/**
	 *Reload the underlying database record of a Source instance.
	 */
	void Reload(Source obj) throws SQLException;
	
}
