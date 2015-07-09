package com.example.gitandroidtest.gesture;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

public class Activity_Gesture extends Activity {

	myViewforGestureTest myView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture);	
		myView = (myViewforGestureTest) findViewById(R.id.myViewforGestureTest);
	}

	

}
