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
public class sourcespider {
	
	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	/**
	 * 统计源与模版关联操作类
	 */
	ISourceSpiderService service = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
	ITemplateService tservice = SpringContext.getInstance().getBean("ITemplateService", ITemplateService.class);
	IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	ISourceService sservice = SpringContext.getInstance().getBean("ISourceService", ISourceService.class);
	ITemplateDataModelService tdmservice = SpringContext.getInstance().getBean("ITemplateDataModelService", ITemplateDataModelService.class);
	
	/**
	 * 显示关联列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sourcespider", method=RequestMethod.GET)
	public ModelAndView PageLoad(
			@RequestParam(required = true) Integer sourceid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取关联列表
		List<SourceSpider> list = service.FindBySourceid(sourceid);

		Map map = new HashMap();
		map.put("list", list);
		map.put("sourceid", sourceid);
		map.put("dmservice", dmservice);
		map.put("sservice", sservice);
		map.put("tservice", tservice);
		map.put("tdmservice", tdmservice);

		ModelAndView model = new ModelAndView();
		model.setViewName("sourcespider");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 显示列具体信息
	 * @param dmid
	 * @param clid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/spedit", method=RequestMethod.GET)
	public ModelAndView PageLoadEdit(
			@RequestParam(required = true) Integer sourceid,
			@RequestParam(required = false) Integer spid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SourceSpider obj;
		if(spid != null){
			obj = service.Find(spid);
		}else{
			obj = new SourceSpider();
			obj.setId(0);
			obj.setIsused(true);
			obj.setJobrule("");
			obj.setRemark("");
			obj.setSpiderentryrule("");
			obj.setTemplateid(0);
		}
		
		//获取数据模型列表
		List<Template> tlist = tservice.FindAll();
		
		Map map = new HashMap();
		map.put("item", obj);
		map.put("tlist", tlist);
		map.put("sourceid", sourceid);
		map.put("dmservice", dmservice);
		map.put("tservice", tservice);
		map.put("tdmservice", tdmservice);

		ModelAndView model = new ModelAndView();
		model.setViewName("spedit");
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
	@RequestMapping(value = "/spupdate", method=RequestMethod.POST)
	public void Update(
			@RequestParam(required = false) Integer spid,
			@RequestParam(required = true) Integer sourceid,
			@RequestParam(required = true) Integer templateid,
			@RequestParam(required = true) String spiderentryrule,
			@RequestParam(required = true) String jobrule,
			@RequestParam(required = true) Boolean isused,
			@RequestParam(required = true) String remark,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SourceSpider obj = new SourceSpider();
		obj.setCreationdate(new Date());
		obj.setId(spid);
		obj.setIsused(isused);
		obj.setJobrule(jobrule.trim());
		obj.setRemark(remark);
		obj.setSourceid(sourceid);
		obj.setSpiderentryrule(spiderentryrule.trim());
		obj.setTemplateid(templateid);
		//判断是否是新增
		if(spid == 0){
			service.Insert(obj);
		}else{
			service.Update(obj);
		}
		
		String js = "<script>window.parent.location = window.parent.location;</script>";
		response.setContentType("text/html;charset=UTF-8");
		response.getOutputStream().write(js.getBytes());
	}
	
}
