package com.apprevelations.Cozzy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Food extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);
	        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.npamount);
	        numberPicker.setMaxValue(10); 
	        numberPicker.setMinValue(0);         
	        numberPicker.setWrapSelectorWheel(true);
	        numberPicker.setOnValueChangedListener( new NumberPicker.
	            OnValueChangeListener() {
	            @Override
	            public void onValueChange(NumberPicker picker, int
	                oldVal, int newVal) {
                     Toast.makeText(getApplicationContext(), "value is" + newVal, Toast.LENGTH_SHORT).show();
	            }
	        });
	}

	
	
}
