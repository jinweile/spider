package com.etaoshi.spider.dao.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.etaoshi.spider.model.DataModel;
import com.etaoshi.spider.dao.intf.IDataModelDao;
	
/**
 *DataModelDao is the implementation of IDataModelDao
 */
public class DataModelDao implements IDataModelDao {
	
	private SqlMapClient mapper = null;
	
	/**
	 * data map
	 * @param mapper
	 */
	public void setMapper(SqlMapClient mapper){
		this.mapper = mapper;
	}

	/**
	 *Implements IDataModelDao.GetCount
	 */
	public int GetCount() throws SQLException {
		String stmtId = "DataModel-GetCount";
		int result = (Integer)mapper.queryForObject(stmtId);
		return result;
	}

	/**
	 *Implements IDataModelDao.Find
	 */
	public DataModel Find(Integer id) throws SQLException {
		String stmtId = "DataModel-Find";
		DataModel result = (DataModel) mapper.queryForObject(stmtId, id);
		return result;
	}

	/**
	 *Implements IDataModelDao.FindAll
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> FindAll() throws SQLException {
		String stmtId = "DataModel-FindAll";
		List<DataModel> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.QuickFindAll
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> QuickFindAll() throws SQLException {
		String stmtId = "DataModel-QuickFindAll";
		List<DataModel> result = mapper.queryForList(stmtId, null);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.FindByName
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> FindByName(String name) throws SQLException {
		String stmtId = "DataModel-FindByName";
		List<DataModel> result = mapper.queryForList(stmtId, name);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.FindByTablename
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> FindByTablename(String tablename) throws SQLException {
		String stmtId = "DataModel-FindByTablename";
		List<DataModel> result = mapper.queryForList(stmtId, tablename);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.FindByCreationdate
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> FindByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "DataModel-FindByCreationdate";
		List<DataModel> result = mapper.queryForList(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.FindByRemark
	 */
	@SuppressWarnings("unchecked")
	public List<DataModel> FindByRemark(String remark) throws SQLException {
		String stmtId = "DataModel-FindByRemark";
		List<DataModel> result = mapper.queryForList(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.Insert
	 */
	public Integer Insert(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "DataModel-Insert";
		return (Integer) mapper.insert(stmtId, obj);
	}
	
	/**
	 *Implements IDataModelDao.Update
	 */
	public int Update(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "DataModel-Update";
		return mapper.update(stmtId, obj);
	}
	
	/**
	 *Implements IDataModelDao.Delete
	 */
	public int Delete(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "DataModel-Delete";
		return mapper.delete(stmtId, obj);
	}
	
	/**
	 *Implements IDataModelDao.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		String stmtId = "DataModel-DeleteByName";
		int result = mapper.delete(stmtId, name);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.DeleteByTablename
	 */
	public int DeleteByTablename(String tablename) throws SQLException {
		String stmtId = "DataModel-DeleteByTablename";
		int result = mapper.delete(stmtId, tablename);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		String stmtId = "DataModel-DeleteByCreationdate";
		int result = mapper.delete(stmtId, creationdate);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		String stmtId = "DataModel-DeleteByRemark";
		int result = mapper.delete(stmtId, remark);
		return result;
	}
	
	/**
	 *Implements IDataModelDao.Reload
	 */
	public void Reload(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		String stmtId = "DataModel-Find";
		mapper.queryForObject(stmtId, obj, obj);
	}
	
	/**
	 * 创建基础数据模型
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int CreateDataModel(String tablename) throws SQLException {
		if (tablename == null || tablename.isEmpty()) throw new NullPointerException("tablename");
		String stmtId = "DataModel-CreateDataModel";
		return mapper.update(stmtId, tablename);
	}
	
	/**
	 * 判断数据库中是否表是否存在
	 * @param tablename
	 * @return
	 * @throws SQLException
	 */
	public Integer ChkDbTableName(String tablename) throws SQLException {
		String stmtId = "DataModel-ChkDbTableName";
		Integer result = (Integer)mapper.queryForObject(stmtId, tablename);
		return result;
	}
	
}
