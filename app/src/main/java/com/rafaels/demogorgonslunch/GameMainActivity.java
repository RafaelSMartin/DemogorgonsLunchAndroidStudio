//Clase Principal del framework, es el punto de entrada, la actividad que inicia la aplicaicion.
//Solo habra una actividad en el juego con un Surface dinamico que muestra el estado actual.
//Es donde se dibujara el juego y contendra una SurfaceView personalizada (GameView).
//Contendra los metodos de acceso de la puntuacion del jugador (Score).
//Autor: Rafael S. Martin Gonzalez 
//Fecha: 22/08/2016

package com.rafaels.demogorgonslunch;

import com.rafaels.demogorgonslunch.GameMainActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.WindowManager;


public class GameMainActivity extends Activity{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static GameView sGame;
	public static AssetManager assets;
	
	//Es la referencia al objeto SharedPreferences 
	//y dentro almacenaremos highScore con clave highScoreKey
	private static SharedPreferences prefs;
	private static final String highScoreKey = "highScoreKey";
	private static int highScore;
	
	private static final String mutedKey = "mutedKey";
	private static boolean muted = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//Recuperamos las preferencias compartidas de nuestra aplicacion
		//para q solo nuestra aplicacion pueda acceder, gracias a Activity.MODE_PRIVATE
		prefs = getPreferences(Activity.MODE_PRIVATE);
		highScore = retrieveHighScore();
		muted = retrieveMuted();
		//Carga archivos desde la carpeta assets
		assets = getAssets();
		//Crea una vista de tipo GameView y la guarda en sGame 
		sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
		//Anade contenido sGame a la Vista
		setContentView(sGame);
		//Hace que se mantenga la pantalla encendida en todo momento, para q no se apague mientras juegas.
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Assets.onResume();
		sGame.onResume();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Assets.onPause();
		sGame.onPause();
	}
	
	//Recibira llamadas cuando el marcador del jugador sea superior al guardado (en GameOverState).
	public static void setHighScore(int highScore){
		GameMainActivity.highScore = highScore;
		//Para editar realmente el marcador
		Editor editor = prefs.edit();
		//Guardamos en editor el entero highScore con la clave highScoreKey.
		editor.putInt(highScoreKey, highScore);
		//Guarda los cambios sobreescribiendo los que habia antes.
		editor.commit();
	}
	
	//Recupera el entero asociado a highScoreKey
	private int retrieveHighScore(){
		return prefs.getInt(highScoreKey, 0);
	}
	
	//Getter que recupera la puntuacion maxima actual.
	public static int getHighScore(){
		return highScore;
	}
	

	//Los tres metodos siguientes reflejan los tres metodos utilizados para establecer,
	//recuperar y obtener una copia local de la variable puntuacion mas alta.
	
	//Este metodo se utiliza para guardar un valor booleano en las preferencias compartidas .
	public static void setMuted(boolean muted) {
		GameMainActivity.muted = muted;
		Editor editor = prefs.edit();
		editor.putBoolean(mutedKey, muted);
		editor.commit();
	}

	//Este metodo se llama para recuperar el valor booleano existente para la llave = mutedKey
	private static boolean retrieveMuted() {
		return prefs.getBoolean(mutedKey, muted);
	}

	
	//Este metodo devuelve la copia local de la variable silenciado,
	//que en onCreate () se establece igual al valor almacenado en las preferencias compartidos
	public static boolean isMuted() {
		return muted;
	}

}
