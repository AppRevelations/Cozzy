package com.apprevelations.Cozzy;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cloths extends ActionBarActivity implements OnClickListener{

	
	private Button shirt,trouser,woolens,others,donate;
	EditText shirt_nos, trouser_nos,woolen_nos,other_nos; 
	int s_no=0;
	int t_no=0;
	int w_no=0;
	int o_no=0;
	SharedPreferences spref;
	Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cloths);
		spref= getSharedPreferences("cloth_numbers", MODE_PRIVATE);
		
		editor = spref.edit();
		
		s_no=spref.getInt("shirt_number", 0);
		t_no=spref.getInt("trouser_number", 0);	
		w_no=spref.getInt("woollen_number", 0);
		o_no=spref.getInt("other_number", 0);
		
		
		shirt= (Button) findViewById(R.id.bshirt);
		donate= (Button) findViewById(R.id.bdonate);
		trouser= (Button) findViewById(R.id.btrouser);
		shirt_nos=(EditText) findViewById(R.id.etshirtnumber);
		trouser_nos=(EditText) findViewById(R.id.ettrousernumber);

		woolens= (Button) findViewById(R.id.bwoollen);
		others= (Button) findViewById(R.id.bothers);
		woolen_nos=(EditText) findViewById(R.id.etwoolennumber);
		other_nos=(EditText) findViewById(R.id.etothernumber);
		
		shirt_nos.setText(String.valueOf(s_no));
		trouser_nos.setText(String.valueOf(t_no));
		woolen_nos.setText(String.valueOf(w_no));
		other_nos.setText(String.valueOf(o_no));
		
		shirt.setOnClickListener(this);
		trouser.setOnClickListener(this);
		woolens.setOnClickListener(this);
		donate.setOnClickListener(this);
		others.setOnClickListener(this);		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bshirt:
			
			s_no++;
			shirt_nos.setText(String.valueOf(s_no));
			break;

		case R.id.btrouser:
			
			t_no++;
			trouser_nos.setText(String.valueOf(t_no));
			break;

		case R.id.bwoollen:
			
			w_no++;
			woolen_nos.setText(String.valueOf(w_no));
			break;

		case R.id.bothers:
			
			o_no++;
			other_nos.setText(String.valueOf(o_no));
			break;
		
		case R.id.bdonate:
			
			o_no=Integer.parseInt(other_nos.getText().toString());
			s_no=Integer.parseInt(shirt_nos.getText().toString());
			t_no=Integer.parseInt(trouser_nos.getText().toString());
			w_no=Integer.parseInt(woolen_nos.getText().toString());
		
			//parse code here to add number of cloths with their types are donated
			
			editor.putInt("shirt_number", 0);
			editor.putInt("trouser_number", 0);
			editor.putInt("woollen_number",0);
			editor.putInt("other_number", 0);
		
			shirt_nos.setText("0");
			trouser_nos.setText("0");
			woolen_nos.setText("0");
			other_nos.setText("0");
			
			editor.commit();
			
			Toast.makeText(getApplicationContext(), "made", Toast.LENGTH_SHORT).show();
		default:
			break;
		}
	}

	
	@Override
	protected void onPause()
	{
		super.onPause();
		s_no=Integer.parseInt(shirt_nos.getText().toString());
		editor.putInt("shirt_number", s_no);

		w_no = Integer.parseInt(woolen_nos.getText().toString());
		editor.putInt("woollen_number", w_no);
		
		t_no = Integer.parseInt(trouser_nos.getText().toString());
		editor.putInt("trouser_number", t_no);
		
		o_no = Integer.parseInt(other_nos.getText().toString());
		editor.putInt("other_number", o_no);
	    	
		editor.commit();
	}
	
}
