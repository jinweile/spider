package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ITemplateDataModelService is the Service interface for com.etaoshi.spider.model.TemplateDataModel
 */
public interface ITemplateDataModelService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a TemplateDataModel instance by the primary key value
	 */
	TemplateDataModel Find(Integer id) throws SQLException;

	/**
	 *Finds all TemplateDataModel instances.
	 */
	List<TemplateDataModel> FindAll() throws SQLException;
	
	/**
	 *Finds all TemplateDataModel instances without Lob columns loaded.
	 */
	List<TemplateDataModel> QuickFindAll() throws SQLException;
	
	/**
	 *Finds TemplateDataModel instances by Templateid value.
	 */
	List<TemplateDataModel> FindByTemplateid(Integer templateid) throws SQLException;
	
	/**
	 *Finds TemplateDataModel instances by Datamodelid value.
	 */
	List<TemplateDataModel> FindByDatamodelid(Integer datamodelid) throws SQLException;
	
	/**
	 *Inserts a new TemplateDataModel instance into underlying database table.
	 */
	Integer Insert(TemplateDataModel obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a TemplateDataModel instance.
	 */
	int Update(TemplateDataModel obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a TemplateDataModel instance.
	 */
	int Delete(TemplateDataModel obj) throws SQLException;
	
    /**
	 *Deletes TemplateDataModel instances by TemplateDataModel.Templateid.
	 */
	int DeleteByTemplateid(Integer templateid) throws SQLException;
	
    /**
	 *Deletes TemplateDataModel instances by TemplateDataModel.Datamodelid.
	 */
	int DeleteByDatamodelid(Integer datamodelid) throws SQLException;
	
	/**
	 *Reload the underlying database record of a TemplateDataModel instance.
	 */
	void Reload(TemplateDataModel obj) throws SQLException;
	
	/**
	 * 更新模版数据模型关系
	 * @param params
	 * @throws SQLException
	 */
	void UpdateByTemplateidAndDataModelid(Map params) throws SQLException;
	
}
