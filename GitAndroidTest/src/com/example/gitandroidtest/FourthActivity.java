package com.example.gitandroidtest;

import java.util.zip.Inflater;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class FourthActivity extends Activity implements OnCheckedChangeListener{
	TextView tv;
	Button btn1, btn2, btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth_fragement);
		RadioGroup rg = (RadioGroup) findViewById(R.id.rGroup_fragment);
		rg.setOnCheckedChangeListener(this);
		Intent intent=getIntent();
		if(intent.getAction()==Intent.ACTION_SEND){
			String text=intent.getStringExtra(Intent.EXTRA_TEXT);
			Toast.makeText(FourthActivity.this, text, Toast.LENGTH_SHORT).show();
		}
		
		
			
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
			FragmentManager fm=getFragmentManager();
			FragmentTransaction fmt = fm.beginTransaction();
		
			switch (checkedId) {
			
			case R.id.radio_1:
				fmt.replace(R.id.fragment_dynamic, new Fragement_First());
				break;
			case R.id.radio_2:
				fmt.replace(R.id.fragment_dynamic, new Fragement_Second());
				break;
			case R.id.radio_3:
				fmt.replace(R.id.fragment_dynamic, new Fragement_Third());
				break;
			case R.id.radio_4:

				break;
			default:
				break;
			}
			fmt.commit();
	
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
}
