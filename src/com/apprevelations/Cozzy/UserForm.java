package com.apprevelations.Cozzy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class UserForm extends Activity implements OnClickListener{

	LinearLayout l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myaccount);
		l1=(LinearLayout)findViewById(R.id.bDone);
		l1.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bDone: 
			Intent ij=new Intent(UserForm.this,Donation.class);
			startActivity(ij);
			break;
		}
	}

	
}
