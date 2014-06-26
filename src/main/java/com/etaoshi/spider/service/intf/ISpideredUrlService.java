package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ISpideredUrlService is the Service interface for com.etaoshi.spider.model.SpideredUrl
 */
public interface ISpideredUrlService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a SpideredUrl instance by the primary key value
	 */
	SpideredUrl Find(Integer id) throws SQLException;

	/**
	 *Finds all SpideredUrl instances.
	 */
	List<SpideredUrl> FindAll() throws SQLException;
	
	/**
	 *Finds all SpideredUrl instances without Lob columns loaded.
	 */
	List<SpideredUrl> QuickFindAll() throws SQLException;
	
	/**
	 *Finds SpideredUrl instances by Domain value.
	 */
	List<SpideredUrl> FindByDomain(String domain) throws SQLException;
	
	/**
	 *Finds SpideredUrl instances by Url value.
	 */
	List<SpideredUrl> FindByUrl(String url) throws SQLException;
	
	/**
	 *Finds SpideredUrl instances by Spidereddate value.
	 */
	List<SpideredUrl> FindBySpidereddate(Date spidereddate) throws SQLException;
	
	/**
	 *Inserts a new SpideredUrl instance into underlying database table.
	 */
	Integer Insert(SpideredUrl obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a SpideredUrl instance.
	 */
	int Update(SpideredUrl obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a SpideredUrl instance.
	 */
	int Delete(SpideredUrl obj) throws SQLException;
	
    /**
	 *Deletes SpideredUrl instances by SpideredUrl.Domain.
	 */
	int DeleteByDomain(String domain) throws SQLException;
	
    /**
	 *Deletes SpideredUrl instances by SpideredUrl.Url.
	 */
	int DeleteByUrl(String url) throws SQLException;
	
    /**
	 *Deletes SpideredUrl instances by SpideredUrl.Spidereddate.
	 */
	int DeleteBySpidereddate(Date spidereddate) throws SQLException;
	
	/**
	 *Reload the underlying database record of a SpideredUrl instance.
	 */
	void Reload(SpideredUrl obj) throws SQLException;
	
}
