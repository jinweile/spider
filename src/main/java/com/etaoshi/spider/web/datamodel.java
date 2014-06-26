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

import com.etaoshi.spider.comm.Pager;
import com.etaoshi.spider.comm.SQLParamHelper;
import com.etaoshi.spider.comm.SpringContext;
import com.etaoshi.spider.model.DataModel;
import com.etaoshi.spider.service.intf.IDataModelService;
import com.etaoshi.spider.service.intf.ISourceService;
import com.etaoshi.spider.service.intf.ISourceSpiderService;
import com.etaoshi.spider.service.intf.ISpiderColumnService;

@Controller
public class datamodel {

	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	/**
	 * 数据模型操作类
	 */
	IDataModelService service = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	ISourceService sservice = SpringContext.getInstance().getBean("ISourceService", ISourceService.class);
	ISourceSpiderService ssservice = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
	
	/**
	 * 显示数据模型列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/datamodel", method=RequestMethod.GET)
	public ModelAndView PageLoad(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取统计源列表
		List<DataModel> list = service.FindAll();
		
		Map map = new HashMap();
		map.put("list", list);

		ModelAndView model = new ModelAndView();
		model.setViewName("datamodel");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 显示数据模型具体信息
	 * @param dmid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dmedit", method=RequestMethod.GET)
	public ModelAndView PageLoadEdit(@RequestParam(required = false) Integer dmid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DataModel obj;
		if(dmid != null){
			obj = service.Find(dmid);
		}else{
			obj = new DataModel();
			obj.setCreationdate(null);
			obj.setId(0);
			obj.setName("");
			obj.setRemark("");
			obj.setTablename("");
		}
		
		Map map = new HashMap();
		map.put("item", obj);

		ModelAndView model = new ModelAndView();
		model.setViewName("dmedit");
		model.addAllObjects(map);
		return model;
	}
	
	/**
	 * 更新数据模型
	 * @param dmid
	 * @param name
	 * @param remark
	 * @param tablename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/dmupdate", method=RequestMethod.POST)
	public void Update(
			@RequestParam(required = true) Integer dmid,
			@RequestParam(required = true) String name,
			@RequestParam(required = true) String remark,
			@RequestParam(required = true) String tablename,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String js = "";
		
		DataModel obj = new DataModel();
		obj.setName(name.trim());
		obj.setRemark(remark);
		//先判断表名是否已经存在，如果存在则提示
		if(dmid == 0 && service.FindByTablename(tablename.trim()).size() > 0){
			//先检查数据库中的表是否存在
			Integer ist = service.ChkDbTableName(tablename.trim());
			if(ist != null && ist > 0){
				js = "<script>alert('表名已经存在，请重新设置');window.location.href = \"dmedit\";</script>";
				response.getOutputStream().write(js.getBytes());
				return;
			}
			
			js = "<script>alert('表名已经存在，请重新设置');window.location.href = \"dmedit\";</script>";
		}else{
			if(dmid == 0){
				//先检查数据库中的表是否存在
				Integer ist = service.ChkDbTableName(tablename.trim());
				if(ist != null && ist > 0){
					js = "<script>alert('表名已经存在，请重新设置');window.location.href = \"dmedit\";</script>";
					response.getOutputStream().write(js.getBytes());
					return;
				}
				
				obj.setTablename(tablename.trim());
				obj.setCreationdate(new Date());
				service.Insert(obj);
				//创建基础数据模型
				service.CreateDataModel(tablename.trim());
			}else{
				obj.setId(dmid);
				service.Update(obj);
				//如果不存在此表，则创建
				String tname = service.Find(dmid).getTablename();
				service.CreateDataModel(tname);
			}
			js = "<script>window.parent.location = window.parent.location;</script>";
		}
		
		response.getOutputStream().write(js.getBytes());
	}
	
	
	@RequestMapping(value = "/spiderdata", method=RequestMethod.GET)
	public ModelAndView ShowSpiderData(
			@RequestParam(required = true) Integer dmid,
			@RequestParam(required = false) Integer page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//根据数据模型id获取表名
		String tablename = service.Find(dmid).getTablename();
		Integer count = scservice.getDynamicTableCount(tablename);
		//一页20条
		int pagecount = 20;
		if(page == null)
			page = 1;
		int start = (page - 1) * pagecount;
		int end = page * pagecount;
		
		Map<String,String> params = new HashMap<String,String>();
		String sql = "select * from ( select *,ROW_NUMBER() over(order by id desc) as sortnum from "
					   + SQLParamHelper.Replace(tablename) + " ) as b where b.sortnum between "
					   + start + " and " + end;
		List list = scservice.getDynamicTable(sql);
		
		// 分页控件赋值
		int pager_pagecount = count % pagecount > 0 ? (count - count % pagecount)/pagecount + 1 : count / pagecount;
		Pager pager = new Pager();
		pager.setCountNum(count);
		pager.setPageCount(pager_pagecount == 0 ? 1 : pager_pagecount);
		pager.setPageIndex(page);
		String query = request.getQueryString();
		query = query == null ? "" : query;
		query = query.replaceAll("(?i)(&|)page=[^&]*", "");
		query = query.isEmpty() ? query : (query + "&");
		pager.setUrlParam(query + "page");
		pager.setUrlPath("");
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("pager", pager);
		map.put("tablename", tablename);
		map.put("sservice", sservice);
		map.put("ssservice", ssservice);

		ModelAndView model = new ModelAndView();
		model.setViewName("spiderdata");
		model.addAllObjects(map);
		return model;
	}
	
}
