package com.etaoshi.spider.comm;

import java.util.concurrent.locks.*;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring容器单例
 * 
 * @author jinweile
 * 
 */
public class SpringContext {

	/**
	 * 容器实例
	 */
	private static volatile ClassPathXmlApplicationContext instance = null;

	/**
	 * 锁
	 */
	private static volatile Lock lockhelper = new ReentrantLock();

	/**
	 * 获取Spring容器实例
	 * 
	 * @return
	 */
	public static ClassPathXmlApplicationContext getInstance() {
		if (instance == null) {
			lockhelper.lock();
			if (instance == null) {
				try {
					instance = new ClassPathXmlApplicationContext("applicationContext.xml");
				} finally {
					lockhelper.unlock();
				}
			}
		}

		return instance;
	}

	/**
	 * 释放资源
	 */
	public static void Dispose() {
		if (instance != null) {
			lockhelper.lock();
			if (instance != null) {
				try {
					instance.destroy();
					instance = null;
				} finally {
					lockhelper.unlock();
				}
			}
		}
	}
}
