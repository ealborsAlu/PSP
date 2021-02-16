/*
 * @author Enrique Albors 
 * Clase para el recurso tercero
 */
package com.cine2.rest.modelo;

public class Cine {
	//Atributos
	private String id ; 
	private String nombre;
	private String direccion;
	private String valoracion;
	
	//Getters and Setters
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getValoracion() {
		return valoracion;
	}
	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	
	

}
