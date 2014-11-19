package com.ube.salinlahifour.lessonActivities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jdom.JDOMException;

import android.content.ClipData;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.ube.salinlahifour.Item;
import com.ube.salinlahifour.enumTypes.LevelType;
import com.ube.salinlahifour.R;

public class Family extends AbstractLessonActivity implements OnClickListener, OnTouchListener {
	private TextView tv_dialog;
	private TextView tv_feedback;
	private ImageButton[] choices;
	private int index;
	private ImageView iv_swipe;
	private String question;
	private String feedback = " ";
	//private ImageView[] speech_bubble;
	//private TextView[] feedback;
	//Timer Vars
	 TextView timerTextView;
	 CountDownTimer timer;
	//End Timer
	 private Object mPauseLock;
	public Family(){
		Log.d("Debug Family","Aldrin: Entered Family Class");
		layoutID = R.layout.lessonactivity_family;
		 mPauseLock = new Object();
		
	}
	


	@Override
	protected void initiateViews() {
		Log.d("Debug Family","Aldrin: Initiate Views");
		index = 1;
		
		//Starts Timer
		initiateTimer();
		//TextView DIALOG BOX (Questions)
		//tv_dialog = (TextView)findViewById(R.id.tv_dialog);//Var to XML
		//define layout
		//RelativeLayout.LayoutParams dialog_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//dialog_params.leftMargin=100; //X
		//dialog_params.topMargin = 30; //Y
		//tv_dialog.setLayoutParams(dialog_params);
		//TV DIALOG INIT END
		//Text View Feedback 
		tv_feedback = (TextView) findViewById(R.id.tv_feedback);
		tv_feedback.setText(" ");
		//RelativeLayout.LayoutParams feedback_params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//feedback_params.leftMargin=100; //X
		//feedback_params.bottomMargin = 60; //Y
		//tv_feedback.setLayoutParams(feedback_params);
		//TV FEEDBACK END
		choices = new ImageButton[9];
		Log.d("Debug Family","Aldrin: Initiate Choices");
		choices[0] = (ImageButton) findViewById(R.id.img_choicea);
		choices[1] = (ImageButton) findViewById(R.id.img_choiceb);
		choices[2] = (ImageButton) findViewById(R.id.img_choicec);
		choices[3] = (ImageButton) findViewById(R.id.img_choiced);
		choices[4] = (ImageButton) findViewById(R.id.img_choicee);
		choices[5] = (ImageButton) findViewById(R.id.img_choicef);
		choices[6] = (ImageButton) findViewById(R.id.img_choiceg);
		choices[7] = (ImageButton) findViewById(R.id.img_choiceh);
		choices[8] = (ImageButton) findViewById(R.id.img_choicei);
		//Log.d("Debug Family","Aldrin: Initiate Speechbubbles");
		//speech_bubble = new ImageView[choices.length];
		//speech_bubble[0] = (ImageView) findViewById(R.id.img_bubblea);
		//speech_bubble[1] = (ImageView) findViewById(R.id.img_bubbleb);
		//speech_bubble[2] = (ImageView) findViewById(R.id.img_bubblec);
		//speech_bubble[3] = (ImageView) findViewById(R.id.img_bubbled);
		//speech_bubble[4] = (ImageView) findViewById(R.id.img_bubblee);
		//Log.d("Debug Family","Aldrin: Initiate Feedback Text Views");
		//feedback = new TextView[choices.length];
		//feedback[0] = (TextView) findViewById(R.id.tv_feedbacka);
		//feedback[1] = (TextView) findViewById(R.id.tv_feedbackb);
		//feedback[2] = (TextView) findViewById(R.id.tv_feedbackc);
		//feedback[3] = (TextView) findViewById(R.id.tv_feedbackd);
		//feedback[4] = (TextView) findViewById(R.id.tv_feedbacke);
		RelativeLayout.LayoutParams params[] = new RelativeLayout.LayoutParams[choices.length];
		//Place Objects Randomly within max and min
		/*int max = 5000;
		int min = 1500;
		Random r = new Random();
		int X_coord = 0;
		for(int i = 0; i< choices.length; i++){
		params[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
		 X_coord = r.nextInt(max - min + 1) + min;
		params[i].leftMargin=X_coord; //X
		params[i].topMargin = 100; //Y
		choices[i].setLayoutParams(params[i]);
		//Log.d("Debug Family","Aldrin: Initiate Views: " + X_coord);
		}*/
		//End Random
		//Place Objects in Specified Location
		for(int i = 0; i< choices.length; i++){
			params[i] = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT); 
			params[i].topMargin = 100; //Y
		}
		params[0].leftMargin=500; //X
		params[1].leftMargin=1000; //X
		params[2].leftMargin=1500; //X
		params[3].leftMargin=2000; //X
		params[4].leftMargin=2500; //X
		params[5].leftMargin=2900; //X
		params[6].leftMargin=3100; //X
		params[7].leftMargin=3300; //X
		params[8].leftMargin=3600; //X
		for(int i = 0; i< choices.length; i++){
			choices[i].setLayoutParams(params[i]);
		}
		//End Placing
		//Initialize Swipe Indicator
		iv_swipe = (ImageView) findViewById(R.id.swipe_ind);
		Log.d("Debug Family","Aldrin: Initiate Views...Done");
	}

	protected void initiateItems() {
		//tv_feedback.setText("");	//delete
		Log.d("Debug Family","Aldrin: Initiate Items");
		items = new ArrayList<Item>();
		items.add(new Item("MissingNo" , "MissingNo", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.EASY));
		items.add(new Item("Lolo" , "Grandfather", "Can you find lolo?", R.drawable.family_lolo, null, LevelType.EASY));
		items.add(new Item("Lola" , "Grandmother", "Can you find Lola?", R.drawable.family_lola, null, LevelType.EASY));
		items.add(new Item("Nanay" , "Mother", "Can you find nanay?", R.drawable.family_nanay, null, LevelType.EASY));
		items.add(new Item("Tatay" , "Father", "Can you find tatay?", R.drawable.family_tatay, null, LevelType.EASY));
		items.add(new Item("Kuya" , "Brother", "Can you find kuya?", R.drawable.family_kuya, null, LevelType.EASY));
		items.add(new Item("Ate" , "Sister", "Can you find ate?", R.drawable.family_ate, null, LevelType.EASY));
		items.add(new Item("Bunso" , "Youngest", "Can you find bunso?", R.drawable.family_bunso, null, LevelType.EASY));
		items.add(new Item("Tito" , "Uncle", "Can you find tito?", R.drawable.family_tito, null, LevelType.EASY));
		items.add(new Item("Tita" , "Auntie", "Can you find tita?", R.drawable.family_tita, null, LevelType.EASY));
		Log.d("Debug Family","Aldrin: Initiate Items...Done");
	}

	@Override
	protected void run() {
		Log.d("Debug Family","Aldrin: Running");
		setChoices();
		//tv_dialog.setText(questions.get(index).getLabel());
		question = questions.get(index).getLabel();
		tv_feedback.setText(question);
		timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
		/*for(int choiceIndex = 0; choiceIndex < speech_bubble.length; choiceIndex++){
			speech_bubble[choiceIndex].setVisibility(View.GONE);
			feedback[choiceIndex].setVisibility(View.GONE);
		}*/
	}
	protected void rerun() {
		Log.d("Debug Family","Aldrin: Running");
		//setChoices();
		//tv_dialog.setText(questions.get(index).getLabel());
		question = questions.get(index).getLabel();
		 
		tv_feedback.setText(question);
		//timer.start();
		Log.d("Debug Family","Aldrin: Running Done");
		/*for(int choiceIndex = 0; choiceIndex < speech_bubble.length; choiceIndex++){
			speech_bubble[choiceIndex].setVisibility(View.GONE);
			feedback[choiceIndex].setVisibility(View.GONE);
		}*/
	}
	public void initiateTimer(){
		Log.d("Debug Family","Aldrin: Initiate Timer");
		timerTextView = (TextView) findViewById(R.id.timer_dialog);
		timerTextView.setText("");
		timer = new CountDownTimer(180000, 1000){

		     public void onTick(long millisUntilFinished) {
		    	 timerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		    	 timerTextView.setText("done!");
		     }
		  };
		
		Log.d("Debug Family","Aldrin: Initiate Timer...Done");
	}
	
	protected void checkAnswer(String answer) {
		Log.d("Debug Family","Aldrin: Checking Answer");
		try {
			if(questions.get(index).getWord().equals(answer)){
				//NLG Part - Correct
				Log.d("Debug Family", "Aldrin: Answer: " + answer);
				Log.d("Debug Family", "Aldrin: Index: " + index);
				feedback = NLG.GenerateImmediateFeedback(answer, index, 1);
				Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
				tv_feedback.setText(feedback + " " + question);
				Log.d("Debug Family", "Aldrin: Immediate Feedback Completed");
				if(index < questions.size()-1){
					Log.d("Debug Family", "Aldrin: Next Question(Reruns)");
					index++;
					rerun();
				}
				else{
					Log.d("Debug Family", "Aldrin: iFeedback says its finished (Delayed Feedback)");
					tv_feedback.setText("Nakakatuwa! You finished the game! You learned \"Bilog\"! Play again to practice more on \"Parisukat\"");
					//feedback = NLG.GenerateDelayedFeedback(score, LessonNum);
					timer.cancel();
				}
			}else{
				Log.d("Debug Family", "Aldrin: Answer: " + answer);
				Log.d("Debug Family", "Aldrin: Index: " + index);
				//NLG Part - Wrong
				//tv_feedback.setText("Oops. That's " + answer + ", Try Again!");
				feedback = NLG.GenerateImmediateFeedback(answer, index, 1);
				Log.d("Debug Family", "Aldrin: Feedback: "+ feedback);
				tv_feedback.setText(feedback + " " + question);
			}
		
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Debug Family", "Aldrin: Something went wrong with JDOM(CATCH)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("Debug Family", "Aldrin: Something went wrong in IO (CATCH)");
			e.printStackTrace();
		}
		
		Log.d("Debug Family","Aldrin: Answer Check");	
	}
    
	private void setChoices(){
		Log.d("Debug Family","Aldrin: Setting Choices");
		int answerIndex = new Random().nextInt(choices.length);
		ArrayList<Integer> taken = new ArrayList<Integer>();
		taken.add(index);
		for(int i = 0; i < choices.length; i++){
			if(i == answerIndex){
				choices[i].setImageResource(questions.get(index).getImageID());
				choices[i].setTag(questions.get(index).getWord());
			}
			else{
				int rand;
				do{
					rand = new Random().nextInt(items.size());
				}while(taken.contains(rand));
					taken.add(rand);
					choices[i].setImageResource(items.get(rand).getImageID());
					choices[i].setTag(items.get(rand).getWord());
			}
		}
		Log.d("Debug Family","Aldrin: Setting Choices...Done");
	}
	@Override
	public void onClick(View v) {
		int choice = 0;
		Log.d("OnClickListened", "Aldrin: Click!");
		//tv_feedback.setText("in onclick");
		Log.d("OnClickListened", "Aldrin: "+ ((ImageButton)findViewById(v.getId())).getTag() + " " + v.getId() );
		switch(v.getId()){
			case R.id.img_choicea: 	
			case R.id.img_choiceb:	
			case R.id.img_choicec:	
			case R.id.img_choiced:	
			case R.id.img_choicee:	
			case R.id.img_choicef:	
			case R.id.img_choiceg:	
			case R.id.img_choiceh:	
			case R.id.img_choicei:	
					checkAnswer(((ImageButton)findViewById(v.getId())).getTag() + "");
								break;
		
			default: 
				//tv_feedback.setText("error in onclick");
		}
		}

	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_SCROLL) {
			iv_swipe.setVisibility(View.GONE);
		    return true;
		} 
		else {
		    return false;
		}
	}

}

