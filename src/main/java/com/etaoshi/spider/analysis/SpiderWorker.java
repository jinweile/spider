package com.etaoshi.spider.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.etaoshi.spider.comm.SQLParamHelper;
import com.etaoshi.spider.comm.ToolsUtils;
import com.etaoshi.spider.model.SpiderRegTemplate;

/**
 * 爬虫业务逻辑
 * @author jinweile
 *
 */
public class SpiderWorker {

	static Logger logger = Logger.getLogger("mylog");
	
	/**
	 * 递归解析模版并入库
	 * @param sourcespiderid 当前抓取的url
	 * @param url 当前抓取的url
	 * @param content 当前抓取的内容
	 * @param parentid 父id
	 * @param tid 模版id
	 * @param templatelist 抓取模版目录树
	 * @param columnmap 映射的数据库字段map
	 * @param request 欻如的数据集合
	 * @param Insert 入库
	 */
	public static void RecursiveExtractTemplateInDb(
			int sourcespiderid,
			String url, 
			String content, 
			int parentid, 
			int tid, 
			List<SpiderRegTemplate> templatelist, 
			Map<Integer, String> columnmap,
			Map<String,String> request,
			IResultInDb Insert){
		//抓取的结果容器(入库字段,使用)
		Map<String, List<String>> indbList = new HashMap<String, List<String>>();
		//每个过滤步骤运行的结果，装入集合，以备下一步骤使用(不提供递归中使用)
		Map<Integer, List<String>> order_result_ht = new HashMap<Integer, List<String>>();
		List<String> headorder = new ArrayList<String>();
		headorder.add(content);
		order_result_ht.put(0, headorder);
		
		//开始正则过滤(获取的排序规则:)
		for(SpiderRegTemplate template : templatelist){
            //如果不是当前同一个父id下的，则跳过
            if (parentid != (int)template.getParentid())
                continue;
            
            //如果有正则匹配，则匹配结果
            if(template.getSpiderreg() != null && !template.getSpiderreg().replaceAll("\r", "").replaceAll("\n", "").trim().isEmpty()){
            	Pattern reg = Pattern.compile("(?s)" + template.getSpiderreg(), Pattern.CASE_INSENSITIVE);
            	int index = (int)template.getSpiderorderby();
            	//获取过滤出来的内容
            	while(true){
            		
            		//索引减少一个，判断是否有上一次的过滤结果
            		index = index - 1;
            		if(order_result_ht.containsKey(index)){
            			
            			List<String> result = new ArrayList<String>();
            			
            			for(String onecontent : order_result_ht.get(index)){
            				List<String> oneresult = TemplateExtractor.ExtractListRegion(reg, onecontent);
            				if(oneresult != null){
            					result.addAll(oneresult);
            				}else{
            					logger.error("\r\n---------------------------------"
                                        + "\r\n没有通过正则获取到数据项："
                                        + "\r\n模版ID:" + tid
                                        + "\r\nParentID:" + parentid
                                        + "\r\nID:" + template.getId()
                                        + "\r\n正则表达式为：" + template.getSpiderreg()
                                        + "\r\n--------------------------------\r\n");
            				}
            			}
            			
            			//非过滤项目存入结果容器
            			if(result.size() > 0 && template.getTypeid() > 0 && !indbList.containsKey(template.getTypeid())){
            				indbList.put(columnmap.get(template.getTypeid()), result);
            			}
            			
            			//如果是过滤项目存入过滤容器
            			if(template.getTypeid().equals(RuleExtractor.filter) 
        					&& !order_result_ht.containsKey(template.getSpiderorderby())
							&& result.size() > 0){
            				order_result_ht.put(template.getSpiderorderby(), result);
            			}
            			break;
            		}
            		
            		if(index <= 0)
            			break;
            		
            	}
            }
            
        	//如果不是entry类型，则跳过
        	if(template.getTypeid() != -1)
        		continue;
        	
        	//entry类型，则解析入口规则，准备入库和进入递归处理
        	
        	//首先获取入口规则
        	Map<EntryRule,String> rulelist = RuleExtractor.ExtractRuleEnum(template.getEntryrule(), EntryRule.class);
        	
        	//判断是否有入库逻辑
        	if(rulelist.containsKey(EntryRule.insertdb)){
        		String insert = rulelist.get(EntryRule.insertdb);
        		//解析存入的表
        		String tablename = RuleExtractor.ExtractTableName(insert);
        		//解析存入字段
        		EntryRuleKeyList rulekeylist = RuleExtractor.ExtractEntryRuleKey(insert);
        		List<String> requestfields = rulekeylist.request;
                List<String> arrayfields = rulekeylist.array;
                
                //开始获取入库的哈希表
                List<Map<String,String>> insertdb_map_list = new ArrayList<Map<String,String>>();
                //如果有列表字段
                if(arrayfields.size() > 0){
                	String fieldkey = arrayfields.get(0);
                	//如果此阶段没有入库的字段，则记录异常日志，并推出循环
                	if(!indbList.containsKey(fieldkey)){
                		logger.error("\r\n---------------------------------"
                                + "\r\n存入数据库参数错误，具体信息："
                                + "\r\n模版ID:" + tid
                                + "\r\nParentID:" + parentid
                                + "\r\nID:" + template.getId()
                                + "\r\n抓取结果中缺少[insertdb]array." + fieldkey
                                + "\r\n--------------------------------\r\n");
                		return;
                	}
                	//开始获取入库的哈希表的列表
                	for(int i = 0; i < indbList.get(fieldkey).size(); i++){
                		Map<String, String> insertdb_map = new HashMap<String, String>();
                		//取一个键值
                		for(int j = 0; j < arrayfields.size(); j++){
                			String _fieldkey = arrayfields.get(j);
                			//如果入库少项，则记录异常日志
                			if(!indbList.containsKey(_fieldkey)){
                        		logger.error("\r\n---------------------------------"
                                        + "\r\n存入数据库参数错误，具体信息："
                                        + "\r\n模版ID:" + tid
                                        + "\r\nParentID:" + parentid
                                        + "\r\nID:" + template.getId()
                                        + "\r\n抓取结果中缺少[insertdb]array." + _fieldkey
                                        + "\r\n--------------------------------\r\n");
                        		return;
                			}
                			if(indbList.get(_fieldkey).size() != indbList.get(fieldkey).size()){
                				logger.error("\r\n---------------------------------"
                                        + "\r\n存入数据库长度不同，具体信息："
                                        + "\r\n模版ID:" + tid
                                        + "\r\nParentID:" + parentid
                                        + "\r\nID:" + template.getId()
                                        + "\r\n[insertdb]array." + _fieldkey + "与array." + fieldkey + "长度不同"
                                        + "\r\n--------------------------------\r\n");
                        		return;
                			}
                			
                			String fieldvalue = indbList.get(_fieldkey).get(i);
                			if(!insertdb_map.containsKey(_fieldkey)){
                				insertdb_map.put(_fieldkey, fieldvalue);
                			}
                		}
                		
                		//装入list
                		insertdb_map_list.add(insertdb_map);
                	}
                }
                
                //解析唯一键算法
                EntryRuleKeyList ukey_list = RuleExtractor.ExtractUniqueKey(rulelist.get(EntryRule.uniquekey));
                
                //拼装sql的容器
                List<String> sql_list = new ArrayList<String>();
                for(Map<String,String> map : insertdb_map_list){
                	//根据唯一键判断是否insert还是update
                	String sql = "";
                	if(ukey_list.array.size() > 0 || ukey_list.request.size() > 0){
                		sql += "if not exists (select 1 from " + tablename + " where (";
                		int ulen = ukey_list.array.size();
                		int ui = 0;
                		for(String ukey : ukey_list.array){
                			sql += ukey + " = '" + SQLParamHelper.Replace(TextHelper.TagClean(map.get(ukey)).replaceAll("\\s", "")) + "'";
                			if(ui < ulen - 1){
                				sql += " and ";
                			}
                			ui++;
                		}
                		ulen = ukey_list.request.size();
                		ui = 0;
                		for(String ukey : ukey_list.request){
                			if(ui == 0){
                				sql += sql.endsWith("(") ? "" : " and ";
                			}
                			sql += ukey + " = '" + SQLParamHelper.Replace(TextHelper.TagClean(request.get(ukey)).replaceAll("\\s", "")) + "'";
                			if(ui < ulen - 1){
                				sql += " and ";
                			}
                			ui++;
                		}
                		sql += sql.endsWith("(") ? "" : " and ";
                		sql += " sourcespiderid = " + sourcespiderid + " )) begin ";
                	}
                	
                	sql += " insert into " + tablename + " (";
                	int len = map.size();
                	int i = 0;
                	for(String key : map.keySet()){
                		sql += key;
                		if(i < len - 1)
                			sql += ",";
                		i++;
                	}
                	len = requestfields.size();
                	i = 0;
                	for(String requestkey : requestfields){
                		if(i == 0){
                			sql += sql.endsWith("(") ? "" : ",";
                		}
                		sql += requestkey;
                		if(i < len - 1)
                			sql += ",";
                		i++;
                	}
            		sql += sql.endsWith("(") ? "" : ",";
                	sql += " sourcespiderid, spidertime ) values (";
                	len = map.size();
                	i = 0;
                	for(String key : map.keySet()){
                		if(i == 0){
                			sql += sql.endsWith("(") ? "" : ",";
                		}
                		sql += "'" + SQLParamHelper.Replace(TextHelper.TagClean(map.get(key)).replaceAll("\\s", "")) + "'";
                		if(i < len - 1)
                			sql += ",";
                		i++;
                	}
                	len = requestfields.size();
                	i = 0;
                	for(String requestkey : requestfields){
                		if(i == 0){
                			sql += sql.endsWith("(") ? "" : ",";
                		}
                		sql += "'" + SQLParamHelper.Replace(TextHelper.TagClean(request.get(requestkey)).replaceAll("\\s", "")) + "'";
                		if(i < len - 1)
                			sql += ",";
                		i++;
                	}
                	sql += sql.endsWith("(") ? "" : ",";
                	sql += "" + sourcespiderid;
                	sql += ",getdate())";
                	
                	//开始根据唯一键update
                	if(ukey_list.array.size() > 0 || ukey_list.request.size() > 0){
                		sql += " end else begin ";
                		sql += " update " + tablename + " set ";
                		int inlen = map.size();
                		int ii = 0;
                		for(String key : map.keySet()){
                			if(!ukey_list.array.contains(key)){
                				if(!sql.endsWith("set "))
                					sql += ",";
	                			sql += key + " = " + "'" + SQLParamHelper.Replace(TextHelper.TagClean(map.get(key)).replaceAll("\\s", "")) + "'";
                			}
                			ii++;
                		}
                		inlen = requestfields.size();
                		ii = 0;
                		for(String requestkey : requestfields){
                			if(!ukey_list.request.contains(requestkey)){
                				if(!sql.endsWith("set "))
                					sql += ",";
	                			sql += requestkey + " = " + "'" + SQLParamHelper.Replace(TextHelper.TagClean(request.get(requestkey)).replaceAll("\\s", "")) + "'";
                			}
                			ii++;
                		}
                		if(!sql.endsWith("set "))
                			sql += ",";
                		sql += " spidertime = getdate() ";
                		sql += " where (";
                		int ulen = ukey_list.array.size();
                		int ui = 0;
                		for(String ukey : ukey_list.array){
                			sql += ukey + " = '" + SQLParamHelper.Replace(TextHelper.TagClean(map.get(ukey)).replaceAll("\\s", "")) + "'";
                			if(ui < ulen - 1){
                				sql += " and ";
                			}
                			ui++;
                		}
                		ulen = ukey_list.request.size();
                		ui = 0;
                		for(String ukey : ukey_list.request){
                			if(ui == 0){
                				sql += sql.endsWith("(") ? "" : " and ";
                			}
                			sql += ukey + " = '" + SQLParamHelper.Replace(TextHelper.TagClean(request.get(ukey)).replaceAll("\\s", "")) + "'";
                			if(ui < ulen - 1){
                				sql += " and ";
                			}
                			ui++;
                		}
                		sql += sql.endsWith("(") ? "" : " and ";
                		sql += " sourcespiderid = " + sourcespiderid + ")";
                		sql += " end ";
                	}
                	
                	sql_list.add(sql);
                }
                
                //入库
                if(sql_list.size() > 0)
                	Insert.Insert(sql_list);
        	}
        	
        	//判断是否有递归逻辑
        	
        	//如果是url递归入口
        	if(rulelist.containsKey(EntryRule.url)){
        		//获取url规则
        		String entry_url_rule = rulelist.get(EntryRule.url);
        		//解析url规则
        		EntryRuleKeyList rulekeylist = RuleExtractor.ExtractEntryRuleKey(entry_url_rule);
        		//解析response规则
        		EntryRuleKeyList responsekeylist = RuleExtractor.ExtractEntryRuleKey(rulelist.containsKey(EntryRule.response) ? rulelist.get(EntryRule.response) : "");
                //解析header规则
        		EntryRuleKeyList headerkeylist = RuleExtractor.ExtractEntryRuleKey(rulelist.containsKey(EntryRule.header) ? rulelist.get(EntryRule.header) : "");
                //解析postparams规则
        		EntryRuleKeyList postparamskeylist = RuleExtractor.ExtractEntryRuleKey(rulelist.containsKey(EntryRule.postparams) ? rulelist.get(EntryRule.postparams) : "");
        		//如果有array则需要循环进入
        		if(rulekeylist.array.size() > 0 || responsekeylist.array.size() > 0 || headerkeylist.array.size() > 0 || postparamskeylist.array.size() > 0){
        			//先取到数组长度
        			int len = rulekeylist.array.size() > 0 ? indbList.get(rulekeylist.array.get(0)).size() : 
        				(responsekeylist.array.size() > 0 ? indbList.get(responsekeylist.array.get(0)).size() : 
        					(headerkeylist.array.size() > 0 ? indbList.get(headerkeylist.array.get(0)).size() : 
        						indbList.get(postparamskeylist.array.get(0)).size()));
        			for(int i = 0; i < len; i++){
        				
        				//抓取地址处理
        				String entry_url = entry_url_rule;
        				//先做request替换
        				for(String rulekey : rulekeylist.request){
        					entry_url = entry_url.replaceAll("\\{\\{request\\." + rulekey + "\\}\\}", request.get(rulekey));
        				}
        				//然后做array替换
        				for(String rulekey : rulekeylist.array){
        					entry_url = entry_url.replaceAll("\\{\\{array\\." + rulekey + "\\}\\}", indbList.get(rulekey).get(i));
        				}
        				
        				//开始拼装response数据作为下一层request
        				Map<String,String> response = null;
        				if(rulelist.containsKey(EntryRule.response)){
        					response = new HashMap<String,String>();
        					//先做request数据拼装
        					for(String rulekey : responsekeylist.request){
        						if(!response.containsKey(rulekey))
        							response.put(rulekey, request.get(rulekey));
        					}
        					//在做array数据拼装
        					for(String rulekey : responsekeylist.array){
        						if(!response.containsKey(rulekey))
        							response.put(rulekey, indbList.get(rulekey).get(i));
        					}
        				}
        				
        				//开始拼装header
        				Map<String,String> headers = null;
        				if(rulelist.containsKey(EntryRule.header)){
        					headers = new HashMap<String,String>();
        					//解析headers
        					Map<String,String> headerdic = RuleExtractor.ExtractHeader(rulelist.get(EntryRule.header));
        					for(String key : headerdic.keySet()){
        						String headervalue = headerdic.get(key);
        						//先做request替换
        						for(String headerkey : headerkeylist.request){
        							headervalue = headervalue.replaceAll("\\{\\{request\\." + headerkey + "\\}\\}", request.get(headerkey));
        						}
        						//然后坐array替换
        						for(String headerkey : headerkeylist.array){
        							headervalue = headervalue.replaceAll("\\{\\{array\\." + headerkey + "\\}\\}", indbList.get(headerkey).get(i));
        						}
        						
        						if(!headers.containsKey(key))
            						headers.put(key, headervalue);
        					}
        				}
        				
        				//解析method和postparams
        				String Method = "GET";
        				Map<String,String> PostBody = null;
        				String PostBodyString = null;
        				if(rulelist.containsKey(EntryRule.method) && 
        						rulelist.get(EntryRule.method)
        								  .replaceAll("\r", "").replaceAll("\n", "").trim()
        								  .equalsIgnoreCase("POST")){
        					Method = "POST";
        					//开始解析post参数
        					if(rulelist.containsKey(EntryRule.postparams)){
        						String postbody = rulelist.get(EntryRule.postparams);
        						//先做request替换
        						for(String rulekey : postparamskeylist.request){
        							postbody = postbody.replaceAll("\\{\\{request\\." + rulekey + "\\}\\}", request.get(rulekey));
        						}
        						//然后做array替换
        						for(String rulekey : postparamskeylist.array){
        							postbody = postbody.replaceAll("\\{\\{array\\." + rulekey + "\\}\\}", indbList.get(rulekey).get(i));
        						}
        						
        						PostBodyString = postbody;
        						PostBody = RuleExtractor.ExtractPostParams(postbody);
        					}
        				}
        				
        				//解析ssl (暂时不支持)
        				
        				
						//做page分页参数解析并替换
        				List<String> spider_url_list = new ArrayList<String>();
        				List<Map<String,String>> PostBody_list = new ArrayList<Map<String,String>>();
        				List<String> PostBodyString_list = new ArrayList<String>();
						if(rulelist.containsKey(EntryRule.page) && !rulelist.get(EntryRule.page).isEmpty()){
							int[] page_array = RuleExtractor.ExtractPage(rulelist.get(EntryRule.page));
							//先替换url
							int min = page_array[0];
							int max = page_array[1];
							int add = page_array[2];
							int nums = min;
							while(nums <= max){
								spider_url_list.add(entry_url.replaceAll("\\{\\{page\\}\\}", "" + nums));
								nums += add;
							}
							if(PostBody != null && PostBody.size() > 0){
								nums = min;
								//再替换postbody
								while(nums <= max){
									Map<String,String> PostBody_map = new HashMap<String,String>();
									for(String key : PostBody.keySet()){
										String value = PostBody.get(key);
										if(PostBody.get(key).contains("{{page}}")){
											value = value.replaceAll("\\{\\{page\\}\\}", "" + nums);
										}
										PostBody_map.put(key, value);
									}
									PostBody_list.add(PostBody_map);
									nums += add;
								}
							}
							if(PostBodyString != null && PostBodyString.trim().length() > 0){
								nums = min;
								//再替换postbody
								while(nums <= max){
									PostBodyString_list.add(PostBodyString.replaceAll("\\{\\{page\\}\\}", "" + nums));
									nums += add;
								}
							}
						}else{
							spider_url_list.add(entry_url);
							PostBody_list.add(PostBody);
							PostBodyString_list.add(PostBodyString);
						}
        				
        				//抓取内容
						for(int ii = 0; ii < spider_url_list.size(); ii++){
							
	        				String spider_url = ToolsUtils.CombineUrl(url, spider_url_list.get(ii));
	        				String entry_content = null;
	        				if(Method.equals("POST")){
	        					int spider_nums = 0;
	        					while(spider_nums < 2){
		        					try {
		        						if(PostBody_list.get(ii).size() > 0){
		        							entry_content = HttpDown.postdown(spider_url, headers, PostBody_list.get(ii));
		        						}else{
		        							entry_content = HttpDown.postbodydown(spider_url, headers, PostBodyString_list.get(ii));
		        						}
		        						break;
									} catch (HttpException e) {
										logger.error(e.getStackTrace());
									} catch (IOException e) {
										logger.error(e.getStackTrace());
									}
		        					spider_nums++;
	        					}
	        				}else{
	        					int spider_nums = 0;
	        					while(spider_nums < 2){
		        					try {
										entry_content = HttpDown.getdown(spider_url, headers);
										break;
									} catch (HttpException e) {
										logger.error(e.getStackTrace());
									} catch (IOException e) {
										logger.error(e.getStackTrace());
									}
		        					spider_nums++;
	        					}
	        				}
	        				
	        				//进入一次递归
	        				if(entry_content != null && !entry_content.isEmpty()){
	        					try{
		            				RecursiveExtractTemplateInDb(
		            						sourcespiderid
		            						,spider_url
		            						,entry_content
		            						,template.getId()
		            						,tid
		            						,templatelist
		            						,columnmap
		            						,response
		            						,Insert);
	        					}catch(Exception e){
	        						logger.error(e.getStackTrace());
	        					}
	        				}
        				
						}
        			}
        		}
        		//否则通过request进入
        		else{
        			
        			String entry_url = entry_url_rule;
        			for(String rulekey : rulekeylist.request){
        				entry_url = entry_url.replaceAll("\\{\\{request\\." + rulekey + "\\}\\}", request.get(rulekey));
        			}
        			
        			//开始拼装response数据作为下一层request
        			Map<String,String> response = null;
        			if(rulelist.containsKey(EntryRule.response)){
        				response = new HashMap<String,String>();
        				//做request数据拼装
        				for(String rulekey : responsekeylist.request){
        					if(!response.containsKey(rulekey))
        						response.put(rulekey, request.get(rulekey));
        				}
        			}
        			
        			//拼装header
        			Map<String,String> headers = null;
        			if(rulelist.containsKey(EntryRule.header)){
        				headers = new HashMap<String,String>();
        				//解析headers
        				Map<String,String> headerdic = RuleExtractor.ExtractHeader(rulelist.get(EntryRule.header));
        				for(String key : headerdic.keySet()){
        					String headervalue = headerdic.get(key);
        					//做request替换
        					for(String headerkey : headerkeylist.request){
        						headervalue = headervalue.replaceAll("\\{\\{request\\." + headerkey + "\\}\\}", request.get(headerkey));
        					}
        					
        					if(!headers.containsKey(key))
        						headers.put(key, headervalue);
        				}
        			}
        			
        			//解析method和postparams
        			String Method = "GET";
        			Map<String,String> PostBody = null;
        			String PostBodyString = null;
    				if(rulelist.containsKey(EntryRule.method) && 
    						rulelist.get(EntryRule.method)
    								  .replaceAll("\r", "").replaceAll("\n", "").trim()
    								  .equalsIgnoreCase("POST")){
    					Method = "POST";
    					//开始解析post参数
    					if(rulelist.containsKey(EntryRule.postparams)){
    						String postbody = rulelist.get(EntryRule.postparams);
    						//先做request替换
    						for(String rulekey : postparamskeylist.request){
    							postbody = postbody.replaceAll("\\{\\{request\\." + rulekey + "\\}\\}", request.get(rulekey));
    						}
    						
    						PostBodyString = postbody;
    						PostBody = RuleExtractor.ExtractPostParams(postbody);
    					}
    				}
    				
    				//解析ssl (暂不支持)
    				
    				
    				
					//做page分页参数解析并替换
    				List<String> spider_url_list = new ArrayList<String>();
    				List<Map<String,String>> PostBody_list = new ArrayList<Map<String,String>>();
    				List<String> PostBodyString_list = new ArrayList<String>();
					if(rulelist.containsKey(EntryRule.page) && !rulelist.get(EntryRule.page).isEmpty()){
						int[] page_array = RuleExtractor.ExtractPage(rulelist.get(EntryRule.page));
						//先替换url
						int min = page_array[0];
						int max = page_array[1];
						int add = page_array[2];
						int nums = min;
						while(nums <= max){
							spider_url_list.add(entry_url.replaceAll("\\{\\{page\\}\\}", "" + nums));
							nums += add;
						}
						if(PostBody != null && PostBody.size() > 0){
							nums = min;
							//再替换postbody
							while(nums <= max){
								Map<String,String> PostBody_map = new HashMap<String,String>();
								for(String key : PostBody.keySet()){
									String value = PostBody.get(key);
									if(PostBody.get(key).contains("{{page}}")){
										value = value.replaceAll("\\{\\{page\\}\\}", "" + nums);
									}
									PostBody_map.put(key, value);
								}
								PostBody_list.add(PostBody_map);
								nums += add;
							}
						}
						if(PostBodyString != null && PostBodyString.trim().length() > 0){
							nums = min;
							//再替换postbody
							while(nums <= max){
								PostBodyString_list.add(PostBodyString.replaceAll("\\{\\{page\\}\\}", "" + nums));
								nums += add;
							}
						}
					}else{
						spider_url_list.add(entry_url);
						PostBody_list.add(PostBody);
						PostBodyString_list.add(PostBodyString);
					}
        			
    				//抓取内容
					for(int ii = 0; ii < spider_url_list.size(); ii++){

	    				String spider_url = ToolsUtils.CombineUrl(url, spider_url_list.get(ii));
	    				String entry_content = null;
	    				if(Method.equals("POST")){
        					int spider_nums = 0;
        					while(spider_nums < 2){
		    					try {
	        						if(PostBody_list.get(ii).size() > 0){
	        							entry_content = HttpDown.postdown(spider_url, headers, PostBody_list.get(ii));
	        						}else{
	        							entry_content = HttpDown.postbodydown(spider_url, headers, PostBodyString_list.get(ii));
	        						}
	        						break;
								} catch (HttpException e) {
									logger.error(e.getStackTrace());
								} catch (IOException e) {
									logger.error(e.getStackTrace());
								}
		    					spider_nums++;
        					}
	    				}else{
        					int spider_nums = 0;
        					while(spider_nums < 2){
		    					try {
		    						entry_content = HttpDown.getdown(spider_url, headers);
		    						break;
								} catch (HttpException e) {
									logger.error(e.getStackTrace());
								} catch (IOException e) {
									logger.error(e.getStackTrace());
								}
		    					spider_nums++;
        					}
	    				}
	    				
        				//进入一次递归
        				if(entry_content != null && !entry_content.isEmpty()){
        					try{
	            				RecursiveExtractTemplateInDb(
	            						sourcespiderid
	            						,spider_url
	            						,entry_content
	            						,template.getId()
	            						,tid
	            						,templatelist
	            						,columnmap
	            						,response
	            						,Insert);
        					}catch(Exception e){
        						logger.error(e.getStackTrace());
        					}
        				}
        				
	        		}
					
        		}
        	}
        	//如果是content递归入口
        	else if(rulelist.containsKey(EntryRule.content)){
        		//获取内容数据
        		String entry_content_rule = rulelist.get(EntryRule.content);
        		//解析内容规则
                EntryRuleKeyList rulekeylist = RuleExtractor.ExtractEntryRuleKey(entry_content_rule);
                //解析response规则
                EntryRuleKeyList responsekeylist = RuleExtractor.ExtractEntryRuleKey(rulelist.containsKey(EntryRule.response) ? rulelist.get(EntryRule.response) : "");
                //如果有array则需要循环进入
                if(rulekeylist.array.size() > 0){
                	//先取到数组长度
                	int len = indbList.get(rulekeylist.array.get(0)).size();
                	for(int i = 0; i < len; i++){
                		String entry_content = entry_content_rule;
                		//先做request替换
                		for(String rulekey : rulekeylist.request){
                			entry_content = entry_content.replaceAll("\\{\\{request\\." + "\\}\\}", request.get(rulekey));
                		}
                		//然后做array替换
                		for(String rulekey : rulekeylist.array){
                			entry_content = entry_content.replaceAll("\\{\\{array\\." + rulekey + "\\}\\}", indbList.get(rulekey).get(i));
                		}
                		
                		//开始拼装response数据作为下一层request
                		Map<String,String> response = null;
                		if(rulelist.containsKey(EntryRule.response)){
                			response = new HashMap<String,String>();
                			//先做request数据拼装
                			for(String rulekey : responsekeylist.request){
                				if(!response.containsKey(rulekey))
                					response.put(rulekey, request.get(rulekey));
                			}
                			//在做array数据拼装
                			for(String rulekey : responsekeylist.array){
                				if(!response.containsKey(rulekey))
                					response.put(rulekey, indbList.get(rulekey).get(i));
                			}
                		}
                		
                		//进入一次递归
        				if(entry_content != null && !entry_content.isEmpty())
        					RecursiveExtractTemplateInDb(
	        						sourcespiderid
	        						,url
	        						,entry_content
	        						,template.getId()
	        						,tid
	        						,templatelist
	        						,columnmap
	        						,response
	        						,Insert);
                	}
                }
                //否则通过request进入
                else{
                	String entry_content = entry_content_rule;
                	for(String rulekey : rulekeylist.request){
                		entry_content = entry_content.replaceAll("\\{\\{request\\." + rulekey + "\\}\\}", request.get(rulekey));
                	}
                	//开始拼装response数据作为下一层request
                	Map<String,String> response = null;
                	if(rulelist.containsKey(EntryRule.response)){
                		response = new HashMap<String,String>();
                		//先做request数据拼装
                		for(String rulekey : responsekeylist.request){
                			if(!response.containsKey(rulekey))
                				response.put(rulekey, request.get(rulekey));
                		}
                	}
                	//进入下一层递归
    				if(entry_content != null && !entry_content.isEmpty())
    					RecursiveExtractTemplateInDb(
        						sourcespiderid
        						,url
        						,entry_content
        						,template.getId()
        						,tid
        						,templatelist
        						,columnmap
        						,response
        						,Insert);
                }
                
        	}
        	
            //处理完entry就退出
            break;
		}
	}
	
	/**
	 * 根据抓取入口规则抓取入口内容
	 * @return
	 */
	public static String[] SpiderEntry(String entryrule){
		Map<EntryRule,String> rulelist = RuleExtractor.ExtractRuleEnum(entryrule, EntryRule.class);
		String url = rulelist.get(EntryRule.url);
		
		//拼装header
		Map<String,String> headers = null;
		if(rulelist.containsKey(EntryRule.header)){
			headers = RuleExtractor.ExtractHeader(rulelist.get(EntryRule.header));
		}
		
		//解析method和postparams
		String Method = "GET";
		Map<String,String> PostBody = null;
		if(rulelist.containsKey(EntryRule.method) && 
				rulelist.get(EntryRule.method)
						  .replaceAll("\r", "").replaceAll("\n", "").trim()
						  .equalsIgnoreCase("POST")){
			Method = "POST";
			//开始解析post参数
			if(rulelist.containsKey(EntryRule.postparams)){
				PostBody = RuleExtractor.ExtractPostParams(rulelist.get(EntryRule.postparams));
			}
		}
		
		//抓取数据
		String entry_content = null;
		if(Method.equals("POST")){
			int spider_nums = 0;
			while(spider_nums < 2){
				try {
					entry_content = HttpDown.postdown(url, headers, PostBody);
					break;
				} catch (HttpException e) {
					logger.error(e.getStackTrace());
				} catch (IOException e) {
					logger.error(e.getStackTrace());
				}
				spider_nums++;
			}
		}else{
			int spider_nums = 0;
			while(spider_nums < 2){
				try {
					entry_content = HttpDown.getdown(url, headers);
					break;
				} catch (HttpException e) {
					logger.error(e.getStackTrace());
				} catch (IOException e) {
					logger.error(e.getStackTrace());
				}
				spider_nums++;
			}
		}
		
		return new String[]{url, entry_content};
	}
	
}
