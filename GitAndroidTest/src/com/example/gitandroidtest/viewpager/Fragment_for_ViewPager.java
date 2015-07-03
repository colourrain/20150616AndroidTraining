package com.example.gitandroidtest.viewpager;

import com.example.gitandroidtest.R;


import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class Fragment_for_ViewPager extends Fragment {

	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragement_for_viewpager, container,false);
	TextView tv=(TextView) view.findViewById(R.id.tv_for_viewpager);
	Bundle argu = getArguments();
	tv.setText("This is Fragment " + argu.getString("id"));
	return view;
}


@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
}
