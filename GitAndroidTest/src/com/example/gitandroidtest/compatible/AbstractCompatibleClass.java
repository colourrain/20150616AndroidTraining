package com.example.gitandroidtest.compatible;

import android.os.Build;
import android.util.Log;

public abstract class AbstractCompatibleClass {

	public static AbstractCompatibleClass CreateInstance(){
		 if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
	            return new CompatibleClassImplemetationHigh();
	        } else {
	            return new CompatibleClassImplemetationLow();
	        }
	}
	public void printLog(String msg){
		Log.i("test","Abstract Class Print-" + msg);
	};
	
	public abstract String getFeedback(String msg);
}
