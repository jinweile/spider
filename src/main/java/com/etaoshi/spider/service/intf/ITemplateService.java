package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ITemplateService is the Service interface for com.etaoshi.spider.model.Template
 */
public interface ITemplateService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a Template instance by the primary key value
	 */
	Template Find(Integer id) throws SQLException;

	/**
	 *Finds all Template instances.
	 */
	List<Template> FindAll() throws SQLException;
	
	/**
	 *Finds all Template instances without Lob columns loaded.
	 */
	List<Template> QuickFindAll() throws SQLException;
	
	/**
	 *Finds Template instances by Datamodelid value.
	 */
	List<Template> FindByDatamodelid(Integer datamodelid) throws SQLException;
	
	/**
	 *Finds Template instances by Name value.
	 */
	List<Template> FindByName(String name) throws SQLException;
	
	/**
	 *Finds Template instances by Creationdate value.
	 */
	List<Template> FindByCreationdate(Date creationdate) throws SQLException;
	
	/**
	 *Finds Template instances by Remark value.
	 */
	List<Template> FindByRemark(String remark) throws SQLException;
	
	/**
	 *Inserts a new Template instance into underlying database table.
	 */
	Integer Insert(Template obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a Template instance.
	 */
	int Update(Template obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a Template instance.
	 */
	int Delete(Template obj) throws SQLException;
	
    /**
	 *Deletes Template instances by Template.Datamodelid.
	 */
	int DeleteByDatamodelid(Integer datamodelid) throws SQLException;
	
    /**
	 *Deletes Template instances by Template.Name.
	 */
	int DeleteByName(String name) throws SQLException;
	
    /**
	 *Deletes Template instances by Template.Creationdate.
	 */
	int DeleteByCreationdate(Date creationdate) throws SQLException;
	
    /**
	 *Deletes Template instances by Template.Remark.
	 */
	int DeleteByRemark(String remark) throws SQLException;
	
	/**
	 *Reload the underlying database record of a Template instance.
	 */
	void Reload(Template obj) throws SQLException;
	
}
