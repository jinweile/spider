package com.etaoshi.spider.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;


/**
 * SourceSpider
 */
public class SourceSpider implements Serializable {
    

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
	

    private Integer sourceid;
	
	/**
	 *getter sourceid
	 */
	public Integer getSourceid() {
    	return sourceid;
    }
	
	/**
	 *setter sourceid
	 */
	public void setSourceid(Integer sourceid) {
    	this.sourceid = sourceid;
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
	

    private String spiderentryrule;
	
	/**
	 *getter spiderentryrule
	 */
	public String getSpiderentryrule() {
    	return spiderentryrule;
    }
	
	/**
	 *setter spiderentryrule
	 */
	public void setSpiderentryrule(String spiderentryrule) {
    	this.spiderentryrule = spiderentryrule;
    }
	

    private String jobrule;
	
	/**
	 *getter jobrule
	 */
	public String getJobrule() {
    	return jobrule;
    }
	
	/**
	 *setter jobrule
	 */
	public void setJobrule(String jobrule) {
    	this.jobrule = jobrule;
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
	

}
