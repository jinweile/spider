package com.etaoshi.spider.dao.intf;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.model.*;

/**
 *ISpiderColumnDao is the DAO interface for com.etaoshi.spider.model.SpiderColumn
 */
public interface ISpiderColumnDao {        

	/**
	 *Returns the total count of objects
	 */
	int GetCount() throws SQLException;

	/**
	 *Finds a SpiderColumn instance by the primary key value
	 */
	SpiderColumn Find(Integer id) throws SQLException;

	/**
	 *Finds all SpiderColumn instances.
	 */
	List<SpiderColumn> FindAll() throws SQLException;
	
	/**
	 *Finds all SpiderColumn instances without Lob columns loaded.
	 */
	List<SpiderColumn> QuickFindAll() throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Datamodelid value.
	 */
	List<SpiderColumn> FindByDatamodelid(Integer datamodelid) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Columnename value.
	 */
	List<SpiderColumn> FindByColumnename(String columnename) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Columncname value.
	 */
	List<SpiderColumn> FindByColumncname(String columncname) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Columntype value.
	 */
	List<SpiderColumn> FindByColumntype(Integer columntype) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Columnlen value.
	 */
	List<SpiderColumn> FindByColumnlen(Integer columnlen) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Columnaccuracy value.
	 */
	List<SpiderColumn> FindByColumnaccuracy(Integer columnaccuracy) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Creationdate value.
	 */
	List<SpiderColumn> FindByCreationdate(Date creationdate) throws SQLException;
	
	/**
	 *Finds SpiderColumn instances by Remark value.
	 */
	List<SpiderColumn> FindByRemark(String remark) throws SQLException;
	
	/**
	 *Inserts a new SpiderColumn instance into underlying database table.
	 */
	Integer Insert(SpiderColumn obj) throws SQLException;
	
	/**
	 *Update the underlying database record of a SpiderColumn instance.
	 */
	int Update(SpiderColumn obj) throws SQLException;
	
	/**
	 *Delete the underlying database record of a SpiderColumn instance.
	 */
	int Delete(SpiderColumn obj) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Datamodelid.
	 */
	int DeleteByDatamodelid(Integer datamodelid) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Columnename.
	 */
	int DeleteByColumnename(String columnename) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Columncname.
	 */
	int DeleteByColumncname(String columncname) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Columntype.
	 */
	int DeleteByColumntype(Integer columntype) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Columnlen.
	 */
	int DeleteByColumnlen(Integer columnlen) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Columnaccuracy.
	 */
	int DeleteByColumnaccuracy(Integer columnaccuracy) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Creationdate.
	 */
	int DeleteByCreationdate(Date creationdate) throws SQLException;
	
    /**
	 *Deletes SpiderColumn instances by SpiderColumn.Remark.
	 */
	int DeleteByRemark(String remark) throws SQLException;
	
	/**
	 *Reload the underlying database record of a SpiderColumn instance.
	 */
	void Reload(SpiderColumn obj) throws SQLException;
	
	/**
	 * 新增字段
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int Alter(Map map) throws SQLException;
	
	/**
	 * 根据模版id获取列列表
	 * @param templateid
	 * @return
	 * @throws SQLException
	 */
	List<SpiderColumn> FindByTemplateid(Integer templateid) throws SQLException;
	
	
	/**
	 * 获取所有列哈希表
	 */
	Map<Integer, String> FindAllMap() throws SQLException;
	
	/**
	 * 插入数据到数据模型创建的表中
	 * @param value
	 * @throws SQLException
	 */
	void InsertIntoDataModel(String sql) throws SQLException;
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	List getDynamicTable(String sql) throws SQLException;
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	Integer getDynamicTableCount(String tablename) throws SQLException;
	
}
