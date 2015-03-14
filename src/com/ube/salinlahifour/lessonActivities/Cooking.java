package com.ube.salinlahifour.lessonActivities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;


import com.kilobolt.framework.Game;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;
import com.ube.salinlahifour.lessonActivities.CookingColors.LoadingScreen;

public class Cooking extends AbstractLAFramework  {

	 
	 @Override
	 public Screen getInitScreen() {
		 Log.d("Debug Init", "Cooking");
		 return new LoadingScreen(this, activityLevel, UserID, this, lesson); 
	 }
	 @Override
	 public void onBackPressed() {
		 getCurrentScreen().backButton();
	 }
	/* public void showReportCard(){
		 super.showReportCard(this);
	 }*/
	 
	}
