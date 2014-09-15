package com.apprevelations.Cozzy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Splash extends FragmentActivity  implements SplashFragment.FinishActivity {
	
	private SplashFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

/*		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
	    bar.setTitle("Profile");
*/	    
		
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new SplashFragment();
	        
	        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.frameLayout, mainFragment)
            .commit();
	        
		} else {
	        // Or set the fragment from restored state info
	        mainFragment = (SplashFragment) getSupportFragmentManager()
	        .findFragmentById(R.id.frameLayout);
	    }
	}

	@Override
	public void finishActivity() {
		// TODO Auto-generated method stub
		finish();
	}
	
	
}
