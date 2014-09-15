package com.apprevelations.Cozzy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;

public class ShouldCare extends Activity {

	    ImageView mImaView,mv2,mv1,mv3;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.test);
	        mImaView = (ImageView) findViewById(R.id.im1);
	        mv2=(ImageView) findViewById(R.id.im2);
	        mv1=(ImageView) findViewById(R.id.im3);
	        mv3=(ImageView) findViewById(R.id.im4);
	        Bitmap bimtBitmap = BitmapFactory.decodeResource(getResources(),
	                R.drawable.pic2);
	        mImaView.setImageBitmap(bimtBitmap);
	        Bitmap bi = BitmapFactory.decodeResource(getResources(),
	                R.drawable.pic1);
	        mv1.setImageBitmap(bi);
	        Bitmap bgi = BitmapFactory.decodeResource(getResources(),
	                R.drawable.pic3);
	        mv2.setImageBitmap(bgi);
	        Bitmap biu = BitmapFactory.decodeResource(getResources(),
	                R.drawable.pic4);
	        mv3.setImageBitmap(biu);
	        /**
	         * Use Simple ImageView
	         */
	        usingSimpleImage(mImaView);
	        usingSimpleImage(mv2);
	        usingSimpleImage(mv1);
	        usingSimpleImage(mv3);
	    }
	   

	    public void usingSimpleImage(ImageView imageView) {
	        ImageAttacher mAttacher = new ImageAttacher(imageView);
	        ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
	        ImageAttacher.MIN_ZOOM = 0.5f; // Half the current Size
	        MatrixChangeListener mMaListener = new MatrixChangeListener();
	        mAttacher.setOnMatrixChangeListener(mMaListener);
	        PhotoTapListener mPhotoTap = new PhotoTapListener();
	        mAttacher.setOnPhotoTapListener(mPhotoTap);
	    }

	    private class PhotoTapListener implements OnPhotoTapListener {

	        @Override
	        public void onPhotoTap(View view, float x, float y) {
	        }
	    }

	    private class MatrixChangeListener implements OnMatrixChangedListener {

	        @Override
	        public void onMatrixChanged(RectF rect) {

	        }
	    }
	}

