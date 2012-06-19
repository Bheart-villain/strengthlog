package net.tricky.clock;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

public class Clock implements Runnable{
	
	long startTime; // clock start time in millis
	long stopTime; // most recent stop time in millis
	
	int updateInterval;
	TextView view=null;
	private boolean resumeEnabled = false;
	private boolean running = false;
	
	//singleton
	//TODO: This necessary? (are Handlers thread-specific?
	protected static Handler mHandler=null;
	public static Handler getHandler(){
		if (mHandler==null){
			return new Handler();
		} else {
			return mHandler;
		}
	}
	
	// create an elapsed time clock
	// TODO: Check for nulls, 0s, negatives, etc.
	public Clock(int updateInterval,TextView boundView){
		this.startTime=SystemClock.uptimeMillis();
		this.updateInterval=updateInterval;
		this.view=boundView;
		this.stopTime=this.startTime;
		mHandler= getHandler();
	}
	
	// perform update task
	public void run(){
		if (isRunning()){
			//time, now, in millis
			long currentMillis = SystemClock.uptimeMillis();
			int seconds = (int)(currentMillis-startTime)/1000;
	
			setTextTime(seconds);
			scheduleNext();
		}
	}
	
    // format seconds appropriately
	protected void setTextTime(int seconds){
		int minutes = seconds/60;
		seconds = seconds %60;
		
		//TODO: Format this more simply
		view.setText((seconds<10?""+minutes+":0"+seconds:""+minutes+":"+seconds));	
	}	
	
	public void startClock(){
		setRunning(true);
		scheduleNext(100);
	}
	
	public void stopClock(){
		if (isRunning()){
			setRunning(false);
			stopTime = SystemClock.uptimeMillis();
			mHandler.removeCallbacks(this);
		}
	}
	
	public void resetClock(){
		startTime=SystemClock.uptimeMillis();
	}
	
	protected void scheduleNext(){
		scheduleNext(updateInterval);
	}
	
	protected void scheduleNext(int delay){
		//if running, schedule next update
		if (isRunning()){
			mHandler.postDelayed(this, delay);
		}	
	}
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public boolean isRunning() {
		return running;
	}

	protected void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isResumeEnabled() {
		return resumeEnabled;
	}

	public void setResumeEnabled(boolean resumeEnabled) {
		this.resumeEnabled = resumeEnabled;
	}

}
