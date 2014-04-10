package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * TemplateDataModel
 */
public class TemplateDataModel implements Serializable {
    

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
	

}
