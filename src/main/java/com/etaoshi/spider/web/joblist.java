package com.etaoshi.spider.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etaoshi.spider.analysis.IResultInDb;
import com.etaoshi.spider.analysis.SpiderWorker;
import com.etaoshi.spider.comm.SpringContext;
import com.etaoshi.spider.job.SpiderQueueTask;
import com.etaoshi.spider.job.SpiderTask;
import com.etaoshi.spider.model.*;
import com.etaoshi.spider.service.intf.*;

@Controller
public class joblist {
	
	/**
	 * 自动装配任务调度器
	 */
	@Autowired
	@Qualifier("spiderTask")
	private SpiderTask spiderTask;

	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	ISourceService sservice = SpringContext.getInstance().getBean("ISourceService", ISourceService.class);
	ISourceSpiderService ssservice = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
	ITemplateService tservice = SpringContext.getInstance().getBean("ITemplateService", ITemplateService.class);
	IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	ITemplateDataModelService tdmservice = SpringContext.getInstance().getBean("ITemplateDataModelService", ITemplateDataModelService.class);
	ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	ISpiderRegTemplateService srtservice = SpringContext.getInstance().getBean("ISpiderRegTemplateService", ISpiderRegTemplateService.class);

	/**
	 * 显示作业列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/joblist", method=RequestMethod.GET)
	public ModelAndView PageLoad(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取统计源列表
		List<SourceSpider> list = ssservice.FindAll();
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("sservice", sservice);
		map.put("tservice", tservice);
		map.put("spiderTask", spiderTask);

		ModelAndView model = new ModelAndView();
		model.setViewName("joblist");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 定制抓取某个作业
	 * @param ssid
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/spiderbyssid", method=RequestMethod.GET)
	public ModelAndView SpiderBySourceSpiderID(
			@RequestParam(required = true) Integer ssid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*SourceSpider ss = ssservice.Find(ssid);
		List<SpiderRegTemplate> srtlist = srtservice.FindByTemplateid(ss.getTemplateid());
		Map<Integer, String> scmap = scservice.FindAllMap();
		
		String[] result = SpiderWorker.SpiderEntry(ss.getSpiderentryrule());
		
		final List<String> sqls = new ArrayList<String>();
		final Map<String, SQLException> sqle_map = new HashMap<String, SQLException>();
		SpiderWorker.RecursiveExtractTemplateInDb(ss.getId(), result[0], result[1], 0, ss.getTemplateid(), srtlist, scmap, null, 
				new IResultInDb(){
					public void Insert(List<String> insert_sql_list) {
						sqls.addAll(insert_sql_list);
						for(String sql : insert_sql_list){
							try {
								scservice.InsertIntoDataModel(sql);
							} catch (SQLException e) {
								sqle_map.put(sql, e);
							}
						}
					}
		});
		map.put("sqls", sqls);
		map.put("sqle_map", sqle_map);
		map.put("msg", "启动了此项抓取！");
	
		model.setViewName("spiderbyssid");
		model.addAllObjects(map);*/
		
    	//如果作业在运行，则跳过
		ModelAndView model = new ModelAndView();
		Map map = new HashMap();
    	if(!SpiderTask.getJobRunMap(ssid)){
			List<String> sqls = new ArrayList<String>();
			Map<String, SQLException> sqle_map = new HashMap<String, SQLException>();
			
			SpiderQueueTask qtask = new SpiderQueueTask(ssid,sqle_map,sqls);
			qtask.start();
			
			//map.put("sqls", sqls);
			//map.put("sqle_map", sqle_map);
			map.put("msg", "启动了此项抓取！");
	
			model.setViewName("spiderbyssid");
			model.addAllObjects(map);
    	}else{
    		map.put("msg", "此抓取项目正在运行，请结束后再设置");
	
			model.setViewName("spiderbyssid");
			model.addAllObjects(map);
    	}
		return model;
	}
	
}
