package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;

import com.ube.salinlahifour.model.UserDetail;
import com.ube.salinlahifour.narrativeStory.NarrativeStory;

public class SalinlahiFour extends Application{
	private static SalinlahiFour salinlahifour;
	private static Context context;
	private static UserDetail loggedInUser;
	private static Typeface fontBpreplay;
	private static Typeface fontKgsecondchances;
	private static Typeface fontPlaytime;
	private static Typeface fontPlaytimeoblique;
	private static Typeface fontKgtangledupin;
	private static Typeface fontAndy;

	private static MediaPlayer bgm;

	private static ArrayList<Item> lessonItems;	//Di ko alam ano ginagawa nito, wag nalang naten idelete =))
	
	private static ArrayList<Lesson> lessonsList;
	private static ArrayList<NarrativeStory> storiesList;
	private static ArrayList<Character> charactersList;
	private static ArrayList<Integer> tutorialsList;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		SalinlahiFour.salinlahifour = this;

		if (context == null)
			context = this.getApplicationContext();

		fontBpreplay = Typeface.createFromAsset(context.getAssets(), "fonts/BPREPLAYBOLD.OTF");
		fontKgsecondchances = Typeface.createFromAsset(context.getAssets(), "fonts/KGSECONDCHANCESSOLID.TTF");
		fontPlaytime = Typeface.createFromAsset(context.getAssets(), "fonts/PLAYTIME.TTF");
		fontPlaytimeoblique = Typeface.createFromAsset(context.getAssets(), "fonts/PLAYTIMEOBLIQUE.TTF");
		fontKgtangledupin = Typeface.createFromAsset(context.getAssets(), "fonts/KGTANGLEDUPINYOU.TTF");
		fontAndy = Typeface.createFromAsset(context.getAssets(), "fonts/ANDYB.TTF");
		bgm = MediaPlayer.create(this, R.raw.bgm_map);
//		bgm.setLooping(true);
		bgm.setVolume(0, 0);
//		bgm.setVolume(0.4f, 0.4f);
		

		
		}
	
	public static SalinlahiFour getSalinlahifour() {
		return salinlahifour;
	}

	public static void setSalinlahifour(SalinlahiFour salinlahifour) {
		SalinlahiFour.salinlahifour = salinlahifour;
	}
	
	public static ArrayList<Item> getLessonItems() {
		return lessonItems;
	}

	public static void setLessonItems(ArrayList<Item> items) {
		SalinlahiFour.lessonItems = items;
	}
	
	public static UserDetail getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(UserDetail loggedInUser) {
		SalinlahiFour.loggedInUser = loggedInUser;
	}


	public static Typeface getFontBpreplay() {
		return fontBpreplay;
	}

	public static Typeface getFontKgsecondchances() {
		return fontKgsecondchances;
	}
	
	public static Typeface getFontPlaytime() {
		return fontPlaytime;
	}
	
	public static Typeface getFontKgtangledupinyou() {
		return fontKgtangledupin;
	}
	
	public static Typeface getFontPlaytimeOblique() {
		return fontPlaytimeoblique;
	}
	
	public static Typeface getFontAndy() {
		return fontAndy;
	}
	
	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		SalinlahiFour.context = context;
	}
	
	public static MediaPlayer getBgm(){
		return bgm;
	}
	
	public static ArrayList<Lesson> getLessonsList() {
		return lessonsList;
	}

	public static void setLessonsList(ArrayList<Lesson> lessonsList) {
		SalinlahiFour.lessonsList = lessonsList;
	}

	public static ArrayList<NarrativeStory> getStoriesList() {
		return storiesList;
	}

	public static void setStoriesList(ArrayList<NarrativeStory> storiesList) {
		SalinlahiFour.storiesList = storiesList;
	}

	public static ArrayList<Character> getCharactersList() {
		return charactersList;
	}

	public static void setCharactersList(ArrayList<Character> charactersList) {
		SalinlahiFour.charactersList = charactersList;
	}

	public static ArrayList<Integer> getTutorialsList() {
		return tutorialsList;
	}

	public static void setTutorialsList(ArrayList<Integer> tutorialsList) {
		SalinlahiFour.tutorialsList = tutorialsList;
	}

}
