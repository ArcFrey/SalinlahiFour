package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.util.Log;

import com.ube.salinlahifour.enumTypes.LevelType;

public abstract class LessonItemLoader {
	private static String error = "";
	
	public static ArrayList<Item> getLessonItems(String activityName, String activityLevel){
		ArrayList<Item> items = new ArrayList();

		switch(activityName){
			case "com.ube.salinlahifour.lessonActivities.Cooking":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(7, "Itim" , "Black", "Itim - This is BLACK. it is also the color fot he night sky", Color.BLACK, 0, 0, LevelType.HARD));
						items.add(new Item(8,"Puti" , "White", "Puti - This is WHITE. It is also the color of clouds", Color.WHITE , 0, 0, LevelType.HARD));
					case "MEDIUM":			
						items.add(new Item(5,"Kayumanggi" , "Brown", "Kayumanggi - This is BROWN. It is the color of a trunk of a tree", Color.rgb(84, 44, 23), 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Lila" , "Violet", "Lila - This is VIOLET. It is also the color of eggplants", Color.rgb(135, 29, 124), 0, 0, LevelType.MEDIUM));
					case "EASY":	
						items.add(new Item(1,"Asul" , "Blue", "Asul - This is BLUE. It is the color of the sky", Color.BLUE, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Berde" , "Green", "Berde - This is GREEN. It is the color of the grass and leaves", Color.GREEN,0, 0, LevelType.EASY));
						items.add(new Item(3,"Pula" , "Red", "Pula - This is RED. It is the color of an apple", Color.RED, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Dilaw" , "Yellow", "Dilaw - This is YELLOW. It is the color of the sun.", Color.YELLOW, 0, 0, LevelType.EASY));
				}
				Log.d("Lesson Loader", items.toString());
				break;
			
			case "com.ube.salinlahifour.lessonActivities.Family":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(8,"Tito" , "Uncle", "Can you find <font color=#299190>tito</font>?", R.drawable.family_tito, R.raw.family_tito, R.raw.family_uncle, LevelType.HARD));
						items.add(new Item(9,"Tita" , "Auntie", "Can you find <font color=#299190>tita</font>?", R.drawable.family_tita, R.raw.family_tita, R.raw.family_aunt, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(5,"Lolo" , "Grandfather", "Can you find<font color=#299190> lolo</font>?", R.drawable.family_lolo, R.raw.family_lolo, R.raw.family_grandfather, LevelType.MEDIUM));
						items.add(new Item(6,"Lola" , "Grandmother", "Can you find<font color=#299190> Lola</font>?", R.drawable.family_lola, R.raw.family_lola, R.raw.family_grandmother, LevelType.MEDIUM));
						items.add(new Item(7,"Bunso" , "Youngest", "Can you find<font color=#299190> bunso</font>?", R.drawable.family_bunso, R.raw.family_bunso, R.raw.family_youngestsibling, LevelType.MEDIUM));
					case "EASY":

						items.add(new Item(1,"Nanay" , "Mother", "Can you find <font color=#299190>nanay</font>?", R.drawable.family_nanay, R.raw.family_nanay, R.raw.family_mother, LevelType.EASY));
						items.add(new Item(2,"Tatay" , "Father", "Can you find <font color=#299190>tatay</font>?", R.drawable.family_tatay, R.raw.family_tatay, R.raw.family_father, LevelType.EASY));
						items.add(new Item(3,"Kuya" , "Brother", "Can you find <font color=#299190>kuya</font>?", R.drawable.family_kuya, R.raw.family_kuya, R.raw.family_olderbrother, LevelType.EASY));
						items.add(new Item(4,"Ate" , "Sister", "Can you find <font color=#299190>ate</font>?", R.drawable.family_ate, R.raw.family_ate, R.raw.family_oldersister, LevelType.EASY));
						
				}	
				Log.d("Lesson Loader", items.get(0).getLabel());
				break;
			case "com.ube.salinlahifour.lessonActivities.House":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(7,"Tsimenea" , "Chimney", "Tsimenea - Smoke comes out of here when tita cooks.", R.drawable.chimney,  0, 0, LevelType.HARD));
						items.add(new Item(8,"Hagdan" , "Stairs", "Hagdan - Makes us go up!", R.drawable.stairs, 0, 0, LevelType.HARD));						
					case "MEDIUM":
						items.add(new Item(5,"Garahe" , "Garage", "Garahe - This is where tito parks his car.", R.drawable.garage, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Bakod" , "fence", "Bakod - This protects our house from strangers", R.drawable.fence, 0, 0, LevelType.MEDIUM));
						
					case "EASY":
						items.add(new Item(1,"Bubong" , "Roof", "Bubong - It covers the top part of the house", R.drawable.roof, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Dingding" , "Wall", "Dingding - Covers the front, side and back parts of the house", R.drawable.wall, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Pinto" , "Door", "Pinto - We open this one to get inside the house", R.drawable.door, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Bintana" , "Window", "Bintana - They are made of glass so that you can look from the inside", R.drawable.window, 0, 0, LevelType.EASY));
					
							}
				Log.d("Lesson Loader", items.get(0).getLabel());
				break;
			case "com.ube.salinlahifour.lessonActivities.Shape":
				switch(activityLevel){
					case "HARD":
						items.add(new Item(8,"Tunod" , "Arrow", "Tunod - An arrow. It shoots 3 enemies in a line.", R.drawable.arrow, 0, 0, LevelType.HARD));
						items.add(new Item(9,"Gasuklay" , "Crescent", "Gasuklay - Looks like a banana or a fingernail. It shoots faraway aliens", R.drawable.cresent, 0, 0, LevelType.HARD));
						items.add(new Item(10,"Puso" , "Heart", "A heart - It's such a LOVELY shape. It restores our life.", R.drawable.heart, 0, 0, LevelType.HARD));

					case "MEDIUM":
						items.add(new Item(5,"Krus" , "Cross", "Krus - It looks like the letter t! It shoots a very wide laser", R.drawable.cross, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Diamante" , "Diamond", "Diamante - It's like a square standing on its edge. It puts a barrier around us.", R.drawable.diamond, 0, 0, LevelType.MEDIUM));
						items.add(new Item(7,"Parihaba" , "Rectangle", "Parihaba - It looks like a long box. It shoots two laserss from the side", R.drawable.rectangle, 0, 0, LevelType.MEDIUM));

					case "EASY":
						items.add(new Item(1,"Bilog" , "Circle", "Bilog - It looks like a ball. This button hits red enemies.", R.drawable.circle, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Parisukat" , "Square", "Parisukat - It has 4 sides. It also shoots one bullet", R.drawable.square, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Bituin" , "Star", "Bituin - Teachers gives these when we do well. It places a bomb in the middle of enemies", R.drawable.star, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Tatsulok" , "Triangle", "Tatsulok - It has 3 sides. It shoots lasers the hits 3 enemies.", R.drawable.triangle, 0, 0, LevelType.EASY));
						
				}
				break;
			case "com.ube.salinlahifour.lessonActivities.Society":
				switch(activityLevel){
					case "HARD":
					case "MEDIUM":
					case "EASY":		
						items.add(new Item(1,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Bombero" , "Fireman", "Which one is Fireman", 0, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Bombero" , "Fireman", "Which one is Fireman", 0, 0, 0, LevelType.EASY));
						items.add(new Item(5,"Pulis" , "Police", "Which one is police", 0, 0, 0, LevelType.EASY));
				}				
				break;
				case "com.ube.salinlahifour.lessonActivities.Animals":
					switch(activityLevel){
						case "HARD":
							items.add(new Item(8,"Elefante" , "Elephant", "Match <font color=#376df7>elefante</font> with its English word, animal sound, and its picture.", R.drawable.animals_elephant, R.raw.animals_elefante, R.raw.animals_elephant, LevelType.HARD));
							items.add(new Item(9,"Oso" , "Bear", "Match <font color=#376df7>oso</font> with its English word, animal sound, and its picture.", R.drawable.animals_bear, R.raw.animals_oso, R.raw.animals_bear, LevelType.HARD));
						case "MEDIUM":	items.add(new Item(5,"Palaka" , "Frog", "Match <font color=#376df7>palaka</font> with its English word, animal sound, and its picture.", R.drawable.animals_frog, R.raw.animals_palaka, R.raw.animals_frog, LevelType.MEDIUM));
							items.add(new Item(5,"Unggoy" , "Monkey", "Match <font color=#376df7>unggoy</font> with its English word, animal sound, and its picture.", R.drawable.animals_monkey, R.raw.animals_unggoy, R.raw.animals_monkey, LevelType.MEDIUM));
							items.add(new Item(6,"Ibon" , "Bird", "Match <font color=#376df7>ibon</font> with its English word, animal sound, and its picture.", R.drawable.animals_birl, R.raw.animals_ibon, R.raw.animals_bird, LevelType.MEDIUM));	
							items.add(new Item(7,"Daga" , "Mouse", "Match <font color=#376df7>daga</font> with its English word, animal sound, and its picture.", R.drawable.animals_mouse, R.raw.animals_daga, R.raw.animals_mouse, LevelType.MEDIUM));
						case "EASY":	
							items.add(new Item(1,"Pusa" , "Cat", "Match <font color=#376df7>pusa</font> with its English word, animal sound, and its picture.", R.drawable.animals_cat, R.raw.animals_pusa, R.raw.animals_cat, LevelType.EASY));
							items.add(new Item(2,"Aso" , "Dog", "Match <font color=#376df7>aso</font> with its English word, animal sound, and its picture.", R.drawable.animals_dog, R.raw.animals_asp, R.raw.animals_dog, LevelType.EASY));
							items.add(new Item(3,"Manok" , "Chicken", "Match <font color=#376df7>manok</font> with its English word, animal sound, and its picture.", R.drawable.animals_chicken, R.raw.animals_manok, R.raw.animals_chicken, LevelType.EASY));
							items.add(new Item(4,"Kalabaw" , "Carabao", "Match <font color=#376df7>kalabaw</font> with its English word, animal sound, and its picture.", R.drawable.animals_carabao, R.raw.animals_kalabao, R.raw.animals_carabao, LevelType.EASY));
					}
					break;
					
			default:
				switch(activityLevel){
					case "HARD":
						items.add(new Item(1,"Sampu" , "Ten", "Which is Ten?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
						items.add(new Item(2,"Siyam" , "Nine", "Which is Nine?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
						items.add(new Item(3,"Walo" , "Eight", "Which is Eight?", R.drawable.placeholder_lesson, 0, 0, LevelType.HARD));
					case "MEDIUM":
						items.add(new Item(4,"Pito" , "Seven", "Which is Seven?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
						items.add(new Item(5,"Anim" , "Six", "Which is Six?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
						items.add(new Item(6,"Lima" , "Five", "Which is Five?", R.drawable.placeholder_lesson, 0, 0, LevelType.MEDIUM));
					case "EASY":
						items.add(new Item(1,"Apat" , "Four", "Which is Four?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(2,"Tatlo" , "Three", "Which is Three?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(3,"Dalawa" , "Two", "Which is Two?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
						items.add(new Item(4,"Isa" , "One", "Which is Isa?", R.drawable.placeholder_lesson, 0, 0, LevelType.EASY));
				}
		}
		
		int easyItems = 0;
		int mediumItems = 0;
		int hardItems = 0;
		error = "";
			
		for(Item item : items){
			
			if(item.getLevel() == LevelType.EASY)
				easyItems++;
			else if(item.getLevel() == LevelType.MEDIUM)
				mediumItems++;
			else if(item.getLevel() == LevelType.HARD)
				hardItems++;
		}
		switch(activityLevel){
			case "HARD":
				if(hardItems < 2)
					error = "ERROR in LessonItemLoader class -> " + activityName + " : HARD(" + hardItems + ") should have at least 2 items!\n";
			case "MEDIUM":
				if(mediumItems < 2)
					error += "ERROR in LessonItemLoader class -> " + activityName + " : MEDIUM(" + mediumItems + ") should have at least 2 items!\n";
			case "EASY":
				if(easyItems < 3)
					error += "ERROR in LessonItemLoader class -> " + activityName + " : EASY(" + easyItems + ") should have at least 3 items!";
		}
		if(!error.equals("")){
			return null;
		}
		
		SalinlahiFour.setLessonItems(items);
		return SalinlahiFour.getLessonItems();
	}
	
	public static String getError(){
		return error;
	}
	
}
