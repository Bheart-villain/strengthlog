package net.tricky;

import net.tricky.clock.Clock;
import net.tricky.clock.CountdownTimer;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

public class StrengthlogActivity extends Activity {
	
	static final int TIMERDURATION=240;
	static final int UPDATEDELAY=220;
	
	long appStartMillis=0;
	
	Clock elapsedTime;
	CountdownTimer countdownTimer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        appStartMillis = SystemClock.uptimeMillis();
        
        setContentView(R.layout.main);
        TextView mTimeView = (TextView)findViewById(R.id.clock);
        TextView mCountdownView = (TextView)findViewById(R.id.countdown);
        
        elapsedTime = new Clock(UPDATEDELAY,mTimeView);
        elapsedTime.startClock();
        
        countdownTimer = new CountdownTimer(UPDATEDELAY,TIMERDURATION,mCountdownView);
    }
    
    public void buttonGo(){
    	countdownTimer.startClock();
    }
    
    public void buttonStop(){
    	countdownTimer.stopClock();
    }
    
    public void buttonResetClock(){
    	countdownTimer.resetClock();
    }
    
    public void onResume(){
    	super.onResume();
    	
    	if (!elapsedTime.isRunning()){
    		elapsedTime.startClock();
    	}
    }
    
    public void onPause(){
    	super.onPause();
    	
    	elapsedTime.stopClock();
    	countdownTimer.stopClock();
    }
}