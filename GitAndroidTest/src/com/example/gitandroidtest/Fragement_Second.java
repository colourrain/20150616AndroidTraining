package com.example.gitandroidtest;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Fragement_Second extends Fragment {

	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragement_second, container,false);
	return view;
}

@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Button btn_2=(Button) getActivity().findViewById(R.id.btn_fragment2);
		btn_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
		
		
	}

}
