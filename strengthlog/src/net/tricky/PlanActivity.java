package net.tricky;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class PlanActivity extends Activity {

	protected final Handler mHandler= new Handler();
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.plans);
        
    }
    
    public void onResume(){
    	super.onResume();
    }
    
    public void onPause(){
    	super.onPause();
    }
}