package com.example.gitandroidtest;

import java.util.zip.Inflater;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
		
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        ActionBar actionBar = getActionBar();
		        actionBar.setDisplayHomeAsUpEnabled(true);
		    }
	    // If your minSdkVersion is 11 or higher, instead use:
	    // getActionBar().setDisplayHomeAsUpEnabled(true);
		//getResources().getString(R.string.hello_world);
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

	
	public void onClick(View view){
//		Inflater li=(Inflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		LayoutInflater li=LayoutInflater.from(this);
//		View view1=li.inflate(R.layout.activity_third, null);
		View view1=view.inflate(SecondActivity.this, R.layout.activity_third, null);

		setContentView(view1);
		Button btn3=(Button) view1.findViewById(R.id.btn_3);
		btn3.setText("Click me");
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(SecondActivity.this, "test", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
}
