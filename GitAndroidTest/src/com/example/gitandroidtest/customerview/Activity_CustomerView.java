package com.example.gitandroidtest.customerview;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity_CustomerView extends Activity {
	
	EditText mRadius;
	PieChart pc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customerview);
		mRadius=(EditText) findViewById(R.id.edit_radius);
		pc=(PieChart) findViewById(R.id.pieChart_test);
		
	}
	
	public void onClick(View view){
		pc.setRadius(Float.parseFloat(mRadius.getText().toString()));
	}

}
