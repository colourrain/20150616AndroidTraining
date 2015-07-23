package com.example.gitandroidtest.battery;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity_Battery extends Activity {

	Intent batteryStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery);
		IntentFilter filter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		batteryStatus=registerReceiver(null, filter);
	}
	
	
	public void onClick(View view){
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
		                     status == BatteryManager.BATTERY_STATUS_FULL;
		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
		
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float)scale;
		
		
		Toast.makeText(this, "Battary is Charging--" + isCharging
				+ "\nusbCharge--" + usbCharge +"\n acCharge--" + acCharge
				+ "\nBattery level = " + batteryPct
				, Toast.LENGTH_SHORT).show();
	}
	
}
