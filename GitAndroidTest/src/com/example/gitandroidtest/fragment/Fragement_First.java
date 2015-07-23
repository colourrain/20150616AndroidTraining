package com.example.gitandroidtest.fragment;

import com.example.gitandroidtest.R;
import com.example.gitandroidtest.R.id;
import com.example.gitandroidtest.R.layout;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.Toast;

public class Fragement_First extends Fragment {

	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragement_first, container,false);
	return view;
}


@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		Button btn1=(Button) getActivity().findViewById(R.id.btn_fragment1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Test", 1000).show();
				SharedPreferences sp=getActivity().getSharedPreferences("bobo", getActivity().MODE_PRIVATE);
				Editor edit = sp.edit();
				edit.putString("fragment", "from fragment1");
				edit.commit();
				
			}
		});
		
		
		
		
	}
}
