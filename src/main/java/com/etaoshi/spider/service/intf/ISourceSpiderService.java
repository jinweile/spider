package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ISourceSpiderService is the Service interface for com.etaoshi.spider.model.SourceSpider
 */
public interface ISourceSpiderService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a SourceSpider instance by the primary key value
	 */
	SourceSpider Find(Integer id) throws SQLException;

	/**
	 *Finds all SourceSpider instances.
	 */
	List<SourceSpider> FindAll() throws SQLException;
	
	/**
	 *Finds all SourceSpider instances without Lob columns loaded.
	 */
	List<SourceSpider> QuickFindAll() throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Sourceid value.
	 */
	List<SourceSpider> FindBySourceid(Integer sourceid) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Templateid value.
	 */
	List<SourceSpider> FindByTemplateid(Integer templateid) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Spiderentryrule value.
	 */
	List<SourceSpider> FindBySpiderentryrule(String spiderentryrule) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Jobrule value.
	 */
	List<SourceSpider> FindByJobrule(String jobrule) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Creationdate value.
	 */
	List<SourceSpider> FindByCreationdate(Date creationdate) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Remark value.
	 */
	List<SourceSpider> FindByRemark(String remark) throws SQLException;
	
	/**
	 *Finds SourceSpider instances by Isused value.
	 */
	List<SourceSpider> FindByIsused(Boolean isused) throws SQLException;
	
	/**
	 *Inserts a new SourceSpider instance into underlying database table.
	 */
	Integer Insert(SourceSpider obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a SourceSpider instance.
	 */
	int Update(SourceSpider obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a SourceSpider instance.
	 */
	int Delete(SourceSpider obj) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Sourceid.
	 */
	int DeleteBySourceid(Integer sourceid) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Templateid.
	 */
	int DeleteByTemplateid(Integer templateid) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Spiderentryrule.
	 */
	int DeleteBySpiderentryrule(String spiderentryrule) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Jobrule.
	 */
	int DeleteByJobrule(String jobrule) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Creationdate.
	 */
	int DeleteByCreationdate(Date creationdate) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Remark.
	 */
	int DeleteByRemark(String remark) throws SQLException;
	
    /**
	 *Deletes SourceSpider instances by SourceSpider.Isused.
	 */
	int DeleteByIsused(Boolean isused) throws SQLException;
	
	/**
	 *Reload the underlying database record of a SourceSpider instance.
	 */
	void Reload(SourceSpider obj) throws SQLException;
	
}
