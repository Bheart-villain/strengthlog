package net.tricky;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

public class StrengthlogActivity extends Activity {
	
	protected Dialog mSplashDialog;
	protected final Handler mHandler= new Handler();
	
	long appStartMillis=0;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        showSplashScreen();
    
        appStartMillis = SystemClock.uptimeMillis();
        
        setContentView(R.layout.main);
        
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

    }
    
    public void buttonStop(View v){
    }
    
    
    public void onResume(){
    	super.onResume();
  	
    }
    
    public void onPause(){
    	super.onPause();
    	
    }
}