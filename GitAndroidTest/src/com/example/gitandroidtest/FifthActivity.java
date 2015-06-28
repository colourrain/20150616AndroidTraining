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
import java.util.HashMap;
import java.util.Map;


import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
	Button btn, btn_image, btn_volley, btn_image_volley,btn_networkimage;
	EditText edit;
	ImageView iv;
	NetworkImageView niv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fifth_internet);
		tv = (TextView) findViewById(R.id.txt_fifth);
		edit = (EditText) findViewById(R.id.edit);
		iv = (ImageView) findViewById(R.id.image_fifth);
		niv=(NetworkImageView) findViewById(R.id.networkiv);

		btn = (Button) findViewById(R.id.btn_send);
		btn_volley = (Button) findViewById(R.id.btn_send_volley);

		btn_image = (Button) findViewById(R.id.btn_image);
		btn_image_volley = (Button) findViewById(R.id.btn_image_volley);
		btn_networkimage=(Button) findViewById(R.id.btn_networkimage_volley);
		btn_image.setOnClickListener(this);
		btn_volley.setOnClickListener(this);
		btn.setOnClickListener(this);
		btn_image_volley.setOnClickListener(this);
		btn_networkimage.setOnClickListener(this);
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
		case R.id.btn_send_volley:
			//RequestQueue queue = Volley.newRequestQueue(this);
			//RequestQueue queue=MySingleton.getInstance(getApplicationContext()).getRequestQueue();
			
			StringRequest strQ = new StringRequest(Method.GET, edit.getText()
					.toString(), new Response.Listener<String>() {

				@Override
				public void onResponse(String response) {
					tv.setText(response.substring(0, 10));
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					tv.setText(error.toString());
				}
			}) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					Map<String, String> map = new HashMap<String, String>();
					map.put("params1", "value1");
					map.put("params2", "value2");
					return map;
				}
			};

			//queue.add(strQ);
			MySingleton.getInstance(getApplicationContext()).addToRequestQueue(strQ);
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
		case R.id.btn_image_volley:
			ImageRequest imgQ=new ImageRequest(edit.getText()
					.toString(), new Response.Listener<Bitmap>() {

				@Override
				public void onResponse(Bitmap response) {
					iv.setImageBitmap(response);
					
				}
			}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(FifthActivity.this, "Load Failure" + error.toString(), Toast.LENGTH_SHORT).show();
				}
			});
			//Volley.newRequestQueue(FifthActivity.this).add(imgQ);
			//RequestQueue rq=MySingleton.getInstance(getApplicationContext()).getRequestQueue();
			MySingleton.getInstance(getApplicationContext()).addToRequestQueue(imgQ);
			break;
			
		case R.id.btn_networkimage_volley:
			ImageLoader imageloader = MySingleton.getInstance(getApplicationContext()).getImageLoader();
			//imageloader.get(requestUrl, imageListener, maxWidth, maxHeight)
			niv.setImageUrl(edit.getText().toString(), imageloader);
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
				Log.i("Test", "image loaded");
				iv.setImageBitmap(result);
			} else {
				Log.i("Test", "image not loaded");
			}

		}

	}
}
