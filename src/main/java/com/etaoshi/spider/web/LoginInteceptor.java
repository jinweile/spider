package com.etaoshi.spider.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.etaoshi.spider.comm.CookieHelper;
import com.etaoshi.spider.comm.ToolsUtils;

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
		String url = request.getServletPath();
		if (url.endsWith("login") || url.endsWith("chkcode.jpg")) {
			return true;
		}
		// 判断是否登陆，没登陆定向到登陆页面，登陆了则刷新cookie过期时间
		if (!CookieHelper.isLogin(request)) {
			String returnurl = request.getContextPath() 
					 + request.getServletPath()
					 + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
			response.sendRedirect(request.getContextPath() + "/login?returnurl=" + ToolsUtils.urlEncode(returnurl));
			return false;
		} else {
			CookieHelper.refreshLogin(request, response, login.logintime);
			return true;
		}
	}

}
