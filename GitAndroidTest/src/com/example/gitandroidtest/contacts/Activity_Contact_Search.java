package com.example.gitandroidtest.contacts;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Intents;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.ShareActionProvider;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Activity_Contact_Search extends Activity implements
		OnClickListener, LoaderCallbacks<Cursor>,
		AdapterView.OnItemClickListener, OnItemLongClickListener {

	private final static String[] FROM_COLUMNS = { ContactsContract.Contacts.DISPLAY_NAME };
	private final static int[] TO_IDS = { android.R.id.text1 };
	private final static String[] PROJECTION = new String[] {
			ContactsContract.Contacts._ID,
			ContactsContract.Contacts.DISPLAY_NAME,
			ContactsContract.Contacts.LOOKUP_KEY,
			ContactsContract.Contacts.HAS_PHONE_NUMBER };
	private final static String SELECTION = "("
			+ ContactsContract.Contacts.DISPLAY_NAME + " like ?)";
	private final static int SEARCH_LOADER_ID = 0;
	private final static int SEARCH_DETAIL_LOADER_ID = 1;
	private String Lookup_KEY = "";
	private String Display_name = "";

	Button btn;
	EditText edit;
	ListView lv;
	ContactCursorAdapter adapter;
	QuickContactBadge badge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("test", "App onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_search);
		edit = (EditText) findViewById(R.id.edit_contact_search);
		lv = (ListView) findViewById(R.id.lv_contact);
		btn = (Button) findViewById(R.id.btn_contact_search);
		badge=(QuickContactBadge) findViewById(R.id.badge_contact);
		btn.setOnClickListener(this);
		//adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1, null, FROM_COLUMNS,TO_IDS, 0);
		adapter=new ContactCursorAdapter(this, null);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		
		
		getLoaderManager().initLoader(SEARCH_LOADER_ID, null, this);
		// getLoaderManager().initLoader(SEARCH_DETAIL_LOADER_ID, null, this);
		edit.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				getLoaderManager().restartLoader(SEARCH_LOADER_ID, null,
						Activity_Contact_Search.this);
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		getLoaderManager().restartLoader(SEARCH_LOADER_ID, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Log.i("test", "LOADER_ID=" + id);
		CursorLoader mLoader = null;
		switch (id) {
		case SEARCH_LOADER_ID:
			Log.i("test", "get into SEARCHL_LOADER_ID");
			String mSearchString = edit.getText().toString();
			String[] mSelectionArgs = { mSearchString };
			mSelectionArgs[0] = "%" + mSearchString + "%";
			mLoader = new CursorLoader(this,
					ContactsContract.Contacts.CONTENT_URI, PROJECTION,
					SELECTION, mSelectionArgs, null);
			break;
		case SEARCH_DETAIL_LOADER_ID:
			Log.i("test", "get into SEARCH_DETAIL_LOADER_ID");
			String[] project_detail = { ContactsContract.Data.LOOKUP_KEY,
					ContactsContract.CommonDataKinds.Phone.NUMBER,
					ContactsContract.CommonDataKinds.Phone.TYPE,
					ContactsContract.CommonDataKinds.Email.ADDRESS };
			String condition_phone = "AND " + Data.MIMETYPE + " = '"
					+ ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
					+ "'";
			String condition_email = "AND " + Data.MIMETYPE + " = '"
					+ ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
					+ "'";
			String selection_detail = ContactsContract.Data.LOOKUP_KEY
					+ " = ? " + condition_phone;
			String[] selectionArgs_detail = { "" };
			selectionArgs_detail[0] = Lookup_KEY;
			String sortOrder_detail = null;
			mLoader = new CursorLoader(this, ContactsContract.Data.CONTENT_URI,
					project_detail, selection_detail, selectionArgs_detail,
					sortOrder_detail);
			break;
		}
		return mLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		Log.i("test", "Loader.getId=" + loader.getId());
		switch (loader.getId()) {
		case SEARCH_LOADER_ID:
			
			adapter.swapCursor(data);
			
			
			break;
		case SEARCH_DETAIL_LOADER_ID:
			if (Lookup_KEY != "") {
				String message = "姓名：" + Display_name;
				Log.i("test", "data number = " + data.getCount());
				while (data.moveToNext()) {
					switch (data.getInt(2)) {
					case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
						message += "\n手机：" + data.getString(1);
						break;
					case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
						message += "\n电话：" + data.getString(1);
						break;
					default:
						break;
					}
				}
				AlertDialog.Builder builder = new Builder(this);
				builder.setTitle("Detail Info")
						.setMessage(message)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create().show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		switch (loader.getId()) {
		case SEARCH_LOADER_ID:
			Log.i("test", "SEARCH_LOADER_Reset");
			adapter.swapCursor(null);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Cursor cursor = (Cursor) lv.getItemAtPosition(position);
		cursor.moveToPosition(position);
		Lookup_KEY = cursor.getString(2);
		Display_name = cursor.getString(1);
		getLoaderManager().restartLoader(SEARCH_DETAIL_LOADER_ID, null,
				Activity_Contact_Search.this);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.contact_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Lookup_KEY = "";
		switch (item.getItemId()) {
		case R.id.action_contact_add:
			Intent intent = new Intent(Intents.Insert.ACTION);
			intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
			intent.putExtra(Intents.Insert.COMPANY, "SVW");

			startActivity(intent);
			break;
		case R.id.action_contact_addoredit:
			// Creates a new Intent to insert or edit a contact
			Intent intentInsertEdit = new Intent(Intent.ACTION_INSERT_OR_EDIT);
			// Sets the MIME type
			intentInsertEdit.setType(Contacts.CONTENT_ITEM_TYPE);
			// Add code here to insert extended data, if desired
			// Sends the Intent with an request ID
			startActivity(intentInsertEdit);
		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		Cursor cursor = (Cursor) lv.getItemAtPosition(position);
		cursor.moveToPosition(position);
		Uri uri = ContactsContract.Contacts.getLookupUri(cursor.getInt(0),
				cursor.getString(2));
		Intent edit_intent = new Intent(Intent.ACTION_EDIT);
		edit_intent.setDataAndType(uri,
				ContactsContract.Contacts.CONTENT_ITEM_TYPE);
		// Sets the special extended data for navigation
		edit_intent.putExtra("finishActivityOnSaveCompleted", true);
		startActivity(edit_intent);

		return false;
	}

}
