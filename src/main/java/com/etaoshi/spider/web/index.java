package com.etaoshi.spider.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etaoshi.spider.comm.SpringContext;
import com.etaoshi.spider.model.Source;
import com.etaoshi.spider.service.intf.ISourceService;

@Controller
public class index {

	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	/**
	 * 统计源操作类
	 */
	ISourceService service = SpringContext.getInstance().getBean("ISourceService", ISourceService.class);
	
	/**
	 * 显示统计源列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public ModelAndView PageLoad(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取统计源列表
		List<Source> list = service.FindAll();
		
		Map map = new HashMap();
		map.put("list", list);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 显示统计源具体信息
	 * @param sourceid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/indexedit", method=RequestMethod.GET)
	public ModelAndView PageLoadEdit(@RequestParam(required = false) Integer sourceid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Source obj;
		if(sourceid != null){
			obj = service.Find(sourceid);
		}else{
			obj = new Source();
			obj.setIsused(null);
			obj.setId(0);
			obj.setName("");
			obj.setRemark("");
			obj.setUrl("");
		}
		
		Map map = new HashMap();
		map.put("item", obj);

		ModelAndView model = new ModelAndView();
		model.setViewName("indexedit");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 更新统计源
	 * @param sourceid
	 * @param name
	 * @param url
	 * @param isused
	 * @param remark
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/indexupdate", method=RequestMethod.POST)
	public void Update(@RequestParam(required = true) Integer sourceid,
			@RequestParam(required = true) String name,
			@RequestParam(required = true) String url,
			@RequestParam(required = true) Boolean isused,
			@RequestParam(required = true) String remark,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Source obj = new Source();
		obj.setIsused(isused);
		obj.setName(name.trim());
		obj.setUrl(url.trim());
		obj.setRemark(remark);
		if(sourceid == 0){
			service.Insert(obj);
		}else{
			obj.setId(sourceid);
			service.Update(obj);
		}

		String js = "<script>window.parent.location = window.parent.location;</script>";
		response.setContentType("text/html;charset=UTF-8");
		response.getOutputStream().write(js.getBytes());
	}
	
}
