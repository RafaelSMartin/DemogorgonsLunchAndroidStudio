//Clase que crea los objetos de laser cuando son lanzados por el player
//Autor: Rafael S. Martin Gonzalez
//Fecha: 23/09/2016

package com.rafaels.game.model;

import com.rafaels.demogorgonslunch.Assets;

import android.graphics.Rect;


public class Laser {
	public float x, y;	
	private int width, height;
	private Rect rect;
	private boolean visible;	
	
	private int VEL_X = 110;
	
	public Laser(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
		visible = false;
	}
	
	public void update(float delta, Player p){	
		if (visible){
			x += VEL_X * delta;
			updateRect();		
			if (x >= 800+35){
				visible = false;
				x = p.getX() + 70;
				y = p.getY() + 18;
				updateRect();				
			}	
		} else if (!visible){
			visible = false;
			x = p.getX() + 70;
			y = p.getY() + 18;
			updateRect();
		}
	}


	public void updateRect() {
		rect.set((int) x, (int) y, (int) x + width, (int) y + height);		
	}
	
	public void onCollideBlock(Block b){
		Assets.playSound(Assets.explosionID);
		visible = false;				
	}
	
	public void shootLaser(){
		visible = true;
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
