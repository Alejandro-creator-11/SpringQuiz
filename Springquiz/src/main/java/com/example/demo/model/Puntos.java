package com.example.demo.model;

public class Puntos {
	
	private Integer id;
	private String nombre;
	private Integer puntuacion;
	
	
	public Puntos() {
	}


	public Puntos(String nombre, Integer puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}


	public Puntos(Integer id, String nombre, Integer puntuacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	
	
	
}
