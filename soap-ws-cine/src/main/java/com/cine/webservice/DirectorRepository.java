package com.cine.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.cine.xml.peliculas.Director;

/**
 * 
 * @author Enrique albors
 * Esta clase será la encargada de almacenar los datos de los directores
 */

@Component
public class DirectorRepository {
	
	private static final Map<String, Director> directores = new HashMap<>();

	@PostConstruct
	public void initData() {

		// Base de datos de películas
		Director director = new Director();
		director.setNombre("Juan Gomez");
		director.setEdad(45);
		directores.put(director.getNombre(), director);

		director = new Director();
		director.setNombre("Pepito perez");
		director.setEdad(65);
		directores.put(director.getNombre(), director);
		
		director = new Director();
		director.setNombre("Marta Lucrecia");
		director.setEdad(25);
		directores.put(director.getNombre(), director);


	}

	//Método que busca el director por su nombre
	public Director findDirector(String name) {
		Assert.notNull(name, "El nombre del director no debe ser null");
		return directores.get(name);
	}
	
	 //Getter del Map, que se usará para asociacion en las peliculas
    public static Map<String, Director> getDirectores() {
		return directores;
	}
}