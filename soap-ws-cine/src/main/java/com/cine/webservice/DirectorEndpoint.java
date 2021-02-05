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
 * Esta clase será la encargada de gestionar las request realizadas hacia los directores
 */

//Clase que recibe las peticiones de los directores
@Endpoint
public class DirectorEndpoint {
	
	//Direccion a la que se dirige
    private static final String NAMESPACE_URI = "http://www.cine.com/xml/peliculas";
 
    private DirectorRepository DirectorRepository;
 
    //publica el endpoint dentro de spring , cuando se levante la aplicación , creará un objeto endpoint
    @Autowired
    public DirectorEndpoint(DirectorRepository DirectorRepository) {
        this.DirectorRepository = DirectorRepository;
    }
    
    
    /*servicio 4
    * Método que se ejecutará cuando se reciba una petición , a este e le pasa el 
    * nombre del director , que se usará para bucar en el map y luego se devolerán todos 
    * los datos del director 
    * */
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DirectorDetailsRequest")
   @ResponsePayload
   public DirectorDetailsResponse getPelicula(@RequestPayload DirectorDetailsRequest request) {
	   DirectorDetailsResponse response = new DirectorDetailsResponse();
       response.setDirector(DirectorRepository.findDirector(request.getNombre()));

       return response;
   }

}
