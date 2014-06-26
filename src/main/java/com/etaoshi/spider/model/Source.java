package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * Source
 */
public class Source implements Serializable {
    

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
	

    private String url;
	
	/**
	 *getter url
	 */
	public String getUrl() {
    	return url;
    }
	
	/**
	 *setter url
	 */
	public void setUrl(String url) {
    	this.url = url;
    }
	

    private Boolean isused;
	
	/**
	 *getter isused
	 */
	public Boolean getIsused() {
    	return isused;
    }
	
	/**
	 *setter isused
	 */
	public void setIsused(Boolean isused) {
    	this.isused = isused;
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
