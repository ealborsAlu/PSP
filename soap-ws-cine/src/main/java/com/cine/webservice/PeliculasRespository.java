package com.cine.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.cine.xml.peliculas.Pelicula;

/**
 * 
 * @author Enrique albors
 * Esta clase será la base de datos de las películas
 */

@Component
public class PeliculasRespository {


	//se crea la unidad donde se guardarán las peliculas
	private static final Map<String, Pelicula> peliculas = new HashMap<>();

	@PostConstruct
	public void initData() {

		//Base de datos de películas
		Pelicula pelicula = new Pelicula();
		pelicula.setName("La pintora y el ladrón");
		pelicula.setDuracion(106);
		pelicula.setGenero("Documental");
		pelicula.setProductora("Filmin");
		//Se usa el getter de DirectorRepository
		pelicula.setDirector(DirectorRepository.getDirectores().get("Pepito perez"));
		//Se añade pelicula al map
		peliculas.put(pelicula.getName(), pelicula);

		pelicula = new Pelicula();
		pelicula.setName("mws lowry son");
		pelicula.setDuracion(91);
		pelicula.setGenero("Drama");
		pelicula.setProductora("Vercine");
		pelicula.setDirector(DirectorRepository.getDirectores().get("Juan Gomez"));
		peliculas.put(pelicula.getName(), pelicula);

		pelicula = new Pelicula();
		pelicula.setName("Inmune");
		pelicula.setDuracion(84);
		pelicula.setGenero("drama , Ciencia Ficcion");
		pelicula.setProductora("Diamond Films");
		pelicula.setDirector(DirectorRepository.getDirectores().get("Marta Lucrecia"));
		peliculas.put(pelicula.getName(), pelicula);
		
		pelicula = new Pelicula();
		pelicula.setName("Anton");
		pelicula.setDuracion(102);
		pelicula.setGenero("drama");
		pelicula.setProductora("European");
		pelicula.setDirector(DirectorRepository.getDirectores().get("Juan Gomez"));
		peliculas.put(pelicula.getName(), pelicula);
		
		pelicula = new Pelicula();
		pelicula.setName("La chica del brazalete");
		pelicula.setDuracion(95);
		pelicula.setGenero("drama , crimen");
		pelicula.setProductora("Surtsey");
		pelicula.setDirector(DirectorRepository.getDirectores().get("Marta Lucrecia"));
		peliculas.put(pelicula.getName(), pelicula);

	}

	//Método que se usará para buscar las peliculas por su nombre
	public Pelicula findPelicula(String name) {
		Assert.notNull(name, "El nombre de la pelicula no debe ser null");
		return peliculas.get(name);
	}
}