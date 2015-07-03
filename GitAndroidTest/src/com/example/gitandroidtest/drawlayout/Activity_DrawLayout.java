package com.example.gitandroidtest.drawlayout;

import com.example.gitandroidtest.R;
import com.example.gitandroidtest.viewpager.Fragment_for_ViewPager;

import android.app.ActionBar;
import android.os.Bundle;
import android.provider.ContactsContract.PinnedPositions;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Activity_DrawLayout extends FragmentActivity {
	ListView lv;
	String[] list = { "a", "b", "c", "d" };
	DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		lv = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
			}

		});

	}

	private void selectItem(int position) {
		Fragment fragment = new Fragment_for_ViewPager();
		Bundle bundle = new Bundle();
		bundle.putString("id", "" + (position + 1));
		fragment.setArguments(bundle);

		FragmentManager fm = getSupportFragmentManager();
		
		fm.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("a").commit();
		lv.setItemChecked(position, true);
		setTitle(list[position]);
		mDrawerLayout.closeDrawer(lv);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.drawer_activity_actions, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_drawer:
			if (mDrawerLayout.isDrawerOpen(lv)) {
				mDrawerLayout.closeDrawer(lv);
			} else {
				mDrawerLayout.openDrawer(lv);
			}
			
			break;
			
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
