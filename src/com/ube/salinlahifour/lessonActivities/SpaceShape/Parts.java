package com.ube.salinlahifour.lessonActivities.SpaceShape;

public class Parts {
	private int x,y;
	
	public Parts(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(int newX, int newY){
		//moveX = newX;
		//moveY = newY;
		 x = newX;
         y = newY;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
