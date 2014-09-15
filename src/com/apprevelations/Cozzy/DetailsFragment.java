package com.apprevelations.Cozzy;

import com.apprevelations.widget.AppRevelationsModifiedProfilePictureView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
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
import android.widget.EditText;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
	
	private AppRevelationsModifiedProfilePictureView profilePictureView;
	private EditText userNameView;
	private EditText userEmailId;
	private EditText userAddress;
	
	private static final int REAUTH_ACTIVITY_CODE = 100;
	
	private UiLifecycleHelper uiHelper;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(final Session session, final SessionState state, final Exception exception) {
	        onSessionStateChange(session, state, exception);
	    
	        if (state.isOpened()) {
	        	
	        } else if (state.isClosed()) {
            // 	Logged out
	        }
	    }
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView = inflater.inflate(R.layout.fragment_details, container, false);
		
		profilePictureView = 
				(AppRevelationsModifiedProfilePictureView)
					rootView.findViewById(R.id.selection_profile_pic);
		
		userNameView = (EditText) rootView.findViewById(R.id.username);
		userEmailId = (EditText) rootView.findViewById(R.id.email_id);
		userAddress = (EditText) rootView.findViewById(R.id.address);
		
//		LoginButton authButton = (LoginButton) rootView.findViewById(R.id.authButton);
//	    authButton.setFragment(this);
		
		return rootView;
	}



	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		
		if (session != null && session.isOpened()) {
	        // Get the user's data.
	        makeMeRequest(session);
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
	                    profilePictureView.setProfileId(user.getId());
	                    // Set the Textview's text to the user's name.
	                    
	                    if(user.getName() != null) {
	                    	userNameView.setText(user.getName());
	                    }
	                    if(user.getProperty("email") != null) {
	                    	userEmailId.setText(user.getProperty("email").toString());
	                    }
	                    if(user.getLocation() != null) {
	                    	userAddress.setText(user.getLocation().toString());
	                    }
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
		
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		uiHelper.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(getActivity(), requestCode, resultCode, data);
	    if (requestCode == REAUTH_ACTIVITY_CODE) {
	        uiHelper.onActivityResult(requestCode, resultCode, data);
	    }
	}


}
