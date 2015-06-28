package com.example.gitandroidtest.contacts;

import com.example.gitandroidtest.R;

import android.animation.Animator.AnimatorListener;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class Activity_Contact extends Activity implements OnClickListener {
	final static String tag="TestTag";

	
	Button btn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(tag, "App onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		btn = (Button) findViewById(R.id.btn_contact_send);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		
	}
	
	
	
}
