package com.ube.salinlahifour.lessonActivities.CookingColors;


import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Sound;
import com.ube.salinlahifour.Lesson;
import com.ube.salinlahifour.MapActivity;
import com.ube.salinlahifour.SalinlahiFour;
import com.ube.salinlahifour.evaluationModule.*;
import com.ube.salinlahifour.lessonActivities.AbstractGameScreen;
import com.ube.salinlahifour.lessonActivities.GameOver;
import com.ube.salinlahifour.lessonActivities.CookingColors.Assets;
import com.ube.salinlahifour.lessonActivities.CookingColors.Parts;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.database.UserLessonProgressOperations;
import com.ube.salinlahifour.database.UserRecordOperations;


public class GameScreen extends AbstractGameScreen  {
	
	    // Variable Setup
	    // You would create game objects here.
	    static String activityName = "Colors";
	   
	    //String activityLevel;
	    
	    private Image bg, wrong, ready,feedboxChef,recipe;
	    private Parts pDialog, pRecipe;
	    private Image[] buttons_sprinkles;
	    private Image[] buttons_bread;
	    private Image[] buttons_frosting;
	    private Image bread, frosting, sprinkles;
	    private Cake cake;
	    private ButtonSet breaderButtons, creamerButtons, sprinklerButtons;
	    private int  userID, isSubmit = 1;
	    private Sound[] sounds; 
	    // Edit lives left to the question size
	    private String[] questions;
	    private String[] feedbacks;
	    private Image backbtn, nobtn, yesbtn, bgBack;
	    private Parts pNo,pYes,pBackg;
	    private boolean isMistake = false;
	    Assets as;
	    private int cor1 = 0, cor2 = 0, cor3 = 0;
	    public GameScreen(Game game, String activityLevel, int userID, Context context, Lesson lesson) {
	    	//Super Parameters Game, ActivityName, ActivityLevel, UserID
	        super(game, activityName, activityLevel, userID, context, lesson);
	        Log.d("Aldrin ExtendedFramework", "This should be after abstract Game");
	        this.userID = userID;
	        this.activityLevel = activityLevel;
	        Log.d("Aldrin ExtendedFramework", "Gamescreen constructor...");
	       questions = new String[3];
	       feedbacks = new String[3];
	       rounds = 1;
	       
	       lessonNumber = 3;
	       
	       for(int i=0; i<feedbacks.length;i++){
	    	   feedbacks[i] = "";
	       }
	    }

		@Override
		protected void loadAssets() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Loading Assets");
	        eval.setLexiconDir("lexicon_cooking.xml");
	        transition = false;
	        exit = false;
	        buttons_bread = new Image[Assets.buttons.size()];
	        buttons_frosting = new Image[Assets.buttons.size()];
	        buttons_sprinkles = new Image[Assets.buttons.size()];
	        sounds = new Sound[Assets.sound.size()];
	        bg = Assets.bg;
	        bread = Assets.nothingness;
	        frosting = Assets.nothingness;
	        sprinkles = Assets.nothingness;
	        wrong = Assets.nothingness;
	        ready = Assets.nothingness;
	        feedboxChef = Assets.nothingness;
	        recipe = Assets.envelope;
	        pDialog = new Parts(160,10);
	        pRecipe = new Parts(160,375);
	        for (int i = 0; i< buttons_bread.length; i++){
	        	buttons_bread[i] = Assets.buttons.get(i);
	        	buttons_frosting[i] = Assets.buttons.get(i);
	        	buttons_sprinkles[i] = Assets.buttons.get(i);
	        	sounds[i] = Assets.sound.get(i);
	        }
	        this.backbtn = Assets.backbtn;
	        this.bgBack = Assets.bgBack;
	        this.yesbtn = Assets.yesbtn;
	        this.nobtn = Assets.nobtn;
	        pYes = new Parts(220,300);
	        pNo = new Parts(400,300);
	        pBackg = new Parts(195, 100);
		    Log.d("Aldrin ExtendedFramework", "Loading Assets...Done");
		}

		@Override
		protected void assetPositionEasy() {
			// TODO Auto-generated method stub
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets");
			
	        breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons_bread[0].getWidth() + " Height: " + buttons_bread[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons_bread[0].getWidth(), buttons_bread[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight(), 2, 3);
	        
	        livesLeft = 3;
	        
	        cake = new Cake();

			breaderButtons.loadAnswer(activityLevel);
			creamerButtons.loadAnswer(activityLevel);
			sprinklerButtons.loadAnswer(activityLevel);
			
			breaderButtons.loadQuestions();
			creamerButtons.loadQuestions();
			sprinklerButtons.loadQuestions();
			
	        Log.d("Aldrin ExtendedFramework", "Positioning Easy Assets...Done");
		}

		@Override
		protected void assetPositionMedium() {
			// TODO Auto-generated method stub
		   Log.d("GameScreen", "Positioning Medium"); 
			
			breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons_bread[0].getWidth() + " Height: " + buttons_bread[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons_bread[0].getWidth(), buttons_bread[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight(), 2, 3);
	        livesLeft = 5;
	        cake = new Cake();
			
			breaderButtons.loadRandomColors(6);
			creamerButtons.loadRandomColors(6);
			sprinklerButtons.loadRandomColors(6);
			
			breaderButtons.loadAnswer(activityLevel);
			creamerButtons.loadAnswer(activityLevel);
			sprinklerButtons.loadAnswer(activityLevel);
			
			breaderButtons.loadQuestions();
			creamerButtons.loadQuestions();
			sprinklerButtons.loadQuestions();
			
 	       Log.d("GameScreen", "Positioning Medium...Done"); 
		}
		
		@Override
		protected void assetPositionHard() {
			// TODO Auto-generated method stub
			 livesLeft = 10;
			breaderButtons = new ButtonSet(4, 70,40);
	        Log.d("ButtonDebug", "Wdith: " + buttons_bread[0].getWidth() + " Height: " + buttons_bread[0].getHeight());
	        breaderButtons.calculateButtonPosition(buttons_bread[0].getWidth(), buttons_bread[0].getHeight(), 2, 3);
	        
	        creamerButtons = new ButtonSet(4, 290, 40);
	        creamerButtons.calculateButtonPosition(buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight(), 2, 3);
	        
	        sprinklerButtons = new ButtonSet(4, 510, 40);
	        sprinklerButtons.calculateButtonPosition(buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight(), 2, 3);
	        
	        cake = new Cake();
			
			breaderButtons.loadRandomColors(8);
			creamerButtons.loadRandomColors(8);
			sprinklerButtons.loadRandomColors(8);
			
			breaderButtons.loadAnswer(activityLevel);
			creamerButtons.loadAnswer(activityLevel);
			sprinklerButtons.loadAnswer(activityLevel);
			
			breaderButtons.loadQuestions();
			creamerButtons.loadQuestions();
			sprinklerButtons.loadQuestions();
		}

		@Override
		protected void updateRunningEasy(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			 
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            
	            if(isSubmit == 1){
	            	questions[0] = breaderButtons.createQuestions(activityLevel,0);
	            	questions[1] = creamerButtons.createQuestions(activityLevel,1);
	            	questions[2] = sprinklerButtons.createQuestions(activityLevel,2);
	            	isSubmit = 0;
	            	recipe = Assets.instructions;
	        		
	            	Log.d("Question", breaderButtons.getQuestionColor() + "");
	            	Log.d("Question", creamerButtons.getQuestionColor() + "");
	            	Log.d("Question", sprinklerButtons.getQuestionColor()+ "");
	            }
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) { //Happens When you press a specifi
	            	Log.d("Touched Down", "X: " + event.x + "Y: " + event.y );
	            	if(inBounds(event, 1 ,1 ,this.backbtn.getWidth(), this.backbtn.getHeight())){
	            		exit = true;
	            	}
		            if(exit){
		            	if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
		            		Log.d("Exit Debug", "This should be once: NO");
		            		exit = false;
		            	}else if (inBounds(event, pYes.getX() ,pYes.getY() ,this.yesbtn.getWidth(), this.yesbtn.getWidth())){
		            		Log.d("Exit Debug", "Quit");
		            		Looper.myLooper().quit();
		            		Intent intent = new Intent(context,MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		            		context.startActivity(intent); 
			            }else if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
			            	exit = false;
			            	Log.d("Exit Debug", "Continue");
			            }
		            }
	            	if(transition){
	            		if(inBounds(event, pRecipe.getX() ,pRecipe.getY() , recipe.getWidth(), recipe.getHeight())){
	            		Log.d("Transition Debug", "Falseing in easy");
	            		recipe = Assets.instructions;
	   				  	transition = false;
	   				  		cor1 = 0; 
	   				  		cor2 = 0;
	   				  		cor3 = 0;
	            		}
	            	}else{
	 	            	
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)){//if bread
	            		cake.move(50, 250);
	            		Log.d("GameScreen", "Thats a breader");
	            		if(inBounds(event, breaderButtons.getX(0) ,breaderButtons.getY(0) , buttons_bread[0].getWidth(), buttons_bread[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a breader and blue button");	
	            			bread = Assets.bread.get(0);
	            			cake.addBead("Asul");
	            			sounds[0].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_bread[0] = Assets.buttons_pressed.get(0);
	            		}else if(inBounds(event, breaderButtons.getX(1) ,breaderButtons.getY(1) , buttons_bread[1].getWidth(), buttons_bread[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a breader and green button");	
	            			bread = Assets.bread.get(1);
	            			cake.addBead("Berde");
	            			sounds[1].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_bread[1] = Assets.buttons_pressed.get(1);
	            		}else if(inBounds(event, breaderButtons.getX(2) ,breaderButtons.getY(2) , buttons_bread[2].getWidth(), buttons_bread[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a breader and red button");	
	            			bread = Assets.bread.get(2);
	            			cake.addBead("Pula");
	            			sounds[2].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_bread[2] = Assets.buttons_pressed.get(2);
	            		}else if(inBounds(event, breaderButtons.getX(3) ,breaderButtons.getY(3) , buttons_bread[3].getWidth(), buttons_bread[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a breader and yellow button");	
	            			bread = Assets.bread.get(3);
	            			cake.addBead("Dilaw");
	            			sounds[3].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_bread[3] = Assets.buttons_pressed.get(3);
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)){//if frosting
	            		cake.move(270, 250);
	            		Log.d("GameScreen", "Thats a creamer");
	            		if(inBounds(event, creamerButtons.getX(0) ,creamerButtons.getY(0) , buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a creamer and blue button");	
	            			frosting = Assets.frosting.get(0);
	            			cake.addCream("Asul");
	            			sounds[0].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_frosting[0] = Assets.buttons_pressed.get(0);
	            		}else if(inBounds(event, creamerButtons.getX(1) ,creamerButtons.getY(1) , buttons_frosting[1].getWidth(), buttons_frosting[1].getHeight())){//if green
	            			Log.d("GameScreen", "Thats a creamer and green button");	
	            			frosting = Assets.frosting.get(1);
	            			cake.addCream("Berde");
	            			sounds[1].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_frosting[1] = Assets.buttons_pressed.get(1);
	            		}else if(inBounds(event, creamerButtons.getX(2) ,creamerButtons.getY(2) , buttons_frosting[2].getWidth(), buttons_frosting[2].getHeight())){//if red
	            			Log.d("GameScreen", "Thats a creamer and red button");	
	            			frosting = Assets.frosting.get(2);
	            			cake.addCream("Pula");
	            			sounds[2].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_frosting[2] = Assets.buttons_pressed.get(2);
	            		}else if(inBounds(event, creamerButtons.getX(3) ,creamerButtons.getY(3) , buttons_frosting[3].getWidth(), buttons_frosting[3].getHeight())){//if yellow
	            			Log.d("GameScreen", "Thats a creamer and yellow button");	
	            			frosting = Assets.frosting.get(3);
	            			cake.addCream("Dilaw");
	            			sounds[3].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_frosting[3] = Assets.buttons_pressed.get(3);
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)){//if sprikinle
	            		cake.move(485, 250);
	            		if(inBounds(event, sprinklerButtons.getX(0) ,sprinklerButtons.getY(0) , buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight())){//if blue
	            			sprinkles = Assets.sprinkles.get(0);
	            			cake.addSprinkles("Asul");
	            			Log.d("GameScreen", "Thats a sprinkle and Blue button");
	            			sounds[0].play(0.85f);
	            			wrong = Assets.nothingness;
	            			buttons_sprinkles[0] = Assets.buttons_pressed.get(0);
	            		}else if(inBounds(event, sprinklerButtons.getX(1) ,sprinklerButtons.getY(1) , buttons_sprinkles[1].getWidth(), buttons_sprinkles[1].getHeight())){//if green
	            			sprinkles = Assets.sprinkles.get(1);
	            			cake.addSprinkles("Berde");
	            			wrong = Assets.nothingness;
	            			buttons_sprinkles[1] = Assets.buttons_pressed.get(1);
	            			Log.d("GameScreen", "Thats a sprinkle and Green button");
	            			sounds[1].play(0.85f);
	            		}else if(inBounds(event, sprinklerButtons.getX(2) ,sprinklerButtons.getY(2) , buttons_sprinkles[2].getWidth(), buttons_sprinkles[2].getHeight())){//if red
	            			sprinkles = Assets.sprinkles.get(2);
	            			cake.addSprinkles("Pula");
	            			wrong = Assets.nothingness;
	            			Log.d("GameScreen", "Thats a sprinkle and Red button");
	            			sounds[2].play(0.85f);
	            			buttons_sprinkles[2] = Assets.buttons_pressed.get(2);
	            		}else if(inBounds(event, sprinklerButtons.getX(3) ,sprinklerButtons.getY(3) , buttons_sprinkles[3].getWidth(), buttons_sprinkles[3].getHeight())){//if yellow
	            			sprinkles = Assets.sprinkles.get(3);
	            			cake.addSprinkles("Dilaw");
	            			wrong = Assets.nothingness;
	            			Log.d("GameScreen", "Thats a sprinkle and yellow button");
	            			sounds[3].play(0.85f);
	            			buttons_sprinkles[3] = Assets.buttons_pressed.get(3);
	            		}
	            	}
	            	if(cake.isBread() && cake.isCream() && cake.isSprinkled()){
	            		ready = Assets.ready;
	            	}
	            	if(inBounds(event, cake.getX() , cake.getY(), bread.getWidth(), bread.getHeight())){
	            		Log.d("Cake", "You clicked on cake");
	            		if(cake.isBread() && cake.isCream() && cake.isSprinkled()){
	            			userRecordOperator.open();
	            			userLessonProgressOperator.open();
	            			//eval.evaluateAnswer(CorrectAnswer, UserAnswer, userRecordOperator, UserID)
	            			for(int s = 0; s<feedbacks.length;s++){
	            				feedbacks[s] = "";
	            			}
	            			//int cor1 = 0, cor2 = 0, cor3 = 0;
	            		
	            			if(eval.evaluateAnswer( breaderButtons.getQuestionColor(), cake.getAnswer(0), userID)){
	            				cor1 = 1;
	            				Log.d("Debug Eval",breaderButtons.getNumberColor()+1 + " + " + cake.getAnswer(0) + " + " + lessonNumber  + " X" +  breaderButtons.getQuestionColor());
	            				feedbacks[0] = eval.getImmediateFeedback(breaderButtons.getNumberColor()+1, cake.getAnswer(0), lessonNumber);
	            			}else{
	            				feedbacks[0] = eval.getImmediateFeedback(breaderButtons.getNumberColor()+1, cake.getAnswer(0), lessonNumber);
		            			
	            			}
	            			if(eval.evaluateAnswer( creamerButtons.getQuestionColor(), cake.getAnswer(1), userID)){
	            				cor2 = 1;
	            				Log.d("Debug Eval",creamerButtons.getNumberColor()+1+ " + " + cake.getAnswer(1) + " + " + lessonNumber + " X" +  creamerButtons.getQuestionColor() );
	            				feedbacks[1] = eval.getImmediateFeedback(creamerButtons.getNumberColor()+1, cake.getAnswer(1), lessonNumber);
	            			}else{
	            				feedbacks[1] = eval.getImmediateFeedback(creamerButtons.getNumberColor()+1, cake.getAnswer(1), lessonNumber);
		            			
	            			}
	            			if(eval.evaluateAnswer( sprinklerButtons.getQuestionColor(), cake.getAnswer(2), userID)){
	            				cor3 = 1;
	            				Log.d("Debug Eval",sprinklerButtons.getNumberColor()+1 + " + " + cake.getAnswer(2) + " + " + lessonNumber + " X" +  sprinklerButtons.getQuestionColor() );
	            				feedbacks[2] = eval.getImmediateFeedback(sprinklerButtons.getNumberColor()+1, cake.getAnswer(2), lessonNumber);
	            			}else{
	            				feedbacks[2] = eval.getImmediateFeedback(sprinklerButtons.getNumberColor()+1, cake.getAnswer(2), lessonNumber);
		            			
	            			}
	            			if(cor1 == 1 && cor2 == 1 && cor3 == 1){
	            				isSubmit = 1;
	            				livesLeft--;
	            				isMistake = false;
	            				transition = true;
	            				sFeedback = feedbacks[0];
	            				   
		            			    ready = Assets.nothingness;
		            				bread = Assets.nothingness;
		            			    frosting = Assets.nothingness;
		            			    sprinkles = Assets.nothingness;
		            			    cake.resetFlags();
		            			    
	            				Log.d("DING", "Its done");
	            			}else{
	            				ready = Assets.nothingness;
	            				wrong = Assets.wrong;
	            				Log.d("DING", "Wrong Order!");
	            				transition = true;
	            				
		            			cake.resetFlags();
		            			isMistake = true;
	            			}
	            			userRecordOperator.close();
	            			userLessonProgressOperator.close();
	            		}
	            	}
	 	            	}
	            
	            }
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)){//if bread
	            		if(inBounds(event, breaderButtons.getX(0) ,breaderButtons.getY(0) , buttons_bread[0].getWidth(), buttons_bread[0].getHeight())){//if blue
	            			buttons_bread[0] = Assets.buttons.get(0);
	            		}else if(inBounds(event, breaderButtons.getX(1) ,breaderButtons.getY(1) , buttons_bread[1].getWidth(), buttons_bread[1].getHeight())){//if blue
	            			buttons_bread[1] = Assets.buttons.get(1);
	            		}else if(inBounds(event, breaderButtons.getX(2) ,breaderButtons.getY(2) , buttons_bread[2].getWidth(), buttons_bread[2].getHeight())){//if blue
	            			buttons_bread[2] = Assets.buttons.get(2);
	            		}else if(inBounds(event, breaderButtons.getX(3) ,breaderButtons.getY(3) , buttons_bread[3].getWidth(), buttons_bread[3].getHeight())){//if blue
	            			buttons_bread[3] = Assets.buttons.get(3);
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)){//if frostying
	            		if(inBounds(event, creamerButtons.getX(0) ,creamerButtons.getY(0) , buttons_frosting[0].getWidth(), buttons_frosting[0].getHeight())){//if blue
	            			buttons_frosting[0] = Assets.buttons.get(0);
	            		}else if(inBounds(event, creamerButtons.getX(1) ,creamerButtons.getY(1) , buttons_frosting[1].getWidth(), buttons_frosting[1].getHeight())){//if blue
	            			buttons_frosting[1] = Assets.buttons.get(1);
	            		}else if(inBounds(event, creamerButtons.getX(2) ,creamerButtons.getY(2) , buttons_frosting[2].getWidth(), buttons_frosting[2].getHeight())){//if blue
	            			buttons_frosting[2] = Assets.buttons.get(2);
	            		}else if(inBounds(event, creamerButtons.getX(3) ,creamerButtons.getY(3) , buttons_frosting[3].getWidth(), buttons_frosting[3].getHeight())){//if blue
	            			buttons_frosting[3] = Assets.buttons.get(3);
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)){//if sprinkle
	            		if(inBounds(event, sprinklerButtons.getX(0) ,sprinklerButtons.getY(0) , buttons_sprinkles[0].getWidth(), buttons_sprinkles[0].getHeight())){//if blue
	            			buttons_sprinkles[0] = Assets.buttons.get(0);

	            		}else if(inBounds(event, sprinklerButtons.getX(1) ,breaderButtons.getY(1) , buttons_sprinkles[1].getWidth(), buttons_sprinkles[1].getHeight())){//if blue
	            			buttons_sprinkles[1] = Assets.buttons.get(1);

	            		}else if(inBounds(event, sprinklerButtons.getX(2) ,sprinklerButtons.getY(2) , buttons_sprinkles[2].getWidth(), buttons_sprinkles[2].getHeight())){//if blue
	            			buttons_sprinkles[2] = Assets.buttons.get(2);

	            		}else if(inBounds(event, sprinklerButtons.getX(3) ,sprinklerButtons.getY(3) , buttons_sprinkles[3].getWidth(), buttons_sprinkles[3].getHeight())){//if blue
	            			buttons_sprinkles[3] = Assets.buttons.get(3);
	            		}
	            	}
	            }
	        }
		}

		@Override
		protected void updateRunningMedium(List<TouchEvent> touchEvents,float deltaTime) {
			// TODO Auto-generated method stub
			int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if(isSubmit == 1){
	            questions[0] = breaderButtons.createQuestions(activityLevel,0);
	            questions[1] = creamerButtons.createQuestions(activityLevel,1);
	            questions[2] = sprinklerButtons.createQuestions(activityLevel,2);
	            isSubmit = 0;
	            recipe = Assets.instructions;
	            }
	            
	            /////////////////////////////////////////////
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	if(inBounds(event, 1 ,1 ,this.backbtn.getWidth(), this.backbtn.getHeight())){
	            		exit = true;
	            	}
		            if(exit){
		            	if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
		            		Log.d("Exit Debug", "This should be once: NO");
		            		exit = false;
		            	}else if (inBounds(event, pYes.getX() ,pYes.getY() ,this.yesbtn.getWidth(), this.yesbtn.getWidth())){
		            		Log.d("Exit Debug", "Quit");
		            		Looper.myLooper().quit();
		            		Intent intent = new Intent(context,MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		            		context.startActivity(intent); 
			            }else if(inBounds(event, pNo.getX() ,pNo.getY() ,this.nobtn.getWidth(), this.nobtn.getWidth())){
			            	exit = false;
			            	Log.d("Exit Debug", "Continue");
			            }
		            }
	            	if(transition){
	            		if(inBounds(event, pRecipe.getX() ,pRecipe.getY() , recipe.getWidth(), recipe.getHeight())){
	            		Log.d("Transition Debug", "Falseing in easy");
	            		recipe = Assets.instructions;
	   				  	transition = false;
	   				  	cor1 = 0; 
	   				  	cor2 = 0;
	   				  	cor3 = 0;
	            		}
	            	}else{
	            	wrong = Assets.nothingness;
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)){//if bread
	            		cake.move(50, 250);
	            		Log.d("GameScreen", "Thats a breader");
	            		for(int bread_i = 0; bread_i<4; bread_i++){
	            		if(inBounds(event, breaderButtons.getX(bread_i) ,breaderButtons.getY(bread_i) , buttons_bread[bread_i].getWidth(), buttons_bread[bread_i].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a breader and" + breaderButtons.getAnswer(breaderButtons.getChosenColors(bread_i)));	
	            			bread = Assets.bread.get(breaderButtons.getChosenColors(bread_i));//correct
	            			cake.addBead(breaderButtons.getAnswer(breaderButtons.getChosenColors(bread_i))); //correct
	            			sounds[breaderButtons.getChosenColors(bread_i)].play(0.85f);
	            			//buttons_bread[0] = Assets.buttons_pressed.get(breaderButtons.getChosenColors(0));
	            			buttons_bread[breaderButtons.getChosenColors(bread_i)] = Assets.buttons_pressed.get(breaderButtons.getChosenColors(bread_i));
	            			Log.d("Buttons", "Breader Index 0 Chosen Color Index: " + breaderButtons.getChosenColors(bread_i) );
	            		}
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)){//if frosting
	            		cake.move(270, 250);
	            		Log.d("GameScreen", "Thats a creamerButtons");
	            		for(int cream_i = 0; cream_i<4; cream_i++){
	            		if(inBounds(event, creamerButtons.getX(cream_i) ,creamerButtons.getY(cream_i) , buttons_frosting[cream_i].getWidth(), buttons_frosting[cream_i].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a creamerButtons and" + creamerButtons.getAnswer(creamerButtons.getChosenColors(cream_i)));	
	            			frosting = Assets.frosting.get(creamerButtons.getChosenColors(cream_i));//correct
	            			cake.addCream(creamerButtons.getAnswer(creamerButtons.getChosenColors(cream_i))); //correct
	            			sounds[creamerButtons.getChosenColors(cream_i)].play(0.85f);
	            			//buttons_bread[0] = Assets.buttons_pressed.get(breaderButtons.getChosenColors(0));
	            			buttons_frosting[creamerButtons.getChosenColors(cream_i)] = Assets.buttons_pressed.get(creamerButtons.getChosenColors(cream_i));
	            			Log.d("Buttons", "Creamer Index 0 Chosen Color Index: " + creamerButtons.getChosenColors(cream_i) );
	            		}
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)){//if bread
	            		cake.move(485, 250);
	            		Log.d("GameScreen", "Thats a sprinklerButtons");
	            		for(int sprinkle_i = 0; sprinkle_i<4; sprinkle_i++){
	            		if(inBounds(event, sprinklerButtons.getX(sprinkle_i) ,sprinklerButtons.getY(sprinkle_i) , buttons_sprinkles[sprinkle_i].getWidth(), buttons_sprinkles[sprinkle_i].getHeight())){//if blue
	            			Log.d("GameScreen", "Thats a sprinklerButtons and" + sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(sprinkle_i)));	
	            			sprinkles = Assets.sprinkles.get(sprinklerButtons.getChosenColors(sprinkle_i));//correct
	            			cake.addSprinkles(sprinklerButtons.getAnswer(sprinklerButtons.getChosenColors(sprinkle_i))); //correct
	            			sounds[sprinklerButtons.getChosenColors(sprinkle_i)].play(0.85f);
	            			//buttons_bread[0] = Assets.buttons_pressed.get(sprinklerButtons.getChosenColors(0));
	            			buttons_sprinkles[sprinklerButtons.getChosenColors(sprinkle_i)] = Assets.buttons_pressed.get(sprinklerButtons.getChosenColors(sprinkle_i));
	            			Log.d("Buttons", "Sprinkler Index 0 Chosen Color Index: " + sprinklerButtons.getChosenColors(sprinkle_i) );
	            		}
	            		}
	            	}
	            	if(cake.isBread() && cake.isCream() && cake.isSprinkled()){
	            		ready = Assets.ready;
	            	}
	            		if(inBounds(event, cake.getX() , cake.getY(), bread.getWidth(), bread.getHeight())){
	            			if(cake.isBread() && cake.isCream() && cake.isSprinkled()){
	            				for(int s = 0; s<feedbacks.length;s++){
		            				feedbacks[s] = "";
		            			}
		            			userRecordOperator.open();
		            			userLessonProgressOperator.open();
		            			//eval.evaluateAnswer(CorrectAnswer, UserAnswer, userRecordOperator, UserID)
		            			
		            			
		            			Log.d("Question  Color",breaderButtons.getQuestionColor()+"- " +creamerButtons.getQuestionColor()+"-"+sprinklerButtons.getQuestionColor() );
		            			Log.d("Cake Colors",cake.getAnswer(0)+"- " +cake.getAnswer(1)+"-"+cake.getAnswer(2) );
		            			Log.d("Number Color",breaderButtons.getNumberColor()+"- " +creamerButtons.getNumberColor()+"-"+sprinklerButtons.getNumberColor() );
		            			
		            			if(eval.evaluateAnswer( breaderButtons.getQuestionColor(), cake.getAnswer(0), userID)){
		            				cor1 = 1;
		            				feedbacks[0] = eval.getImmediateFeedback(breaderButtons.getNumberColor()+1, cake.getAnswer(0), lessonNumber);
		            			}else{
		            				feedbacks[0] = eval.getImmediateFeedback(breaderButtons.getNumberColor()+1, cake.getAnswer(0), lessonNumber);
			            			
		            			}
		            			if(eval.evaluateAnswer( creamerButtons.getQuestionColor(), cake.getAnswer(1), userID)){
		            				cor2 = 1;
		            				feedbacks[1] = eval.getImmediateFeedback(creamerButtons.getNumberColor()+1, cake.getAnswer(1), lessonNumber);
		            			}else{
		            				feedbacks[1] = eval.getImmediateFeedback(creamerButtons.getNumberColor()+1, cake.getAnswer(1), lessonNumber);
			            			
		            			}
		            			if(eval.evaluateAnswer( sprinklerButtons.getQuestionColor(), cake.getAnswer(2), userID)){
		            				cor3 = 1;
		            				feedbacks[2] = eval.getImmediateFeedback(sprinklerButtons.getNumberColor()+1, cake.getAnswer(2), lessonNumber);
		            			}else{
		            				feedbacks[2] = eval.getImmediateFeedback(sprinklerButtons.getNumberColor()+1, cake.getAnswer(2), lessonNumber);
			            			
		            			}
		            			if(cor1 == 1 && cor2 == 1 && cor3 == 1){
		            				isMistake = false;
		            				transition = true;
		            				isSubmit = 1;
		            				livesLeft--;
		            				Log.d("DING", "Its done");
		            				
		            				
		            			    ready = Assets.nothingness;
		            				bread = Assets.nothingness;
		            			    frosting = Assets.nothingness;
		            			    sprinkles = Assets.nothingness;
		            			    cake.resetFlags();
		            			}else{
		            				transition = true;
		            				isMistake = true;
		            				ready = Assets.nothingness;
		            				wrong = Assets.wrong;
		            				Log.d("DING", "Wrong Order!");
		            				
			            			cake.resetFlags();
		            			}
		            			userRecordOperator.close();
		            			userLessonProgressOperator.close();
		            		}
		            	}
	            }
	            	}
	            
	            if (event.type == TouchEvent.TOUCH_UP) {
	            	if(inBounds(event, breaderButtons.getInitX() ,breaderButtons.getInitY() , buttons_bread[0].getWidth() * 2, buttons_bread[0].getHeight() * 2)){//if bread
	            		for(int bread_i = 0; bread_i <4; bread_i++ ){
	            		if(inBounds(event, breaderButtons.getX(bread_i) ,breaderButtons.getY(bread_i) , buttons_bread[bread_i].getWidth(), buttons_bread[bread_i].getHeight())){//if blue
	            			buttons_bread[breaderButtons.getChosenColors(bread_i)] = Assets.buttons.get(breaderButtons.getChosenColors(bread_i));
	            		}
	            		}
	            	}else if(inBounds(event, creamerButtons.getInitX() ,creamerButtons.getInitY() , buttons_frosting[0].getWidth() * 2, buttons_frosting[0].getHeight() * 2)){//if frostying
	            		for(int cream_i = 0; cream_i <4; cream_i++ ){
	            		if(inBounds(event, creamerButtons.getX(cream_i) ,creamerButtons.getY(cream_i) , buttons_frosting[cream_i].getWidth(), buttons_frosting[cream_i].getHeight())){//if blue
	            			buttons_frosting[creamerButtons.getChosenColors(cream_i)] = Assets.buttons.get(creamerButtons.getChosenColors(cream_i));
	            		}
	            		}
	            	}else if(inBounds(event, sprinklerButtons.getInitX() ,sprinklerButtons.getInitY() , buttons_sprinkles[0].getWidth() * 2, buttons_sprinkles[0].getHeight() * 2)){//if sprinkle
	            		for(int sprinkle_i = 0; sprinkle_i <4; sprinkle_i++ ){
	            		if(inBounds(event, sprinklerButtons.getX(sprinkle_i) ,sprinklerButtons.getY(sprinkle_i) , buttons_sprinkles[sprinkle_i].getWidth(), buttons_sprinkles[sprinkle_i].getHeight())){//if blue
	            			buttons_sprinkles[sprinklerButtons.getChosenColors(sprinkle_i)] = Assets.buttons.get(sprinklerButtons.getChosenColors(sprinkle_i));

	            		}
	            		}
	            	}
	            }
	        }
		}

		@Override
		protected void updateRunningHard(List<TouchEvent> touchEvents, float deltaTime) {
			// TODO Auto-generated method stub
			updateRunningMedium(touchEvents, deltaTime);
		}

		@Override
		protected void updatePaused(List<TouchEvent> touchEvents) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void updateGameOver(List<TouchEvent> touchEvents) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
		@Override
		protected void nullify() {
			// TODO Auto-generated method stub
			 paint = null;
			 paint2 = null;
		     bg = null;
		     bread = null;
		     frosting = null;
		     sprinkles = null;
		    
		        System.gc();

		}

		@Override
		protected void painterEasy() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			
			 g.drawImage(bg, 0, 0);
			 g.drawImage(wrong, cake.getX(), cake.getY());
			 g.drawImage(ready, cake.getX(), cake.getY());
			 g.drawImage(recipe, pRecipe.getX(), pRecipe.getY());
			 
			 if(cake.isBread()){
				 g.drawImage(bread, cake.getX(), cake.getY());
			 }
			 if(cake.isCream()){
				 g.drawImage(frosting, cake.getX(), cake.getY());
			 }
			 if(cake.isSprinkled()){
				 g.drawImage(sprinkles, cake.getX(), cake.getY());
			 }
			
			 for(int i = 0; i<buttons_bread.length;i++){
				// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) + " Y: " + breaderButtons.getY(i));
				 g.drawImage(buttons_bread[i], breaderButtons.getX(i), breaderButtons.getY(i));
				 g.drawImage(buttons_frosting[i], creamerButtons.getX(i), creamerButtons.getY(i));
				 g.drawImage(buttons_sprinkles[i], sprinklerButtons.getX(i), sprinklerButtons.getY(i));
			 }
			 
			 
			 }
		protected void painterMedium() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			g.drawImage(bg, 0, 0);
			g.drawImage(ready, cake.getX(), cake.getY());
			g.drawImage(wrong, cake.getX(), cake.getY());
			g.drawImage(recipe, pRecipe.getX(), pRecipe.getY());
			 if(cake.isBread()){
				 g.drawImage(bread, cake.getX(), cake.getY());
			 }
			 if(cake.isCream()){
				 g.drawImage(frosting, cake.getX(), cake.getY());
			 }
			 if(cake.isSprinkled()){
				 g.drawImage(sprinkles, cake.getX(), cake.getY());
			 }
			 for(int i = 0; i<4;i++){
					// Log.d("Painter", "I: " + i + " X: "+ breaderButtons.getX(i) + " Y: " + breaderButtons.getY(i));
					 g.drawImage(buttons_bread[breaderButtons.getChosenColors(i)], breaderButtons.getX(i), breaderButtons.getY(i));
					 g.drawImage(buttons_frosting[creamerButtons.getChosenColors(i)], creamerButtons.getX(i), creamerButtons.getY(i));
					 g.drawImage(buttons_sprinkles[sprinklerButtons.getChosenColors(i)], sprinklerButtons.getX(i), sprinklerButtons.getY(i));
				 }
			}
		protected void painterHard() {
			// TODO Auto-generated method stub
			 Graphics g = game.getGraphics();
			 painterMedium(); 
		}

		@Override
		protected void drawRunningUI() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			g.drawImage(backbtn, 1, 1);
			//g.drawString(feedbacks[0], 300, 425, paint3);//sFeedback 
			//g.drawString(feedbacks[1], 300, 450, paint3);//sFeedback 
	        //g.drawString(feedbacks[2], 300, 475, paint3);//sFeedback 
	        g.drawString(questions[0], 305, 425, paint2);//sQuestion
	        g.drawString(questions[1], 305, 450, paint2);//sQuestion
	        g.drawString(questions[2], 305, 475, paint2);//sQuestion
	        showTransition();
	        showExit();
	       
		}

		@Override
		protected void showTransition() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			if(super.transition){
				Log.d("Transition Debug", "Enters: Knock Knock");
				  g.drawARGB(155, 0, 0, 0);
				  feedboxChef = Assets.feedboxChef;
				  recipe = Assets.envelope;
				  g.drawImage(this.feedboxChef, pDialog.getX(), pDialog.getY());
				  g.drawImage(this.recipe, pRecipe.getX(), pRecipe.getY());
				  //g.drawString(feedbacks[0], 350,155, paint4);
				  //g.drawString(feedbacks[1], 350,175, paint4);
				  //g.drawString(feedbacks[2], 350,195, paint4);
			
				String[] cuttedWord;
				
				  if(isMistake){
						if(cor1 == 0){
							String lineOne = "", lineTwo ="";
							//g.drawString("- ", 350,155, paint5);
							//g.drawString(feedbacks[0], 350,155, paint5);
							cuttedWord = sentenceCutter(feedbacks[0]);
							for(int s = 0; s<cuttedWord.length;s++){
								if(s>5){
									lineTwo += cuttedWord[s] + " ";
								}else{
									lineOne += cuttedWord[s]+ " ";
								}
							}
							g.drawString("1: " + lineOne, 352,155, paint5);
							g.drawString(lineTwo, 352,175, paint5);
						}
						if(cor2 == 0){
							String lineOne = "", lineTwo ="";
							//g.drawString(feedbacks[1], 350,195, paint5);
							//g.drawString("- ", 350,195, paint5);
							cuttedWord = sentenceCutter(feedbacks[1]);
							for(int s = 0; s<cuttedWord.length;s++){
								if(s>5){
									lineTwo += cuttedWord[s] + " ";
								}else{
									lineOne += cuttedWord[s]+ " ";
								}
							}
							g.drawString("2: " + lineOne, 352,195, paint5);
							g.drawString(lineTwo, 352,215, paint5);
						}
						if(cor3== 0){
							String lineOne = "", lineTwo ="";
							//g.drawString(feedbacks[2], 350,225, paint5);
							//g.drawString("- ", 350,225, paint5);
							cuttedWord = sentenceCutter(feedbacks[2]);
							for(int s = 0; s<cuttedWord.length;s++){
								if(s>5){
									lineTwo += cuttedWord[s] + " ";
								}else{
									lineOne += cuttedWord[s]+ " ";
								}
							}
							g.drawString("3: " + lineOne, 352,235, paint5);
							g.drawString(lineTwo, 352,255, paint5);
						}
				  }else{
						g.drawString(feedbacks[0], 350,155, paint4);
					}
			}
		}
		
		private String[] sentenceCutter(String sentence){
			  String[] words;
			  words = sentence.split(" ");
			  
			  return words;
		}
		
		@Override
		protected void drawReadyUI() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			feedboxChef = Assets.feedbox;
			g.drawARGB(200, 0, 0, 0);
			g.drawImage(this.feedboxChef, pDialog.getX(), pDialog.getY());
			g.drawImage(this.recipe, pRecipe.getX(), pRecipe.getY());
			g.drawString("Tap the envelope to get the new", 350,95, paint4);
			g.drawString("recipe and create the cake!",350, 115, paint4);
			g.drawString("Tap the cake when it's ready!", 350, 145, paint4);
			
		}

		@Override
		protected void showExit() {
			// TODO Auto-generated method stub
			Graphics g = game.getGraphics();
			if(exit){
				 g.drawARGB(200, 0, 0, 0);
				 g.drawImage(bgBack, this.pBackg.getX(), pBackg.getY());
				 g.drawImage(nobtn, this.pNo.getX(), pNo.getY());
				 g.drawImage(yesbtn, pYes.getX(), pYes.getY());
			}
		}

	   
}