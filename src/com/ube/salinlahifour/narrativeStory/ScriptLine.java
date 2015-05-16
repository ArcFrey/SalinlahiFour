package com.ube.salinlahifour.narrativeStory;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.ube.salinlahifour.SalinlahiFour;

public class ScriptLine{
	private String lessonname;
	private String line;
	private int id;
	private Context context;
	
	public ScriptLine(Context context, String lessonname, String line, int i){
    	Log.d("TEST0", "Script Line: New Script Line");
		this.line = line;
		this.id = i;
		this.context = context;

		int index;
		String tempLine = line;
		int indexStart = 0;
		
		do{

			index = tempLine.indexOf(')', indexStart);
			

	    	Log.d("TEST0", "Script Line: ')' - " + index);
			
			if(index > 0){
				int index2 = tempLine.indexOf('(', indexStart);
				int index3 = tempLine.indexOf('/', indexStart);
				
				if(index2 < index3 && index3 < index && index2 != -1 && index3 != -1){

					String tagalog = "<i>"+line.substring(index2+1, index3) + " </i>";
					String english = "<font color=#8C8C8C>("+line.substring(index3+1, index)+")</font>";
					
					String newLine = "";

					newLine = tempLine.substring(0, index2);
					newLine += tagalog + english;
					
					if(tempLine.length() > index+1){

						newLine += tempLine.substring(index+1);
						
						tempLine = newLine;
					
						indexStart = tempLine.indexOf("</font>", indexStart)+5;
					}else{
						index = -1;
					}
				}else{
//					if(index !=-1){
//						indexStart = index;
//					}else{
						indexStart++;
//					}
				}
			}
		}while(index != -1);


    	Log.d("TEST0", "Script Line: Done initialization");
		
		line = tempLine;
	}

	public String getLine(){
		String tempLine = line;
		if(tempLine.contains("(username)")){
	    	Log.d("TEST0", "Script Line: contains (username)");
			tempLine.replace("(username)", SalinlahiFour.getLoggedInUser().getName());
		}
		if(tempLine.contains("(maincharacter)")){
	    	Log.d("TEST0", "Script Line: contains (maincharacter)");
			tempLine.replace("(maincharacter)", SalinlahiFour.getLoggedInUser().getGender().equals("female") ? "Pepay" : "Popoi");
		}
		
		return tempLine;
	}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public int getVoiceResId(String lessonname, int id, boolean isMain){
		int voiceResID = 0;
		String resourceName = "";
		String filename = lessonname.toLowerCase();

		Log.d("TEST0", "ScriptLine: Sound file " + "narration_"+filename+"_"+id);
		if(!isMain){
			Log.d("TEST0", "ScriptLine: Character speaking is not a main character: " + id);
			resourceName = "narration_"+filename+"_"+id;
			voiceResID = SalinlahiFour.getContext().getResources().getIdentifier(resourceName, "raw", SalinlahiFour.getContext().getPackageName());
		} 
		else{
			Log.d("TEST0", "ScriptLine: Character speaking IS a main character: " + id);
			if(SalinlahiFour.getLoggedInUser().getGender().equals("female")){
				Log.d("TEST0", "ScriptLine: Main character is female: narration_"+filename+"_"+id+"f");
				resourceName = "narration_"+filename+"_"+id+"f";
				voiceResID = SalinlahiFour.getContext().getResources().getIdentifier(resourceName, "raw", SalinlahiFour.getContext().getPackageName());
			}else{
				Log.d("TEST0", "ScriptLine: Main character is male: narration_"+filename+"_"+id+"m");
				resourceName = "narration_"+filename+"_"+id+"m";
				voiceResID = SalinlahiFour.getContext().getResources().getIdentifier(resourceName, "raw", SalinlahiFour.getContext().getPackageName());
			}
			if(voiceResID == 0){
				Log.d("TEST0", "ScriptLine: No f or m sound file: " + id);
				resourceName = "narration_"+filename+"_"+id;
				voiceResID = SalinlahiFour.getContext().getResources().getIdentifier(resourceName, "raw", SalinlahiFour.getContext().getPackageName());
			}
		}
		if(voiceResID == 0){
			SalinlahiFour.errorPopup(context, "No sound file found", "Add " + resourceName + "file to res/raw folder");
		}
		id++;
		return voiceResID;
	}
	
	
}