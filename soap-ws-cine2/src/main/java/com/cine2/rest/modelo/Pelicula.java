package com.cine2.rest.modelo;
/*
 * @author Enrique Albors
 * Esta clase será el modelo del recurso primero . 
 */
public class Pelicula {
	//Atributos de la Clase
	private String id;
	private String nombre ; 
	private int Duracion;
	private String Genero;
	private String Productora;
	private Director director;
	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public int getDuracion() {
		return Duracion;
	}
	public void setDuracion(int duracion) {
		Duracion = duracion;
	}
	public String getGenero() {
		return Genero;
	}
	public void setGenero(String genero) {
		Genero = genero;
	}
	public String getProductora() {
		return Productora;
	}
	public void setProductora(String productora) {
		Productora = productora;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	


}
