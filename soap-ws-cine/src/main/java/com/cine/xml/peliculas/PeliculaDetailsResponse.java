//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.05 a las 10:33:09 PM CET 
//


package com.cine.xml.peliculas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pelicula" type="{http://www.cine.com/xml/peliculas}Pelicula"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pelicula"
})
@XmlRootElement(name = "PeliculaDetailsResponse")
public class PeliculaDetailsResponse {

    @XmlElement(name = "Pelicula", required = true)
    protected Pelicula pelicula;

    /**
     * Obtiene el valor de la propiedad pelicula.
     * 
     * @return
     *     possible object is
     *     {@link Pelicula }
     *     
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Define el valor de la propiedad pelicula.
     * 
     * @param value
     *     allowed object is
     *     {@link Pelicula }
     *     
     */
    public void setPelicula(Pelicula value) {
        this.pelicula = value;
    }

}
