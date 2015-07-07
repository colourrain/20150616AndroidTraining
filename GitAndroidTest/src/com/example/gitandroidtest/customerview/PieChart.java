package com.example.gitandroidtest.customerview;

import java.util.List;

import com.example.gitandroidtest.R;
import com.example.gitandroidtest.R.color;
import com.example.gitandroidtest.customerview.Activity_CustomerView.PiePortition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class PieChart extends View {

	boolean mshowText;
	int mlabelPostion;
	float mRadius;
	Paint mTextPaint, mPiePaint, mShadowPaint;
	int mTextColor;
	float mTextHeight;
	float mTextWidth;
	List<PiePortition> mData;
	GestureDetector mDetector;
	float startAngle = 0;

	public PieChart(Context context) {
		this(context, null);
	}

	public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
		this(context, null);
	}

	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.PieChart, 0, 0);
		try {
			mshowText = ta.getBoolean(R.styleable.PieChart_showPieText, false);
			mlabelPostion = ta
					.getInteger(R.styleable.PieChart_labelPosition, 0);
			mRadius = ta.getDimension(R.styleable.PieChart_radius, 20);
			mTextColor = color.yellow;
			mTextHeight = 30f;
			mTextWidth = 80f;

		} finally {
			ta.recycle();
		}
		init();
		mDetector = new GestureDetector(PieChart.this.getContext(), new mListener());
	}

	private void init() {
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(mTextColor);
		if (mTextHeight == 0) {
			mTextHeight = mTextPaint.getTextSize();
		} else {
			mTextPaint.setTextSize(mTextHeight);
		}

		mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPiePaint.setStyle(Paint.Style.FILL);
		mPiePaint.setTextSize(mTextHeight);

		mShadowPaint = new Paint(0);
		mShadowPaint.setColor(0xff101010);
		mShadowPaint.setMaskFilter(new BlurMaskFilter(8,
				BlurMaskFilter.Blur.NORMAL));
		setMinimumWidth((int) mRadius);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// testDraw(canvas);

		canvas.drawColor(Color.LTGRAY);
		
		float start=startAngle;
		RectF oval = new RectF(100, 100, 300, 300);
		for (int i = 0; i < mData.size(); i++) {
			float endAngle = mData.get(i).getQuota() * 360f;
			mPiePaint.setShader(mData.get(i).getShader());
			canvas.drawArc(oval, start, endAngle, true, mPiePaint);
			start += endAngle;
		}

	}

	private void testDraw(Canvas canvas) {
		// draw a circle
		canvas.drawCircle(50, 50, mRadius, mPiePaint);

		// draw a oval
		Shader shader = new LinearGradient(0, 0, 100, 200, new int[] {
				Color.RED, Color.GREEN, Color.GRAY }, null,
				Shader.TileMode.REPEAT);
		mShadowPaint.setShader(shader);
		RectF mShadowBounds = new RectF(50, 150, 250, 250);
		canvas.drawOval(mShadowBounds, mShadowPaint);
		// Draw the label text
		canvas.drawText("label", 100, 120, mTextPaint);
		RectF oval = new RectF(150, 250, 300, 400);
		canvas.drawArc(oval, 90, 30, true, mPiePaint);
		// draw a line
		canvas.drawLine(0, 0, 300, 300, mShadowPaint);
		Path path = new Path();
		// path.addCircle(300, 300, 50, Path.Direction.CCW);
		path.moveTo(300, 300);
		path.lineTo(400, 450);
		path.lineTo(200, 500);
		path.lineTo(230, 500);
		path.close();
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawPath(path, paint);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int minw = getPaddingLeft() + getPaddingRight()
				+ getSuggestedMinimumWidth();
		int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

		int minh = MeasureSpec.getSize(w) + getPaddingBottom()
				+ getPaddingTop();
		int h = resolveSizeAndState(MeasureSpec.getSize(w), heightMeasureSpec,
				0);
		setMeasuredDimension(w, h);
		/*Log.i("test", MeasureSpec.toString(widthMeasureSpec) + "---"
				+ MeasureSpec.toString(heightMeasureSpec));
		Log.i("test", w + "----------------" + h);*/
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// Account for padding
		float xpad = (float) (getPaddingLeft() + getPaddingRight());
		float ypad = (float) (getPaddingTop() + getPaddingBottom());

		// Account for the label
		if (mshowText)
			xpad += mTextWidth;

		float ww = (float) w - xpad;
		float hh = (float) h - ypad;

		// Figure out how big we can make the pie.
		float diameter = Math.min(ww, hh);

	}

	public void setShowPieText(Boolean bool) {
		mshowText = bool;
		invalidate();
		requestLayout();
	}

	public void setRadius(float radius) {
		mRadius = radius;
		setMinimumWidth((int) radius);
		invalidate();
		requestLayout();
	}

	public void setData(List<PiePortition> list) {
		mData = list;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean result = mDetector.onTouchEvent(event);
		   if (!result) {
		       if (event.getAction() == MotionEvent.ACTION_UP) {
		           result = true;
		       }
		   }
		   return result;
		
	}
	class mListener extends GestureDetector.SimpleOnGestureListener{
		@Override
		public boolean onDown(MotionEvent e) {
			 Log.i("test","----onDown-----");
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.i("test","----onFling-----X=" + velocityX + ":Y=" + velocityY);
			return super.onFling(e1, e2, velocityX, velocityY);
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.i("test","----onScroll-----X=" + distanceX + ":Y=" + distanceY);
			startAngle -= distanceY;
			//invalidate();
			//requestLayout();
			postInvalidate();
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}
	

}
