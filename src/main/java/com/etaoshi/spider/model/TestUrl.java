package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * TestUrl
 */
public class TestUrl implements Serializable {
    

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
	

    private Integer templateid;
	
	/**
	 *getter templateid
	 */
	public Integer getTemplateid() {
    	return templateid;
    }
	
	/**
	 *setter templateid
	 */
	public void setTemplateid(Integer templateid) {
    	this.templateid = templateid;
    }
	

    private String testurl;
	
	/**
	 *getter testurl
	 */
	public String getTesturl() {
    	return testurl;
    }
	
	/**
	 *setter testurl
	 */
	public void setTesturl(String testurl) {
    	this.testurl = testurl;
    }
	

    private String testcontent;
	
	/**
	 *getter testcontent
	 */
	public String getTestcontent() {
    	return testcontent;
    }
	
	/**
	 *setter testcontent
	 */
	public void setTestcontent(String testcontent) {
    	this.testcontent = testcontent;
    }
	

    private Integer parentid;
	
	/**
	 *getter parentid
	 */
	public Integer getParentid() {
    	return parentid;
    }
	
	/**
	 *setter parentid
	 */
	public void setParentid(Integer parentid) {
    	this.parentid = parentid;
    }
	

}
