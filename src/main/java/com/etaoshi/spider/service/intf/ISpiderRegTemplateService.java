package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ISpiderRegTemplateService is the Service interface for com.etaoshi.spider.model.SpiderRegTemplate
 */
public interface ISpiderRegTemplateService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a SpiderRegTemplate instance by the primary key value
	 */
	SpiderRegTemplate Find(Integer id) throws SQLException;

	/**
	 *Finds all SpiderRegTemplate instances.
	 */
	List<SpiderRegTemplate> FindAll() throws SQLException;
	
	/**
	 *Finds all SpiderRegTemplate instances without Lob columns loaded.
	 */
	List<SpiderRegTemplate> QuickFindAll() throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Templateid value.
	 */
	List<SpiderRegTemplate> FindByTemplateid(Integer templateid) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Parentid value.
	 */
	List<SpiderRegTemplate> FindByParentid(Integer parentid) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Typeid value.
	 */
	List<SpiderRegTemplate> FindByTypeid(Integer typeid) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Spiderreg value.
	 */
	List<SpiderRegTemplate> FindBySpiderreg(String spiderreg) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Spiderorderby value.
	 */
	List<SpiderRegTemplate> FindBySpiderorderby(Integer spiderorderby) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Isspiderentry value.
	 */
	List<SpiderRegTemplate> FindByIsspiderentry(Integer isspiderentry) throws SQLException;
	
	/**
	 *Finds SpiderRegTemplate instances by Entryrule value.
	 */
	List<SpiderRegTemplate> FindByEntryrule(String entryrule) throws SQLException;
	
	/**
	 *Inserts a new SpiderRegTemplate instance into underlying database table.
	 */
	Integer Insert(SpiderRegTemplate obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a SpiderRegTemplate instance.
	 */
	int Update(SpiderRegTemplate obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a SpiderRegTemplate instance.
	 */
	int Delete(SpiderRegTemplate obj) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Templateid.
	 */
	int DeleteByTemplateid(Integer templateid) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Parentid.
	 */
	int DeleteByParentid(Integer parentid) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Typeid.
	 */
	int DeleteByTypeid(Integer typeid) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Spiderreg.
	 */
	int DeleteBySpiderreg(String spiderreg) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Spiderorderby.
	 */
	int DeleteBySpiderorderby(Integer spiderorderby) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Isspiderentry.
	 */
	int DeleteByIsspiderentry(Integer isspiderentry) throws SQLException;
	
    /**
	 *Deletes SpiderRegTemplate instances by SpiderRegTemplate.Entryrule.
	 */
	int DeleteByEntryrule(String entryrule) throws SQLException;
	
	/**
	 *Reload the underlying database record of a SpiderRegTemplate instance.
	 */
	void Reload(SpiderRegTemplate obj) throws SQLException;
	
}
