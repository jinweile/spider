package com.etaoshi.spider.comm;

import java.util.Date;
import java.util.Properties;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.net.URI;

import org.apache.commons.lang.StringEscapeUtils;

public class ToolsUtils {

	/**
	 * 时间格式化
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date time, String pattern) {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.format(time);
	}

	/**
	 * 字符串转换成时间
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String source, String pattern)
			throws Exception {
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		return formater.parse(source);
	}

	/**
	 * url转码
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String urlEncode(String source) throws Exception {
		return URLEncoder.encode(source, "UTF-8");
	}

	/**
	 * url解码
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public static String urlDecode(String source) throws Exception {
		return URLDecoder.decode(source, "UTF-8");
	}

	/**
	 * html编码
	 * 
	 * @param source
	 * @return
	 */
	public static String htmlEncode(String source) {
		return StringEscapeUtils.escapeHtml(source);
	}

	/**
	 * html解码
	 * 
	 * @param source
	 * @return
	 */
	public static String htmlDecode(String source) {
		return StringEscapeUtils.unescapeHtml(source);
	}

	/**
	 * 规范化并合并URL
	 * 
	 * @param baseUrl
	 * @param urlpath
	 * @return
	 */
	public static String CombineUrl(String baseUrl, String urlpath) {
		if (urlpath == null || urlpath.isEmpty()) {
			return null;
		}

		String lowUrl = urlpath.toLowerCase();
		if (!lowUrl.startsWith("http://") && !lowUrl.startsWith("https://")) {
			try {
				URI uri = new URI(baseUrl);
				return uri.resolve(urlpath).toString();
			} catch (URISyntaxException e) {
				return null;
			}
		} else {
			return urlpath;
		}
	}

	/**
	 * InputStream转String
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String InputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	/**
	 * 获取操作系统信息
	 * 
	 * @return
	 */
	public static Properties getOSInfo() {
		Properties props = System.getProperties(); // 获得系统属性集
		return props;
	}

	/**
	 * 获取操作系统名称
	 * 
	 * @return
	 */
	public static String GetOSName() {
		Properties props = getOSInfo(); // 获得系统属性集
		String OSName = props.getProperty("os.name"); // 操作系统名称
		return OSName;
	}

	/**
	 * 获取操作系统架构
	 * 
	 * @return
	 */
	public static String GetOSArch() {
		Properties props = getOSInfo(); // 获得系统属性集
		String OSArch = props.getProperty("os.arch"); // 操作系统构架
		return OSArch;
	}

	/**
	 * 获取操作系统版本
	 * 
	 * @return
	 */
	public static String GetOSVersion() {
		Properties props = getOSInfo(); // 获得系统属性集
		String OSVersion = props.getProperty("os.version"); // 操作系统版本
		return OSVersion;
	}

	/**
	 * 获取程序集目录
	 * 
	 * @return 目录结构<font color="red">D:/temp/</font>
	 *         注：此处只能获取非jar或者war等打包的目录路径，如果路径在jar，war包中则无法获取
	 *         如果需要获取jar，war包中的文件内容则需要通过类加载器获取，
	 *         例：Thread.currentThread().getContextClassLoader().getResourceAsStream("xx.config") 只能加载文件流，无法加载文件
	 */
	public static String GetClassPath() {
		String regex = GetOSName().toLowerCase().startsWith("window") ? "^file:/" : "^file:";

		String loader_path = Thread.currentThread().getContextClassLoader()
				.getClass().getResource("/").toString().replaceAll(regex, "");
		// + File.separator; //windows系统不需要此项，linux系统没有测试

		return loader_path;
	}

	/**
	 * 获取WEB项目目录
	 * 
	 * @return /dir/
	 */
	public static String GetWebPath() {
		URL url = Thread.currentThread().getContextClassLoader().getClass()
				.getProtectionDomain().getCodeSource().getLocation();
		String path = url.toString();
		int index = path.indexOf("WEB-INF");

		if (index == -1) {
			index = path.indexOf("classes");
		}

		if (index == -1) {
			index = path.indexOf("bin");
		}

		path = path.substring(0, index);

		if (path.startsWith("zip")) {// 当class文件在war中时，此时返回zip:D:/...这样的路径
			path = path.substring(4);
		} else if (path.startsWith("file")) {// 当class文件在class文件中时，此时返回file:/D:/...这样的路径
			int sublen = GetOSName().toLowerCase().startsWith("window") ? 6 : 5;
			path = path.substring(sublen);
		} else if (path.startsWith("jar")) {// 当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径
			int sublen = GetOSName().toLowerCase().startsWith("window") ? 10
					: 9;
			path = path.substring(sublen);
		} else if (path.startsWith("vfs")) {// vfs:/在jboss中将会加入此前缀，需要过滤
			int sublen = GetOSName().toLowerCase().startsWith("window") ? 5 : 4;
			path = path.substring(sublen);
		}
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return path;// vfs:/在jboss中将会加入此前缀，需要过滤
	}
	
	/**
	 * 把汉字转成unicode型编码
	 * @param s
	 * @return
	 */
	public static String enUnicode(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) <= 256) {
				sb.append("\\u00");
			} else {
				sb.append("\\u");
			}
			sb.append(Integer.toHexString(s.charAt(i)));
		}
		return sb.toString();
	}
	
	/**
	 * unicode型编码转成汉字(还未测试)
	 * @param theString
	 * @return
	 */
	public static String deUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

}
