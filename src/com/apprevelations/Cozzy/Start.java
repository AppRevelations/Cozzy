package com.apprevelations.Cozzy;

import com.apprevelations.Cozzy.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.apprevelations.widget.AppRevelationsModifiedProfilePictureView;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends ActionBarActivity implements
		NavigationDrawerCallbacks {

	/*
	 * A bug was introduced in Android 4.3 that ignores changes to the Canvas
	 * state between multiple calls to super.dispatchDraw() when running with
	 * hardware acceleration. To account for this bug, a slightly different
	 * approach was taken to fold a static image whereby a bitmap of the
	 * original contents is captured and drawn in segments onto the canvas.
	 * However, this method does not permit the folding of a TextureView hosting
	 * a live camera feed which continuously updates. Furthermore, the sepia
	 * effect was removed from the bitmap variation of the demo to simplify the
	 * logic when running with this workaround."
	 */
	static final boolean IS_JBMR2 = Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2;
	static final boolean IS_ISC = Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	static final boolean IS_GINGERBREAD_MR1 = Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD_MR1;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	static FoldingDrawerLayout mDrawerLayout;
	static FrameLayout mContainer;

	private AppRevelationsModifiedProfilePictureView profilePictureView;
	private TextView userNameView;
	private TextView emailId;

	private static final int REAUTH_ACTIVITY_CODE = 100;

	private UiLifecycleHelper uiHelper;

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(final Session session, final SessionState state,
				final Exception exception) {
			onSessionStateChange(session, state, exception);

			if (state.isOpened()) {

			} else if (state.isClosed()) {
				// Logged out
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.start);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		userNameView = (TextView) findViewById(R.id.textView2);
		emailId = (TextView) findViewById(R.id.textView3);

		profilePictureView = (AppRevelationsModifiedProfilePictureView) findViewById(R.id.selection_profile_pic);
		profilePictureView.setCropped(true);

		Session session = Session.getActiveSession();
		if (session != null && session.isOpened()) {
			// Get the user's data
			makeMeRequest(session);
		}

		mDrawerLayout = (FoldingDrawerLayout) findViewById(R.id.drawer_layout);
		mContainer = (FrameLayout) findViewById(R.id.container);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment
				.setUp(R.id.navigation_drawer, (mDrawerLayout));

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = null;

		switch (position) {
		case R.id.navigation_drawer_item_0:
			// if(flag){
			fragment = new Home();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();
			// flag = false;
			// }
			break;

		case R.id.navigation_drawer_item_1:
			// To be replaced afterwards with the fragment
			fragment = new MyAccount();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();
			break;

		case R.id.navigation_drawer_item_2:
			// To be replaced afterwards with the fragment
			Toast.makeText(Start.this, "Coming Soon!!!", Toast.LENGTH_SHORT)
					.show();
			// fragment = new MapFragment();
			break;

		case R.id.navigation_drawer_item_3:
			// To be replaced afterwards with the fragment
			Toast.makeText(Start.this, "Coming Soon!!!", Toast.LENGTH_SHORT)
					.show();
			// fragment = new MapFragment();
			break;

		case R.id.navigation_drawer_item_4:
			// To be replaced afterwards with the fragment
			fragment = new AboutCozzy();
			fragmentManager.beginTransaction()
					.replace(R.id.container, fragment).commit();
			break;

		/*
		 * case R.id.navigation_drawer_item_5 : //To be replaced afterwards with
		 * the fragment Toast.makeText(Start.this, "Coming Soon!!!",
		 * Toast.LENGTH_SHORT).show(); //fragment = new MapFragment(); break;
		 * 
		 * case R.id.navigation_drawer_item_6 : //To be replaced afterwards with
		 * the fragment //Toast.makeText(Start.this, "Coming Soon!!!",
		 * Toast.LENGTH_SHORT).show(); Intent settingsActivityIntent = new
		 * Intent(Start.this, com.example.dtumap.SettingsActivity.class);
		 * startActivity(settingsActivityIntent); //fragment = new
		 * MapFragment(); break;
		 */
		default:
			break;
		}

		// fragmentManager.beginTransaction()
		// .replace(R.id.container, fragment)
		// .commit();
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {

		if (session != null && session.isOpened()) {
			// Get the user's data.
			makeMeRequest(session);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		// For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
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
								// view that in turn displays the profile
								// picture.
								profilePictureView.setProfileId(user.getId());
								// Set the Textview's text to the user's name.
								userNameView.setText(user.getName());
								userNameView.setVisibility(View.VISIBLE);
//								emailId.setText(user.get);
//								emailId.setVisibility(View.VISIBLE);
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
		if (requestCode == REAUTH_ACTIVITY_CODE) {
			uiHelper.onActivityResult(requestCode, resultCode, data);
		}
	}
}
