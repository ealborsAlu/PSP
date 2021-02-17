package com.cine2.rest;

/*
 * @author Enrique Albors
 * Clase que se encargará de las request de pelicula y directores
 * */
//import para el hashmap
import java.util.HashMap;
import java.util.Map;

//import para los métodos CRUD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import de anotaciones
import org.springframework.web.bind.annotation.RestController;

//importamos pelicula
import com.cine2.rest.modelo.Pelicula;
//importamos director
import com.cine2.rest.modelo.Director;

@RestController
public class DirectorServiceController {

	// Map que será nuestra base de datos para directores
	private static Map<String, Director> directorRepo = new HashMap<>();
	static {
		//Se crea el director
		Director d1 = new Director();
		d1.setId("1");
		d1.setNombre("Enrique Albors");
		d1.setEdad(45);
		//Se introduce en el HashMap
		directorRepo.put(d1.getId(), d1);

		Director d2 = new Director();
		d2.setId("2");
		d2.setNombre("Adolfo Garcia");
		d2.setEdad(60);
		directorRepo.put(d2.getId(), d2);

		Director d3 = new Director();
		d3.setId("3");
		d3.setNombre("Daniel Sarmiento");
		d3.setEdad(25);
		directorRepo.put(d3.getId(), d3);
	}

	// Map que será nuestra base de datos para peliculas
	private static Map<String, Pelicula> peliculaRepo = new HashMap<>();
	static {
		// pelicula 1
		Pelicula peli1 = new Pelicula();
		peli1.setId("1");
		peli1.setNombre("La pintora y el ladrón");
		peli1.setDuracion(106);
		peli1.setGenero("Documental");
		peli1.setProductora("Filmmin");
		peli1.setDirector(directorRepo.get("1"));
		// se añade al map
		peliculaRepo.put(peli1.getId(), peli1);

		Pelicula peli2 = new Pelicula();
		peli2.setId("2");
		peli2.setNombre("mws lowry son");
		peli2.setDuracion(91);
		peli2.setGenero("Drama");
		peli2.setProductora("Vercine");
		peli2.setDirector(directorRepo.get("1"));
		peliculaRepo.put(peli2.getId(), peli2);

		Pelicula peli3 = new Pelicula();
		peli3.setId("3");
		peli3.setNombre("Inmune");
		peli3.setDuracion(84);
		peli3.setGenero("Drama  , Ciencia Ficcion");
		peli3.setProductora("Diamond Films");
		peli3.setDirector(directorRepo.get("2"));
		peliculaRepo.put(peli3.getId(), peli3);

		Pelicula peli4 = new Pelicula();
		peli4.setId("4");
		peli4.setNombre("Anton");
		peli4.setDuracion(102);
		peli4.setGenero("Drama");
		peli4.setProductora("European");
		peli4.setDirector(directorRepo.get("3"));
		peliculaRepo.put(peli4.getId(), peli4);

		Pelicula peli5 = new Pelicula();
		peli5.setId("5");
		peli5.setNombre("La chica del brazalete");
		peli5.setDuracion(95);
		peli5.setGenero("Drama, crimen");
		peli5.setProductora("Surtsey");
		peli5.setDirector(directorRepo.get("2"));
		peliculaRepo.put(peli5.getId(), peli5);

	}

	//------------------------------------------------------- Métodos CRUD para director-------------------------------------------------------

	// Listar todos los directores
	/**
	 * @return devuelve los datos de los directores
	 */
	@GetMapping("/directores")
	public ResponseEntity<Object> getDirector() {
		return new ResponseEntity<>(directorRepo.values(), HttpStatus.OK);
	}

	// Listar un director en específico
	/**
	 * 
	 * @param id del director determinado a saber
	 * @return se devuelven los datos del director
	 */
	@GetMapping("/directores/{id}")
	public ResponseEntity<Object> getDirectoresId(@PathVariable("id") String id) {
		if (directorRepo.containsKey(id)) {
			return new ResponseEntity<>(directorRepo.get(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Director no encontrado", HttpStatus.NO_CONTENT);
		}
	}

	// CREAR un director
	/**
	 * 
	 * @param director 
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta
	 */
	@PostMapping("/directores")
	public ResponseEntity<Object> createDirector(@RequestBody Director director) {
		directorRepo.put(director.getId(), director);
		return new ResponseEntity<>("Director creado correctamente", HttpStatus.CREATED);
	}

	// Actualizar un director
	/**
	 * 
	 * @param id del director a editar
	 * @param director
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta 
	 */
	@PutMapping("/directores/{id}")
	public ResponseEntity<Object> updateDirector(@PathVariable("id") String id, @RequestBody Director director) {
		directorRepo.remove(id);
		director.setId(id);
		directorRepo.put(id, director);
		return new ResponseEntity<>("Director actualizado correctamente", HttpStatus.OK);
	}

	// Borrar una director en en específico
	/**
	 * 
	 * @param id del director a borrar
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta
	 */
	@DeleteMapping("/directores/{id}")
	public ResponseEntity<Object> deleteDirector(@PathVariable("id") String id) {
		directorRepo.remove(id);
		return new ResponseEntity<>("Director borrado correctamente", HttpStatus.OK);
	}

	//--------------------------------------------------------------CRUD Peliculas--------------------------------------------------------------

	// Listar todas las películas de un director
	/**
	 * 
	 * @param idD , id del director a la que se le asignara una pelicula
	 * @return devuelve un array con las peliculas determinadas 
	 */
	@GetMapping("/directores/{idD}/peliculas")
	public ResponseEntity<Object> getPeliculas(@PathVariable("idD") String idD) {
		Map<String, Pelicula> peliculaRepoAdicional = new HashMap<>();// hashamp adicional , se guardarán la película que se busquen
		// el for se debe inicializar a 1 , si no da error de nullpointer
		for (int i = 1; i <= peliculaRepo.size(); i++) {// se recorre el hashmap
			// si el valor del director de la pelicula es el mismo que se le ha pasado por parametros
			if (peliculaRepo.get(String.valueOf(i)).getDirector().getId().equals(idD)) {
				// se añade al HashMap adicional usando el valor del la "i" como key
				peliculaRepoAdicional.put(String.valueOf(i), peliculaRepo.get(String.valueOf(i)));
			}
		}
		//se devuelve el HashMap nuevo con las peliculas indicadas
		return new ResponseEntity<>(peliculaRepoAdicional.values(), HttpStatus.OK);// se devuelve
		
	}

	// Listar una pelicula determinada del director indicado
	/**
	 * 
	 * @param idD , id del director 
	 * @param idP , id de la película
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta
	 */
	@GetMapping("/directores/{idD}/peliculas/{idP}")
	public ResponseEntity<Object> getPeliculaById(@PathVariable("idD") String idD, @PathVariable("idP") String idP) {
		if (peliculaRepo.get(idP).getDirector().getId().equals(idD)) {
			return new ResponseEntity<>(peliculaRepo.get(idP), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se puede mostrar la pelicula ya que no es de este director",
					HttpStatus.CREATED);//mensaje para el usuario
		}

	}

	// Crear una pelicula con los datos del director que se le indique
	/**
	 * 
	 * @param idD ,  id del director
	 * @param pelicula , objeto que se usará para crear la nueva película
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta
	 */
	@PostMapping("/directores/{idD}/peliculas")
	public ResponseEntity<Object> createPelicula(@PathVariable("idD") String idD, @RequestBody Pelicula pelicula) {
		pelicula.setDirector(directorRepo.get(idD));//se  le añade el director a la película
		peliculaRepo.put(pelicula.getId(), pelicula);//se añade la película al map
		return new ResponseEntity<>("Pelicula con Director  creada correctamente", HttpStatus.CREATED);//mensaje para el usuario
	}

	// Actualizar pelicula de un director determinado
	/**
	 * 
	 * @param idD ,  id del director
	 * @param idP , id de la película
	 * @param  pelicula , objeto que se usará para crear la nueva película que remplazará a la anterior
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o incorrecta
	 */
	@PutMapping("/directores/{idD}/peliculas/{idP}") // id del director -------------- id de la pelicula---------------objeto pelicula que se usará como nueva
	public ResponseEntity<Object> upadtePelicula(@PathVariable("idD") String idD, @PathVariable("idP") String idP,@RequestBody Pelicula pelicula) {
		if (peliculaRepo.get(idP).getDirector().getId().equals(idD)) {//si existe la película
			peliculaRepo.remove(idP);//se borra del map la pelicula indicada
			pelicula.setId(idP);//se crea una nueva
			peliculaRepo.put(idP, pelicula);//se añade la nueva película al HashMap
			return new ResponseEntity<>("Actualizacion de pelicula realizada correctamente", HttpStatus.OK);//mensaje para el usuario
		} else {
			return new ResponseEntity<>("No se ha podido actualizar la pelicula ", HttpStatus.OK);
		}
	}

	// Borrar pelicula de un director determinado
	/**
	 * 
	 * @param idD ,  id del director
	 * @param idP ,  id de la película
	 * @return mensaje para usuario  indicando resultado de operacion correcta o indcorrecta
	 */
	@DeleteMapping("/directores/{idD}/peliculas/{idP}")
	public ResponseEntity<Object> deletePelicula(@PathVariable("idD") String idD, @PathVariable("idP") String idP) {
		if (peliculaRepo.get(idP).getDirector().getId().equals(idD)) {
			peliculaRepo.remove(idP);//se borra la pelicula indicada 
			return new ResponseEntity<>("Pelicula borrada correctamente", HttpStatus.OK);//mensaje para el usuario
		} else {
			return new ResponseEntity<>("No se ha podido borrar la pelicula ", HttpStatus.OK);
		}

	}
	
	
	


}
