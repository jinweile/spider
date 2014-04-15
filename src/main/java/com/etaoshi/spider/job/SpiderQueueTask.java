package com.etaoshi.spider.job;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.etaoshi.spider.analysis.IResultInDb;
import com.etaoshi.spider.analysis.SpiderWorker;
import com.etaoshi.spider.comm.SpringContext;
import com.etaoshi.spider.model.SourceSpider;
import com.etaoshi.spider.model.SpiderRegTemplate;
import com.etaoshi.spider.service.intf.ISourceSpiderService;
import com.etaoshi.spider.service.intf.ISpiderColumnService;
import com.etaoshi.spider.service.intf.ISpiderRegTemplateService;

/**
 * 爬虫队列任务(非quartz调度器使用)，后续开发
 * 
 * @author jinweile
 *
 */
public class SpiderQueueTask extends Thread {
	
	int sourcespiderid;
	final Map<String, SQLException> sqle_map;
	final List<String> sqls;
	
	public SpiderQueueTask(int sourcespiderid, Map<String, SQLException> sqle_map, List<String> sqls){
		this.sourcespiderid = sourcespiderid;
		this.sqle_map = sqle_map;
		this.sqls = sqls;
	}
	
	static ISourceSpiderService ssservice = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
	static ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	static ISpiderRegTemplateService srtservice = SpringContext.getInstance().getBean("ISpiderRegTemplateService", ISpiderRegTemplateService.class);

	/**
	 * 执行
	 * @param sourcespiderid
	 * @throws SQLException 
	 */
	public void run() {
		
    	//如果作业在运行，则跳过
    	if(SpiderTask.getJobRunMap(sourcespiderid))
    		return;
    	SpiderTask.setJobRunMap(sourcespiderid, true);
		SourceSpider ss = null;
		List<SpiderRegTemplate> srtlist = null;
		Map<Integer, String> scmap = null;
		try {
			ss = ssservice.Find(sourcespiderid);
			srtlist = srtservice.FindByTemplateid(ss.getTemplateid());
			scmap = scservice.FindAllMap();
		} catch (SQLException e1) {
			return;
		}
		
		String[] result = SpiderWorker.SpiderEntry(ss.getSpiderentryrule());
		
		SpiderWorker.RecursiveExtractTemplateInDb(ss.getId(), result[0], result[1], 0, ss.getTemplateid(), srtlist, scmap, null, 
				new IResultInDb(){
					public void Insert(List<String> insert_sql_list) {
						sqls.addAll(insert_sql_list);
						for(String sql : insert_sql_list){
							try {
								scservice.InsertIntoDataModel(sql);
							} catch (SQLException e) {
								sqle_map.put(sql, e);
							}
						}
					}
		});
		
	}
	
}
