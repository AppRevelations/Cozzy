package com.apprevelations.Cozzy;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, 
				"ozSFGg467aFtaFzm752FGeXQ1SaqjCozALAM5dix", 
				"sOL6dgLtTPHCvzNObOKPpyAHttCGsIGDfUtCJHWi");


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
