/*
 * Copyright (C) 2013 Priboi Tiberiu
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apprevelations.Cozzy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/**
 * FoldingDrawerLayout change the sliding effect with folding effect of
 * DrawerLayout
 * 
 */
public class FoldingDrawerLayout extends DrawerLayout {
	
	Boolean flag = false;

	public FoldingDrawerLayout(Context context) {
		super(context);
	}

/*	@Override
	public boolean onTouchEvent(MotionEvent me) {
		// TODO Auto-generated method stub
//		if(isDrawerVisible(MainActivity.mDrawerLayout)){
//			MainActivity.mScrollGestureDetector.onTouchEvent(me);
//		}
		return super.onTouchEvent(me);
	}
*/

	public FoldingDrawerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FoldingDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

/*	@Override
	public boolean onInterceptTouchEvent(MotionEvent me) {
		// TODO Auto-generated method stub
		flag = true;
		onTouchEvent(me);
		flag = false;
		//MainActivity.mScrollGestureDetector.onTouchEvent(me);
		return super.onInterceptTouchEvent(me);
	}
*/
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = getChildAt(i);
			if (isDrawerView2(child)) {
				System.out.println("at" + i);
				BaseFoldingLayout foldingNavigationLayout = new BaseFoldingLayout(
						getContext());
				foldingNavigationLayout.setAnchorFactor(1);
				removeView(child);
				foldingNavigationLayout.addView(child);
				ViewGroup.LayoutParams layPar = child.getLayoutParams();
				addView(foldingNavigationLayout, i, layPar);
			}

		}

	}

	public BaseFoldingLayout getFoldingLayout(View drawerView) {
		if (!isDrawerView2(getRealDrawer(drawerView))) {
			throw new IllegalArgumentException("View " + drawerView
					+ " is not a sliding drawer");
		}
		
		return isFoldingLayout(getRealDrawer(drawerView))?(BaseFoldingLayout)getRealDrawer(drawerView):null;
	}

	boolean isDrawerView2(View child) {
		final int gravity = ((LayoutParams) child.getLayoutParams()).gravity;
		final int absGravity = GravityCompat.getAbsoluteGravity(gravity,
				ViewCompat.getLayoutDirection(child));
		return (absGravity & (Gravity.LEFT | Gravity.RIGHT)) != 0;
	}

	/**
	 * Close the specified drawer view by animating it into view.
	 * 
	 * @param drawerView
	 *            Drawer view to close
	 */
	public void closeDrawer(View drawerView) {

		super.closeDrawer(getRealDrawer(drawerView));
	}

	private View getRealDrawer(View drawerView) {
		View drawerView2 = (View) drawerView.getParent();
		if (isFoldingLayout(drawerView2)) {
			return drawerView2;
		} else {
			return drawerView;
		}

	}
	
	private boolean isFoldingLayout(View drawerView)
	{
		return drawerView instanceof BaseFoldingLayout;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
	private void translateX(float slideOffset){
		float x = getResources().getDimension(R.dimen.navigation_drawer_width);
		Start.mContainer.setTranslationX(slideOffset * x);
	}

}