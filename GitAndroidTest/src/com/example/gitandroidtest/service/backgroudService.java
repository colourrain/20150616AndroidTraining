package com.example.gitandroidtest.service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class backgroudService extends IntentService {

	public backgroudService() {
		super("com.example.gitandroidtest.service.backgroundService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("test",
				"get message from activity ="
						+ intent.getStringExtra("service"));

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent broadcastor_intent = new Intent();
				broadcastor_intent.putExtra("FINISH", true);
				broadcastor_intent.setAction("SERVICE");
				sendBroadcast(broadcastor_intent);

			}
		}).start();

		

	}

}
