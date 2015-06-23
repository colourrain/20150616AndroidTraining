package com.example.gitandroidtest;

import java.lang.ref.WeakReference;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragement_Fourth extends Fragment {
	ImageView iv;
	EditText edit;

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragement_fourth, container,
				false);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		iv = (ImageView) getActivity().findViewById(R.id.fragment4_iv);
		edit = (EditText) getActivity().findViewById(R.id.fragment4_edit);
		Button btn = (Button) getActivity().findViewById(R.id.btn_fragment4);
		Button btn_pic=(Button) getActivity().findViewById(R.id.btn_fragment4_addpic);
		final LinearLayout ly=(LinearLayout) getActivity().findViewById(R.id.frament4);
		btn_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ImageView iv2=new ImageView(getActivity());
				iv2.setImageDrawable(getResources().getDrawable(R.drawable.tulips));
				ly.addView(iv2);
				
			}
		});

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// int pic_id=getResources().getIdentifier("tulips.jpg",
				// "drawable",getActivity().getBaseContext().getPackageName());
				// BitmapFactory.Options option=new Options();
				// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				// R.drawable.tulips, option);
				// Bitmap bitmap =
				// decodeSampledBitmapFromResource(getResources(),R.drawable.tulips,Integer.parseInt(edit.getText().toString()),Integer.parseInt(edit.getText().toString()));
				// iv.setImageBitmap(bitmap);
				final String imageKey = String.valueOf(R.drawable.tulips);
				final Bitmap bitmap = getBitmapFromMemCache(imageKey);
				if (bitmap != null) {
					iv.setImageBitmap(bitmap);
				} else {
					BitmapWorkerTask task = new BitmapWorkerTask(iv);
					task.execute(R.drawable.tulips);
				}

			}
		});
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
		private final WeakReference<ImageView> imageViewReference;
		private int data = 0;

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage
			// collected
			imageViewReference = new WeakReference<ImageView>(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(Integer... params) {
			data = params[0];
			Bitmap bitmap=decodeSampledBitmapFromResource(getResources(), data, Integer.parseInt(edit.getText().toString()),
					Integer.parseInt(edit.getText().toString()));
			addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
			return bitmap;
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = imageViewReference.get();
				if (imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

	private LruCache<String, Bitmap> mMemoryCache;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// Get max available VM memory, exceeding this amount will throw an
		// OutOfMemory exception. Stored in kilobytes as LruCache takes an
		// int in its constructor.
		super.onCreate(savedInstanceState);
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

		// Use 1/8th of the available memory for this memory cache.
		final int cacheSize = maxMemory / 8;

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return bitmap.getByteCount() / 1024;
			}
		};

	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}
}
