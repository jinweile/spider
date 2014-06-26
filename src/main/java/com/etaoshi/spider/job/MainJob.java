package com.etaoshi.spider.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MainJob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("作业调度开始"); 
        SchedulerHelper test = new SchedulerHelper(); 
        test.one();
 
        System.out.println("作业调度结束");
	}
	
	public static class SchedulerHelper{
		
		private void addJob(Scheduler scheduler, String jobName, String jobGroup, String cron){ 
	        try { 
	            JobDetail jobOne = JobBuilder.newJob(GbSimpleJob.class).withIdentity(jobName, jobGroup).build(); 
	            CronTrigger trgOne = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup) 
	            .withSchedule(CronScheduleBuilder.cronSchedule(cron)) // [0 0/1 * * * ?]每一分钟执行一次 
	            .forJob(jobName, jobGroup).build(); 
	            scheduler.scheduleJob(jobOne, trgOne); 
	            
	        } catch (ParseException e) {
				e.printStackTrace();
			} catch (SchedulerException e) { 
	            e.printStackTrace(); 
	        } 
	    }
		
		public void one(){ 
	        try {
	        	//SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        	//schedulerFactory.initialize(xx)
	        	//Scheduler scheduler = schedulerFactory.getScheduler();
	           Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	            
	           addJob(scheduler, "job1", "jobGroup1", "0 0/1 * * * ?"); 
	           scheduler.pauseJob(new JobKey("job1","jobGroup1")); 
	           scheduler.start(); 
	           
	           addJob(scheduler, "job2", "jobGroup2", "0 0/1 * * * ?"); 
	           //停止相关job
	           //scheduler.pauseJob(new JobKey("job1","jobGroup1")); 
	           //回复相关job
	           //scheduler.resumeJob(new JobKey("job1","jobGroup1"));
	       } catch (SchedulerException e) { 
	           e.printStackTrace(); 
	       }
		}
		
	}

	public static class GbSimpleJob implements Job {
		
		private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		/**
	     * 执行任务
	     */ 
	    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException { 
	        System.out.println("start job 任务开始，时间：" + sdf.format(new Date())); 
	        JobKey jobKey = jobExecutionContext.getJobDetail().getKey(); 
	        System.out.println("任务---名称:" + jobKey.getName() + ",分组：" + jobKey.getGroup()); 
	        System.out.println("内容暂无!"); 
	        System.out.println("end job"); 
	    }
	    
	}

}
