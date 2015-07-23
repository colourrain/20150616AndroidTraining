package com.example.gitandroidtest.contacts;

import com.example.gitandroidtest.R;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class ContactCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;
	public ContactCursorAdapter(Context context, Cursor c) {
		super(context, c);
		mInflater = LayoutInflater.from(context);
	}
	private class ViewHolder {
		TextView displayname;
		QuickContactBadge quickcontact;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View itemView = mInflater.inflate(R.layout.activity_contact_listitem,parent, false);
		final ViewHolder holder = new ViewHolder();
		holder.displayname = (TextView) itemView
				.findViewById(R.id.txt_contact_listitem);
		holder.quickcontact = (QuickContactBadge) itemView
				.findViewById(R.id.badge_contact);
		itemView.setTag(holder);
		return itemView;
	}
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final ViewHolder holder = (ViewHolder) view.getTag();
		holder.displayname.setText(cursor.getString(1));
		Uri mContactUri = Contacts.getLookupUri(cursor.getLong(0),
				cursor.getString(2));
		holder.quickcontact.assignContactUri(mContactUri);
		holder.quickcontact.setImageResource(R.drawable.tulips);
		holder.quickcontact.assignContactUri(mContactUri);

	}

}
