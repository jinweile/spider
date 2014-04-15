package com.etaoshi.spider.job;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.etaoshi.spider.analysis.IResultInDb;
import com.etaoshi.spider.analysis.SpiderWorker;
import com.etaoshi.spider.comm.*;
import com.etaoshi.spider.service.intf.*;
import com.etaoshi.spider.model.*;

public class SpiderTask implements InitializingBean, ServletContextAware {
	
	static Logger logger = Logger.getLogger("mylog");

	static ISourceService sservice = SpringContext.getInstance().getBean("ISourceService", ISourceService.class);
	static ISourceSpiderService ssservice = SpringContext.getInstance().getBean("ISourceSpiderService", ISourceSpiderService.class);
	static ITemplateService tservice = SpringContext.getInstance().getBean("ITemplateService", ITemplateService.class);
	static IDataModelService dmservice = SpringContext.getInstance().getBean("IDataModelService", IDataModelService.class);
	static ITemplateDataModelService tdmservice = SpringContext.getInstance().getBean("ITemplateDataModelService", ITemplateDataModelService.class);
	static ISpiderColumnService scservice = SpringContext.getInstance().getBean("ISpiderColumnService", ISpiderColumnService.class);
	static ISpiderRegTemplateService srtservice = SpringContext.getInstance().getBean("ISpiderRegTemplateService", ISpiderRegTemplateService.class);
	
	static Scheduler scheduler = null;
	
	/**
	 * 作业规则
	 */
	volatile static Map<Integer,String> jobrule_map = new HashMap<Integer,String>();
	
	/**
	 * 获取正在运行的作业规则
	 * @param key
	 * @return
	 */
	public static String getJobRuleMap(int key){
		if(jobrule_map.containsKey(key))
			return jobrule_map.get(key);
		
		return "";
	}
	
	/**
	 * 作业状态(是否在运行)
	 */
	volatile static Map<Integer,Boolean> jobrun_map = new HashMap<Integer,Boolean>();
	
	/**
	 * 设定作业状态
	 * @param key
	 * @param value
	 */
	public static void setJobRunMap(int key, boolean value){
		synchronized(jobrun_map){
			jobrun_map.put(key, value);
		}
	}
	
	/**
	 * 获取某键的作业状态
	 * @param key
	 * @return
	 */
	public static boolean getJobRunMap(int key){
		synchronized(jobrun_map){
			if(jobrun_map.containsKey(key))
				return jobrun_map.get(key);
		}
		
		return false;
	}
	
	/**
	 * 作业是否暂停
	 */
	volatile static Map<Integer,Boolean> jobpause_map = new HashMap<Integer,Boolean>();
	
	/**
	 * 设定是否暂停
	 * @param key
	 * @param value
	 */
	private static synchronized void setJobPauseMap(int key, boolean value){
		jobpause_map.put(key, value);
	}
	
	/**
	 * 获取作业是否暂停
	 * @param key
	 * @return
	 */
	public static boolean getJobPauseMap(int key){
		if(jobpause_map.containsKey(key))
			return jobpause_map.get(key);
		
		return true;
	}
	
	/**
	 * 开始调度
	 * 说明：调度开始时设定了作业是否经过程序设定为暂停状态，非此作业的工作状态
	 * 作业的最终状态由 1.系统设定作业是否暂停；2.作业本身是否在运行 两项决定
	 * 例如：系统设定的作业暂停，但是本身还在运行，那么此作业在这次运行完后将处于暂停状态，除非通过系统设定为“启用”
	 * @throws SQLException 
	 * @throws SchedulerException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public void Start() throws SQLException, SchedulerException, ParseException, IOException {
		logger.error("\r\n调度器开始启动...\r\n");
		//设定调度器
		Properties prop = new Properties();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("quartz.properties");
		prop.load(is);
    	//SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    	StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
    	schedulerFactory.initialize(prop);
    	scheduler = schedulerFactory.getScheduler();
		
		//首先获取统计源
		List<Source> source_list = sservice.FindAll();
		for(Source source : source_list){
			//获取统计源抓取模版关联
			List<SourceSpider> sourcespider_list = ssservice.FindBySourceid(source.getId());
			for(SourceSpider sourcespider : sourcespider_list){
				jobrule_map.put(sourcespider.getId(), sourcespider.getJobrule());
				String jobName = "sourcespider_" + sourcespider.getId();
				String jobGroup = "source_" + source.getId();
				//创建作业并加入到调度任务
				JobDetail jobOne = JobBuilder.newJob(SourceSpiderJob.class)
															   .withIdentity(jobName, jobGroup)
															   .build();
				CronTrigger trgOne = TriggerBuilder.newTrigger()
																.withIdentity(jobName, jobGroup) 
																.withSchedule(CronScheduleBuilder.cronSchedule(sourcespider.getJobrule()))
																.forJob(jobName, jobGroup).build();
				jobOne.getJobDataMap().put("sourcespiderid", (int)sourcespider.getId());
				scheduler.scheduleJob(jobOne, trgOne);
				setJobPauseMap(sourcespider.getId(), false);
				//如果作业不可用，则设定作业状态停止
				if(source.getIsused().equals(false) || sourcespider.getIsused().equals(false)){
					scheduler.pauseJob(new JobKey(jobName,jobGroup)); 
					setJobPauseMap(sourcespider.getId(), true);
				}
			}
		}
		
		//开启检查作业调整任务
		JobDetail chkjobOne = JobBuilder.newJob(CheckSpiderJob.class)
														   .withIdentity("check", "checkspider")
														   .build();
		CronTrigger chktrgOne = TriggerBuilder.newTrigger()
															.withIdentity("check", "checkspider") 
															.withSchedule(CronScheduleBuilder.cronSchedule("0 0/9 * * * ?")) //每9分钟执行一次
															.forJob("check", "checkspider").build();
		scheduler.scheduleJob(chkjobOne, chktrgOne);
		
		//启动
		scheduler.start(); 
		
		logger.error("\r\n调度器启动成功...\r\n");
	}
	
	public static class SourceSpiderJob implements Job {
		/**
	     * 执行任务
	     */ 
	    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	    	int sourcespiderid = jobExecutionContext.getJobDetail().getJobDataMap().getIntValue("sourcespiderid");
	    	//如果作业在运行，则跳过
	    	if(getJobRunMap(sourcespiderid))
	    		return;
	    	setJobRunMap(sourcespiderid, true);
			SourceSpider ss = null;
			try {
				ss = ssservice.Find(sourcespiderid);
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
				setJobRunMap(sourcespiderid, false);
				return;
			}
			List<SpiderRegTemplate> srtlist = null;
			try {
				srtlist = srtservice.FindByTemplateid(ss.getTemplateid());
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
				setJobRunMap(sourcespiderid, false);
				return;
			}
			Map<Integer, String> scmap = null;
			try {
				scmap = scservice.FindAllMap();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
				setJobRunMap(sourcespiderid, false);
				return;
			}
			
			try{
				String[] result = SpiderWorker.SpiderEntry(ss.getSpiderentryrule());
				
				SpiderWorker.RecursiveExtractTemplateInDb(
						ss.getId(), 
						result[0], 
						result[1], 
						0, 
						ss.getTemplateid(), 
						srtlist, 
						scmap, 
						null, 
						new IResultInDb(){
							public void Insert(List<String> insert_sql_list) {
								for(String sql : insert_sql_list){
									try {
										scservice.InsertIntoDataModel(sql);
									} catch (SQLException e) {
										logger.error(e.getStackTrace() + "\r\n" + sql);
									}
								}
							}
						}
					);
		    } catch (Exception ex){
		    	logger.error(ex.getStackTrace());
		    }
			
			setJobRunMap(sourcespiderid, false);
	    }
	}
	
	public static class CheckSpiderJob implements Job {
		/**
		 * 执行任务
		 */
		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			//首先获取统计源
			List<Source> source_list = null;
			try {
				source_list = sservice.FindAll();
			} catch (SQLException e) {
				logger.error(e.getStackTrace());
				return;
			}
			for(Source source : source_list){
				//获取统计源抓取模版关联
				List<SourceSpider> sourcespider_list = null;
				try {
					sourcespider_list = ssservice.FindBySourceid(source.getId());
				} catch (SQLException e) {
					logger.error(e.getStackTrace());
					return;
				}
				for(SourceSpider sourcespider : sourcespider_list){
					
					String jobName = "sourcespider_" + sourcespider.getId();
					String jobGroup = "source_" + source.getId();

					//先判断此作业是否存在
					JobDetail jobOne = null;
					try {
						jobOne = scheduler.getJobDetail(new JobKey(jobName,jobGroup));
					} catch (SchedulerException e1) {
						logger.error(e1.getStackTrace());
						return;
					}
					//如果作业不存在，则创建作业并加入到调度任务
					if(jobOne == null){
						jobOne = JobBuilder.newJob(SourceSpiderJob.class)
													   .withIdentity(jobName, jobGroup)
													   .build();
						CronTrigger trgOne = null;
						try {
							trgOne = TriggerBuilder.newTrigger()
																.withIdentity(jobName, jobGroup) 
																.withSchedule(CronScheduleBuilder.cronSchedule(sourcespider.getJobrule()))
																.forJob(jobName, jobGroup).build();
						} catch (ParseException e) {
							logger.error(e.getStackTrace());
							continue;
						}
						jobOne.getJobDataMap().put("sourcespiderid", (int)sourcespider.getId());
						try {
							scheduler.scheduleJob(jobOne, trgOne);
							jobrule_map.put(sourcespider.getId(), sourcespider.getJobrule());
							setJobPauseMap(sourcespider.getId(),false);
						} catch (SchedulerException e) {
							logger.error(e.getStackTrace());
							continue;
						}
						//如果作业不可用，则设定作业状态停止
						if(source.getIsused().equals(false) || sourcespider.getIsused().equals(false)){
							try {
								scheduler.pauseJob(new JobKey(jobName,jobGroup));
								setJobPauseMap(sourcespider.getId(),true);
							} catch (SchedulerException e) {
								logger.error(e.getStackTrace());
								continue;
							} 
						}
					}
					//如果作业存在，则判断作业规则是否改变，如果改变则重新配置规则
					else{
						//先判断规则是否改变
						if(!sourcespider.getJobrule().trim().equals(jobrule_map.get(sourcespider.getId()))){
							CronTrigger trgOne = null;
							try {
								trgOne = TriggerBuilder.newTrigger()
																	.withIdentity(jobName, jobGroup) 
																	.withSchedule(CronScheduleBuilder.cronSchedule(sourcespider.getJobrule()))
																	.forJob(jobName, jobGroup).build();
							} catch (ParseException e) {
								logger.error(e.getStackTrace());
								continue;
							}
							try {
								//停止调度触发器相关的job
								scheduler.unscheduleJob(new TriggerKey(jobName,jobGroup));
								//重新恢复触发器相关的job任务
								scheduler.rescheduleJob(new TriggerKey(jobName,jobGroup), trgOne);
								jobrule_map.put(sourcespider.getId(), sourcespider.getJobrule());
								setJobPauseMap(sourcespider.getId(),false);
							} catch (SchedulerException e) {
								logger.error(e.getStackTrace());
								continue;
							}
						}
						
						//如果作业不可用，则设定作业状态停止
						if(source.getIsused().equals(false) || sourcespider.getIsused().equals(false)){
							try {
								//停止一个job任务
								scheduler.pauseJob(new JobKey(jobName,jobGroup));
								setJobPauseMap(sourcespider.getId(),true);
							} catch (SchedulerException e) {
								logger.error(e.getStackTrace());
								continue;
							} 
						}else{
							try {
								//恢复相关的job任务
								scheduler.resumeJob(new JobKey(jobName,jobGroup));
								setJobPauseMap(sourcespider.getId(),false);
							} catch (SchedulerException e) {
								logger.error(e.getStackTrace());
								continue;
							}
						}
					}

				}
			}
		}
	}

	public void setServletContext(ServletContext servletContext) {
		try {
			Start();
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		} catch (SchedulerException e) {
			logger.error(e.getStackTrace());
		} catch (ParseException e) {
			logger.error(e.getStackTrace());
		} catch (IOException e) {
			logger.error(e.getStackTrace().toString());
		}
	}

	/**
	 * 初始化也可以在这里做处理
	 */
	public void afterPropertiesSet() throws Exception { }
	
}
