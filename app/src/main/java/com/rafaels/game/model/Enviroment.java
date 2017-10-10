//Clase que crea los objetos de ambiente a una velocidad constante y cuando llegan a la izquierda 
//se vuelven a la derecha para reutilizar el objeto usando.
//Autor: Rafael S. Martin Gonzalez
//Fecha: 27/08/2016

package com.rafaels.game.model;

import com.rafaels.framework.util.RandomNumberGenerator;

public class Enviroment {
	private float x, y;
	//private static final int POS_Y = 250;
	private static final int VEL_X = -60;
	
	public Enviroment(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void update(float delta){
		x += VEL_X * delta;
		//y = POS_Y;
		if (x <= -200){
			//Reiniciar a la derecha
			x += 1000 + RandomNumberGenerator.getRandIntBetween(500, 1800);
			//y = RandomNumberGenerator.getRandIntBetween(20, 100);
		}		
	}

	//Getters
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
}
