package com.etaoshi.spider.analysis;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHelper {
	
	/**
	 * 指示正则表达式在一次匹配时，最多匹配4000个项
	 */
	public final static int max_match_count = 4000;

	/**
	 * 清除HTML内容中指定的标签,如果不指定标记参数则清除字符中的所有HTML标签
	 */
	public static String TagClean(String value, String... tagNames) {
		String strReg = "<.+?>";

		if (tagNames != null && tagNames.length > 0) {
			strReg = "<\\s*[/]?(";
			boolean isFirst = true;
			for (int i = 0; i < tagNames.length; i++) {
				if (isFirst)
					strReg += tagNames[i];
				else
					strReg += "|" + tagNames[i];

				isFirst = false;
			}
			strReg += ").*?>";
		}

		// 正则对象匹配模式
		// return Pattern.compile(strReg,Pattern.CASE_INSENSITIVE).matcher(value).replaceAll("");
		// 如果直接使用替换，则需要使用(?i)来忽略大小写
		return value.replaceAll("(?i)" + strReg, "");
	}

	/**
	 * 对字符串进行MD5编码,32位
	 */
	public static String MD5Encoding(String inStr) {
		if (inStr == null || inStr.isEmpty()) {
			return null;
		}

		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 将HTML中获取到的&nbsp;空格去掉,并对字符串执行trim
	 */
	public static String DecodeHtmlSpace(String html) {
		if (html == null) {
			return null;
		}

		return html.replaceAll("&nbsp;", " ").trim();
	}

	/**
	 * 清除html文档中的链接和样式
	 */
	public static String ClearScriptStyle(String html) {
		if (html == null)
			return null;

		return html.replaceAll(
				"(?i)<(script|style)([^>])*>.*?</(script|style)\\s*>", "");
	}

	/**
	 * 清除hmtl文档中的空格
	 */
	public static String CleanHtmlEscapChar(String str) {
		if (str == null)
			return null;

		return str.replaceAll("(?i)&\\w+?;", "");
	}

	/**
	 * 清除html标记
	 */
	public static String ClearHtml(String html) {
		if (html == null)
			return null;

		return CleanHtmlEscapChar(TagClean(ClearScriptStyle(html), null));
	}

	/**
	 * 规范化模板和即将要比较的内容，
	 * 将windows换行符\r\n转换为统一的unix格式的\n 
	 * 在模板存入库之前需要全部处理为这种格式的
	 */
	public static String FormatCRLF(String strtoformat) {
		if (strtoformat == null)
			return strtoformat;

		return strtoformat.replaceAll("\r\n", "\n");
	}

	/**
	 * 将unix格式的字符串，转换为windows格式的，
	 * 主要征对模板从数据库读出需要编辑的时候
	 * 还源字符串在编辑器中的格式
	 */
	public static String UnFormatCRLF(String strtoformat) {
		if (strtoformat == null)
			return strtoformat;

		// 必须先全部转换为unix格式的，再转换为windows格式的
		return strtoformat.replaceAll("\r\n", "\n").replaceAll("\n", "\r\n");
	}

	/**
	 * 返回符合条件的字符串 return 匹配的字符串，
	 * 如果不匹配，则返回null
	 */
	public static String MatchedGroup(String strToMatch, String regex) {
		if (strToMatch == null || regex == null)
			return null;
		Matcher m = Pattern.compile(strToMatch, Pattern.CASE_INSENSITIVE)
				.matcher(regex);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

	/**
	 * 根据分组排序匹配结果
	 */
	public static String MatchedGroup(String strToMatch, String regex,
			int groupId) {
		if (strToMatch == null || regex == null) {
			return null;
		}
		Matcher m = Pattern.compile(regex).matcher(strToMatch);
		if (m.find()) {
			return m.group(groupId);
		}
		return null;
	}

	/**
	 * 找出一个字符串中由start和end包围的一个值 如果开始和结速字符用 $$包含起来，表示需要把开始和结束标记也放进匹配的内容中
	 * 如果开始的左侧由%开始,表示开始标签和第一个匹配的标签匹配(默许) 如果开始的右侧由%开始,表示要开始标签和结束标签的左边匹配
	 * 如果结束的左侧由%开始,表示结束标签和最靠近开始标签的匹配 如果结束右侧由%开始，表示结束标签和离开始标签最远的标签匹配
	 * %只有出现在整个字符串的最初或最必才有效
	 */
	public static String StrBetween(String text, String start, String end) {
		if (text == null || text.isEmpty() || start == null || start.isEmpty()
				|| end == null || end.isEmpty())
			return null;

		boolean rightAligh = false;
		boolean startLeft = true;
		if (start.charAt(0) == '%') {
			startLeft = true;
			start = start.substring(1, start.length());
		}

		if (start.charAt(start.length() - 1) == '%') {
			startLeft = false;
			start = start.substring(0, start.length());
		}

		if (end.charAt(end.length() - 1) == '%') {
			rightAligh = true;
			end = end.substring(0, end.length());
		}

		if (end.charAt(0) == '%') {
			rightAligh = false;
			end = end.substring(1, end.length());
		}

		boolean startInclude = start.charAt(0) == '$'
				&& start.charAt(start.length() - 1) == '$';
		boolean endInclude = end.charAt(0) == '$'
				&& end.charAt(end.length() - 1) == '$';

		if (startInclude) {
			if (start.length() <= 2) {
				return null;
			}
			start = start.substring(1, start.length() - 1);
		}

		if (endInclude) {
			if (end.length() <= 2) {
				return null;
			}
			end = end.substring(1, end.length() - 1);
		}

		if (start.length() + end.length() >= text.length()) {
			return null;
		}

		int startIndex = -1;
		int endIndex = -1;
		int len = 0;

		if (startLeft) {
			startIndex = text.indexOf(start);
			if (startIndex < 0) {
				return null;
			}
			startIndex = startIndex + start.length();

			if (rightAligh) {// 右对齐
				endIndex = text.lastIndexOf(end);
			} else {
				endIndex = text.indexOf(end, startIndex);
			}

			if (endIndex < 0 || endIndex <= startIndex) {
				return null;
			}

			len = endIndex - startIndex;
		} else {
			if (rightAligh) {
				endIndex = text.lastIndexOf(end);
			} else {
				endIndex = text.indexOf(end);
			}

			if (endIndex < 0) {
				return null;
			}

			String temptext = text.substring(0, endIndex);
			startIndex = temptext.lastIndexOf(start);
			if (startIndex == -1) {
				return null;
			}
			startIndex = startIndex + start.length();
			len = temptext.length() - startIndex;
		}

		if (len <= 0) {
			return null;
		}
		String result = text.substring(startIndex, startIndex + len - 1);
		if (startInclude)
			result = start + result;

		if (endInclude)
			result = result + end;

		return result;
	}

	/**
	 * 找出text中，由开始和结束标记指定的所有数据项，并放到List中返回 如果开始和结速字符用
	 * $$包含起来，表示需要把开始和结束标记也放进匹配的内容中
	 */
	public static List<String> StrBetweenList(String text, String start,
			String end) {
		if (text == null || text.isEmpty() || start == null || start.isEmpty()
				|| end == null || end.isEmpty())
			return null;

		boolean startInclude = start.charAt(0) == '$'
				&& start.charAt(start.length() - 1) == '$';
		boolean endInclude = end.charAt(0) == '$'
				&& end.charAt(end.length() - 1) == '$';
		if (startInclude) {
			if (start.length() <= 2) {
				return null;
			}
			start = start.substring(1, start.length() - 1);
		}

		if (endInclude) {
			if (end.length() <= 2) {
				return null;
			}
			end = end.substring(1, end.length() - 1);
		}

		if (start.length() + end.length() >= text.length()) {
			return null;
		}

		List<String> strList = new ArrayList<String>();
		int currentIndex = 0;
		while (currentIndex < text.length() - 1) {
			int startIndex = text.indexOf(start, currentIndex);
			if (startIndex < 0 || (startIndex + end.length() >= text.length())) {
				break;
			}

			int endIndex = text.indexOf(end, startIndex + start.length());
			if (endIndex < 0 || (endIndex - startIndex) == 0) {
				break;
			}
			startIndex = startIndex + start.length();
			String item = text.substring(startIndex, endIndex);
			if (!item.isEmpty() && item != null && item.trim().length() > 0) {
				strList.add(item.trim());
			}
			currentIndex = endIndex + end.length();
		}

		if (strList.size() > 0) {
			return strList;
		}

		return null;
	}

}
