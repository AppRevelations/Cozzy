package com.apprevelations.Cozzy;

import android.app.Dialog;
import android.app.Notification;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAccount extends Fragment implements OnClickListener{

	ImageView im1,im2,im3;
	
	SharedPreferences sp;
	Editor ed;
	
	EditText et;
	TextView name, phone, address;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.userform, container,false);
		im1=(ImageView) view.findViewById(R.id.iname);
		im2=(ImageView) view.findViewById(R.id.iadd);
		im3=(ImageView) view.findViewById(R.id.ino);
		
		sp = getActivity().getSharedPreferences("User Details", 0);
		ed = sp.edit();
		
		name = (TextView) view.findViewById(R.id.textView1);
		phone = (TextView) view.findViewById(R.id.tphone);
		address = (TextView) view.findViewById(R.id.taddress);
		
		im1.setOnClickListener(this);
		im2.setOnClickListener(this);
		im3.setOnClickListener(this);
		
		String NAME = sp.getString("User Name", null);
		String PHONE = sp.getString("User Phone", null);
		String ADDRESS = sp.getString("User Address", null);
		
		if(NAME != null){
			name.setText(NAME);
		}
		if(PHONE != null){
			phone.setText(PHONE);
		}
		if(ADDRESS != null){
			address.setText(ADDRESS);
		}
		
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.iname:
			final Dialog dialog = new Dialog(getActivity()); 
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
			dialog.setContentView(R.layout.custom_dialog); 
			dialog.show();
			ImageView i1=(ImageView) dialog.findViewById(R.id.isubmit);
			ImageView i2=(ImageView) dialog.findViewById(R.id.iremove);
			et = (EditText) dialog.findViewById(R.id.editText1);
			i1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ed.putString("User Name", et.getText().toString());
					ed.commit();
					dialog.dismiss();
				}
			});
			i2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			break;
		case R.id.iadd:
			final Dialog dial = new Dialog(getActivity()); 
			dial.requestWindowFeature(Window.FEATURE_NO_TITLE); 
			dial.setContentView(R.layout.custom_dialog); 
			dial.show();
			ImageView ii1=(ImageView) dial.findViewById(R.id.isubmit);
			ImageView io2=(ImageView) dial.findViewById(R.id.iremove);
			et = (EditText) dial.findViewById(R.id.editText1);
			ii1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ed.putString("User Phone", et.getText().toString());
					ed.commit();
					dial.dismiss();
				}
			});
			io2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dial.cancel();
				}
			});
			break;
		case R.id.ino:
			final Dialog dialg = new Dialog(getActivity()); 
			dialg.requestWindowFeature(Window.FEATURE_NO_TITLE); 
			dialg.setContentView(R.layout.custom_dialog); 
			dialg.show();
			ImageView i=(ImageView) dialg.findViewById(R.id.isubmit);
			ImageView ie=(ImageView) dialg.findViewById(R.id.iremove);
			et = (EditText) dialg.findViewById(R.id.editText1);
			i.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ed.putString("User Address", et.getText().toString());
					ed.commit();
					dialg.dismiss();
				}
			});
			ie.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialg.cancel();
				}
			});
			break;
		}
			
	}
}
