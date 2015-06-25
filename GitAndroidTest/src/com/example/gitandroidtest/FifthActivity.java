package com.example.gitandroidtest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class FifthActivity extends Activity implements OnClickListener {
	final static String tag = "TestTag";

	TextView tv;
	Button btn, btn_image;
	EditText edit;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth_internet);
		tv = (TextView) findViewById(R.id.txt_fifth);
		edit = (EditText) findViewById(R.id.edit);
		iv=(ImageView) findViewById(R.id.image_fifth);

		btn = (Button) findViewById(R.id.btn_send);
		btn_image = (Button) findViewById(R.id.btn_image);
		btn_image.setOnClickListener(this);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		NetworkInfo networkinfo;
		ConnectivityManager cm;

		switch (v.getId()) {
		case R.id.btn_send:
			cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			networkinfo = cm.getActiveNetworkInfo();
			if (networkinfo != null && networkinfo.isConnected()) {
				Log.i("test", "is Connected");
				new DownloadWebTask().execute(edit.getText().toString());
			} else {
				Toast.makeText(this, "internet disconnected",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_image:
			cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			networkinfo = cm.getActiveNetworkInfo();
			if (networkinfo != null && networkinfo.isConnected()) {
				Log.i("test", "is Connected");
				new DownLoadImageTask().execute(edit.getText().toString());
			} else {
				Toast.makeText(this, "internet disconnected",
						Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}

	}

	class DownloadWebTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				Log.i("test", "go into doinbackgroud");
				return DownloadURL(params[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			tv.setText(result);
		}
	}

	private String DownloadURL(String loadurl) throws IOException {
		// return "load webpage success " + string;

		URL url = new URL(loadurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		// Starts the query
		conn.connect();

		int response = conn.getResponseCode();
		Log.i("test", "getResponseCode=" + response);

		InputStream is = conn.getInputStream();

		// Convert the InputStream into a string
		String contentAsString = readIt(is, 100);
		return contentAsString;

	}

	// Reads an InputStream and converts it to a String.
	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "GBK");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	private Bitmap DownloadImage(String loadurl) throws IOException {
		// return "load webpage success " + string;

		URL url = new URL(loadurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		// Starts the query
		conn.connect();

		int response = conn.getResponseCode();
		Log.i("test", "getResponseCode=" + response);

		InputStream is = conn.getInputStream();
		return BitmapFactory.decodeStream(is);

	}

	class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				return DownloadImage(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				Log.i("Test","image loaded");
				iv.setImageBitmap(result);
			}else{
				Log.i("Test","image not loaded");
			}

		}

	}
}
