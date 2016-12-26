/**
 * 
 */
package com.masterserver.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author huangguanlin
 *
 * 2016年12月26日
 */
public class MasterServerStarter implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		MasterServerScheduled.instance.getExecutor().shutdown();
		System.out.println("结束销毁");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		MasterServer.instance.init();
		System.out.println("启动初始化");
	}
}
