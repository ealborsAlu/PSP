package com.cine2.rest.modelo;
/*
 * @author Enrique Albors
 * Esta clase será el modelo del recurso segundo . 
 */
public class Director {
	//Atributos de la clase
	private String id ; 
	private String nombre ; 
	private int edad;
	
	
	//Getters y Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	} 
	
	

}
