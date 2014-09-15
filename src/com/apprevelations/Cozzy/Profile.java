package com.apprevelations.Cozzy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Profile extends FragmentActivity {

	private ProfileFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

/*		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
	    bar.setTitle("Profile");
*/	    
		
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new ProfileFragment();
	        
	        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.frameLayout, mainFragment)
            .commit();
	        
		} else {
	        // Or set the fragment from restored state info
	        mainFragment = (ProfileFragment) getSupportFragmentManager()
	        .findFragmentById(R.id.frameLayout);
	    }
		
/*
	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new ProfileFragment();
	        
	        findViewById(android.R.id.content).post(new Runnable() {
	            public void run() {
	              if (getSupportFragmentManager().findFragmentById((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? android.R.id.content : R.id.action_bar_activity_content)) == null) {
	                getSupportFragmentManager()
	                .beginTransaction()
	                .add((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? android.R.id.content : R.id.action_bar_activity_content), mainFragment)
	                .commit();
	              }
	            }
	          });
	        
//	        getSupportFragmentManager()
//	        .beginTransaction()
//	        .add((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? android.R.id.content : R.id.action_bar_activity_content), mainFragment)
//	        .commit();

	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (ProfileFragment) getSupportFragmentManager()
	        .findFragmentById((Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? android.R.id.content : R.id.action_bar_activity_content));
	    }
*/

	}

    

}
