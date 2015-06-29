package com.example.gitandroidtest.contacts;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Activity_Contact_Search extends Activity implements
		OnClickListener, LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
	
	
	private final static String[] FROM_COLUMNS = { ContactsContract.Contacts.DISPLAY_NAME };
	private final static int[] TO_IDS = { android.R.id.text1 };
	static final String[] PROJECTION = new String[] {
			ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
			ContactsContract.Contacts.LOOKUP_KEY,
			ContactsContract.Contacts.HAS_PHONE_NUMBER};
	static final String SELECTION = "(" + ContactsContract.Contacts.DISPLAY_NAME
			+ " like ?)";

	Button btn;
	EditText edit;
	ListView lv;
	SimpleCursorAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("test", "App onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_search);
		edit = (EditText) findViewById(R.id.edit_contact_search);
		lv = (ListView) findViewById(R.id.lv_contact);
		btn = (Button) findViewById(R.id.btn_contact_search);
		btn.setOnClickListener(this);
		adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, null, FROM_COLUMNS,
				TO_IDS, 0);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		getLoaderManager().initLoader(0, null, this);
		edit.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				getLoaderManager().restartLoader(0, null, Activity_Contact_Search.this);
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		getLoaderManager().restartLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {

		String mSearchString=edit.getText().toString();
		    
		    String[] mSelectionArgs = { mSearchString };
		    mSelectionArgs[0] = "%" + mSearchString + "%";
				
		return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                PROJECTION, SELECTION, mSelectionArgs, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

		adapter.swapCursor(data);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Cursor cursor = (Cursor)lv.getItemAtPosition(position);
		cursor.moveToPosition(position);
		Toast.makeText(this, "contact_id= "+ cursor.getString(0) +",phoneno="+cursor.getString(3) , Toast.LENGTH_SHORT).show();
		
	}

}
