//Clase que controlara toda la jugabilidad del juego
//Autor: Rafael S. Martin Gonzalez
//Fecha: 23/09/2016

package com.rafaels.game.state;

import java.util.ArrayList;


import com.rafaels.demogorgonslunch.Assets;
import com.rafaels.demogorgonslunch.GameMainActivity;
import com.rafaels.framework.util.Painter;
import com.rafaels.framework.util.UIButton;
import com.rafaels.game.model.Block;
import com.rafaels.game.model.Cloud;
import com.rafaels.game.model.Enviroment;
import com.rafaels.game.model.Laser;
import com.rafaels.game.model.Player;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class PlayState extends State{
	
	private Player player;
	private ArrayList<Block> blocks;
	//private ArrayList<Laser> lasers;
	private Cloud cloud, cloud2;
	private Enviroment cactus, grassBrown1, mushroomRed;
	private Laser laser;
	
	
	private int playerScore = 0;
	//Guarda la coordenada X e Y del toque mas reciente
	private float recentTouchY, recentTouchX;
	
	private static final int BLOCK_WIDTH = 20; //20 124 89
	private static final int BLOCK_HEIGHT = 50; //50
	private int blockSpeed = -200;
	
	private static final int PLAYER_WIDTH = 66;
	private static final int PLAYER_HEIGHT = 92;
	//private int laserSpeed = 120;
	
	//Registra las pausas del juego.
	private boolean gamePaused = false;
	//Cadena mostrada cuando se pausa el juego.
	private String pausedString = "Game paused. Tap to resume";
	//Declaracion del boton de pausa.
	private UIButton pauseButton;

	@Override
	public void init() {
		player = new Player(160, GameMainActivity.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		blocks = new ArrayList<Block>();	
		//lasers = new ArrayList<Laser>();
		cloud = new Cloud(100, 100);
		cloud2 = new Cloud(500, 50);
		cactus = new Enviroment(450, GameMainActivity.GAME_HEIGHT - 45 - 160);
		grassBrown1 = new Enviroment(700, GameMainActivity.GAME_HEIGHT - 45 - 57);
		mushroomRed = new Enviroment(100, GameMainActivity.GAME_HEIGHT - 45 - 99);
		laser = new Laser(player.getX()+70, player.getY(), 35, 15);
		
		
		for (int i = 0; i < 5; i++){
			Block b = new Block(i * 200, GameMainActivity.GAME_HEIGHT - 95, BLOCK_WIDTH, BLOCK_HEIGHT);
			blocks.add(b);		
		}
		/**
		for (int i = 0; i < 5; i++){
		//if (player.isShoot()){
			Laser l = new Laser(player.getX()+70, player.getY()+18, 70, 70);
			lasers.add(l);
		}*/
		
		//Inicializar el Boton de pausa en x = 752, y = 0, anchura y altura 48.
		//Son valores experimentales
		pauseButton = new UIButton(752 - 49, 0 + 25, 800 - 49, 48 + 20, Assets.pause, Assets.pauseDown);
	}
	
	//Sobreescribe onPause() de State
	//LLamado cuand la Activity esta en pausa.
	@Override
	public void onPause(){
		gamePaused = true;
	}

	@Override
	public void update(float delta) {
		//Si el juego esta en pausa no actualizar nada.
		if(gamePaused){
			return;
		}
		
		if(!player.isAlive()){
			setCurrentState(new GameOverState(playerScore / 100));
			
		}
		
		playerScore += 1;
		
		if(playerScore % 500 == 0 && blockSpeed > -280){
			blockSpeed -= 10;
		}
		
		cloud.update(delta);
		cloud2.update(delta);
		cactus.update(delta);
		grassBrown1.update(delta);
		mushroomRed.update(delta);
		Assets.runAnim.update(delta);
		player.update(delta);
		Assets.runAnimBall.update(delta);
		updateBlocks(delta);		
		laser.update(delta, player);
		Assets.runAnimExplosion.update(delta);
	}
	
	private void updateBlocks(float delta){		
		for (int i = 0; i < blocks.size(); i++){			
			Block b = blocks.get(i);
			b.update(delta,  blockSpeed);		
			if (b.isVisible()){
				if (player.isDucked() && Rect.intersects(b.getRect(), player.getDuckRect())){
					b.onCollidePlayer(player);
				} else if (!player.isDucked() && Rect.intersects(b.getRect(), player.getRect())){
					b.onCollidePlayer(player);
				} else if (laser.isVisible() && Rect.intersects(b.getRect(), laser.getRect())){
					laser.onCollideBlock(b);
					b.onCollideLaser(laser);						
				}				
			}			
		}
	}


	@Override
	public void render(Painter g) {
		//g.setColor(Color.rgb(208, 244, 247));
		//g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		g.drawImage(Assets.fondo, 0, 0);
		
		renderEnviroment(g);
		renderBlocks(g);
		
		if (player.IsHit()){
			g.drawImage(Assets.hit, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());			
		} else if(player.isShoot()){
			g.drawImage(Assets.shoot, (int) player.getX(), (int) player.getY(), player.getWidth()+26, player.getHeight());			
		} else {
			renderPlayer(g);
		}	
		
		renderLaser(g);
		renderSun(g);
		renderClouds(g);
		g.drawImage(Assets.demogorgon, -140, 233, 240, 172);
		g.drawImage(Assets.grass, 0, 405);
		renderScore(g);				
		
		//Si el juego esta pausado, dibujar elementos de interfaz de usuario adicionales
		if(gamePaused){
			//ARGB se usa para establecer un color RGB con alpha transparencia.
			g.setColor(Color.argb(153, 0, 0, 0));
			g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
			g.drawString(pausedString, 235, 240);
		} else {
			//Dibujar el boton de pausa si no esta el juego pausado.
			pauseButton.render(g);
		}
	}
	
	private void renderScore(Painter g){
		g.setFont(Typeface.SANS_SERIF, 25);
		g.setColor(Color.GRAY);
		g.drawString("" + playerScore / 100, 20, 30);
	}
	
	private void renderPlayer(Painter g){		
		if(player.isGrounded()){
			if(player.isDucked()){
				g.drawImage(Assets.duck, (int) player.getX(), (int) player.getY());							
			} else {
				Assets.runAnim.render(g, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
			}
		} else {
			g.drawImage(Assets.jump, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
		}		
	}
	
	private void renderBlocks(Painter g){
		for (int i = 0; i < blocks.size(); i++){
			Block b = blocks.get(i);
			if(b.isVisible()){
				//g.drawImage(Assets.block, (int) b.getX(), (int) b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT);
				//g.drawImage(Assets.blockBall, (int) b.getX(), (int) b.getY(), 124, BLOCK_HEIGHT);
				//Assets.runAnimBall.render(g, (int) b.getX(), (int) b.getY(), 124, BLOCK_HEIGHT);
				Assets.runAnimBall.render(g, (int) b.getX(), (int) b.getY(), 89, BLOCK_HEIGHT);
			} else if (!b.isVisible()){			 
					Assets.runAnimExplosion.render(g, (int) b.getX(), (int) b.getY(), 50, 50);
			}
		}
	}
	
	private void renderLaser(Painter g){	
		if(laser.isVisible()){
		//g.drawImage(Assets.laser, (int) l.getX(), (int) l.getY(), (int) l.getWidth(), (int) l.getHeight());
			g.drawImage(Assets.laser, (int) laser.getX(), (int) laser.getY()+18);			
		}
	}
	
	
	private void renderSun(Painter g){
		g.drawImage(Assets.sun, GameMainActivity.GAME_WIDTH - 148, 0, 148, 142);
		/*g.setColor(Color.rgb(255, 165, 0));
		g.fillOval(715, -85, 170, 170);
		g.setColor(Color.YELLOW);
		g.fillOval(725, -75, 150, 150);*/
	}
	
	private void renderClouds(Painter g){
		g.drawImage(Assets.cloud1, (int) cloud.getX(), (int) cloud.getY(), 100, 60);
		g.drawImage(Assets.cloud2, (int) cloud2.getX(), (int) cloud2.getY(), 100, 60);
	}
	
	private void renderEnviroment(Painter g)
	{
		g.drawImage(Assets.cactus, (int) cactus.getX(), (int) cactus.getY(), 117, 160);		
		g.drawImage(Assets.mushroomRed, (int) mushroomRed.getX(), (int) mushroomRed.getY(), 81, 99);
		g.drawImage(Assets.grassBrown1, (int) grassBrown1.getX() , (int) grassBrown1.getY(), 58, 57);
	}
	
	//Si empujamos hacia arriba saltamos y hacia abjo nos agachamos
	//Comparamos el evento tactil mas reciente y el evento de levantar el dedo y la dif 
	//es mayor o menor de 50 saltaremos o nos agacharemos
	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if(e.getAction() == MotionEvent.ACTION_DOWN){
			recentTouchY = scaledY;
			recentTouchX = scaledX;
			//Llamamos onTouchDown en cada UIButton
			pauseButton.onTouchDown(scaledX, scaledY);
		} else if (e.getAction() == MotionEvent.ACTION_UP){
			//El juego vuelve a empezar si esta pausado
			if(gamePaused){
				gamePaused = false;
				//Cancelamos pauseButton antes de que el metodo onTouch() retorne.
				pauseButton.cancel();
				return true;
			}
			if(scaledY - recentTouchY < -50){
				player.jump();
			} else if(scaledY - recentTouchY > 50){
				player.duck();
			} else if(scaledX - recentTouchX > 50){
				player.shoot();				
				laser.shootLaser();	
				
			}
			
			// Si Touch Up desencadena PauseButton, pausa el juego.
			if (pauseButton.isPressed(scaledX, scaledY)) {
				pauseButton.cancel();
				gamePaused = true;
			} else {
				pauseButton.cancel();
			}
		}
		return true;
	}

}
