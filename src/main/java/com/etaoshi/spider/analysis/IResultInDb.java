package com.etaoshi.spider.analysis;

import java.util.List;

/**
 * 解析出来的数据入库
 * @author jinweile
 *
 */
public interface IResultInDb {

	/**
	 * insert
	 * @param insert_sql
	 */
	void Insert(List<String> insert_sql_list);
	
}
