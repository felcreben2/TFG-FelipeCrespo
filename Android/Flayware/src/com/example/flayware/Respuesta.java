package com.example.flayware;

import android.graphics.Bitmap;


public class Respuesta {

	
	private Bitmap image;
	private String texto;
	private int value;

	public Respuesta(Bitmap image, String texto, int value) {
		super();
		this.image = image;
		this.texto = texto;
		this.value = value;
	}




	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}


	public int getValue() {
		return value;
	}




	public void setValue(int value) {
		this.value = value;
	}




	public Bitmap getImage() {
		return image;
	}



	public void setImage(Bitmap image) {
		this.image = image;
	}



}

