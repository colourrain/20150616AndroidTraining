package com.example.gitandroidtest.gesture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class myViewforGestureTest extends View {
	float startX = 100;
	float startY = 100;
	float stopX = 300;
	float stopY = 300;
	final String TAG = "gesture";
	GestureDetector gesture;
	VelocityTracker tracker = null;
	private Scroller scroller;
	
	private Context context;

	public myViewforGestureTest(Context context) {
		this(context,null);
		
	}

	public myViewforGestureTest(Context context, AttributeSet attrs,
			int defStyleAttr) {
		this(context, attrs);

	}

	public myViewforGestureTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		gesture = new GestureDetector(context, new myListener());
		scroller=new Scroller(context);
	}
	
	public myViewforGestureTest(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		this(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (tracker == null) {
				tracker = VelocityTracker.obtain();
			} else {
				tracker.clear();
			}
			tracker.addMovement(event);
			scroller.startScroll(0, 0, (int)(-event.getX()), (int)(-event.getY()));
			gesture.onTouchEvent(event);
			Log.i(TAG, "ontouchDown fired " + tracker.toString());
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "ACTION_MOVE fired");
			this.startX = event.getX();
			this.startY=event.getY();
			tracker.addMovement(event);
			tracker.computeCurrentVelocity(1000);
			//Log.i(TAG, "ACTION_MOVE fired X Velocity=" + tracker.getXVelocity() + "Y Velocity=" + tracker.getYVelocity());
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			// Log.i(TAG, "ACTION_CANCEL fired " + event.toString());
			if (tracker != null) {
				tracker.recycle();
			}
			break;
		default:
			return super.onTouchEvent(event);
		}
		
		postInvalidate();
		return true;
		
	}

	class myListener extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.i(TAG, "onSingleTapUp fired ");
			return super.onSingleTapUp(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			Log.i(TAG, "onLongPress fired ");
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.i(TAG, "onScroll fired ");
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i(TAG, "onFling fired ");
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			Log.i(TAG, "onShowPress fired ");
			super.onShowPress(e);
		}

		@Override
		public boolean onDown(MotionEvent e) {
			Log.i(TAG, "onDown fired ");
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.i(TAG, "onDoubleTap fired ");
			return super.onDoubleTap(e);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			Log.i(TAG, "onDoubleTapEvent fired ");
			return super.onDoubleTapEvent(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.i(TAG, "onSingleTapConfirmed fired ");
			return super.onSingleTapConfirmed(e);
		}
		
		

	}
	
	@Override
	public void computeScroll() {
		if(scroller.computeScrollOffset()){
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
		}
		postInvalidate();
		super.computeScroll();
	}


}
