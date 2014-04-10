package com.etaoshi.spider.model;

/**
 * 字段类型枚举
 * @author jinweile
 *
 */
public enum enumctype {
	
	/**
	 * int
	 */
	//integer(1),
	
	/**
	 * decimal
	 */
	//decimal(2),
	
	/**
	 * nvarchar
	 */
	nvarchar(3);
	
	/**
	 * datetime
	 */
	//datetime(4);
	
	private enumctype(int ctype){
		this.enumctype = ctype;
	}
	
	public int getEnumctype(){
		return this.enumctype;
	}
	
	private int enumctype;
	
}
