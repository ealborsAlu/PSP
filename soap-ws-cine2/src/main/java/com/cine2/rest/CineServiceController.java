package com.cine2.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * @author Enrique Albors
 * Clase que se encargará de las request  de cine
 * */

import org.springframework.web.bind.annotation.RestController;
import com.cine2.rest.modelo.Cine;



@RestController
public class CineServiceController {
	//HashMap que será la base de datos de  los cines
	private static Map<String , Cine> cineRepo = new HashMap<>();

	static {
		//se crean el cine
		Cine c1 = new Cine();
		c1.setId("1");
		c1.setNombre("Erosky");
		c1.setDireccion("Carretera Amarilla 20");
		c1.setValoracion("4/5");
		//se introduce en el HashMap , siendo su Id la key
		cineRepo.put(c1.getId(), c1);

		Cine c2 = new Cine();
		c2.setId("2");
		c2.setNombre("Lagoh");
		c2.setDireccion("Castillo de Tolosa 1");
		c2.setValoracion("2/5");
		cineRepo.put(c2.getId(), c2);
	}
	
	//----------------------------------------------Métodos CRUD----------------------------------------------
	
	// Listar todos los cines
	/**
	 * 
	 * @return  devuelve los cines
	 */
	@GetMapping("/cines")
	public ResponseEntity<Object> getCine() {
		return new ResponseEntity<>(cineRepo.values(), HttpStatus.OK);
	}

	// Listar un cine en específico
	/**
	 * 
	 * @param id
	 * @return devuelve el cine indicando
	 */
	@GetMapping("/cines/{id}")
	public ResponseEntity<Object> getCinesId(@PathVariable("id") String id) {
		return new ResponseEntity<>(cineRepo.get(id), HttpStatus.OK);
	}

	// CREAR un cine
	/**
	 * 
	 * @param cine
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o indcorrecta
	 */
	@PostMapping("/cines")
	public ResponseEntity<Object> creteCine(@RequestBody Cine cine) {
		cineRepo.put(cine.getId(), cine);
		return new ResponseEntity<>("Cine creado correctamente", HttpStatus.CREATED);
	}
	// Actualizar  un cine
	/**
	 * 
	 * @param id
	 * @param cine
	 * @return  mensaje para usuario  indicando resultado de operacion correcta o indcorrecta
	 */
	@PutMapping("/cines/{id}")
	public ResponseEntity<Object> updateCine(@PathVariable("id") String id, @RequestBody Cine cine) {
		cineRepo.remove(id);
		cine.setId(id);
		cineRepo.put(id, cine);
		return new ResponseEntity<>("Cine actualizada correctamente", HttpStatus.OK);
	}
		 

	// Borrar una cine  en en específico
	/**
	* 
	* @param id
	* @return  mensaje para usuario  indicando resultado de operacion correcta o indcorrecta
	*/
	@DeleteMapping("/cines/{id}")
	public ResponseEntity<Object> deleteCine(@PathVariable("id") String id) {
		cineRepo.remove(id);
		return new ResponseEntity<>("Cine borrado correctamente", HttpStatus.OK);
	}

	
	
}
