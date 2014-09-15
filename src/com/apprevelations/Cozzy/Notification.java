package com.apprevelations.Cozzy;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apprevelations.widget.AppRevelationsModifiedProfilePictureView;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class Notification extends ActionBarActivity {

	int pp=0;
	LinearLayout mainlayout, commentlayout;
	LinearLayout rl;
	Button post;
	EditText postcontent;
	TextView tvname, tvdate, tvcontent, tvcommentname, tvcommentdate,
			tvcommentcontent, tvtag;
	ImageView userPic,posttag,postdone;
	AppRevelationsModifiedProfilePictureView ivprofilepic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		mainlayout = (LinearLayout) findViewById(R.id.llmain);
		post= (Button) findViewById(R.id.bpost);
		post.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
			     Bundle params = new Bundle();

		            //params.putString("tags", tagged_friends_id);

		             WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(Notification.this, Session.getActiveSession(),params))
		                        .setOnCompleteListener(new OnCompleteListener() {

		                        @Override
		                        public void onComplete(Bundle values, FacebookException error) {
		                            if (error == null) {
		                                final String postId = values.getString("post_id");
		                                if (postId != null) {
		                                    Toast.makeText(Notification.this,"Succesfully posted ", Toast.LENGTH_SHORT).show();
		                                } 
		                                else {
		                                    // User clicked the Cancel button
		                                    Toast.makeText(Notification.this,  "Publish cancelled", Toast.LENGTH_SHORT).show();
		                                }
		                            } 
		                            else if (error instanceof FacebookOperationCanceledException) {
		                                // User clicked the "x" button
		                                Toast.makeText(Notification.this,  "Publish cancelled", Toast.LENGTH_SHORT).show();
		                            } 
		                            else {
		                                // Generic, ex: network error
		                                Toast.makeText(Notification.this, "Error posting story", Toast.LENGTH_SHORT).show();
		                            }
		                        }

		                    }).build();
		                feedDialog.show();
				
			
			/*
					final Dialog dialog = new Dialog(Notification.this, android.R.style.Theme_Light);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.post_dialog);
					postcontent= (EditText) dialog.findViewById(R.id.etpostcontent);
					postdone= (ImageView) dialog.findViewById(R.id.ivpost);
					posttag= (ImageView) dialog.findViewById(R.id.ivtag);
					dialog.show();
					
					postdone.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(!Session.getActiveSession().isPermissionGranted("publish_actions")){
								Session.NewPermissionsRequest newPermissionsRequest1 = new Session
									.NewPermissionsRequest(Notification.this, Arrays.asList("publish_actions"));
						    	Session.getActiveSession().requestNewPublishPermissions(newPermissionsRequest1);
							} else {
							makepost();
						}
						}
					});
					
					
					posttag.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(!Session.getActiveSession().isPermissionGranted("user_friends")){
								Session.NewPermissionsRequest newPermissionsRequest2 = new Session
									.NewPermissionsRequest(Notification.this, Arrays.asList("user_friends"));
						    	Session.getActiveSession().requestNewPublishPermissions(newPermissionsRequest2);
							} else {
								
								getfriends();
								}
						}
					});
				*/	
						
			}
		});
		if (!Session.getActiveSession().isPermissionGranted("read_stream")) {
			Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(
					this, Arrays.asList("read_stream"));
			Session.getActiveSession().requestNewPublishPermissions(
					newPermissionsRequest);
		} else {
			getdesiredpost();
		}
	}

	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);

		if (Session.getActiveSession().isPermissionGranted("read_stream")) {

			getdesiredpost();

		}

		

		if(Session.getActiveSession().isPermissionGranted("publish_actions"))
		{
			makepost();
			
		}
		if(Session.getActiveSession().isPermissionGranted("user_friends"))
		{
			getfriends();
		}
		
	}

	private void getdesiredpost()
	{
		
		new Request(Session.getActiveSession(), "/me/tagged", null,
				HttpMethod.GET, new Request.Callback() {
					public void onCompleted(Response response) {
						/* handle the result */
						
						
						// response.getGraphObject();

						JSONObject json = response.getGraphObject()
								.getInnerJSONObject();
						try {
							JSONArray dataarray = json.getJSONArray("data");
							
							for(int w=0;w<dataarray.length();w++)
							{

								JSONObject data= dataarray.getJSONObject(w);
							String message = data.getString("message");
							String a=message;
							String poster_id,poster_name,time;
							if (message.toLowerCase().contains("tree")) {
								
								createdymanic(pp++);
								tvcontent.setText(a);
								JSONObject from=data.getJSONObject("from");
								poster_id=from.getString("id");
								
								//use this id to fetch user pic
								//makeMeRequest(Session.getActiveSession(), ivprofilepic);
								ivprofilepic.setProfileId(poster_id);
								
								poster_name=from.getString("name");
								tvname.setText(poster_name);
								
								time=data.getString("created_time");
								tvdate.setText(time);
								
								JSONObject to= data.getJSONObject("to");
								JSONArray to_data= to.getJSONArray("data");
								String tagname="";
								
								for(int q=0;q<to_data.length();q++)
								{
									JSONObject tagobj= to_data.getJSONObject(q);
									tagname.concat(tagobj.getString("name")+" ");
									
								}
								tvtag.setText(tagname);
								
							}	
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}).executeAsync();
	}
	
	private void makepost()
	{
		Bundle params = new Bundle();
		String msg=postcontent.getText().toString();
		params.putString("message", msg);
		/* make the API call */
		new Request(
		    Session.getActiveSession(),
		    "/me/feed",
		    params,
		    HttpMethod.POST,
		    new Request.Callback() {
		        public void onCompleted(Response response) {
		            /* handle the result */
		        	
		        }
		    }
		).executeAsync();

	}
	
	private void getfriends()
	{
		new Request(
				Session.getActiveSession(),
			    "/me/taggable_friends",
			    null,
			    HttpMethod.GET,
			    new Request.Callback() {
			        public void onCompleted(Response response) {
			            /* handle the result */


						JSONObject json = response.getGraphObject()
								.getInnerJSONObject();
						
			        	Intent i= new Intent(Notification.this, GetFriendList.class);
			        	i.putExtra("jsonvalue", json.toString());
			        	
			        	startActivity(i);

			        	Log.d("tag json feed", response.toString());
			        	
			        }
			    }
			).executeAsync();	
	
	}
	
	
	
	void createdymanic(int a) {

		rl = new LinearLayout(Notification.this);
		rl.setOrientation(LinearLayout.VERTICAL);
		rl.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		rlp.setMargins(15, 15, 15, 15);
		
		
		LinearLayout ll1 = new LinearLayout(this);
		ll1.setOrientation(LinearLayout.HORIZONTAL);

		
		ivprofilepic = new AppRevelationsModifiedProfilePictureView(this);
		//ivprofilepic = new ImageView(this);
		ivprofilepic.setId(1+a);
		ivprofilepic.setPresetSize(AppRevelationsModifiedProfilePictureView.SMALL);
		
		LinearLayout ll12 = new LinearLayout(this);
		ll12.setOrientation(LinearLayout.VERTICAL);
		
		tvname = new TextView(this);
		LinearLayout.LayoutParams tvname_param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tvname.setText("Name");
		tvname.setLayoutParams(tvname_param);
		
		tvdate = new TextView(this);
		LinearLayout.LayoutParams tvdate_param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tvdate.setText("Date");
		tvdate.setLayoutParams(tvdate_param);
		
		

		
		tvcontent = new TextView(this);
		tvcontent.setText("Content");

		LinearLayout.LayoutParams tvcontent_param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tvcontent_param.setMargins(30, 10, 0, 0);
		tvcontent.setLayoutParams(tvcontent_param);
		
		
		tvtag = new TextView(this);
		tvtag.setText("TV Tag");

		LinearLayout.LayoutParams tvtag_param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		tvcontent_param.setMargins(00, 10, 0, 0);
		tvcontent.setLayoutParams(tvtag_param);
		
		
		ll12.addView(tvname, 0);
		ll12.addView(tvdate, 1);
		ll12.addView(tvtag, 2);
		ll12.addView(tvcontent, 3);
		

		ll1.addView(ivprofilepic, 0);
		ll1.addView(ll12, 1);
		rl.addView(ll1, 0);
		mainlayout.addView(rl, 0, rlp);

	}

/*	
	private void makeMeRequest(final Session session, final AppRevelationsModifiedProfilePictureView i) {
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
	                	i.setProfileId(user.getId());
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }
	    });
	    request.executeAsync();
	}
*/
}
