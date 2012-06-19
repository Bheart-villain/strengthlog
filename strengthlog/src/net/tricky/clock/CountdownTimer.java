package net.tricky.clock;

import android.os.SystemClock;
import android.widget.TextView;

public class CountdownTimer extends Clock {
	
	final int countdownTime; //timer duration, in seconds
	
	//create a countdown clock
	public CountdownTimer(int updateInterval, int countdownTime, TextView boundView){
		super(updateInterval,boundView);
		this.countdownTime=countdownTime;
		
		setTextTime(countdownTime);

	}
	
	// perform update task
	@Override
	public void run(){
		if (isRunning()){
			
			long currentMillis = SystemClock.uptimeMillis();
			int seconds = (int)(countdownTime-((currentMillis-startTime)/1000));
	
			if (seconds >= 0){
				setTextTime(seconds);
				scheduleNext();
			} else {
				view.setText("0:00");
				this.stopClock();
			}
		}
	}
	
	@Override
	public void startClock(){
    	long currentTime = SystemClock.uptimeMillis();
    	// account for the time that the countdown has been stopped
    	startTime = currentTime - (stopTime-startTime);
    	
    	super.startClock();
	}
	
	@Override
	public void resetClock(){
        super.resetClock();
        if (!isRunning()) {stopTime=startTime;}
    	setTextTime(countdownTime);
	}
}
