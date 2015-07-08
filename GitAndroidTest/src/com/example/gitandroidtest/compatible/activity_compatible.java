package com.example.gitandroidtest.compatible;

import com.example.gitandroidtest.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.internal.widget.CompatTextView;
import android.util.Log;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.WindowManager;
import android.widget.TextView;

@SuppressLint("NewApi")
public class activity_compatible extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compatible);

		AbstractCompatibleClass ab = AbstractCompatibleClass.CreateInstance();
		ab.printLog("Message");
		TextView tv = (TextView) findViewById(R.id.txt_compatible);
		tv.setText(ab.getFeedback("feedback"));
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (Build.VERSION.SDK_INT < 16) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			View decorView = getWindow().getDecorView();
			if (hasFocus) {
				decorView
						.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
								| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
								| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
								| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
								| View.SYSTEM_UI_FLAG_FULLSCREEN
								| View.SYSTEM_UI_FLAG_IMMERSIVE);
			}
		}
	}

}
