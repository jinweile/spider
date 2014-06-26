package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * SpiderRegTemplate
 */
public class SpiderRegTemplate implements Serializable {
    

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
	

    private Integer typeid;
	
	/**
	 *getter typeid
	 */
	public Integer getTypeid() {
    	return typeid;
    }
	
	/**
	 *setter typeid
	 */
	public void setTypeid(Integer typeid) {
    	this.typeid = typeid;
    }
	

    private String spiderreg;
	
	/**
	 *getter spiderreg
	 */
	public String getSpiderreg() {
    	return spiderreg;
    }
	
	/**
	 *setter spiderreg
	 */
	public void setSpiderreg(String spiderreg) {
    	this.spiderreg = spiderreg;
    }
	

    private Integer spiderorderby;
	
	/**
	 *getter spiderorderby
	 */
	public Integer getSpiderorderby() {
    	return spiderorderby;
    }
	
	/**
	 *setter spiderorderby
	 */
	public void setSpiderorderby(Integer spiderorderby) {
    	this.spiderorderby = spiderorderby;
    }
	

    private Integer isspiderentry;
	
	/**
	 *getter isspiderentry
	 */
	public Integer getIsspiderentry() {
    	return isspiderentry;
    }
	
	/**
	 *setter isspiderentry
	 */
	public void setIsspiderentry(Integer isspiderentry) {
    	this.isspiderentry = isspiderentry;
    }
	

    private String entryrule;
	
	/**
	 *getter entryrule
	 */
	public String getEntryrule() {
    	return entryrule;
    }
	
	/**
	 *setter entryrule
	 */
	public void setEntryrule(String entryrule) {
    	this.entryrule = entryrule;
    }
	

}
