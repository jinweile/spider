package com.etaoshi.spider.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常类
 * @author jinweile
 *
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	static Logger logger = Logger.getLogger("mylog");
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse reponse, Object obj, Exception e) {
		//可以在此记录全局异常日志(但是此异常信息不能知道具体controler那里出错)
		logger.error(e.getStackTrace());
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("exception", e);
        //这里可根据不同异常引起类做不同处理方式，本例做不同返回页面。
        String viewName = "include/error";
        return new ModelAndView(viewName, model);
	}

}
