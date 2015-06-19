package com.example.gitandroidtest;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragement_Third extends Fragment {

	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.fragement_third, container,false);
	return view;
}

@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Button btn_3=(Button) getActivity().findViewById(R.id.btn_fragment3);
		btn_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction fmt = fm.beginTransaction();
				fmt.replace(R.id.fragment_1,new Fragement_Second());
				fmt.commit();
			}
		});
		
		
	}

}
