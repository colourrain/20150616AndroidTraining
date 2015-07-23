package com.example.gitandroidtest.fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.gitandroidtest.R;
import com.example.gitandroidtest.R.id;
import com.example.gitandroidtest.R.layout;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class Fragement_Third extends Fragment implements OnClickListener {
	public Activity context = getActivity();
	final static int PICK_CONTACT_REQUEST=1;
	static final int CAMERA_CAPTURE_REQUEST=3;
	static final int VIDEO_CAPTURE_REQUEST=4;
	VideoView video;
	ImageView iv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragement_third, container, false);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Button btn_3 = (Button) getActivity().findViewById(R.id.btn_fragment3);
		Button btn_phonecall = (Button) getActivity().findViewById(
				R.id.btn_fragment3_phonecall);
		Button btn_map = (Button) getActivity().findViewById(
				R.id.btn_fragment3_map);
		Button btn_web = (Button) getActivity().findViewById(
				R.id.btn_fragment3_web);
		Button btn_cam = (Button) getActivity().findViewById(
				R.id.btn_fragment3_cam);
		Button btn_chooser = (Button) getActivity().findViewById(
				R.id.btn_fragment3_chooser);
		Button btn_contact = (Button) getActivity().findViewById(
				R.id.btn_fragment3_contact);
		Button btn_video=(Button) getActivity().findViewById(R.id.btn_fragment3_video);
		iv=(ImageView) getActivity().findViewById(R.id.fragment3_tv);
		video=(VideoView) getActivity().findViewById(R.id.fragment3_video);
		
		btn_3.setOnClickListener(this);
		btn_phonecall.setOnClickListener(this);
		btn_map.setOnClickListener(this);
		btn_web.setOnClickListener(this);
		btn_cam.setOnClickListener(this);
		btn_chooser.setOnClickListener(this);
		btn_contact.setOnClickListener(this);
		btn_video.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.btn_fragment3:
			String env = Environment.getExternalStorageState();
			// Toast.makeText(getActivity(), "External stoarage state is " +
			// env, Toast.LENGTH_SHORT).show();
			// File file = new File(getActivity().getFilesDir(),
			// "filetest");
			// Log.i("test","file " + file.exists());

			File file = new File(getActivity().getExternalFilesDir(null),
					"BoboApp");
			if (!file.exists()) {
				if (!file.mkdirs()) {
					Toast.makeText(getActivity(), "build folder failure",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "build folder success",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(getActivity(), "folder exist",
						Toast.LENGTH_SHORT).show();
				file.delete();
				Toast.makeText(getActivity(), "folder deleted",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_fragment3_phonecall:
			Uri phone = Uri.parse("tel:021-62525955");
			intent = new Intent(Intent.ACTION_DIAL, phone);
			break;
		case R.id.btn_fragment3_map:
			Uri location = Uri
					.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
			intent = new Intent(Intent.ACTION_VIEW, location);
			break;
		case R.id.btn_fragment3_web:
			Uri webpage = Uri.parse("http://www.baidu.com");
			intent = new Intent(Intent.ACTION_VIEW, webpage);		
			break;
		case R.id.btn_fragment3_cam:
			File imagefile = null;
			Intent intent_cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if(intent_cam.resolveActivity(getActivity().getPackageManager())!=null){
				try {
					imagefile=createImageFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(imagefile!=null){
					//intent_cam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagefile));
				}
				startActivityForResult(intent_cam, CAMERA_CAPTURE_REQUEST);
			}
			
			break;
		case R.id.btn_fragment3_chooser:
			Intent intent1 = new Intent(Intent.ACTION_SEND);
			intent1.putExtra(Intent.EXTRA_TEXT, "this is waht i want to share");
			intent1.setType("text/plain");
			Intent chooser = Intent.createChooser(intent1, "share");
			startActivity(chooser);
			break;
		case R.id.btn_fragment3_contact:
			Intent intentcontact=new Intent(Intent.ACTION_PICK,Uri.parse("content://contacts"));
			intentcontact.setType(Phone.CONTENT_TYPE);
			startActivityForResult(intentcontact, PICK_CONTACT_REQUEST);
			break;
		case R.id.btn_fragment3_video:
			new Thread(new Runnable() {
				@Override
				public void run() {
					Intent intent_video=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
					startActivityForResult(intent_video, VIDEO_CAPTURE_REQUEST);
				}
			}).start();
			break;
		default:
			break;
		}
		if (intent != null) {
			PackageManager packageManager = getActivity().getPackageManager();
			List<ResolveInfo> activities = packageManager
					.queryIntentActivities(intent, 0);
			boolean isIntentSafe = activities.size() > 0;
			// Start an activity if it's safe
			if (isIntentSafe) {
				startActivity(intent);
			}
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PICK_CONTACT_REQUEST:
			
			if(resultCode==Activity.RESULT_OK){
				Uri phone=data.getData();
				String[] projection = {Phone.NUMBER};
				Cursor cursor=getActivity().getContentResolver().query(phone, projection, null,null,null);
				int column = cursor.getColumnIndex(Phone.NUMBER);
				cursor.moveToFirst();
	            String number = cursor.getString(column);
				Toast.makeText(getActivity(), "phone num"+number, Toast.LENGTH_SHORT).show();
			}
			
			break;
		case CAMERA_CAPTURE_REQUEST:
			//Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_SHORT).show();
			Bundle bundle=data.getExtras();
			Bitmap bitmap=(Bitmap) bundle.get("data");
			iv.setImageBitmap(bitmap);
			break;
		case VIDEO_CAPTURE_REQUEST:
			Uri videoUri = data.getData();
	        video.setVideoURI(videoUri);
		default:
			break;
		}
	}
	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	
	
}
