package com.apprevelations.Cozzy;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.LoginButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {
	
	private LoginButton login;
	
	private static final int REAUTH_ACTIVITY_CODE = 100;

	RelativeLayout rl;
	 private static final int TIME = 7000;// 4 seconds 
	 @Override protected void onCreate(Bundle savedInstanceState) 
	 { 
		 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 super.onCreate(savedInstanceState); 
		 setContentView(R.layout.activity_main); 
		 rl=(RelativeLayout)findViewById(R.id.Rl);
		 
		 login = (LoginButton) findViewById(R.id.authButton);
		 
		 Session session = Session.getActiveSession();
	        if (session != null && session.isOpened()) {
	            // Get the user's data
//	            makeMeRequest(session);
	        	overridePendingTransition(R.anim.blink_reverse,R.anim.blink);
	            Intent i = new Intent(this, Start.class);
	            startActivity(i);
	            finish();
	        }

//		 overridePendingTransition(R.anim.blink_reverse,R.anim.blink); 
	 }
	 
	 private void onSessionStateChange(Session session, SessionState state, Exception exception) {
			
			if (session != null && session.isOpened()) {
		        // Get the user's data.
//		        makeMeRequest(session);
				overridePendingTransition(R.anim.blink_reverse,R.anim.blink);
		        Intent i = new Intent(this, Start.class);
		        startActivity(i);
		        finish();
		        
		    }
		}
	 
	 @Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
			// For scenarios where the main activity is launched and user
		    // session is not null, the session state change notification
		    // may not be triggered. Trigger it if it's open/closed.
		    Session session = Session.getActiveSession();
		    if (session != null &&
		           (session.isOpened() || session.isClosed()) ) {
		        onSessionStateChange(session, session.getState(), null);
		    }
			
		}
	 
	 @Override public void onBackPressed()
	 { this.finish(); super.onBackPressed(); } 
	 
	 @Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		    if (requestCode == REAUTH_ACTIVITY_CODE) {
//		        Intent i = new Intent(getActivity(), Details.class);
//		        startActivity(i);
		        
//		        mCallback.finishActivity();
		    }
		}
}
