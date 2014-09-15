package com.apprevelations.Cozzy;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashFragment extends Fragment {
	
	FinishActivity mCallback;
	
	private LoginButton loginButton;
	
	private static final int REAUTH_ACTIVITY_CODE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}	

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
		
		loginButton = (LoginButton) rootView.findViewById(R.id.authButton);
//		loginButton.setReadPermissions(Arrays.asList("email", "user_location"));
		loginButton.setFragment(this);
		
		Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            // Get the user's data
//            makeMeRequest(session);
            Intent i = new Intent(getActivity(), MainActivity1.class);
            startActivity(i);
            
            mCallback.finishActivity();
        }
		
		return rootView;
	}

	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		
		if (session != null && session.isOpened()) {
	        // Get the user's data.
	        makeMeRequest(session);
	        
	        Intent i = new Intent(getActivity(), MainActivity1.class);
	        startActivity(i);
	        mCallback.finishActivity();
	    }
	}
	
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Set the id for the ProfilePictureView
	                    // view that in turn displays the profile picture.
//	                    profilePictureView.setProfileId(user.getId());
	                    // Set the Textview's text to the user's name.
//	                    userNameView.setText(user.getName());
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }
	    });
	    request.executeAsync();
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

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try {
            mCallback = (FinishActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FinishActivity");
        }
	}
	
	public interface FinishActivity {
		void finishActivity();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
	    if (requestCode == REAUTH_ACTIVITY_CODE) {
//	        Intent i = new Intent(getActivity(), Details.class);
//	        startActivity(i);
	        
//	        mCallback.finishActivity();
	    }
	}

}
