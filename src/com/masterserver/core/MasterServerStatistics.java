/**
 * 
 */
package com.masterserver.core;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author huangguanlin
 *
 * 2016年12月26日
 */
public class MasterServerStatistics {
	public static final MasterServerStatistics instance = new MasterServerStatistics();
	private int lastMinCount;
	private int lastHourCount;
	private int lastDayCount;
	
	private int currentMinCount;
	private int currentHourCount;
	private int currentDayCount;
	
	private int Min;
	private int Hour;
	private int Day;
	
	private MasterServerStatistics(){
		lastMinCount = 0;
		lastHourCount = 0;
		lastDayCount = 0;
		currentMinCount = 0;
		currentHourCount = 0;
		currentDayCount = 0;
		Calendar current = Calendar.getInstance();
		Min = current.get(Calendar.MINUTE);
		Hour = current.get(Calendar.HOUR_OF_DAY);
		Day = current.get(Calendar.DAY_OF_MONTH);
		MasterServerScheduled.instance.getExecutor().scheduleWithFixedDelay(new JobForStatistics(), 0, 60, TimeUnit.SECONDS);
	}
	
	public void increase(){
		currentMinCount ++;
		currentHourCount ++;
		currentDayCount ++;
	}

	public int getLastMinCount() {
		return lastMinCount;
	}

	public int getLastHourCount() {
		return lastHourCount;
	}

	public int getLastDayCount() {
		return lastDayCount;
	}

	public int getCurrentMinCount() {
		return currentMinCount;
	}

	public int getCurrentHourCount() {
		return currentHourCount;
	}

	public int getCurrentDayCount() {
		return currentDayCount;
	}
	
	private void onCheckTime(){
		int currentMin;
		int currentHour;
		int currentDay;
		Calendar current = Calendar.getInstance();
		currentMin = current.get(Calendar.MINUTE);
		currentHour = current.get(Calendar.HOUR_OF_DAY);
		currentDay = current.get(Calendar.DAY_OF_MONTH);
		if(currentMin != Min){
			lastMinCount = currentMinCount;
			currentMinCount = 0;
			Min = currentMin;
		}
		if(currentHour != Hour){
			lastHourCount = currentHourCount;
			currentHourCount = 0;
			Hour = currentHour;
		}
		if(currentDay != Day){
			lastDayCount = currentDayCount;
			currentDayCount = 0;
			Day = currentDay;
		}
	}
	
	class JobForStatistics implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			MasterServerStatistics.instance.onCheckTime();
		}
		
	}
}
