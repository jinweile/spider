package com.etaoshi.spider.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 规则解析类
 * @author jinweile
 *
 */
public class RuleExtractor {
	
	/**
	 * 是否是过滤项
	 */
	public final static int filter = 0;
	
	/**
	 * 是否是入口项
	 */
	public final static int entry = -1;

	/**
	 * 解析入口规则
	 * @param rule
	 * @param eclass
	 * @return
	 */
	public static <E extends Enum<E>> Map<E, String> ExtractRuleEnum(String rule, Class<E> eclass){
		Map<E, String> map = new HashMap<E, String>();
		String rulereg = "\\[(?<key>.+?)\\]\n(?<value>.+)";
		String content = TextHelper.FormatCRLF(rule).replaceAll(" ", "");
		Pattern p = Pattern.compile(rulereg, Pattern.CASE_INSENSITIVE);
		Matcher matchers = p.matcher(content);
		while (matchers.find()) {
			// 通过分组名称获取正则匹配结果
			String key = matchers.group("key");
			String value = matchers.group("value").replaceAll("[\r\n\t]", "").trim();
			if(value.isEmpty() || value == null){
				continue;
			}
			
			E enum_key = Enum.valueOf(eclass, key);
			if(!map.containsKey(enum_key))
				map.put(enum_key, value);
		}
		
		return map;
	}
	
	/**
	 * 解析入口规则的http头信息
	 * @param header
	 * @return
	 */
	public static Map<String,String> ExtractHeader(String header){
		Map<String,String> map = new HashMap<String,String>();
		String reg = "\\(\\((?<key>.+?)=(?<value>.+?)\\)\\)";
		String content = TextHelper.FormatCRLF(header).replaceAll(" ", "").replace("\r", "").replace("\n", "");
		Pattern p = Pattern.compile("(?s)" + reg, Pattern.CASE_INSENSITIVE);
		Matcher matchers = p.matcher(content);
		while (matchers.find()) {
			// 通过分组名称获取正则匹配结果
			String key = matchers.group("key");
			String value = matchers.group("value").replaceAll("[\r\n\t]", "").trim();
			if(value.isEmpty() || value == null){
				continue;
			}

			if(!map.containsKey(key))
				map.put(key, value);
		}
		
		return map;
	}
	
	/**
	 * 解析入口规则的POST数据体
	 * @param postparams
	 * @return
	 */
	public static Map<String,String> ExtractPostParams(String postparams){
		Map<String,String> map = new HashMap<String,String>();
		String content = TextHelper.FormatCRLF(postparams).replaceAll(" ", "").replace("\r", "").replace("\n", "");
		String[] list = content.split("&");
		for(String one : list){
			String[] result = one.split("=");
			if(result.length == 2){
				map.put(result[0], result[1]);
			}
		}
		
		return map;
	}
	
	/**
	 * 解析出如果规则需要入库的表名[cinema({{request.cid}}{{request.cname}})]
	 * @param entryrule
	 * @return
	 */
	public static String ExtractTableName(String entryrule){
		String reg = "^(?<match>.+?)\\(.+?\\)$";
		entryrule = TextHelper.FormatCRLF(entryrule).replaceAll(" ", "").replace("\r", "").replace("\n", "");
		Pattern p = Pattern.compile("(?s)" + reg, Pattern.CASE_INSENSITIVE);
		Matcher matchers = p.matcher(entryrule);
		String tablename = null;
		if(matchers.find()){
			tablename = matchers.group("match");
		}
		
		return tablename;
	}
	
	/**
	 * 解析出入口规则需要替换的键[cinema({{request.cid}}{{request.cname}})]
	 * @param entryrule
	 * @return
	 */
	public static EntryRuleKeyList ExtractEntryRuleKey(String entryrule){
		EntryRuleKeyList rulekeylist = new EntryRuleKeyList();
		String rulereg = "\\{\\{(?<match>.+?)\\}\\}";
		entryrule = TextHelper.FormatCRLF(entryrule).replaceAll(" ", "").replace("\r", "").replace("\n", "");
		Pattern p = Pattern.compile("(?s)" + rulereg, Pattern.CASE_INSENSITIVE);
		Matcher matchers = p.matcher(entryrule);
		while(matchers.find()){
			String key = matchers.group("match");
			if(key.startsWith("array.")){
				String add_key = key.replace("array.", "");
				if(!rulekeylist.array.contains(add_key))
					rulekeylist.array.add(add_key);
			}else if(key.startsWith("request.")){
				String add_key = key.replace("request.", "");
				if(!rulekeylist.request.contains(add_key))
					rulekeylist.request.add(add_key);
			}
		}
		
		return rulekeylist;
	}
	
	/**
	 * 解析出分页参数
	 * @param page
	 * @return
	 */
	public static int[] ExtractPage(String page){
		page = TextHelper.FormatCRLF(page).replaceAll(" ", "").replace("\r", "").replace("\n", "");
		String[] min_max_add = page.split("\\|");
		String[] min_max = min_max_add[0].split("-");
		
		int[] result = new int[3];
		result[0] = Integer.parseInt(min_max[0]);
		result[1] = Integer.parseInt(min_max[1]);
		result[2] = Integer.parseInt(min_max_add[1]);
		
		return result;
	}
	
	/**
	 * 解析唯一键列名
	 * @param ukey
	 * @return
	 */
	public static EntryRuleKeyList ExtractUniqueKey(String ukey){
		EntryRuleKeyList rulekeylist = new EntryRuleKeyList();
		
		if(ukey != null){
			ukey = TextHelper.FormatCRLF(ukey).replaceAll(" ", "").replace("\r", "").replace("\n", "");
			String[] ukey_array = ukey.split("\\|");
			for(String key : ukey_array){
				if(key.startsWith("array.")){
					String add_key = key.replace("array.", "");
					if(!rulekeylist.array.contains(add_key))
						rulekeylist.array.add(add_key);
				}else if(key.startsWith("request.")){
					String add_key = key.replace("request.", "");
					if(!rulekeylist.request.contains(add_key))
						rulekeylist.request.add(add_key);
				}
			}
		}
		
		return rulekeylist;
	}
	
}
