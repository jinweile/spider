package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * SpiderColumn
 */
public class SpiderColumn implements Serializable {
    

    private Integer id;
	
	/**
	 *getter id
	 */
	public Integer getId() {
    	return id;
    }
	
	/**
	 *setter id
	 */
	public void setId(Integer id) {
    	this.id = id;
    }
	

    private Integer datamodelid;
	
	/**
	 *getter datamodelid
	 */
	public Integer getDatamodelid() {
    	return datamodelid;
    }
	
	/**
	 *setter datamodelid
	 */
	public void setDatamodelid(Integer datamodelid) {
    	this.datamodelid = datamodelid;
    }
	

    private String columnename;
	
	/**
	 *getter columnename
	 */
	public String getColumnename() {
    	return columnename;
    }
	
	/**
	 *setter columnename
	 */
	public void setColumnename(String columnename) {
    	this.columnename = columnename;
    }
	

    private String columncname;
	
	/**
	 *getter columncname
	 */
	public String getColumncname() {
    	return columncname;
    }
	
	/**
	 *setter columncname
	 */
	public void setColumncname(String columncname) {
    	this.columncname = columncname;
    }
	

    private Integer columntype;
	
	/**
	 *getter columntype
	 */
	public Integer getColumntype() {
    	return columntype;
    }
	
	/**
	 *setter columntype
	 */
	public void setColumntype(Integer columntype) {
    	this.columntype = columntype;
    }

    private Integer columnlen;
	
	/**
	 *getter columnlen
	 */
	public Integer getColumnlen() {
    	return columnlen;
    }
	
	/**
	 *setter columnlen
	 */
	public void setColumnlen(Integer columnlen) {
    	this.columnlen = columnlen;
    }
	

    private Integer columnaccuracy;
	
	/**
	 *getter columnaccuracy
	 */
	public Integer getColumnaccuracy() {
    	return columnaccuracy;
    }
	
	/**
	 *setter columnaccuracy
	 */
	public void setColumnaccuracy(Integer columnaccuracy) {
    	this.columnaccuracy = columnaccuracy;
    }
	

    private Date creationdate;
	
	/**
	 *getter creationdate
	 */
	public Date getCreationdate() {
    	return creationdate;
    }
	
	/**
	 *setter creationdate
	 */
	public void setCreationdate(Date creationdate) {
    	this.creationdate = creationdate;
    }
	

    private String remark;
	
	/**
	 *getter remark
	 */
	public String getRemark() {
    	return remark;
    }
	
	/**
	 *setter remark
	 */
	public void setRemark(String remark) {
    	this.remark = remark;
    }
	

}
