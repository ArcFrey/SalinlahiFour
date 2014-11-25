package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.SalinlahiFour;

import iFeedback.iFeedback;

public abstract class AbstractLessonActivity extends Activity {
	protected ArrayList<ImageView> backgrounds;
	protected ArrayList<Item> items;
	protected ArrayList<Item> questions;
	protected ArrayList<SoundPool> timeoutvoices;
	protected String activityClass;
	protected String activityLevel;
	protected int layoutID;
	protected int UserID;	
	protected iFeedback NLG;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_lesson);
		setContentView(layoutID);
		
		Bundle bundle = getIntent().getExtras();
		activityClass = bundle.getString("activityClass");
		activityLevel = bundle.getString("activityLevel");
		UserID = bundle.getInt("UserID");
		Log.d(activityClass, "TEST ActivityName in lesson act");

		items = ((SalinlahiFour)getApplication()).getLessonItems();
		initiateNarrationModule();


		initiateViews();
		getQuestions();
		run();
	}
	
	protected void getQuestions(){
		Log.d("TESTINGLessonActivity", "Aldrin: getting Questions");
		questions = new ArrayList<Item>();
		questions.add(items.get(0));
		questions.add(items.get(1));
		questions.add(items.get(2));
	}
	
	protected void initiateNarrationModule(){
		Log.d("TESTINGLessonActivity", "Aldrin: Initiating iFeedback..");
		NLG = new iFeedback();
		Log.d("TESTINGLessonActivity", "Aldrin: Reading iFeedback properties");
		NLG.readProperties();
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback Initiated");
		Log.d("TESTINGLessonActivity", "Aldrin: iFeedback LOL");

	}
	
	protected void showReportCard(){
	}

	
	abstract protected void initiateViews();
	abstract protected void run();
	abstract protected void checkAnswer(String answer);
}
