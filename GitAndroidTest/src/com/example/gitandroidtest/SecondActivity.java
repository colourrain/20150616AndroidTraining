package com.example.gitandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends Activity {
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent intent=getIntent();
		String str=intent.getStringExtra("Message");
		tv=(TextView) findViewById(R.id.tv);
		tv.setText(str);
	}
	
	
}
