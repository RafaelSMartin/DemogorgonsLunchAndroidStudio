//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 20/12/2016

package com.rafaels.game.state;

import com.rafaels.demogorgonslunch.Assets;
import com.rafaels.demogorgonslunch.GameMainActivity;
import com.rafaels.framework.util.Painter;

//import android.graphics.Color;
import android.view.MotionEvent;

public class InstructionsState extends State {

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Painter g) {
		//g.setColor(Color.rgb(53, 156, 253));
		//g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		g.drawImage(Assets.fondoInstructions, 0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);


	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if(e.getAction() == MotionEvent.ACTION_UP){
			setCurrentState(new MenuState());
		}
		return true;
	}

}
