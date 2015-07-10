package com.example.gitandroidtest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcasterReciver_Service extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean msg=intent.getBooleanExtra("FINISH", false);
		Log.i("test","Recevier received " + msg);
		Toast.makeText(context, "msg=" + msg, Toast.LENGTH_SHORT).show();
		
	}
	

}
