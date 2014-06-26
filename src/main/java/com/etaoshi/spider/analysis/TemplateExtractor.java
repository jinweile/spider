package com.etaoshi.spider.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.etaoshi.spider.comm.ToolsUtils;
import com.etaoshi.spider.model.SpiderRegTemplate;

/**
 * 模版解析类
 * @author jinweile
 *
 */
public class TemplateExtractor {

	public final static int MAX_MATCH_COUNT = 4000;
	
	/**
	 * 解析出某一步骤的结果（不做递归处理，页面测试逻辑使用）
	 * @return
	 */
	public static List<String> ExtractTemplate(int rtid, List<SpiderRegTemplate> rtlist, String content){
		//正则过滤的结果
		List<String> resultlist = new ArrayList<String>();
		
		//每个过滤步骤运行的结果
		Map<Integer, List<String>> order_result_ht = new HashMap<Integer, List<String>>();
		List<String> headorder = new ArrayList<String>();
		headorder.add(content);
		order_result_ht.put(0, headorder);
		//开始正则过滤
		for(SpiderRegTemplate rt : rtlist){
			//如果没有正则匹配，则不做匹配结果
			if(rt.getSpiderreg() == null || rt.getSpiderreg().trim().isEmpty())
				continue;
			
			Pattern p = Pattern.compile("(?s)" + rt.getSpiderreg(), Pattern.CASE_INSENSITIVE );
			boolean flag = false;
			
			int index = rt.getSpiderorderby();
			while(true){
				//索引减少一个，判断是否有上一次的过滤结果
				index = index - 1;
				if(order_result_ht.containsKey(index)){
					List<String> result = new ArrayList<String>();
					for(String onecontent : order_result_ht.get(index)){
						List<String> oneresult = ExtractListRegion(p, onecontent);
						if(oneresult != null)
							result.addAll(oneresult);
					}
					
					//如果获取的是当前过滤结果，则装入返回结果集，并退出循环
					if(rtid == rt.getId()){
						resultlist = result.size() > 0 ? result : null;
						flag = true;
						break;
					}
					
					if(rt.getTypeid().equals(RuleExtractor.filter)
							&& !order_result_ht.containsKey(rt.getSpiderorderby())
							&& result.size() > 0){
						order_result_ht.put(rt.getSpiderorderby(), result);
					}
					break;
				}
				
				if(index <= 0)
					break;
			}
			
			if(flag)
				break;
		}
		
		return resultlist;
	}
	
	/**
	 * 正则解析（取多项数据）
	 * @param p
	 * @param content
	 * @return
	 */
	public static List<String> ExtractListRegion(Pattern p, String content){
		if(p == null)
			return null;
		List<String> result_list = new ArrayList<String>();
		Matcher matchers = p.matcher(content);
		int mcount = 0;
		while(matchers.find()){
			String one = null;
			try{
				one = matchers.group("match");
			}catch(Exception e){}
			if(one == null){
				if(matchers.groupCount() > 1)
					one = matchers.group(1);
				else
					one = matchers.group(0);
			}
			if(one != null && !one.isEmpty())
				result_list.add(one);
			mcount++;
			if(mcount > MAX_MATCH_COUNT)
				break;
		}
		
		if(result_list.size() == 0)
			return null;
		
		return result_list;
	}
	
}
