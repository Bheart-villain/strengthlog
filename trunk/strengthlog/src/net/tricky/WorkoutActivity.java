package net.tricky;

import net.tricky.clock.Clock;
import net.tricky.clock.CountdownTimer;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class WorkoutActivity extends Activity implements OnItemSelectedListener{
	
	static final int DEFAULTDURATION = 240;
	static final int UPDATEDELAY=220;
	
	protected Dialog mSplashDialog;
	protected final Handler mHandler= new Handler();
	
	long appStartMillis=0;
	int countdownDuration;
	
	Clock elapsedTime;
	CountdownTimer countdownTimer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        showSplashScreen();
    
        appStartMillis = SystemClock.uptimeMillis();
        
        setContentView(R.layout.main);
        TextView mTimeView = (TextView)findViewById(R.id.clock);
        TextView mCountdownView = (TextView)findViewById(R.id.countdown);
        
        Spinner spinner = (Spinner) findViewById(R.id.countdownSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        elapsedTime = new Clock(UPDATEDELAY,mTimeView);
        elapsedTime.startClock();
        
        countdownDuration=DEFAULTDURATION;
        countdownTimer = new CountdownTimer(UPDATEDELAY,countdownDuration,mCountdownView);
    }
    
    protected void showSplashScreen(){
    	mSplashDialog = new Dialog(this, R.style.SLTheme_Splash);
        mSplashDialog.setContentView(R.layout.title);
        mSplashDialog.setCancelable(false);
        mSplashDialog.show();
         
        // Set Runnable to remove splash screen just in case
        mHandler.postDelayed(new Runnable() {
          @Override
          public void run() {
            removeSplashScreen();
          }
        }, 3000);
    	
    }
    
    protected void removeSplashScreen() {
        if (mSplashDialog != null) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
        }
    }
    
    public void buttonGo(View v){
    	countdownTimer.startClock();
    }
    
    public void buttonStop(View v){
    	countdownTimer.stopClock();
    }
    
    public void buttonResetClock(View v){
    	TextView mCountdownView = (TextView)findViewById(R.id.countdown);
        countdownTimer = new CountdownTimer(UPDATEDELAY,countdownDuration,mCountdownView);
    }
    
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
    	countdownDuration= Integer.parseInt(((String)parent.getItemAtPosition(pos)));
    }
    
    public void onNothingSelected(AdapterView<?> parent){
    	countdownDuration = DEFAULTDURATION;   	
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