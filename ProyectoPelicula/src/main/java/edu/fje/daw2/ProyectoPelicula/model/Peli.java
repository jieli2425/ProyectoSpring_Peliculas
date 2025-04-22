package edu.fje.daw2.ProyectoPelicula.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa una película almacenada en la colección "pelicules" de MongoDB.
 */
@Document(collection = "pelicules")
public class Peli {

    /**
     * Identificador único de la película (generado por MongoDB).
     */
    @Id
    private String id;

    /**
     * Título de la película.
     */
    private String titol;

    /**
     * Nombre del director de la película.
     */
    private String director;

    /**
     * Año de estreno de la película.
     */
    private int any;

    /**
     * Descripción o sinopsis de la película.
     */
    private String descripcio;

    /**
     * URL de la imagen de la película.
     */
    private String imagen;

    /**
     * Constructor vacío necesario para la deserialización de Spring Data.
     */
    public Peli() {}

    /**
     * Constructor con todos los campos excepto el identificador.
     *
     * @param titol      título de la película
     * @param director   nombre del director
     * @param any        año de estreno
     * @param descripcio descripción de la película
     * @param imagen     URL de la imagen
     */
    public Peli(String titol, String director, int any, String descripcio, String imagen) {
        this.titol = titol;
        this.director = director;
        this.any = any;
        this.descripcio = descripcio;
        this.imagen = imagen;
    }

    /**
     * Devuelve el identificador de la película.
     * @return id de la película
     */
    public String getId() { return id; }

    /**
     * Asigna un identificador a la película.
     * @param id identificador nuevo
     */
    public void setId(String id) { this.id = id; }

    /**
     * Devuelve el título de la película.
     * @return título
     */
    public String getTitol() { return titol; }

    /**
     * Asigna el título de la película.
     * @param titol nuevo título
     */
    public void setTitol(String titol) { this.titol = titol; }

    /**
     * Devuelve el director de la película.
     * @return director
     */
    public String getDirector() { return director; }

    /**
     * Asigna el director de la película.
     * @param director nuevo director
     */
    public void setDirector(String director) { this.director = director; }

    /**
     * Devuelve el año de estreno de la película.
     * @return año de estreno
     */
    public int getAny() { return any; }

    /**
     * Asigna el año de estreno de la película.
     * @param any nuevo año de estreno
     */
    public void setAny(int any) { this.any = any; }

    /**
     * Devuelve la descripción de la película.
     * @return descripción
     */
    public String getDescripcio() { return descripcio; }

    /**
     * Asigna una nueva descripción a la película.
     * @param descripcio nueva descripción
     */
    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}