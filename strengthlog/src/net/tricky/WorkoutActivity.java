package net.tricky;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class WorkoutActivity extends Activity {

	protected final Handler mHandler= new Handler();
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.workout);
        
//        Spinner spinner = (Spinner) findViewById(R.id.countdownSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
        
    }
    
    
//    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
//    	countdownDuration= Integer.parseInt(((String)parent.getItemAtPosition(pos)));
//    }
//    
//    public void onNothingSelected(AdapterView<?> parent){
//    	countdownDuration = DEFAULTDURATION;   	
//    }
    
    public void onResume(){
    	super.onResume();
    }
    
    public void onPause(){
    	super.onPause();
    }
}