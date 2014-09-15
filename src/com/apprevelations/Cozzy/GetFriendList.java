package com.apprevelations.Cozzy;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

public class GetFriendList extends ActionBarActivity {

	ArrayList<String> myArray;
	CheckBox ch;
	ArrayList<PInfo> apps;
	List<PInfo> listCity;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getfriendlist);

		ActionBar actionbar= getSupportActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C4CD")));

	
		Intent intent = getIntent();
		String jsondata = intent.getStringExtra("jsonvalue");
		listCity = new ArrayList<PInfo>();
	
		
		try {
			JSONObject json= new JSONObject(jsondata);
			JSONArray dataarray = json.getJSONArray("data");
			
			for(int w=0;w<dataarray.length();w++)
			{

				JSONObject data= dataarray.getJSONObject(w);
				PInfo a= new PInfo();
				a.userid = data.getString("id");
				a.username= data.getString("name");
				
				listCity.add(a);
			
			}	
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		 lv = (ListView) findViewById(R.id.lvlist);
		
		 
		lv.setAdapter(new CustomAdapterForList(getApplicationContext(), R.layout.list_row, listCity));
		
		
		
		
	
			}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_done) {
			


			for (int i = 0; i < lv.getCount(); i++) {
		        View v = lv.getAdapter().getView(i, null, null);
		        ch = (CheckBox) v.findViewById(R.id.checkBox1);
		        
		        if(ch.isChecked())
		        {
		        	
		        }
		        else
		        {
		        }
		    }


			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	
}
