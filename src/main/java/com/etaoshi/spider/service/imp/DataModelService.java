package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.IDataModelDao;

import com.etaoshi.spider.model.DataModel;
import com.etaoshi.spider.service.intf.IDataModelService;
	
/**
 *DataModelService is the implementation of IDataModelService
 */
public class DataModelService implements IDataModelService {
	
	private IDataModelDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setDataModelDao(IDataModelDao dao){
		this.dao = dao;
	}

	/**
	 *Implements IDataModelService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements IDataModelService.Find
	 */
	public DataModel Find(Integer id) throws SQLException {
		DataModel result = dao.Find(id);
		return result;
	}

	/**
	 *Implements IDataModelService.FindAll
	 */
	public List<DataModel> FindAll() throws SQLException {
		List<DataModel> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements IDataModelService.QuickFindAll
	 */
	public List<DataModel> QuickFindAll() throws SQLException {
		List<DataModel> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements IDataModelService.FindByName
	 */
	public List<DataModel> FindByName(String name) throws SQLException {
		List<DataModel> result = dao.FindByName(name);
		return result;
	}
	
	/**
	 *Implements IDataModelService.FindByTablename
	 */
	public List<DataModel> FindByTablename(String tablename) throws SQLException {
		List<DataModel> result = dao.FindByTablename(tablename);
		return result;
	}
	
	/**
	 *Implements IDataModelService.FindByCreationdate
	 */
	public List<DataModel> FindByCreationdate(Date creationdate) throws SQLException {
		List<DataModel> result = dao.FindByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements IDataModelService.FindByRemark
	 */
	public List<DataModel> FindByRemark(String remark) throws SQLException {
		List<DataModel> result = dao.FindByRemark(remark);
		return result;
	}
	
	/**
	 *Implements IDataModelService.Insert
	 */
	public Integer Insert(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements IDataModelService.Update
	 */
	public int Update(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements IDataModelService.Delete
	 */
	public int Delete(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements IDataModelService.DeleteByName
	 */
	public int DeleteByName(String name) throws SQLException {
		int result = dao.DeleteByName(name);
		return result;
	}
	
	/**
	 *Implements IDataModelService.DeleteByTablename
	 */
	public int DeleteByTablename(String tablename) throws SQLException {
		int result = dao.DeleteByTablename(tablename);
		return result;
	}
	
	/**
	 *Implements IDataModelService.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		int result = dao.DeleteByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements IDataModelService.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		int result = dao.DeleteByRemark(remark);
		return result;
	}
	
	/**
	 *Implements IDataModelService.Reload
	 */
	public void Reload(DataModel obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
	/**
	 * 创建基础数据模型
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int CreateDataModel(String tablename) throws SQLException {
		if (tablename == null || tablename.isEmpty()) throw new NullPointerException("tablename");
		return dao.CreateDataModel(tablename);
	}
	
	/**
	 * 判断数据库中是否表是否存在
	 * @param tablename
	 * @return
	 * @throws SQLException
	 */
	public Integer ChkDbTableName(String tablename) throws SQLException {
		if (tablename == null || tablename.isEmpty()) throw new NullPointerException("tablename");
		return dao.ChkDbTableName(tablename);
	}
	
}
