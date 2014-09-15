package com.apprevelations.Cozzy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Details extends FragmentActivity {
	
	private DetailsFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);

/*		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
	    bar.setTitle("Profile");
*/	    
		
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new DetailsFragment();
	        
	        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.frameLayout, mainFragment)
            .commit();
	        
		} else {
	        // Or set the fragment from restored state info
	        mainFragment = (DetailsFragment) getSupportFragmentManager()
	        .findFragmentById(R.id.frameLayout);
	    }
	}
}
