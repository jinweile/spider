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
import com.etaoshi.spider.model.*;
import com.etaoshi.spider.service.intf.*;

@Controller
public class template {
	
	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	ITemplateService service = SpringContext.getInstance().getBean("ITemplateService", ITemplateService.class);
	IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	ITemplateDataModelService tdmservice = SpringContext.getInstance().getBean("ITemplateDataModelService", ITemplateDataModelService.class);
	
	/**
	 * 显示关联列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/template", method=RequestMethod.GET)
	public ModelAndView PageLoad(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取关联列表
		List<Template> list = service.FindAll();

		Map map = new HashMap();
		map.put("list", list);
		map.put("tdmservice", tdmservice);
		map.put("dmservice", dmservice);

		ModelAndView model = new ModelAndView();
		model.setViewName("template");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 显示列具体信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tedit", method=RequestMethod.GET)
	public ModelAndView PageLoadEdit(
			@RequestParam(required = false) Integer tid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Template obj;
		List<TemplateDataModel> tdmlist = null;
		if(tid != null){
			obj = service.Find(tid);
			tdmlist = tdmservice.FindByTemplateid(tid);
		}else{
			obj = new Template();
			obj.setId(0);
			obj.setName("");
			obj.setRemark("");
		}
		
		//获取数据模型列表
		List<DataModel> dmlist = dmservice.FindAll();
		
		Map map = new HashMap();
		map.put("item", obj);
		map.put("dmlist", dmlist);
		map.put("tdmlist", tdmlist);

		ModelAndView model = new ModelAndView();
		model.setViewName("tedit");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 更新列信息
	 * @param dmid
	 * @param name
	 * @param remark
	 * @param tablename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/tupdate", method=RequestMethod.POST)
	public void Update(
			@RequestParam(required = true) Integer tid,
			@RequestParam(required = true) Integer[] datamodelid,
			@RequestParam(required = true) String name,
			@RequestParam(required = true) String remark,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Template obj = new Template();
		obj.setCreationdate(new Date());
		obj.setId(tid);
		obj.setName(name);
		obj.setRemark(remark);
		Map params = new HashMap();
		params.put("templateid", tid);
		params.put("datamodelid", datamodelid);
		if(tid == 0){
			service.Insert(obj);
			tdmservice.UpdateByTemplateidAndDataModelid(params);
		}else{
			service.Update(obj);
			tdmservice.UpdateByTemplateidAndDataModelid(params);
		}
		
		String js = "<script>window.parent.location = window.parent.location;</script>";
		response.setContentType("text/html;charset=UTF-8");
		response.getOutputStream().write(js.getBytes());
	}
	
}
