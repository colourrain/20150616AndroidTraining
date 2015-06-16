package com.example.gitandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	EditText edit;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit=(EditText) findViewById(R.id.edit);
		btn=(Button) findViewById(R.id.btn_send);
		btn.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, edit.getText().toString(), Toast.LENGTH_SHORT).show();
		Intent intent=new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("Message", edit.getText().toString());
		startActivity(intent);
		
		
	}
}
