package com.example.gitandroidtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
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
		//Toast.makeText(MainActivity.this, edit.getText().toString(),Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("Message", edit.getText().toString());
		startActivityForResult(intent, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("test", "ResultCode=" + resultCode + "data=" + data);
		if(resultCode==1){
		Bundle bundle = data.getExtras();
		edit.setText(bundle.getString("fragment1"));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		 MenuItem item = menu.findItem(R.id.action_share);

		    // Fetch and store ShareActionProvider
		    ShareActionProvider mShareActionProvider = (ShareActionProvider) item.getActionProvider();

		    // Return true to display menu
		    return true;

	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		switch (item.getItemId())
		{
		case R.id.action_search:
			Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_always:
			Toast.makeText(this, "Always", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_share:
			Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
			Intent intent1 = new Intent(Intent.ACTION_SEND);
			intent1.putExtra(Intent.EXTRA_TEXT, "this is waht i want to share");
			intent1.setType("text/plain");
			Intent chooser = Intent.createChooser(intent1, "share");
			startActivity(chooser);
			return true;
		case R.id.action_sub1:
			startActivity(new Intent("AA"));
			return true;
		case R.id.action_sub2:
			
			ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netinfo = cm.getActiveNetworkInfo();
			Log.i("test","network is " + netinfo.getTypeName());
			Log.i("test","network is " + netinfo.isConnected());
			NetworkInfo mobileInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			
			Log.i("test","wifi is " + wifiInfo.isConnected());
		
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
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i(tag, "onSaveInstanceState");
		outState.putString("testbundle", "onSaveInstancestate saved");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i(tag, savedInstanceState.getString("testbundle"));
		Log.i(tag, "onRestoreInstanceState");
	}
	
}
