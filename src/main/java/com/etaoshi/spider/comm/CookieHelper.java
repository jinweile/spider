package com.etaoshi.spider.comm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Cookie操作
 * 
 * @author jinweile
 * 
 */
public class CookieHelper {

	/**
	 * 密钥
	 */
	private static final String key = "BBDmwTjBsF7IwTIyGWt1bmFntRyUgMQL";

	/**
	 * 登陆cookie名称
	 */
	private static final String cookiename = "etaoshispider";

	/**
	 * 加cookie
	 * 
	 * @param response
	 * @param expiry
	 *            过期时间(秒为单位)
	 * @param cookieName
	 * @param cookieValue
	 * @param isencode
	 *            是否加密
	 * @throws Exception
	 */
	public static void addCookie(HttpServletResponse response, int expiry,
			String cookieName, String cookieValue, boolean isencode)
			throws Exception {
		if (isencode)
			cookieValue = ToolsUtils.urlEncode(DESedeCoder.encrypt(cookieValue, key));
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param request
	 * @param cookieName
	 * @param isdecode
	 * @throws Exception
	 */
	public static String getCookieValueByName(HttpServletRequest request,
			String cookieName, boolean isdecode) throws Exception {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				value = isdecode ? DESedeCoder.decrypt(ToolsUtils.urlDecode(cookie.getValue()), key)
						: cookie.getValue();
				break;
			}
		}
		return value;
	}

	/**
	 * 写cookie
	 * 
	 * @param response
	 * @param expiry
	 *            过期时间，以秒为单位
	 * @param UserName
	 * @param UserID
	 * @throws Exception
	 */
	public static void Login(HttpServletResponse response, int expiry,
			String UserName, String UserID, Date LoginTime) throws Exception {
		String logdate = ToolsUtils.formatDate(LoginTime, "yyyy-MM-dd HH:mm");
		Date dd = new Date();
		String cvalue = "uname=" + UserName + ";uid=" + UserID + ";logintime="
				+ logdate;
		String encvalue = ToolsUtils.urlEncode(DESedeCoder.encrypt(cvalue, key));
		Cookie cookie = new Cookie(cookiename, encvalue);
		cookie.setPath("/");
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}

	/**
	 * 刷新cookie过期时间
	 * 
	 * @param request
	 * @param response
	 */
	public static void refreshLogin(HttpServletRequest request,
			HttpServletResponse response, int expiry) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookiename)) {
				cookie.setMaxAge(expiry);
				cookie.setPath("/");
				response.addCookie(cookie);
				break;
			}
		}
	}

	/**
	 * 读Cookie
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String[] getLoginInfo(HttpServletRequest request)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookiename)) {
				String encvalue = ToolsUtils.urlDecode(cookie.getValue());
				String cvalue = DESedeCoder.decrypt(encvalue, key);
				String regexstr = "^uname=(.+?);uid=(.+?);logintime=(.+?)$";
				Pattern p = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE);
				Matcher matchers = p.matcher(cvalue);
				if (matchers.find()) {
					String UserName = matchers.group(1);
					String UserID = matchers.group(2);
					String LoginTime = matchers.group(3);
					return new String[] { UserName, UserID, LoginTime };
				}
				break;
			}
		}
		return null;
	}

	/**
	 * 判断是否登陆
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static boolean isLogin(HttpServletRequest request) throws Exception {
		if (getLoginInfo(request) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 退出登陆
	 * 
	 * @param response
	 */
	public static void Logout(HttpServletResponse response) {
		Cookie cookie = new Cookie(cookiename, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	// 测试使用
	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test() throws Exception{
		String keya = "BBDmwTjBsF7IwTIyGWt1bmFntRyUgMQL";
		String cvalue = "uname=o3r&fa;asef,ooj;uid=fdsdfsadfdf;+&*%$\nuname=sgvd;uid=987\nuname=o;uid=0876\n";
		String encvalue = DESedeCoder.encrypt(cvalue, keya);
		cvalue = DESedeCoder.decrypt(encvalue, keya);

		// 通过分组index获取正则匹配结果
		// String regexstr = "uname=(.+?);uid=(.+?)\n";

		// 通过分组名称获取正则匹配结果
		//jdk7开始支持分组名
		String regexstr = "uname=(?<jwl1>.+?);uid=(?<jwl2>.+?)\n";

		Pattern p = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE);
		Matcher matchers = p.matcher(cvalue);
		while (matchers.find()) {
			// 通过分组index获取正则匹配结果
			// String UserName = matchers.group(1);
			// String UserID = matchers.group(2);

			// 通过分组名称获取正则匹配结果
			String UserName = matchers.group("jwl1");
			String UserID = matchers.group("jwl2");

			System.out.println(UserName);
			System.out.println(UserID);
		}
	}

}
