package com.apprevelations.Cozzy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

public class Home extends Fragment implements OnClickListener
{
	private FragmentActivity mainActivity;
	RelativeLayout r1,r2,rchallenge;
	 private static final int SWIPE_MIN_DISTANCE = 10;
	    private static final int SWIPE_THRESHOLD_VELOCITY = 18;
	    private ViewFlipper mViewFlipper;
	    private AnimationListener mAnimationListener;
	    private Context mContext;
	ImageView v1;
	
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	public Home(){
		setHasOptionsMenu(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.start1,container, false);
		r1=(RelativeLayout) view.findViewById(R.id.start);
		r2=(RelativeLayout) view.findViewById(R.id.relativeLayout1);
		rchallenge=(RelativeLayout) view.findViewById(R.id.rlchallenge);
		v1=(ImageView) view.findViewById(R.id.islidingmenu);
		   r1.setOnClickListener(this);
		   v1.setOnClickListener(this);
		   r2.setOnClickListener(this);
		   rchallenge.setOnClickListener(this);
		  mContext=getActivity();
	        mViewFlipper = (ViewFlipper) view.findViewById(R.id.view_flipper);
	        mViewFlipper.setAutoStart(true);
	        mViewFlipper.setFlipInterval(3000);
	        mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
	        mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
	        // controlling animation

	        mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
	        mViewFlipper.startFlipping();
	        mViewFlipper.setOnTouchListener(new OnTouchListener() {

	            @Override
	            public boolean onTouch(final View view, final MotionEvent event) {

	                detector.onTouchEvent(event);
	                return true;
	            }
	        });
	        mAnimationListener = new Animation.AnimationListener() {
	            public void onAnimationStart(Animation animation) {
	                //animation started event

	            }

	            public void onAnimationRepeat(Animation animation) {

	            }

	            public void onAnimationEnd(Animation animation) {
	                //TODO animation stopped event

	            }
	        };
		return view;
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (FragmentActivity) activity;
        //((MainActivity) activity).onSectionAttached(Integer.parseInt(this.ARG_SECTION_NUMBER));
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.start:
			 Intent intent = new Intent(getActivity(),ShouldCare.class); 
			 startActivity(intent); 
			break;
		case R.id.islidingmenu:
			Start.mDrawerLayout.openDrawer(Gravity.LEFT);
			break;
		case R.id.relativeLayout1:
			 
			 
			 SharedPreferences sp1 = getActivity().getSharedPreferences("Flag", 0);
			 int i1 = sp1.getInt("Flag rl1", 0);
			 
			 if(i1 == 0){
				 i1 = 1;
				 
				 Editor ed = sp1.edit();
				 ed.putInt("Flag rl1", i1).commit();
				 
				 Intent intentchallenge = new Intent(getActivity(), UserForm.class); 
				 startActivity(intentchallenge);
			 } else {
				 
				 
				 Intent intentchallenge = new Intent(getActivity(), Donation.class); 
				 startActivity(intentchallenge);
			 }
			 
			break;

		case R.id.rlchallenge:
			  
			 
			 SharedPreferences sp = getActivity().getSharedPreferences("Flag", 0);
			 int i = sp.getInt("Flag", 0);
			 
			 if(i == 0){
				 i = 1;
				 
				 Editor ed = sp.edit();
				 ed.putInt("Flag", i).commit();
				 
				 Intent intentchallenge = new Intent(getActivity(), TreeChallenge.class); 
				 startActivity(intentchallenge);
			 } else {
				 
				 
				 Intent intentchallenge = new Intent(getActivity(),Notification.class); 
				 startActivity(intentchallenge);
			 }
			 
			break;
			
		}
	}
	 class SwipeGestureDetector extends SimpleOnGestureListener {

	        @Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	            try {
	                // right to left swipe
					/**/


	                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

	                    mViewFlipper.stopFlipping();
	                    //mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
	                    //mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
	                    // controlling animation
	                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
	                    mViewFlipper.showNext();

	                    mViewFlipper.setAutoStart(true);
	                    mViewFlipper.setFlipInterval(3000);
	                    mViewFlipper.startFlipping();

	                    return true;
	                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

	                    mViewFlipper.stopFlipping();
	                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
	                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
	                    // controlling animation
	                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
	                    mViewFlipper.showPrevious();

	                    mViewFlipper.setAutoStart(true);
	                    mViewFlipper.setFlipInterval(3000);
	                    mViewFlipper.startFlipping();

	                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
	                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));

	                    return true;
	                }


	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        
     return true;
	        }
 }
}
