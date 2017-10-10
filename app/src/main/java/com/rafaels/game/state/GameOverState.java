//Contendra la pantalla de GameOver
//Guardara la puntuacion maxima.
//Autor: Rafael S. Martin Gonzalez
//Fecha: 17/08/2016

package com.rafaels.game.state;

import com.rafaels.demogorgonslunch.Assets;
import com.rafaels.demogorgonslunch.GameMainActivity;
import com.rafaels.framework.util.Painter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class GameOverState extends State {
	
	private String playerScore;
	private String gameOverMessage = "GAME OVER";

	//Constructor
	public GameOverState(int playerScore){
		//Convertimos int a string
		this.playerScore = playerScore + "";
		//Guarda la puntuacion playerscore si es mayor a la que habia antes.
		if(playerScore > GameMainActivity.getHighScore()){
			GameMainActivity.setHighScore(playerScore);
			gameOverMessage = "HIGH SCORE";
		}		
	}
	
	@Override
	public void init() {		
		//Assets.playSound(Assets.gameOverID);		
		Assets.onStop();
		Assets.playMusic("gameOver.wav", false);
		
	}

	@Override
	public void update(float delta) {
		Assets.runAnimSkull.update(delta);
		
	}

	@Override
	public void render(Painter g) {
		//g.setColor(Color.rgb(255, 145, 0));
		//g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		g.drawImage(Assets.fondoGameOver, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		Assets.runAnimSkull.render(g, 100, 50, 600, 374);
		//Assets.runAnimSkull.render(g, 0, 0);
		g.setColor(Color.BLUE);
		g.setFont(Typeface.DEFAULT_BOLD, 50);
		g.drawString("Demogorgon ate you!!!", 145, 100);
		g.drawString(gameOverMessage, 257, 175);
		g.drawString(playerScore, 385, 250);
		g.drawString("Touch the screen.", 220, 350);
		
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if (e.getAction() == MotionEvent.ACTION_UP){
			setCurrentState(new MenuState());			
		}
		return true;
	}

}
