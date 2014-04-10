package com.etaoshi.spider.web;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etaoshi.spider.analysis.EntryRule;
import com.etaoshi.spider.analysis.HttpDown;
import com.etaoshi.spider.analysis.RuleExtractor;
import com.etaoshi.spider.analysis.TemplateExtractor;
import com.etaoshi.spider.comm.JSONHelper;
import com.etaoshi.spider.comm.SpringContext;
import com.etaoshi.spider.comm.ToolsUtils;
import com.etaoshi.spider.model.*;
import com.etaoshi.spider.service.intf.*;

@Controller
public class regtemplate {

	static Logger logger = Logger.getLogger("mylog");
	
	ITemplateService tservice = SpringContext.getInstance().getBean("ITemplateService", ITemplateService.class);
	IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	ISpiderRegTemplateService rtservice = SpringContext.getInstance().getBean("ISpiderRegTemplateService", ISpiderRegTemplateService.class);
	ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	ITestUrlService tuservice = SpringContext.getInstance().getBean("ITestUrlService", ITestUrlService.class);
	
	@RequestMapping(value = "/regtemplate", method=RequestMethod.GET)
	public ModelAndView PageLoad(
			@RequestParam(required = true) Integer tid,
			@RequestParam(required = true) Integer parentid,
			@RequestParam(required = false) Integer rtid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//首先获取模版
		Template tobj = tservice.Find(tid);
		
		//获取正则模版列表
		List<SpiderRegTemplate> alllist = rtservice.FindByTemplateid(tid);
		
		//产生递归树
		String js = "var tree = new MzTreeView('tree');"
	            +"tree.icons['book'] = 'book_red.png';"
	            +"tree.iconsExpand['book'] = 'book_red.png';"
	            +"tree.setIconPath('images/');";
		for(SpiderRegTemplate obj : alllist){
			SpiderColumn scobj = null;
			if(obj.getTypeid() > 0)
				scobj = scservice.Find(obj.getTypeid());
			js += "tree.nodes['"
			   +obj.getParentid()+"_"+obj.getId()+"'] = 'text:"
			   +(obj.getTypeid() > 0 ? "["+scobj.getColumnename()+"]" + scobj.getColumncname() : (obj.getTypeid() == 0 ? "[filter]过滤" : "[entry]入口"))
			   +"; icon:book;url:regtemplate?tid="+tid+"&parentid="+obj.getParentid()+"&rtid="+obj.getId()+";';";
		}
		
		List<SpiderRegTemplate> list = new ArrayList<SpiderRegTemplate>();
		for(SpiderRegTemplate one : alllist){
			if(one.getParentid().equals(parentid)){
				list.add(one);
			}
		}
		
		//填充测试url等信息
		List<TestUrl> tulist = tuservice.FindByTemplateid(tid);
		TestUrl tuitem = null;
		for(TestUrl one : tulist){
			if(one.getParentid().equals(parentid)){
				tuitem = one;
				break;
			}
		}
		if(tuitem == null){
			tuitem = new TestUrl();
			tuitem.setTestcontent("");
			tuitem.setTesturl("");
		}
		
		//获取列列表
		List<SpiderColumn> sclist = scservice.FindByTemplateid(tid);
		
		//填充抓取模版信息
		SpiderRegTemplate rtitem;
		if(rtid != null){
			rtitem = rtservice.Find(rtid);
		}else{
			rtitem = new SpiderRegTemplate();
			rtitem.setEntryrule("");
			rtitem.setId(0);
			rtitem.setIsspiderentry(1);
			rtitem.setParentid(parentid);
			rtitem.setSpiderorderby(1);
			rtitem.setSpiderreg("");
			rtitem.setTemplateid(tid);
			rtitem.setTypeid(0);
		}

		Map map = new HashMap();
		map.put("tid", tid);
		map.put("parentid", parentid);
		map.put("rtid", rtid);
		map.put("list", list);
		map.put("sclist", sclist);
		map.put("rtitem", rtitem);
		map.put("tuitem", tuitem);
		map.put("scservice", scservice);
		map.put("js", js);
		map.put("tobj", tobj);

		ModelAndView model = new ModelAndView();
		model.setViewName("regtemplate");
		model.addAllObjects(map);
		return model;
	}
	
	@RequestMapping(value = "/rtupdate", method=RequestMethod.POST)
	public void Update(
			@RequestParam(required = true) Integer tid,
			@RequestParam(required = true) Integer parentid,
			@RequestParam(required = true) Integer rtid,
			@RequestParam(required = true) String testurl,
			@RequestParam(required = true) String testcontent,
			@RequestParam(required = true) Integer typeid,
			@RequestParam(required = true) String spiderreg,
			@RequestParam(required = true) Integer spiderorderby,
			@RequestParam(required = true) Integer isspiderentry,
			@RequestParam(required = true) String entryrule,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//首先更新测试url
		TestUrl tuobj = new TestUrl();
		tuobj.setParentid(parentid);
		tuobj.setTemplateid(tid);
		tuobj.setTestcontent(testcontent.trim());
		tuobj.setTesturl(testurl.trim());
		tuservice.Update(tuobj);
		
		//然后更新模版信息
		SpiderRegTemplate rtobj = new SpiderRegTemplate();
		rtobj.setEntryrule(entryrule.trim());
		rtobj.setId(rtid);
		rtobj.setIsspiderentry(isspiderentry);
		rtobj.setParentid(parentid);
		rtobj.setSpiderorderby(spiderorderby);
		rtobj.setSpiderreg(spiderreg.trim());
		rtobj.setTemplateid(tid);
		rtobj.setTypeid(typeid);
		int new_rtid = rtid;
		if(rtid == 0){
			new_rtid = rtservice.Insert(rtobj);
		}else{
			rtservice.Update(rtobj);
		}
		
		response.sendRedirect("regtemplate?tid="+tid+"&parentid="+parentid+"&rtid="+new_rtid);
	}
	
	@RequestMapping(value = "/rtdel", method=RequestMethod.GET)
	public void Delete(
			@RequestParam(required = true) Integer tid,
			@RequestParam(required = true) Integer parentid,
			@RequestParam(required = true) Integer rtid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpiderRegTemplate obj = new SpiderRegTemplate();
		obj.setId(rtid);
		rtservice.Delete(obj);
		
		response.sendRedirect("regtemplate?tid="+tid+"&parentid="+parentid);
	}
	
	@RequestMapping(value = "/testreg", method=RequestMethod.POST)
	public void UrlTestReg(
			@RequestParam(required = true) int type,
			@RequestParam(required = true) String url,
			@RequestParam(required = true) String reg,
			@RequestParam(required = true) String rule,
			@RequestParam(required = true) Integer tid,
			@RequestParam(required = true) Integer parentid,
			@RequestParam(required = true) Integer rtid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		
		//返回的对象
		template temp = new template();
		temp.setIstrue(false);
		
		//判断是否是Url格式
		if(type == 1 && !url.toLowerCase().trim().startsWith("http://") && !url.toLowerCase().trim().startsWith("https://")){
			temp.setMsg("url地址格式不正确！格式为：http://www.etaoshi.com");
			response.getWriter().write(JSONHelper.serialize(temp));
			//response.getOutputStream().write(JSONHelper.serialize(temp).getBytes("UTF-8"));
			return;
		}
		
		//判断正则表达式是否填写
		if(reg.toLowerCase().trim().isEmpty()){
			temp.setMsg("请填写正则表达式！");
			response.getWriter().write(JSONHelper.serialize(temp));
			//response.getOutputStream().write(JSONHelper.serialize(temp).getBytes("UTF-8"));
			return;
		}
		
		//入口规则解析
		Map<EntryRule,String> entryrule_map = RuleExtractor.ExtractRuleEnum(rule, EntryRule.class);
		//解析http头信息
		Map<String, String> header = null;
		if(entryrule_map.containsKey(EntryRule.header) && !entryrule_map.get(EntryRule.header).isEmpty())
			header = RuleExtractor.ExtractHeader(entryrule_map.get(EntryRule.header));
		//根据入口规则下载页面
		String content = ""; 
		if(type == 1){
			if(entryrule_map.containsKey(EntryRule.method) 
					&& entryrule_map.get(EntryRule.method).equalsIgnoreCase("POST")){
				//解析post信息
				Map<String, String> postparams = null;
				if(entryrule_map.containsKey(EntryRule.postparams) 
						&& !entryrule_map.get(EntryRule.postparams).isEmpty())
					postparams = RuleExtractor.ExtractPostParams(entryrule_map.get(EntryRule.postparams));
				content = HttpDown.postdown(url, header, postparams);
			}else{
				content = HttpDown.getdown(url, header);
			}
		}else{
			content = url;
		}
		
		if(content == null){
			temp.setMsg("获取不到数据，请检查url或者入口规则是否正确！");
			response.getWriter().write(JSONHelper.serialize(temp));
			//response.getOutputStream().write(JSONHelper.serialize(temp).getBytes("UTF-8"));
			return;
		}
		
		//获取此项之前的过滤项
		List<SpiderRegTemplate> alllist = rtservice.FindByTemplateid(tid);
		List<SpiderRegTemplate> list = new ArrayList<SpiderRegTemplate>();
		for(SpiderRegTemplate one : alllist){
			if(one.getParentid().equals(parentid)){
				list.add(one);
			}
		}
		List<String> result = TemplateExtractor.ExtractTemplate(rtid, list, content);
		if(result == null){
			temp.setMsg("不能通过正则表达式截获数据，请检查正则表达式！");
			response.getWriter().write(JSONHelper.serialize(temp));
			//response.getOutputStream().write(JSONHelper.serialize(temp).getBytes("UTF-8"));
			return;
		}
		temp.setIstrue(true);
		temp.setMsg("");
		temp.setResult(result);
		
		response.getWriter().write(JSONHelper.serialize(temp));
		//response.getOutputStream().write(JSONHelper.serialize(temp).getBytes("UTF-8"));
	}
	
	/**
	 * 正则匹配返回结果集
	 * @author jinweile
	 *
	 */
	public static class template implements Serializable {
		
		private boolean istrue;
		/**
		 * 是否成功获取
		 * @return
		 */
		public boolean getIstrue(){
			return this.istrue;
		}
		/**
		 * 是否成功获取
		 * @param istrue
		 */
		public void setIstrue(boolean istrue){
			this.istrue = istrue;
		}
		
		private String msg;
		/**
		 * 信息
		 * @return
		 */
		public String getMsg(){
			return this.msg;
		}
		/**
		 * 信息
		 * @param msg
		 */
		public void setMsg(String msg){
			this.msg = msg;
		}
		
		private List<String> result;
		/**
		 * 结果集
		 * @return
		 */
		public List<String> getResult(){
			return this.result;
		}
		/**
		 * 结果集
		 * @param result
		 */
		public void setResult(List<String> result){
			 List<String> newresult = new ArrayList<String>();
			 for(String one : result){
				 newresult.add(ToolsUtils.htmlEncode(one));
			 }
			 this.result = newresult;
		}
		
	}
	
}
