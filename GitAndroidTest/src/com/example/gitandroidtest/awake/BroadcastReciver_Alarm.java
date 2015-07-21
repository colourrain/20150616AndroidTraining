package com.example.gitandroidtest.awake;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class BroadcastReciver_Alarm extends BroadcastReceiver{
	static Context a;
	@Override
	public void onReceive(Context context, Intent intent) {
		a=context;
		Log.i("test", "alarm received");
		Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show();
		AlertDialog.Builder builder=new Builder(context);
		builder.setTitle("Title").setMessage("Message").setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(BroadcastReciver_Alarm.a, "alarm test", Toast.LENGTH_SHORT).show();
				
			}
		});
		AlertDialog alert = builder.create(); 
		  alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		  alert.show();

	}
	

}
