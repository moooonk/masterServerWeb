/**
 * 
 */
package com.masterserver.core;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author huangguanlin
 *
 * 2016年12月23日
 */
public class MasterServerScheduled {
	public static MasterServerScheduled instance = new MasterServerScheduled();
	
	private ScheduledThreadPoolExecutor executor;
	
	private MasterServerScheduled(){
		executor = new ScheduledThreadPoolExecutor(1);
	}
	
	public ScheduledThreadPoolExecutor getExecutor(){
		return executor;
	}
	
}
