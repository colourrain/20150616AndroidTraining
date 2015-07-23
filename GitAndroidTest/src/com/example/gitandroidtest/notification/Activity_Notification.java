package com.example.gitandroidtest.notification;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Notification extends Activity implements OnClickListener {
	Button btn_send;
	private int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:

			Intent intent_dismiss = new Intent(this, Service.class);
			intent_dismiss.setAction(Intent.ACTION_BATTERY_LOW);
			Intent intent_snooze = new Intent(this, Service.class);
			intent_snooze.setAction(Intent.ACTION_BATTERY_OKAY);
			final PendingIntent dis = PendingIntent.getService(this, 0,
					intent_dismiss, 0);
			final PendingIntent sno = PendingIntent.getService(this, 0,
					intent_snooze, 0);

			Intent intent = new Intent(this, Activity_Notification.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			final PendingIntent pendingIntent = PendingIntent.getActivity(this,
					1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

			final NotificationCompat.Builder builder = new NotificationCompat.Builder(
					this);

			new Thread(new Runnable() {

				@Override
				public void run() {
					builder.setContentTitle("Notification 1")
							.setContentIntent(pendingIntent)
							.setContentText("This the Notification 1!!!")
							.setNumber(++num)
							.setSmallIcon(R.drawable.tulips)
							.setStyle(
									new NotificationCompat.BigTextStyle()
											.bigText("This the Notification 1111111!!!"))
							.addAction(
									R.drawable.abc_menu_hardkey_panel_mtrl_mult,
									"Dismiss", dis)
							.addAction(
									R.drawable.abc_btn_switch_to_on_mtrl_00001,
									"Snooze", sno)

					;

					for (int i = 50; i < 100; i++) {
						builder.setProgress(100, i, false);
						NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
						Notification notice = builder.build();
						notice.flags |= Notification.FLAG_AUTO_CANCEL;
						manager.notify(1, notice);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}).start();

			break;

		default:
			break;
		}

	}
	@Override
	protected void onResume() {
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//manager.cancel(1);
		Log.i("test","cancel notification");
		super.onResume();
	}

}
