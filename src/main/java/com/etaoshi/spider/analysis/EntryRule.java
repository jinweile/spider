package com.etaoshi.spider.analysis;

/**
 * 入口算法枚举
 * 
 * @author jinweile
 * 
 */
public enum EntryRule {

	/**
	 * http请求的模式 格式: 
	 * [method]
	 * GET
	 */
	method(1),

	/**
	 * POST请求的表单参数(method=POST时有效) 格式: 
	 * [postparams]
	 * cid={{array.cid}}&fid={{request.fid}}
	 */
	postparams(2),

	/**
	 * url作为递归抓取的入口 格式: 
	 * [url] 
	 * http://www.wangpiao.com/{{request.cid}}
	 */
	url(3),

	/**
	 * 表示当前抓取的内容作为递归的入口(与url只允许有一个) 格式: 
	 * [content] 
	 * {{array.enter}}
	 */
	content(4),

	/**
	 * 下一步入口需要传入的参数 格式: 
	 * [response] 
	 * {{array.cid}}{{request.cname}}{{array.curl}}
	 */
	response(5),

	/**
	 * 当前页需要获取的传入参数(格式{{request.cid}}，表示获取传入参数)
	 */
	request(6),

	/**
	 * 表示这一步入库的类型(映射的表名) 格式: 
	 * [insertdb] 
	 * cinema({{request.cid}}{{request.cname}})
	 */
	insertdb(7),

	/**
	 * http头信息 格式: 
	 * [header] 
	 * ((Referer={{request.curl}}))
	 */
	header(8),

	/**
	 * 
	 * ssl证书文件名，都放在config目录下 格式:
	 * [ssl]
	 * 1.scr
	 */
	ssl(9),
	
	/**
	 * 分页参数，格式:
	 * [page]
	 * 1-100|1
	 * 用来替换?page={{page}}
	 */
	page(10),
	
	/**
	 * 唯一键列名，格式:
	 * [uniquekey]
	 * request.ctid|array.ctname
	 */
	uniquekey(11);

	private EntryRule(int entryrule) {
		this.entryrule = entryrule;
	}

	public EntryRule getEntryRule(int value){
		EntryRule rule = null;
		for(EntryRule e : EntryRule.values()){
			if(e.getEntryRuleValue() == value){
				rule = e;
				break;
			}
		}
		
		return rule;
	}

	public int getEntryRuleValue() {
		return this.entryrule;
	}

	private int entryrule;

}
