package net.tricky;

import net.tricky.clock.Clock;
import net.tricky.clock.CountdownTimer;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
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
                
        elapsedTime = new Clock(UPDATEDELAY,mTimeView);
        elapsedTime.startClock();
        
    }
    
    public void buttonGo(View v){
    	countdownTimer.startClock();
    }
    
    public void buttonStop(View v){
    	countdownTimer.stopClock();
    }
    
    public void buttonResetClock(View v){
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