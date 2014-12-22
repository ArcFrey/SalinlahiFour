package com.ube.salinlahifour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.uibuilders.Button.AbstractBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BackBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.BtnStatesDirector;
import com.ube.salinlahifour.uibuilders.Button.FemaleBtnStatesBuilder;
import com.ube.salinlahifour.uibuilders.Button.MaleBtnStatesBuilder;

public class RegistrationActivityGender extends Activity {
	private String name;
	private UserDetailOperations userDetailOperator;
	private ImageButton btn_male;
	private ImageButton btn_female;
	private ImageButton btn_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_gender);
		
		instantiateViews();
		
		userDetailOperator = new UserDetailOperations(this);
		userDetailOperator.open();	//DON'T FORGET
		Intent beforeIntent = getIntent();
		name = beforeIntent.getStringExtra("userName");
		
	}
	
	private void instantiateViews() {
		btn_male = (ImageButton)findViewById(R.id.btn_male);
		btn_female = (ImageButton)findViewById(R.id.btn_female);
		btn_back = (ImageButton)findViewById(R.id.btn_back);
		
		btn_male.setImageDrawable(BtnStatesDirector.getImageDrawable(new MaleBtnStatesBuilder()));
		btn_female.setImageDrawable(BtnStatesDirector.getImageDrawable(new FemaleBtnStatesBuilder()));
		btn_back.setImageDrawable(BtnStatesDirector.getImageDrawable(new BackBtnStatesBuilder()));

		btn_male.setBackgroundDrawable(null);
		btn_female.setBackgroundDrawable(null);
		btn_back.setBackgroundDrawable(null);
	}

	public void registerUser(View view){
		String gender;
		switch(view.getId()){
			case R.id.btn_male:
				gender = "male";
				break;
			case R.id.btn_female:
				gender = "female";
				break;
			default:
				gender = "bisexual";
		}

		Intent intent = new Intent(this, RegistrationActivityConfirm.class);
		Bundle extras = new Bundle();
		extras.putString("userName", name);
		extras.putString("userGender", gender);
		intent.putExtras(extras);
		startActivity(intent);
		
	}

	public void back(View view){
		this.finish();
	}
	
	@Override
	protected void onPause() {
		userDetailOperator.close();
		super.onPause();
	}

	@Override
	protected void onResume() {
		userDetailOperator.open();
		super.onResume();
	} 
}
