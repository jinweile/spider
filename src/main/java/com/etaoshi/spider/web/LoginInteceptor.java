package com.etaoshi.spider.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInteceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	/**
	 * 拦截器
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		return true;
	}

}
