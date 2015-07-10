package com.example.gitandroidtest.gesture;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;

public class Activity_Gesture extends Activity {

	myViewforGestureTest myView;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture);	
		myView = (myViewforGestureTest) findViewById(R.id.myViewforGestureTest);
		btn=(Button) findViewById(R.id.btn_gesture);
		View view=findViewById(R.id.LinearLayout_gesture);
		view.post(new Runnable() {
			
			@Override
			public void run() {
				Rect outRect=new Rect();
				btn.getHitRect(outRect);
				outRect.right+=100;
				outRect.bottom+=100;
				TouchDelegate tDelegate=new TouchDelegate(outRect, btn);
				if (View.class.isInstance(btn.getParent())) {
                    ((View) btn.getParent()).setTouchDelegate(tDelegate);
                }

			}
		});
	
	}
}
