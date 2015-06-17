package com.example.gitandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	final static String tag="TestTag";

	EditText edit;
	Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(tag, "App onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit = (EditText) findViewById(R.id.edit);
		btn = (Button) findViewById(R.id.btn_send);
		btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, edit.getText().toString(),
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("Message", edit.getText().toString());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId())
		{
		case R.id.action_search:
			Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_always:
			Toast.makeText(this, "Always", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onOptionsMenuClosed(menu);
		Toast.makeText(this, "OptionsMenuClosed", Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(tag, "App Onresume");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(tag, "App onDestroy");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(tag, "App onPause");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(tag, "App onStart");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(tag, "App onStop");
	}
	
}
