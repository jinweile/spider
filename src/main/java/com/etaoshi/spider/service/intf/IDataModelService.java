package com.etaoshi.spider.service.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *IDataModelService is the Service interface for com.etaoshi.spider.model.DataModel
 */
public interface IDataModelService {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a DataModel instance by the primary key value
	 */
	DataModel Find(Integer id) throws SQLException;

	/**
	 *Finds all DataModel instances.
	 */
	List<DataModel> FindAll() throws SQLException;
	
	/**
	 *Finds all DataModel instances without Lob columns loaded.
	 */
	List<DataModel> QuickFindAll() throws SQLException;
	
	/**
	 *Finds DataModel instances by Name value.
	 */
	List<DataModel> FindByName(String name) throws SQLException;
	
	/**
	 *Finds DataModel instances by Tablename value.
	 */
	List<DataModel> FindByTablename(String tablename) throws SQLException;
	
	/**
	 *Finds DataModel instances by Creationdate value.
	 */
	List<DataModel> FindByCreationdate(Date creationdate) throws SQLException;
	
	/**
	 *Finds DataModel instances by Remark value.
	 */
	List<DataModel> FindByRemark(String remark) throws SQLException;
	
	/**
	 *Inserts a new DataModel instance into underlying database table.
	 */
	Integer Insert(DataModel obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a DataModel instance.
	 */
	int Update(DataModel obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a DataModel instance.
	 */
	int Delete(DataModel obj) throws SQLException;
	
    /**
	 *Deletes DataModel instances by DataModel.Name.
	 */
	int DeleteByName(String name) throws SQLException;
	
    /**
	 *Deletes DataModel instances by DataModel.Tablename.
	 */
	int DeleteByTablename(String tablename) throws SQLException;
	
    /**
	 *Deletes DataModel instances by DataModel.Creationdate.
	 */
	int DeleteByCreationdate(Date creationdate) throws SQLException;
	
    /**
	 *Deletes DataModel instances by DataModel.Remark.
	 */
	int DeleteByRemark(String remark) throws SQLException;
	
	/**
	 *Reload the underlying database record of a DataModel instance.
	 */
	void Reload(DataModel obj) throws SQLException;
	
	/**
	 * 创建基础数据模型
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	int CreateDataModel(String tablename) throws SQLException;
	
	/**
	 * 判断数据库中是否表是否存在
	 * @param tablename
	 * @return
	 * @throws SQLException
	 */
	Integer ChkDbTableName(String tablename) throws SQLException;
	
}
