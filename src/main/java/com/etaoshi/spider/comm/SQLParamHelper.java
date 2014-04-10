package com.etaoshi.spider.comm;

public class SQLParamHelper {

	/**
	 * 替换'为''，防止拼sql参数出错
	 * @param value
	 * @return
	 */
	public static String Replace(String value){
		return value.replaceAll("'", "''");
	}
	
}
