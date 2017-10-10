//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 20/09/2016

package com.rafaels.game.model;

import com.rafaels.demogorgonslunch.Assets;

import android.graphics.Rect;

public class Player {
	private float x, y;
	private int width, height, velY;
	private Rect rect, duckRect, ground;	
	
	private boolean isHit;
	private float hitDuration = .2f;
	private boolean isAlive;
	private boolean isDucked;
	private float duckDuration = .6f;	
	private boolean isShoot;
	private float shootDuration = .15f;
	
	private static final int JUMP_VELOCITY = -600;
	private static final int ACCEL_GRAVITY = 1800;
	
	public Player(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		ground = new Rect(0, 405, 0 + 800, 405 + 45);
		rect = new Rect();
		duckRect = new Rect();
		isAlive = true;
		isDucked = false;	
		isHit = false;
		isShoot = false;
	}
	
	public void update(float delta){
		if(duckDuration > 0 &&  isDucked){
			duckDuration -= delta;			
		} else {
			isDucked = false;
			duckDuration = .6f;			
		}
		if(shootDuration > 0 && isShoot){
			shootDuration -= delta;
		} else{
			isShoot = false;
			shootDuration = .2f;
		}		
		if(hitDuration > 0 && isHit){
			hitDuration -= delta;
		} else {
			isHit = false;
			hitDuration = .3f;
		}
		
		if(!isGrounded()){
			velY += ACCEL_GRAVITY * delta;
			//isHit = false;
		} else {
			y = 406 - height;
			velY = 0;			
		}	
		
		y += velY * delta;		
		updateRects();		
	}

	public void updateRects() {
		rect.set((int) x + 10, (int) y, (int) x + (width - 20), (int) y + height);
		duckRect.set((int) x, (int) y + 20, (int) x + (width - 20), (int) y + 20 + (height - 0));		
	}
	
	public void jump(){
		if (isGrounded()){
			Assets.playSound(Assets.onJumpID);
			isDucked = false;
			duckDuration = .6f;			
			y -= 10;
			velY = JUMP_VELOCITY;
			updateRects();
		}
	}
	
	public void shoot(){
		if(!IsHit()){
			Assets.playSound(Assets.shootID);
			isShoot = true;
		}
	}
	
	public void duck(){
		if (isGrounded()){
			isDucked = true;
		}
	}


	public void pushBack(int dX){	
		isHit = true;
		Assets.playSound(Assets.hitID);	
		x -= dX;		
		if(x < -width / 2){
			isAlive = false;			
		}
		rect.set((int) x, (int) y, (int) x + width, (int) y + height);		
	}	

	
	public boolean isGrounded() {		
		return Rect.intersects(rect, ground);
	}
	
	//Getters	
	public boolean isShoot(){
		return isShoot;
	}
	
	public boolean IsHit(){
		return isHit;
	}
	
	public boolean isDucked(){
		return isDucked;
	}
	
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
	
	public int getVelY(){
		return velY;
	}
	
	public Rect getRect(){
		return rect;
	}
	
	public Rect getDuckRect(){
		return duckRect;
	}
	
	public Rect getGround(){
		return ground;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public float getDuckDuration(){
		return duckDuration;
	}	
	
}
