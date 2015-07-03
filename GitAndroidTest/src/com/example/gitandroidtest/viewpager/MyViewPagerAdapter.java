package com.example.gitandroidtest.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

	public MyViewPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}


	@Override
	public Fragment getItem(int id) {
		Fragment fragment=new Fragment_for_ViewPager();
		Bundle args = new Bundle();
		args.putString("id", "" + (id+1));
		fragment.setArguments(args);
		return fragment;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return "Pager " + (position+1);
	}
	

}
