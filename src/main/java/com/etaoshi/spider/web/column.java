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
import com.etaoshi.spider.model.DataModel;
import com.etaoshi.spider.model.SpiderColumn;
import com.etaoshi.spider.service.intf.IDataModelService;
import com.etaoshi.spider.service.intf.ISpiderColumnService;
import com.etaoshi.spider.model.enumctype;

@Controller
public class column {
	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	/**
	 * 表结构操作类
	 */
	ISpiderColumnService service = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	
	/**
	 * 显示表结构列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/column", method=RequestMethod.GET)
	public ModelAndView PageLoad(
			@RequestParam(required = true) Integer dmid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取统计源列表
		List<SpiderColumn> list = service.FindByDatamodelid(dmid);
		
		Map enummap = new HashMap();
		for(enumctype one : enumctype.values()){
			enummap.put(one.getEnumctype(), one.name());
		}
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("dmid", dmid);
		map.put("enummap", enummap);

		ModelAndView model = new ModelAndView();
		model.setViewName("column");
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
	@RequestMapping(value = "/cledit", method=RequestMethod.GET)
	public ModelAndView PageLoadEdit(
			@RequestParam(required = true) Integer dmid,
			@RequestParam(required = false) Integer clid,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpiderColumn obj;
		if(clid != null){
			obj = service.Find(clid);
		}else{
			obj = new SpiderColumn();
			obj.setId(0);
			obj.setColumnaccuracy(0);
			obj.setColumncname("");
			obj.setColumnename("");
			obj.setColumnlen(0);
			obj.setColumntype(0);
			obj.setDatamodelid(dmid);
			obj.setRemark("");
		}
		
		enumctype[] enumlist = enumctype.values();
		Map enummap = new HashMap();
		for(enumctype one : enumlist){
			enummap.put(one.name(), one.getEnumctype());
		}
		
		Map map = new HashMap();
		map.put("item", obj);
		map.put("dmid", dmid);
		map.put("enummap", enummap);

		ModelAndView model = new ModelAndView();
		model.setViewName("cledit");
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
	@RequestMapping(value = "/clupdate", method=RequestMethod.POST)
	public void Update(
			@RequestParam(required = true) Integer dmid,
			@RequestParam(required = true) Integer clid,
			@RequestParam(required = true) String columnename,
			@RequestParam(required = true) String columncname,
			@RequestParam(required = true) Integer columntype,
			@RequestParam(required = true) Integer columnlen,
			@RequestParam(required = true) Integer columnaccuracy,
			@RequestParam(required = true) String remark,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpiderColumn obj = new SpiderColumn();
		obj.setColumncname(columncname.trim());
		obj.setDatamodelid(dmid);
		obj.setRemark(remark);
		String js = "";
		//先判断列名是否已经存在，如果存在则提示
		List<SpiderColumn> list = service.FindByColumnename(columnename);
		boolean flag = false;
		for(SpiderColumn one : list){
			if(one.getDatamodelid().equals(dmid) && columnename.equals(one.getColumnename())){
				flag = true;
				break;
			}
		}
		if(clid == 0 && flag){
			js = "<script>alert('列名已经存在，请重新设置');window.location.href = \"cledit?dmid=" + dmid + "\";</script>";
		}else{
			IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
			DataModel dmodel = dmservice.Find(dmid);
			if(clid == 0){
				obj.setColumnaccuracy(columnaccuracy);
				obj.setColumnename(columnename.trim());
				obj.setColumnlen(columnlen);
				obj.setColumntype(columntype);
				obj.setCreationdate(new Date());
				service.Insert(obj);
				//增加列
				Map sql = new HashMap();
				sql.put("tablename", dmodel.getTablename());
				sql.put("columnename", obj.getColumnename());
				String ctypelen = "";
				switch(obj.getColumntype()){
					case 1:
						ctypelen = "int";
						break;
					case 2:
						ctypelen = "decimal("+columnlen+","+columnaccuracy+")";
						break;
					case 3:
						ctypelen = "nvarchar("+columnlen+")";
						break;
					case 4:
						ctypelen = "datetime";
						break;
				}
				sql.put("ctypelen", ctypelen);
				service.Alter(sql);
			}else{
				obj.setId(clid);
				service.Update(obj);
				//如果不存在此列，则新增
				SpiderColumn oldobj = service.Find(clid);
				Map sql = new HashMap();
				sql.put("tablename", dmodel.getTablename());
				sql.put("columnename", oldobj.getColumnename());
				String ctypelen = "";
				switch(oldobj.getColumntype()){
					case 1:
						ctypelen = "int";
						break;
					case 2:
						ctypelen = "decimal("+oldobj.getColumnlen()+","+oldobj.getColumnaccuracy()+")";
						break;
					case 3:
						ctypelen = "nvarchar("+oldobj.getColumnlen()+")";
						break;
					case 4:
						ctypelen = "datetime";
						break;
				}
				sql.put("ctypelen", ctypelen);
				service.Alter(sql);
			}
			js = "<script>window.parent.location = window.parent.location;</script>";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getOutputStream().write(js.getBytes());
	}
	
}
