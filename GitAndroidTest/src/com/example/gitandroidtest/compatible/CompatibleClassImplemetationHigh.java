package com.example.gitandroidtest.compatible;

import android.util.Log;

public class CompatibleClassImplemetationHigh extends AbstractCompatibleClass {

	@Override
	public void printLog(String msg) {
		// TODO Auto-generated method stub
		super.printLog(msg);
		Log.i("test","High Implementation Class Print-" + msg);
	}

	@Override
	public String getFeedback(String msg) {
		// TODO Auto-generated method stub
		return "This is high level android -- " + msg;
	}

	
}
