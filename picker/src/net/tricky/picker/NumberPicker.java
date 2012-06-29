package net.tricky.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NumberPicker extends LinearLayout {
	
	private final long REPEAT_DELAY = 50;
	
	private final int ELEMENT_HEIGHT = 50;
	private final int ELEMENT_WIDTH = ELEMENT_HEIGHT;
	
	Button decrement;
	Button increment;
	public EditText valueText;
	
	class RepetitiveUpdater implements Runnable {
		public void run() {
			if( autoIncrement ){
				increment();
				repeatUpdateHandler.postDelayed( this, REPEAT_DELAY );
			} else if( autoDecrement ){
				decrement();
				repeatUpdateHandler.postDelayed( this, REPEAT_DELAY );
			}
		}
	}
	
	public NumberPicker (Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		
		this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		LayoutParams elemenatParams = new LinearLayout.LayoutParams(ELEMENT_HEIGHT, ELEMENT_WIDTH);
		
		// init the individual elements
		initDecrementButton( context );
		initValueEditText( context );
		initIncrementButton( context );
		
		// Can be configured to be vertical or horizontal
		// Thanks for the help, LinearLayout!	
		if( getOrientation() == VERTICAL ){
			addView( increment, elementParams );
			addView( valueText, elementParams );
			addView( decrement, elementParams );
		} else {
			addView( decrement, elementParams );
			addView( valueText, elementParams );
			addView( increment, elementParams );
		}
		
		
	}

}
