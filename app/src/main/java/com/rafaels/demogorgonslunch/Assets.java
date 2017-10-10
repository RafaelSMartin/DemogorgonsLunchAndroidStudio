//
//Autor: Rafael S. Martin Gonzalez
//Fecha: 20/12/2016

package com.rafaels.demogorgonslunch;

import java.io.IOException;
import java.io.InputStream;


import com.rafaels.framework.animation.Animation;
import com.rafaels.framework.animation.Frame;

//import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioAttributes;
//import android.media.AudioAttributes.Builder;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.content.res.AssetFileDescriptor;
import android.media.SoundPool;
import android.os.Build;

public class Assets {
	
	private static SoundPool soundPool;
	private static MediaPlayer mediaPlayer;
	public static Bitmap welcome, fondo, fondoGameOver, fondoScore, fondoInstructions, block, sun, cloud1, cloud2, duck, grass, cactus, jump, grassBrown1, mushroomRed;
	public static Bitmap run1, run2, run3, run4, run5, hit, shoot, laser;
	public static Bitmap ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8;
	public static Bitmap exp1, exp2, exp3, exp4, exp5, exp6, exp7;
	public static Bitmap skull1, skull2, skull3, skull4, skull5, skull6, skull7, skull8, skull9, skull10, skull11, skull12;
	public static Bitmap demogorgon;
	public static Bitmap confA1, confA2, confA3, confA4, confA5, confA6, confA7, confA8, confA9, confA10;
	public static Bitmap confB1, confB2, confB3, confB4, confB5, confB6, confB7, confB8, confB9, confB10;
	public static Bitmap scoreDown, score, startDown, start, instructions;
	public static Bitmap pause, pauseDown, mute, muteDown, unmute, unmuteDown;
	public static Animation runAnim, runAnimBall, runAnimExplosion, runAnimSkull, runAnimConfA, runAnimConfB;
	
	private static float velAnimBall = .075f;
	
	public static int hitID, onJumpID, shootID, explosionID, gameOverID, scoreID;
	
	//Carga de ficheros con o sin transparencia.
	public static void load(){
		welcome = loadBitmap("welcome.png", false);
		fondo = loadBitmap("fondo.png", true);
		fondoGameOver = loadBitmap("fondoGameOver.png", true);
		fondoScore = loadBitmap("fondoScore.png", false);
		fondoInstructions = loadBitmap("fondoInstructions.png", false);
		block = loadBitmap("block.png", false);		
		sun = loadBitmap("sun1.png", false);
		cloud1 = loadBitmap("cloud1.png", true);
		cloud2 = loadBitmap("cloud2.png", true);
		duck = loadBitmap("duck.png", true);
		grass = loadBitmap("grass.png", false);
		grassBrown1 = loadBitmap("grass_brown1.png", false);
		mushroomRed = loadBitmap("mushroom_red.png", false);
		cactus = loadBitmap("cactus.png", false);		
		jump = loadBitmap("jump.png", true);
		hit = loadBitmap("hit.png", true);
		shoot = loadBitmap("shoot.png", true);
		laser = loadBitmap("laser.png", false);		
		demogorgon= loadBitmap("demogorgon.png", true);
		
		run1 = loadBitmap("run_anim1.png", true);
		run2 = loadBitmap("run_anim2.png", true);
		run3 = loadBitmap("run_anim3.png", true);
		run4 = loadBitmap("run_anim4.png", true);
		run5 = loadBitmap("run_anim5.png", true);		
		
		ball1 = loadBitmap("ball1.png", true);
		ball2 = loadBitmap("ball2.png", true);
		ball3 = loadBitmap("ball3.png", true);
		ball4 = loadBitmap("ball4.png", true);
		ball5 = loadBitmap("ball5.png", true);
		ball6 = loadBitmap("ball6.png", true);
		ball7 = loadBitmap("ball7.png", true);
		ball8 = loadBitmap("ball8.png", true);
		
		exp1 = loadBitmap("explosion1.png", true);
		exp2 = loadBitmap("explosion2.png", true);
		exp3 = loadBitmap("explosion3.png", true);
		exp4 = loadBitmap("explosion4.png", true);
		exp5 = loadBitmap("explosion5.png", true);
		exp6 = loadBitmap("explosion6.png", true);
		exp7 = loadBitmap("explosion7.png", true);
		
		skull1 = loadBitmap("skull1.png", true);
		skull2 = loadBitmap("skull2.png", true);
		skull3 = loadBitmap("skull3.png", true);
		skull4 = loadBitmap("skull4.png", true);
		skull5 = loadBitmap("skull5.png", true);
		skull6 = loadBitmap("skull6.png", true);
		skull7 = loadBitmap("skull7.png", true);
		skull8 = loadBitmap("skull8.png", true);
		skull9 = loadBitmap("skull9.png", true);
		skull10 = loadBitmap("skull10.png", true);
		skull11 = loadBitmap("skull11.png", true);
		skull12 = loadBitmap("skull12.png", true);
		/**
		confA1 = loadBitmap("confA1.png", true);
		confA2 = loadBitmap("confA2.png", true);
		confA3 = loadBitmap("confA3.png", true);
		confA4 = loadBitmap("confA4.png", true);
		confA5 = loadBitmap("confA5.png", true);
		confA6 = loadBitmap("confA6.png", true);
		confA7 = loadBitmap("confA7.png", true);
		confA8 = loadBitmap("confA8.png", true);
		confA9 = loadBitmap("confA9.png", true);
		confA10 = loadBitmap("confA10.png", true);
		
		confB1 = loadBitmap("confB1.png", true);
		confB2 = loadBitmap("confB2.png", true);
		confB3 = loadBitmap("confB3.png", true);
		confB4 = loadBitmap("confB4.png", true);
		confB5 = loadBitmap("confB5.png", true);
		confB6 = loadBitmap("confB6.png", true);
		confB7 = loadBitmap("confB7.png", true);
		confB8 = loadBitmap("confB8.png", true);
		confB9 = loadBitmap("confB9.png", true);
		confB10 = loadBitmap("confB10.png", true);	*/	
		
		scoreDown = loadBitmap("score_button_down.png", true);
		score = loadBitmap("score_button.png", true);
		startDown = loadBitmap("start_button_down.png", true);
		start = loadBitmap("start_button.png", true);
		instructions= loadBitmap("instructions_button.png", true);
		pause = loadBitmap("pause_button.png", true);
		pauseDown = loadBitmap("pause_button_down.png", true);
		mute = loadBitmap("mute_button.png", false);
		muteDown = loadBitmap("mute_button_down.png", false);
		unmute = loadBitmap("unmute_button.png", false);
		unmuteDown = loadBitmap("unmute_button_down.png", false);
		
		Frame f1 = new Frame(run1, .1f);
		Frame f2 = new Frame(run2, .1f);
		Frame f3 = new Frame(run3, .1f);
		Frame f4 = new Frame(run4, .1f);
		Frame f5 = new Frame(run5, .1f);
		runAnim = new Animation(f1, f2, f3, f4, f5, f3, f2);		
		
		Frame b1 = new Frame(ball1, velAnimBall);
		Frame b2 = new Frame(ball2, velAnimBall);
		Frame b3 = new Frame(ball3, velAnimBall);
		Frame b4 = new Frame(ball4, velAnimBall);
		Frame b5 = new Frame(ball5, velAnimBall);
		Frame b6 = new Frame(ball6, velAnimBall);
		Frame b7 = new Frame(ball7, velAnimBall);
		Frame b8 = new Frame(ball8, .05f);
		runAnimBall = new Animation(b1, b2, b3, b4, b5, b6, b7, b8, b4, b5, b6);
		
		Frame e1 = new Frame(exp1, .1f);
		Frame e2 = new Frame(exp2, .1f);
		Frame e3 = new Frame(exp3, .1f);
		Frame e4 = new Frame(exp4, .1f);
		Frame e5 = new Frame(exp5, .1f);
		Frame e6 = new Frame(exp6, .1f);
		Frame e7 = new Frame(exp7, .1f);
		runAnimExplosion = new Animation(e1, e2, e3, e4, e5, e6, e7);
		
		Frame s1 = new Frame(skull1, .1f);
		Frame s2 = new Frame(skull2, .1f);
		Frame s3 = new Frame(skull3, .1f);
		Frame s4 = new Frame(skull4, .1f);
		Frame s5 = new Frame(skull5, .1f);
		Frame s6 = new Frame(skull6, .1f);
		Frame s7 = new Frame(skull7, .1f);
		Frame s8 = new Frame(skull8, .1f);
		Frame s9 = new Frame(skull9, .1f);
		Frame s10 = new Frame(skull10, .1f);
		Frame s11 = new Frame(skull11, .1f);
		Frame s12 = new Frame(skull12, .1f);
		runAnimSkull = new Animation(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
		/**
		Frame cA1 = new Frame(confA1, .1f);
		Frame cA2 = new Frame(confA2, .1f);
		Frame cA3 = new Frame(confA3, .1f);
		Frame cA4 = new Frame(confA4, .1f);
		Frame cA5 = new Frame(confA5, .1f);
		Frame cA6 = new Frame(confA6, .1f);
		Frame cA7 = new Frame(confA7, .1f);
		Frame cA8 = new Frame(confA8, .1f);
		Frame cA9 = new Frame(confA9, .1f);
		Frame cA10 = new Frame(confA10, .1f);
		runAnimConfA = new Animation(cA1, cA2, cA3, cA4, cA5, cA6, cA7, cA8, cA9, cA10);
		
		Frame cB1 = new Frame(confB1, .1f);
		Frame cB2 = new Frame(confB2, .1f);
		Frame cB3 = new Frame(confB3, .1f);
		Frame cB4 = new Frame(confB4, .1f);
		Frame cB5 = new Frame(confB5, .1f);
		Frame cB6 = new Frame(confB6, .1f);
		Frame cB7 = new Frame(confB7, .1f);
		Frame cB8 = new Frame(confB8, .1f);
		Frame cB9 = new Frame(confB9, .1f);
		Frame cB10 = new Frame(confB10, .1f);
		runAnimConfB = new Animation(cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8, cB9, cB10);*/
		
		//hitID = loadSound("hit.wav");
		//onJumpID = loadSound("onjump.wav");		
	}
	
	//Sera llamado por GameMainActivity.onResume() invocado por el sistema.
	public static void onResume(){
		hitID = loadSound("hit.wav");
		onJumpID = loadSound("onjump.wav");
		shootID = loadSound("shoot.wav");
		explosionID = loadSound("explosion.wav");
		gameOverID = loadSound("gameOver.wav");
		scoreID = loadSound("score.mp3");
		playMusic("bgmusic.mp3", true);
	}
	
	//Sera llamado por GameMainActivity.onPause() y eliminamos soundPool para liberar recursos.
	public static void onPause(){
		if(soundPool != null){
			soundPool.release();
			soundPool = null;
		}
		
		if(mediaPlayer != null){
			//mediaPlayer.stop();			
			//mediaPlayer.release();
			//mediaPlayer = null;
			onStop();			
		}
	}
	
	public static void onStop(){
		if(mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}

	}
	
	//Carga de la Imagen (filename) en tres etapas sabiendo la transparencia.
	//Primero se crea inputStream para leer datos desde el sistema de archivos que hay en assets
	//Segundo se crea option que dice la forma de guardado (si es transparente ocupara mas)
	//Tercero se crea bitmap usando la calse BitmapFactory pasando los anteriores como argumentos
	private static Bitmap loadBitmap(String filename, boolean transparency) {
		InputStream inputStream = null;
		try {
			inputStream = GameMainActivity.assets.open(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Options options = new Options();
		if (transparency) {
			options.inPreferredConfig = Config.ARGB_8888;
		} else {
			options.inPreferredConfig = Config.RGB_565;
		}
		Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
				options);
		return bitmap;
	}
	
	//Se creara un solo objeto SoundPool como gestor de cada unos de los sonidos
	//SoundPool ha sido deprecated en la api 21 asi pues debemos de tener en cuente a las apis inferiores
	//Creamos un metodo buildSoundPool que dependiendo de la version usa una u otra
	
	private static int loadSound(String filename) {
		int soundID = 0;
		if (soundPool == null) {
			soundPool = buildSoundPool();
		}
		try {
			soundID = soundPool.load(GameMainActivity.assets.openFd(filename), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return soundID;
	}
	
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private static SoundPool buildSoundPool() {
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
	         AudioAttributes audioAttributes = new AudioAttributes.Builder()
	                                 .setUsage(AudioAttributes.USAGE_GAME)
	                                 .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
	                                 .build();
	                                 
	         soundPool = new SoundPool.Builder()
	                     .setMaxStreams(25)
	                     .setAudioAttributes(audioAttributes)
	                     .build();
	      } else {
	         soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
	      }
	      return soundPool;
	   }
	
	public static void playSound(int soundID){
		//Antes de poner el sonido, compruema si el juego esta en mute y devuelve lo necesario.
		if(GameMainActivity.isMuted()) {
			return;
		}
		
		if(soundPool != null){
		soundPool.play(soundID, 1, 1, 1, 0, 1);
		}
	}
	
	public static void playMusic(String filename, boolean looping){
		//Antes de poner el sonido, compruema si el juego esta en mute y devuelve lo necesario.
		if(GameMainActivity.isMuted()){
			return;
		}
		
		if(mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
		}
		try{
			AssetFileDescriptor afd = GameMainActivity.assets.openFd(filename);
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.prepare();
			mediaPlayer.setLooping(looping);
			mediaPlayer.start();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Llama cuando se muta y pausa el juego	.
	public static void onMute() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	//Llama cuando se vuelve la musica al juego.
	public static void onUnmute() {
		if (mediaPlayer != null) {			
			mediaPlayer.start();
		} else {		
			playMusic("bgmusic.mp3", true);
		}
	}

}
