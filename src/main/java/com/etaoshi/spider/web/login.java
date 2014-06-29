package com.etaoshi.spider.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.etaoshi.spider.comm.Captcha;
import com.etaoshi.spider.comm.CookieHelper;
import com.etaoshi.spider.comm.ToolsUtils;
import com.etaoshi.spider.model.SpiderColumn;
import com.etaoshi.spider.model.enumctype;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.thoughtworks.xstream.XStream;

@Controller
public class login {
	
	/**
	 * 登陆持续时间
	 */
	static final int logintime = 30 * 60;
	
	/**
	 * 日志
	 */
	static Logger logger = Logger.getLogger("mylog");
	
	@RequestMapping(value = "/login")
	public ModelAndView PageLoad(
			@RequestParam(required = false) String uname,
			@RequestParam(required = false) String pwd,
			@RequestParam(required = false) String chkcode,
			@RequestParam(required = false) String returnurl,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		if((uname == null || uname.isEmpty())||(pwd == null || pwd.isEmpty())||(chkcode == null || chkcode.isEmpty())){
			map.put("msg", "用户名，密码，验证码不能为空！");
		}else{
			//判断验证码是否正确
			if(!chkcode.equals(CookieHelper.getCookieValueByName(request, "chkcode", true))){
				map.put("msg", "验证码错误！");
			}else{
				//判断用户名密码是否正确
				boolean flag = chkuser(uname,pwd);
				if(flag){
					CookieHelper.Login(response, logintime, uname, "1", new Date());
					if(returnurl != null && !returnurl.isEmpty())
						response.sendRedirect(returnurl);
					else
						response.sendRedirect("index");
				}else{
					map.put("msg", "用户名或密码错误！");
				}
			}
		}
		if(returnurl != null && !returnurl.isEmpty())
			map.put("returnurl", ToolsUtils.urlEncode(returnurl));

		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		model.addAllObjects(map);
		return model;
	}
	
	@RequestMapping(value = "/chkcode.jpg")
	public void ShowChkcodeImg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String randomStr = RandomStringUtils.randomAlphabetic(4).toLowerCase();
		CookieHelper.addCookie(response, 5 * 50, "chkcode", randomStr, true);
		BufferedImage bi = new Captcha().generate(60, 25, randomStr).getImage();
		//此处无需做jpeg编码
		//ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		//encoder.encode(bi);
		response.setContentType("image/jpeg");
		ImageIO.write(bi, "JPEG", response.getOutputStream());
	}
	
	@RequestMapping(value = "/logout")
	public void Logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CookieHelper.Logout(response);
		response.sendRedirect("login");
	}
	
	private boolean chkuser(String name, String pwd){
		//读取配置文件
		InputStream is = Thread.currentThread().getContextClassLoader()
									.getResourceAsStream("user.xml");
		XStream xstream = new XStream();
		List<user> userlist = (List<user>) xstream.fromXML(is);
		boolean flag = false;
		for(user obj : userlist){
			if(name.equals(obj.getName()) && pwd.equals(obj.getPwd())){
				flag = true;
			}
		}
		return flag;
	}
	
	public static class user{
		private String name;
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
		
		private String pwd;
		
		public void setPwd(String pwd){
			this.pwd = pwd;
		}
		
		public String getPwd(){
			return this.pwd;
		}
	}

}
