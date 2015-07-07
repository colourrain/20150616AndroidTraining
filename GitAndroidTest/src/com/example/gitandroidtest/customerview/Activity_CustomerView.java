package com.example.gitandroidtest.customerview;

import java.util.ArrayList;
import java.util.List;

import com.example.gitandroidtest.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity_CustomerView extends Activity {
	
	EditText mRadius;
	PieChart pc;
	List<PiePortition> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customerview);
		mRadius=(EditText) findViewById(R.id.edit_radius);
		pc=(PieChart) findViewById(R.id.pieChart_test);
		InitData();
		pc.setData(list);
	}
	
	private void InitData() {
		list=new ArrayList<Activity_CustomerView.PiePortition>();
		Shader shader1=new LinearGradient(0, 0, 100, 200,new int[]{Color.RED,Color.RED}, null, Shader.TileMode.REPEAT);
		Shader shader2=new LinearGradient(0, 0, 100, 200,new int[]{Color.BLACK,Color.BLACK}, null, Shader.TileMode.REPEAT);
		Shader shader3=new LinearGradient(0, 0, 100, 200,new int[]{Color.BLUE,Color.BLUE}, null, Shader.TileMode.REPEAT);
		list.add(new PiePortition(0.30f,shader1));
		list.add(new PiePortition(0.60f,shader2));
		list.add(new PiePortition(0.10f,shader3));
		
	}

	public void onClick(View view){
		pc.setRadius(Float.parseFloat(mRadius.getText().toString()));
	}
	
	public class PiePortition{
		
		private float quota;
		private Shader shader;
		public PiePortition(float quota, Shader shader) {
			super();
			this.quota = quota;
			this.shader = shader;
		}

		public float getQuota() {
			return quota;
		}
		public void setQuota(float quota) {
			this.quota = quota;
		}
		public Shader getShader() {
			return shader;
		}
		public void setShader(Shader shader) {
			this.shader = shader;
		}		
	}

}
