package com.cine.webservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//importamos todo el bloque
import com.cine.xml.peliculas.*;
 
/**
 * 
 * @author Enrique albors
 * Esta clase será la encargada de gestionar las request realizadas hacia Pelicula
 */

//Clase que recibe las peticiones de las peliculas
@Endpoint
public class PeliculaEndpoint {

	//Direccion a la que se dirige
    private static final String NAMESPACE_URI = "http://www.cine.com/xml/peliculas";
 
    private PeliculasRespository PeliculasRespository;
 
    //publica el endpoint dentro de spring , cuando se levante la aplicación , creará un objeto endpoint
    @Autowired
    public PeliculaEndpoint(PeliculasRespository PeliculaRepository) {
        this.PeliculasRespository = PeliculaRepository;
    }
 

    
    
    /*servicio 1
     * Método que se ejecutará cuando se reciba una petición , a este e le pasa el 
     * nombre de la pelicula , que se usará para bucar en el map y luego se devolerán todos 
     * los datos de la película 
     * */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PeliculaDetailsRequest")
    @ResponsePayload
    public PeliculaDetailsResponse getPelicula(@RequestPayload PeliculaDetailsRequest request) {
    	PeliculaDetailsResponse response = new PeliculaDetailsResponse();
        response.setPelicula(PeliculasRespository.findPelicula(request.getName()));
 
        return response;
    }
    
    /*servicio 2
     * Método que se ejecutará cuando se reciba una petición , a este e le pasa el 
     * nombre de la pelicula , que se usará para bucar en el map y luego se devolerán todos 
     * los datos de la película 
     * */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PeliculaGeneroRequest")
    @ResponsePayload
    public PeliculaGeneroResponse getPelicula(@RequestPayload PeliculaGeneroRequest request) {
    	PeliculaGeneroResponse response = new PeliculaGeneroResponse();
        response.setGenero(PeliculasRespository.findPelicula(request.getName()).getGenero());
 
        return response;
    }
    
    /*servicio 3
     * Método que se ejecutará cuando se reciba una petición , a este e le pasa el 
     * nombre de la pelicula , que se usará para bucar en el map y luego se devolerán todos 
     * los datos de la película 
     * */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PeliculaDuracionRequest")
    @ResponsePayload
    public PeliculaDuracionResponse getPelicula(@RequestPayload PeliculaDuracionRequest request) {
    	PeliculaDuracionResponse response = new PeliculaDuracionResponse();
        response.setDuracion(PeliculasRespository.findPelicula(request.getName()).getDuracion());
 
        return response;
    }
    

    
  
    
    
    
    
}