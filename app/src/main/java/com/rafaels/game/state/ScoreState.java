//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 17/08/2016

package com.rafaels.game.state;

import com.rafaels.demogorgonslunch.Assets;
import com.rafaels.demogorgonslunch.GameMainActivity;
import com.rafaels.framework.util.Painter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class ScoreState extends State {
	private String highScore;

	@Override
	public void init() {
		highScore = GameMainActivity.getHighScore() + "";	
		Assets.onStop();
		Assets.playMusic("score.mp3", false);
		//Assets.playSound(Assets.scoreID);
	}

	@Override
	public void update(float delta) {
		//Assets.runAnimConfA.update(delta);
		//Assets.runAnimConfB.update(delta);
		
	}

	@Override
	public void render(Painter g) {
		//g.setColor(Color.rgb(53, 156, 253));
		//g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		g.drawImage(Assets.fondoScore, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		//Assets.runAnimConfA.render(g, 100, 50, 750, 465);
		//Assets.runAnimConfB.render(g, 150, 100, 750, 465);
		g.setColor(Color.BLACK);
		g.setFont(Typeface.DEFAULT_BOLD, 50);
		g.drawString("The All-Time High Score", 120, 175);
		g.setFont(Typeface.DEFAULT_BOLD, 70);
		g.drawString(highScore, 370, 260);
		g.setFont(Typeface.DEFAULT_BOLD, 50);
		g.drawString("Touch the screen", 220, 350);		
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if(e.getAction() == MotionEvent.ACTION_UP){
			setCurrentState(new MenuState());
		}
		return true;
	}

}
