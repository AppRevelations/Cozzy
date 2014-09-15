package com.apprevelations.Cozzy;


import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.parse.ParseObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Donation extends Activity implements OnClickListener{

	ImageView tshirt,trouser,wollens,food,others,cart;
	Animation animdown,animdown1,animdown2,animdown3,animslide,animslide1,animslide2,animslide3;
	LinearLayout parent;
	
	EditText quantity;
	ImageView done, cancel;
	
	String userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.donation);
		
		Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            // Get the user's data
            makeMeRequest(session);
        }
		
		parent=(LinearLayout) findViewById(R.id.parent);
		tshirt=(ImageView)findViewById(R.id.itshirt);
		trouser=(ImageView)findViewById(R.id.itrousers);
		wollens=(ImageView)findViewById(R.id.iwollens);
		food=(ImageView)findViewById(R.id.ifood);
		others=(ImageView)findViewById(R.id.iothers);
		cart = (ImageView) findViewById(R.id.icart);
		
		animdown=AnimationUtils.loadAnimation(Donation.this, R.anim.bounce);
		animdown1=AnimationUtils.loadAnimation(Donation.this, R.anim.bounce);
		animdown2=AnimationUtils.loadAnimation(Donation.this, R.anim.bounce);
		animdown3=AnimationUtils.loadAnimation(Donation.this, R.anim.bounce);
		animslide=AnimationUtils.loadAnimation(Donation.this, R.anim.slidedown);
		animslide1=AnimationUtils.loadAnimation(Donation.this, R.anim.slidedown);
		animslide2=AnimationUtils.loadAnimation(Donation.this, R.anim.slidedown);
		animslide3=AnimationUtils.loadAnimation(Donation.this, R.anim.slidedown);
		
		cart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				tshirt.setVisibility(View.VISIBLE);
				trouser.setVisibility(View.VISIBLE);
				others.setVisibility(View.VISIBLE);
				food.setVisibility(View.VISIBLE);
				
				tshirt.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.tshirt_move));
				trouser.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.trouser_move));
				others.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.others_move));
				food.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.food_move));
			}
		});
		
		tshirt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final Dialog dialog = new Dialog(Donation.this); 
				dialog.setTitle("Shirts");
				dialog.setContentView(R.layout.post_dialog1); 
				quantity = (EditText) dialog.findViewById(R.id.quantity); 
				
				done = (ImageView) dialog.findViewById(R.id.done);
				cancel = (ImageView) dialog.findViewById(R.id.cancel);
				
				done.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Done", Toast.LENGTH_SHORT).show();
						
						ParseObject donators = new ParseObject("donators");
						donators.put("userid", userName);
						donators.put("type", "tshirt");
						donators.put("quantity", quantity.getText().toString());
						donators.saveInBackground();
						
						Animation a = AnimationUtils.loadAnimation(Donation.this, R.anim.tshirt_move);
						a.setInterpolator(new ReverseInterpolator());
						a.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								// TODO Auto-generated method stub
								//tshirt.setVisibility(View.INVISIBLE);
								tshirt.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.fade_in));
							}
						});
						dialog.dismiss();
						tshirt.startAnimation(a);
					}
				});
				
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Cancel", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				});
				
				dialog.show(); 
				
			}
		});
		
		trouser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final Dialog dialog = new Dialog(Donation.this); 
				dialog.setTitle("Trouser");
				dialog.setContentView(R.layout.post_dialog1); 
				quantity = (EditText) dialog.findViewById(R.id.quantity); 
				
				done = (ImageView) dialog.findViewById(R.id.done);
				cancel = (ImageView) dialog.findViewById(R.id.cancel);
				
				done.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Done", Toast.LENGTH_SHORT).show();
						
						ParseObject donators = new ParseObject("donators");
						donators.put("userid", userName);
						donators.put("type", "trousers");
						donators.put("quantity", quantity.getText().toString());
						donators.saveInBackground();
						
						Animation a = AnimationUtils.loadAnimation(Donation.this, R.anim.trouser_move);
						a.setInterpolator(new ReverseInterpolator());
						a.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								// TODO Auto-generated method stub
								//tshirt.setVisibility(View.INVISIBLE);
								trouser.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.fade_in));
							}
						});
						dialog.dismiss();
						trouser.startAnimation(a);
					}
				});
				
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Cancel", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				});
				
				dialog.show(); 
				
			}
		});

		others.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final Dialog dialog = new Dialog(Donation.this); 
				dialog.setTitle("Others"); 
				dialog.setContentView(R.layout.post_dialog1); 
				quantity = (EditText) dialog.findViewById(R.id.quantity); 
				
				done = (ImageView) dialog.findViewById(R.id.done);
				cancel = (ImageView) dialog.findViewById(R.id.cancel);
				
				done.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Done", Toast.LENGTH_SHORT).show();
						
						ParseObject donators = new ParseObject("donators");
						donators.put("userid", userName);
						donators.put("type", "others");
						donators.put("quantity", quantity.getText().toString());
						donators.saveInBackground();
						
						Animation a = AnimationUtils.loadAnimation(Donation.this, R.anim.others_move);
						a.setInterpolator(new ReverseInterpolator());
						a.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								// TODO Auto-generated method stub
								//tshirt.setVisibility(View.INVISIBLE);
								others.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.fade_in));
							}
						});
						dialog.dismiss();
						others.startAnimation(a);
					}
				});
				
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Cancel", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				});
				
				dialog.show(); 
				
			}
		});
		
		food.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final Dialog dialog = new Dialog(Donation.this); 
				dialog.setTitle("Food"); 
				dialog.setContentView(R.layout.post_dialog1); 
				quantity = (EditText) dialog.findViewById(R.id.quantity); 
				
				done = (ImageView) dialog.findViewById(R.id.done);
				cancel = (ImageView) dialog.findViewById(R.id.cancel);
				
				done.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Done", Toast.LENGTH_SHORT).show();
						
						ParseObject donators = new ParseObject("donators");
						donators.put("userid", userName);
						donators.put("type", "food");
						donators.put("quantity", quantity.getText().toString());
						donators.saveInBackground();
						
						Animation a = AnimationUtils.loadAnimation(Donation.this, R.anim.food_move);
						a.setInterpolator(new ReverseInterpolator());
						a.setAnimationListener(new AnimationListener() {
							
							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animation animation) {
								// TODO Auto-generated method stub
								//tshirt.setVisibility(View.INVISIBLE);
								food.startAnimation(AnimationUtils.loadAnimation(Donation.this, R.anim.fade_in));
							}
						});
						dialog.dismiss();
						food.startAnimation(a);
					}
				});
				
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(Donation.this, "Cancel", Toast.LENGTH_SHORT).show();
						dialog.dismiss();
					}
				});
				
				dialog.show(); 
				
			}
		});
	}
	
	public class ReverseInterpolator implements Interpolator {
	    @Override
	    public float getInterpolation(float paramFloat) {
	        return Math.abs(paramFloat -1f);
	    }
	}
	
/*		
		animdown.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				tshirt.setOnClickListener(Donation.this);
				tshirt.clearAnimation();
				trouser.setVisibility(View.VISIBLE);
			    trouser.startAnimation(animslide);
			}
		});
		animslide.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				trouser.clearAnimation();
				trouser.startAnimation(animdown1);
				wollens.setVisibility(View.VISIBLE);
				wollens.startAnimation(animslide1);
			}
		});
         animslide1.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				wollens.clearAnimation();
				wollens.startAnimation(animdown2);
 				food.setVisibility(View.VISIBLE);
 				food.startAnimation(animslide2);
			}
		});
         animslide2.setAnimationListener(new AnimationListener() {
  			
  			@Override
  			public void onAnimationStart(Animation animation) {
  				// TODO Auto-generated method stub
  				
  			}
  			
  			@Override
  			public void onAnimationRepeat(Animation animation) {
  				// TODO Auto-generated method stub
  				
  			}
  			
  			@Override
  			public void onAnimationEnd(Animation animation) {
  				// TODO Auto-generated method stub
  				food.clearAnimation();
  				food.startAnimation(animdown3);
  				others.setVisibility(View.VISIBLE);
 				others.startAnimation(animslide2);
  			}
  		});
         animslide3.setAnimationListener(new AnimationListener() {
 			
 			@Override
 			public void onAnimationStart(Animation animation) {
 				// TODO Auto-generated method stub
 				
 			}
 			
 			@Override
 			public void onAnimationRepeat(Animation animation) {
 				// TODO Auto-generated method stub
 				
 			}
 			
 			@Override
 			public void onAnimationEnd(Animation animation) {
 				// TODO Auto-generated method stub
 				others.clearAnimation();
 				others.startAnimation(animdown3);
 				cart.setVisibility(View.VISIBLE);
 			}
 		});
		tshirt.startAnimation(animdown);
	}
*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.itshirt: 
			Animation a=AnimationUtils.loadAnimation(Donation.this, R.anim.tshirt_move);
			a.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					tshirt.setVisibility(View.INVISIBLE);
					tshirt.clearAnimation();
					
				}
			});
			tshirt.bringToFront();
			cart.bringToFront();
			parent.invalidate();
			
			tshirt.startAnimation(a);
			
			    break;
		case R.id.itrousers:
			     
			break;
		}

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
//	                    profilePictureView.setProfileId(user.getId());
	                    // Set the Textview's text to the user's name.
//	                    userNameView.setText(user.getName());
	                	
	                	userName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
	                	
	                	if(user.getFirstName() != null){
	                		userName.concat(user.getFirstName());
	                	}
	                	if(user.getMiddleName() != null){
	                		userName.concat(user.getMiddleName());
	                	}
	                	if(user.getLastName() != null){
	                		userName.concat(user.getLastName());
	                	}
	                	if(userName == null){
//	                		Toast.makeText(Donation.this, "", Toast.LENGTH_SHORT).show();
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
	
}
