//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 20/12/2016

package com.rafaels.game.state;

import com.rafaels.demogorgonslunch.Assets;
import com.rafaels.demogorgonslunch.GameMainActivity;
import com.rafaels.framework.util.Painter;
import com.rafaels.framework.util.UIButton;

//import android.util.Log;
import android.view.MotionEvent;

public class MenuState extends State{
	
	private UIButton playButton, scoreButton, instructionsButton, muteButton, unmuteButton;
	
	//Inicializamos los botones Rect en las coordenadas precisas
	@Override
	public void init() {
		playButton = new UIButton(316, 227-45, 484, 286-45, Assets.start, Assets.startDown);
		scoreButton = new UIButton(316, 300-45, 484, 359-45, Assets.score, Assets.scoreDown);
		instructionsButton = new UIButton(10, 10, 168+10, 59+10, Assets.instructions, Assets.instructions);
		muteButton = new UIButton(752, 0, 800, 48, Assets.mute, Assets.muteDown);		
		unmuteButton = new UIButton(752, 0, 800, 48, Assets.unmute, Assets.unmuteDown);
		
		Assets.onStop();
		Assets.playMusic("bgmusic.mp3", true);
		
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub		
	}
	
	//Dibuja en pantalla la imagen de bienvenida y los botones.
	@Override
	public void render(Painter g) {
		g.drawImage(Assets.welcome, 0, 0);
		playButton.render(g);
		scoreButton.render(g);	
		instructionsButton.render(g);
		
		if (GameMainActivity.isMuted()) {
			unmuteButton.render(g);
		} else {
			muteButton.render(g);
		}
	}
	
	//Solo un boton debera estar pulsado a la vez
	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		
		if (e.getAction() == MotionEvent.ACTION_DOWN){
			playButton.onTouchDown(scaledX, scaledY);
			scoreButton.onTouchDown(scaledX, scaledY);
			instructionsButton.onTouchDown(scaledX, scaledY);
			
			if (GameMainActivity.isMuted()) {
				unmuteButton.onTouchDown(scaledX, scaledY);				
			} else {
				muteButton.onTouchDown(scaledX, scaledY);				
			}
		}
		
		if(e.getAction() == MotionEvent.ACTION_UP){
			//Si el boton play esta activo y se libera dentro del propio boton play
			//y pasamos el estado de juego a PlayState.
			if(playButton.isPressed(scaledX, scaledY)){
				//El boton se ha liberado
				playButton.cancel();
				setCurrentState(new PlayState());				
				
			//Si el boton score esta activo y se libera sobre el propio boton score
			//y pasamos el estado de juego a ScoreState.
			} else if(scoreButton.isPressed(scaledX, scaledY)){
				//El boton se ha liberado
				scoreButton.cancel();
				//Log.d("MenuState", "Boton Score presionado!!");
				setCurrentState(new ScoreState());
				
			//Si el boton score esta activo y se libera sobre el propio boton instructions
			//y pasamos el estado de juego a IntructionsState.
			} else if(instructionsButton.isPressed(scaledX, scaledY)){
				//El boton se ha liberado
				instructionsButton.cancel();
				//Log.d("MenuState", "Boton Instrucciones presionado!!");
				setCurrentState(new InstructionsState());
				
			//Si levantamos sobre mute o unmute boton silenciamos o no la musica.
			} else if (muteButton.isPressed(scaledX, scaledY)) {
				muteButton.cancel();
				Assets.onMute();
				GameMainActivity.setMuted(true);
			} else if (unmuteButton.isPressed(scaledX, scaledY)) {
				unmuteButton.cancel();
				Assets.onUnmute();
				GameMainActivity.setMuted(false);
				
			//Si el dedo se levanta en cualquier otro lugar cancelamos todas las acciones
			} else {
				playButton.cancel();
				scoreButton.cancel();
				instructionsButton.cancel();
				muteButton.cancel();
				unmuteButton.cancel();
				//Log.d("MenuState", "Te has salido del area de presionar!!!");
			}			
		}
		return true;
	}
}
