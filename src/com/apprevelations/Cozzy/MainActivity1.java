package com.apprevelations.Cozzy;

import java.util.Arrays;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity1 extends ActionBarActivity implements OnClickListener {

	
	private Button food,cloths,books;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		
		food=(Button) findViewById(R.id.bfood);
		cloths=(Button) findViewById(R.id.bcloths);
		books=(Button) findViewById(R.id.bbooks);
		
		food.setOnClickListener(this);
		cloths.setOnClickListener(this);
		books.setOnClickListener(this);
		Intent i= new Intent(MainActivity1.this, Notification.class);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch (v.getId()) {
		case R.id.bbooks:
			
			if(!Session.getActiveSession().isPermissionGranted("publish_actions")){
				Session.NewPermissionsRequest newPermissionsRequest = new Session
					.NewPermissionsRequest(this, Arrays.asList("publish_actions"));
		    	Session.getActiveSession().requestNewPublishPermissions(newPermissionsRequest);
			} else {
				Bundle params = new Bundle();
				params.putString("message", "This is a new message");
				/* make the API call */
				new Request(
				    Session.getActiveSession(),
				    "/me/feed",
				    params,
				    HttpMethod.POST,
				    new Request.Callback() {
				        public void onCompleted(Response response) {
				            /* handle the result */
				        	Toast.makeText(MainActivity1.this, response.toString(), Toast.LENGTH_LONG).show();
				        }
				    }
				).executeAsync();
			}
			
//			i=new Intent(MainActivity.this, Book.class);
//			startActivity(i);
			break;
			
		case R.id.bfood:
			i=new Intent(MainActivity1.this, Food.class);
			startActivity(i);
			break;
			
		case R.id.bcloths:
			i=new Intent(MainActivity1.this, Cloths.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		
		if(Session.getActiveSession().isPermissionGranted("publish_actions"))
		{
			Bundle params = new Bundle();
			params.putString("message", "This is message bro");
			/* make the API call */
			new Request(
			    Session.getActiveSession(),
			    "/me/feed",
			    params,
			    HttpMethod.POST,
			    new Request.Callback() {
			        public void onCompleted(Response response) {
			            /* handle the result */
			        	Toast.makeText(MainActivity1.this, response.toString(), Toast.LENGTH_LONG).show();
			        }
			    }
			).executeAsync();
			
		}
		
	}

	
	
}
