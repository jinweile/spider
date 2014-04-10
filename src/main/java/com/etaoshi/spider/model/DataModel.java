package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * DataModel
 */
public class DataModel implements Serializable {
    

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
	

    private String name;
	
	/**
	 *getter name
	 */
	public String getName() {
    	return name;
    }
	
	/**
	 *setter name
	 */
	public void setName(String name) {
    	this.name = name;
    }
	

    private String tablename;
	
	/**
	 *getter tablename
	 */
	public String getTablename() {
    	return tablename;
    }
	
	/**
	 *setter tablename
	 */
	public void setTablename(String tablename) {
    	this.tablename = tablename;
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
