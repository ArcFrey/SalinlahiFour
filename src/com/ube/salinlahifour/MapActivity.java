package com.ube.salinlahifour;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ube.salinlahifour.debugclasses.DebugUserModuleActivity;

public class MapActivity extends Activity implements OnClickListener{
	private ArrayList<Scene> scenes;
	private ImageButton[] imgBtns;
	private TextView[] txtViews;
	private Scene scene;
	private int UserID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getInt("UserID");
		}
		imgBtns = new ImageButton[5];
		txtViews = new TextView[5];
		//setContentView(R.layout.activity_map);

		Log.d("PasringXML","TestTestTest");
		try {
			parseXML();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout();
	}
	
	public void parseXML() throws XmlPullParserException, IOException{
		scenes = new ArrayList();
		scene = makeNewScene();
		String lessonName = "";
		String activityName = "";
		String value = "";
		int lessonImgID = 0;
		
		XmlResourceParser parser = getResources().getXml(R.xml.lessonlist);
		
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
         if(eventType == XmlPullParser.START_DOCUMENT) {
        	 
             Log.d("Start document", "TEST");
         } else if(eventType == XmlPullParser.END_DOCUMENT) {
        	 
        	 Log.d("End document", "TEST");
         } else if(eventType == XmlPullParser.START_TAG) {
        	 
        	 Log.d("Start tag "+parser.getName(), "TEST");
         } else if(eventType == XmlPullParser.END_TAG) {
        	 if(parser.getName().equals("Lesson")){
        		 scene.addLesson(new Lesson(lessonName, activityName, lessonImgID));
        		 lessonName = "";
        		 activityName = "";
        		 value = "";
        		 lessonImgID = 0;
        	 }else if(parser.getName().equals("Name")){
        		 lessonName = value;
        	 }else if(parser.getName().equals("Image")){
        		 lessonImgID = getResources().getIdentifier(value, "drawable", getPackageName());
        	 }else if(parser.getName().equals("ActivityName")){
        		 activityName = value;
        	 }
         } else if(eventType == XmlPullParser.TEXT) {
        	 value = parser.getText();
        	 Log.d("Text "+parser.getText(), "TEST");
         }
         eventType = parser.next();
        }
		 
	}
	
	private Scene makeNewScene(){
		switch(scenes.size()){
		case 0:
			scenes.add(new Scene(null, R.layout.scene_layout_1));
			break;
		}
		return scenes.get(scenes.size()-1);
	}
	
	private void setLayout(){
		setContentView(scene.getLayoutID());
		imgBtns[0] = (ImageButton)findViewById(R.id.img_lesson1);
		imgBtns[1] = (ImageButton)findViewById(R.id.img_lesson2);
		imgBtns[2] = (ImageButton)findViewById(R.id.img_lesson3);
		imgBtns[3] = (ImageButton)findViewById(R.id.img_lesson4);
		imgBtns[4] = (ImageButton)findViewById(R.id.img_lesson5);
		txtViews[0] = (TextView)findViewById(R.id.tv_lesson1);
		txtViews[1] = (TextView)findViewById(R.id.tv_lesson2);
		txtViews[2] = (TextView)findViewById(R.id.tv_lesson3);
		txtViews[3] = (TextView)findViewById(R.id.tv_lesson4);
		txtViews[4] = (TextView)findViewById(R.id.tv_lesson5);
		
		for(int i = 0; i < scene.getLessons().size(); i++){
			imgBtns[i].setImageResource(scene.getLessons().get(i).image);
			txtViews[i].setText(scene.getLessons().get(i).name);
			imgBtns[i].setVisibility(View.VISIBLE);
			imgBtns[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		int index = -1;
		switch(view.getId()){
		case R.id.img_lesson1:
			index = 0;
			break;
		case R.id.img_lesson2:
			index = 1;
			break;
		case R.id.img_lesson3:
			index = 2;
			break;
		case R.id.img_lesson4:
			index = 3;
			break;
		case R.id.img_lesson5:
			index = 4;
			break;
		case R.id.btn_usermodule:
			intent = new Intent(this, DebugUserModuleActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_register:
			intent = new Intent(this, RegistrationActivity.class);
			startActivity(intent);
			break;
		}
		if(index != -1){
			intent = new Intent(scene.getLessons().get(index).tutorial);
			intent.putExtra("activityClass", scene.getLessons().get(index).activity);
			intent.putExtra("UserID", UserID);
			startActivity(intent);
		}
	}
	
}