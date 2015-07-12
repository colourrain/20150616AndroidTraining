package com.example.gitandroidtest.awake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReciver_Alarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("test", "alarm received");
		Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show();
		
	}
	

}
