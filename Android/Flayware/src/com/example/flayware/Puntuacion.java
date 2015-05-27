package com.example.flayware;

public class Puntuacion {

	
	private int drawableId;
	private String nombre;
	private String apellido;
	private double puntuacion;

	public Puntuacion(int drawableId, String nombre, String apellido, double puntuacion) {
		super();
		this.drawableId = drawableId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.puntuacion = puntuacion;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public double getPuntuacion() {
		return puntuacion;
	}
	
	public String otro(double x){
		String res= x+"";
		return res;
		
	}



	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}



	public int getDrawableId() {
		return drawableId;
	}

	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}

}

