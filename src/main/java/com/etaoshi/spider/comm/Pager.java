package com.etaoshi.spider.comm;

public class Pager {
	private Integer countNum;//记录总数
	private Integer pageIndex;// 当前页数
	private Integer pageCount;// 总页数
	private String urlPath;// 页面链接
	private String urlParam;// 传入的参数
	
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
}
