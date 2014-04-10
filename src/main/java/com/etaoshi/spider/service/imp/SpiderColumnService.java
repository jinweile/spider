package com.etaoshi.spider.service.imp;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.etaoshi.spider.dao.intf.ISpiderColumnDao;

import com.etaoshi.spider.model.SpiderColumn;
import com.etaoshi.spider.service.intf.ISpiderColumnService;
	
/**
 *SpiderColumnService is the implementation of ISpiderColumnService
 */
public class SpiderColumnService implements ISpiderColumnService {
	
	private ISpiderColumnDao dao = null;
	
	/**
	 * dao interface
	 * @param dao
	 */
	public void setSpiderColumnDao(ISpiderColumnDao dao){
		this.dao = dao;
	}

	/**
	 *Implements ISpiderColumnService.GetCount
	 */
	public int GetCount() throws SQLException {
		int result = dao.GetCount();
		return result;
	}

	/**
	 *Implements ISpiderColumnService.Find
	 */
	public SpiderColumn Find(Integer id) throws SQLException {
		SpiderColumn result = dao.Find(id);
		return result;
	}

	/**
	 *Implements ISpiderColumnService.FindAll
	 */
	public List<SpiderColumn> FindAll() throws SQLException {
		List<SpiderColumn> result = dao.FindAll();
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.QuickFindAll
	 */
	public List<SpiderColumn> QuickFindAll() throws SQLException {
		List<SpiderColumn> result = dao.QuickFindAll();
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByDatamodelid
	 */
	public List<SpiderColumn> FindByDatamodelid(Integer datamodelid) throws SQLException {
		List<SpiderColumn> result = dao.FindByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByColumnename
	 */
	public List<SpiderColumn> FindByColumnename(String columnename) throws SQLException {
		List<SpiderColumn> result = dao.FindByColumnename(columnename);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByColumncname
	 */
	public List<SpiderColumn> FindByColumncname(String columncname) throws SQLException {
		List<SpiderColumn> result = dao.FindByColumncname(columncname);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByColumntype
	 */
	public List<SpiderColumn> FindByColumntype(Integer columntype) throws SQLException {
		List<SpiderColumn> result = dao.FindByColumntype(columntype);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByColumnlen
	 */
	public List<SpiderColumn> FindByColumnlen(Integer columnlen) throws SQLException {
		List<SpiderColumn> result = dao.FindByColumnlen(columnlen);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByColumnaccuracy
	 */
	public List<SpiderColumn> FindByColumnaccuracy(Integer columnaccuracy) throws SQLException {
		List<SpiderColumn> result = dao.FindByColumnaccuracy(columnaccuracy);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByCreationdate
	 */
	public List<SpiderColumn> FindByCreationdate(Date creationdate) throws SQLException {
		List<SpiderColumn> result = dao.FindByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.FindByRemark
	 */
	public List<SpiderColumn> FindByRemark(String remark) throws SQLException {
		List<SpiderColumn> result = dao.FindByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.Insert
	 */
	public Integer Insert(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Insert(obj);
	}
	
	/**
	 *Implements ISpiderColumnService.Update
	 */
	public int Update(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Update(obj);
	}
	
	/**
	 *Implements ISpiderColumnService.Delete
	 */
	public int Delete(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		return dao.Delete(obj);
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByDatamodelid
	 */
	public int DeleteByDatamodelid(Integer datamodelid) throws SQLException {
		int result = dao.DeleteByDatamodelid(datamodelid);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByColumnename
	 */
	public int DeleteByColumnename(String columnename) throws SQLException {
		int result = dao.DeleteByColumnename(columnename);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByColumncname
	 */
	public int DeleteByColumncname(String columncname) throws SQLException {
		int result = dao.DeleteByColumncname(columncname);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByColumntype
	 */
	public int DeleteByColumntype(Integer columntype) throws SQLException {
		int result = dao.DeleteByColumntype(columntype);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByColumnlen
	 */
	public int DeleteByColumnlen(Integer columnlen) throws SQLException {
		int result = dao.DeleteByColumnlen(columnlen);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByColumnaccuracy
	 */
	public int DeleteByColumnaccuracy(Integer columnaccuracy) throws SQLException {
		int result = dao.DeleteByColumnaccuracy(columnaccuracy);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByCreationdate
	 */
	public int DeleteByCreationdate(Date creationdate) throws SQLException {
		int result = dao.DeleteByCreationdate(creationdate);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.DeleteByRemark
	 */
	public int DeleteByRemark(String remark) throws SQLException {
		int result = dao.DeleteByRemark(remark);
		return result;
	}
	
	/**
	 *Implements ISpiderColumnService.Reload
	 */
	public void Reload(SpiderColumn obj) throws SQLException {
		if (obj == null) throw new NullPointerException("obj");
		dao.Reload(obj);
	}
	
	/**
	 * 新增字段
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int Alter(Map map) throws SQLException{
		if (map == null) throw new NullPointerException("map");
		return dao.Alter(map);
	}
	
	/**
	 * 根据模版id获取列列表
	 * @param templateid
	 * @return
	 * @throws SQLException
	 */
	public List<SpiderColumn> FindByTemplateid(Integer templateid) throws SQLException {
		return dao.FindByTemplateid(templateid);
	}
	
	/**
	 * 获取所有列哈希表
	 */
	public Map<Integer, String> FindAllMap() throws SQLException {
		return dao.FindAllMap();
	}
	
	/**
	 * 插入数据到数据模型创建的表中
	 * @param value
	 * @throws SQLException
	 */
	public void InsertIntoDataModel(String sql) throws SQLException {
		dao.InsertIntoDataModel(sql);
	}
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	public List getDynamicTable(String sql) throws SQLException {
		return dao.getDynamicTable(sql);
	}
	
	/**
	 * 根据表名，开始行，结束行 获取表数据
	 * @param params
	 * @throws SQLException 
	 */
	public Integer getDynamicTableCount(String tablename) throws SQLException {
		return dao.getDynamicTableCount(tablename);
	}
	
}
