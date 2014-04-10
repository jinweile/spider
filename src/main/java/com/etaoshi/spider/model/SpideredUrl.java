package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * SpideredUrl
 */
public class SpideredUrl implements Serializable {
    

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
	

    private String domain;
	
	/**
	 *getter domain
	 */
	public String getDomain() {
    	return domain;
    }
	
	/**
	 *setter domain
	 */
	public void setDomain(String domain) {
    	this.domain = domain;
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
	

    private Date spidereddate;
	
	/**
	 *getter spidereddate
	 */
	public Date getSpidereddate() {
    	return spidereddate;
    }
	
	/**
	 *setter spidereddate
	 */
	public void setSpidereddate(Date spidereddate) {
    	this.spidereddate = spidereddate;
    }
	

}
