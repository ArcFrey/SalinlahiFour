package com.ube.salinlahifour.lessonActivities;

import java.util.ArrayList;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.LevelType;
import com.ube.salinlahifour.R;

import android.view.View;

public class Society  extends AbstractLessonActivity{

	public Society(){
		layoutID = R.layout.lessonactivity_society;
	}

	@Override
	protected void initiateViews() {
		items = new ArrayList();
		items.add(new Item("Pulis" , "Police", "Which one is police", null, LevelType.EASY));
		items.add(new Item("Bombero" , "Fireman", "Which one is Fireman", null, LevelType.EASY));
	}

	@Override
	protected void initiateItems() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkAnswer(String answer) {
		// TODO Auto-generated method stub
		
	}

}
