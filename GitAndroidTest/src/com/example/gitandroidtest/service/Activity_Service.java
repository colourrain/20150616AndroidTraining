package com.example.gitandroidtest.service;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Activity_Service extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		
	}
	
	public void onClick(View view){
		Log.i("test", "Service started");
		Intent intent=new Intent(this, backgroudService.class);
		intent.putExtra("service", "Message from service");
		startService(intent);
		
		
		
		/*BroadcasterReciver_Service receiver=new BroadcasterReciver_Service() ;
		IntentFilter filter=new IntentFilter("SERVICE");
		registerReceiver(receiver, filter);*/
		
	}
}
