//Clase que crea las nubes a una velocidad constante y cuando llegan a la izquierda 
//se vuelven a la derecha para reutilizar el objeto usando una y aleatoria.
//Autor: Rafael S. Martin Gonzalez
//Fecha: 15/08/2016

package com.rafaels.game.model;

import com.rafaels.framework.util.RandomNumberGenerator;

public class Cloud {
	private float x, y;
	private static final int VEL_X = -15;
	
	public Cloud(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(float delta){
		x += VEL_X * delta;
		if (x <= -200){
			//Reiniciar a la derecha
			x += 1000;
			y = RandomNumberGenerator.getRandIntBetween(20, 100);
		}
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

}
