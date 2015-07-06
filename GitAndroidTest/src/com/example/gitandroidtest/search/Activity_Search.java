package com.example.gitandroidtest.search;

import com.example.gitandroidtest.R;
import com.example.gitandroidtest.contacts.Activity_Contact_Search;
import com.example.gitandroidtest.drawlayout.Activity_DrawLayout;
import com.example.gitandroidtest.location.Activity_Location;
import com.example.gitandroidtest.notification.Activity_Notification;
import com.example.gitandroidtest.viewpager.Activity_ViewPager;

import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Search extends Activity {
	final static String tag = "TestTag";

	TextView tv_search;
	SearchDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(tag, "App onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		tv_search = (TextView) findViewById(R.id.txt_search);
		handleIntent(getIntent());
		db = new SearchDatabase(this);

	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
		super.onNewIntent(intent);
	}

	private void handleIntent(Intent intent) {
		String msg = intent.getStringExtra(SearchManager.QUERY);
		if (msg != null) {
			Cursor c = db.getWordMatches(msg, null);
			if (c != null) {
				String result = "Result:";
				do {
					result += "\n" + c.getString(0) + "-" + c.getString(1);
				} while (c.moveToNext());
				tv_search.setText(result);

			} else {
				tv_search.setText("There is no data");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_search_activity, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		final SearchView searchView = (SearchView) menu.findItem(
				R.id.action_search).getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return true;
	}

}
