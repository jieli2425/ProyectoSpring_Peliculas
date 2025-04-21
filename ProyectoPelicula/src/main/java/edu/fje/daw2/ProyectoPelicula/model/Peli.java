package edu.fje.daw2.ProyectoPelicula.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pelicules")
public class Peli {
    @Id
    private String id;
    private String titol;
    private String director;
    private int any;
    private String descripcio;

    public Peli() {}

    public Peli(String titol, String director, int any, String descripcio) {
        this.titol = titol;
        this.director = director;
        this.any = any;
        this.descripcio = descripcio;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getAny() { return any; }
    public void setAny(int any) { this.any = any; }

    public String getDescripcio() { return descripcio; }
    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }
}