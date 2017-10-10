//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 23/09/2016

package com.rafaels.game.model;

import com.rafaels.framework.util.RandomNumberGenerator;

import android.graphics.Rect;

public class Block {
	private float x, y;
	private int width, height;
	private Rect rect;
	private boolean visible;
	
	
	private static final int UPPER_Y = 280; //275 
	private static final int LOWER_Y = 345; //355
	
	public Block(float x, float y, int width, int height){
		this.x = x + 800;
		this.y = y + 800;
		this.width = width;
		this.height = height;
		rect = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
		visible = false;
	}
	
	public void update(float delta, float velX){
		x += velX * delta;
		updateRect();
		if (x <= -50){
			reset();
		}
	}

	public void reset() {
		visible = true;
		// 1 de 3 posibilidades de ser un Upper Block
		if (RandomNumberGenerator.getRandInt(3) == 0){
			y = UPPER_Y;
		} else {
			y = LOWER_Y;
		} 
		x += 1000;
		updateRect();		
	}

	public void updateRect() {
		rect.set((int) x, (int) y, (int) x + width, (int) y + height -15);		
	}
	
	public void onCollidePlayer(Player p){
		visible = false;
		p.pushBack(30);		
	}
	
	public void onCollideLaser(Laser l){
		visible = false;
	}
	
	//Getters	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public Rect getRect(){
		return rect;
	}

}
