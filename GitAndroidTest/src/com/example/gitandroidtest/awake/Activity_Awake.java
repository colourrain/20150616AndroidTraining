package com.example.gitandroidtest.awake;

import java.util.Calendar;
import com.example.gitandroidtest.R;
import com.example.gitandroidtest.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Awake extends Activity implements OnClickListener {

	Button btn,btnStop;
	AlarmManager alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__awake);

//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		btn = (Button) findViewById(R.id.btn_alarm);
		btnStop=(Button) findViewById(R.id.btn_alarm_stop);
		btn.setOnClickListener(this);
		btnStop.setOnClickListener(this);

	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(Activity_Awake.this,
				BroadcastReciver_Alarm.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(
				Activity_Awake.this, 0, intent, 0);
		switch (v.getId()) {
		case R.id.btn_alarm:
			
			
			// alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
			// SystemClock.elapsedRealtime()+1000, 2 * 1000, alarmIntent);
			Calendar calendar = Calendar.getInstance();

			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.add(Calendar.SECOND, 20);
			alarm.setRepeating(AlarmManager.RTC,
					calendar.getTimeInMillis(), 5000, alarmIntent);
			Log.i("test", "alarm started from " + SystemClock.elapsedRealtime());
			break;
		case R.id.btn_alarm_stop:
			alarm.cancel(alarmIntent);
		default:
			break;
		}
	}
	
	

}
