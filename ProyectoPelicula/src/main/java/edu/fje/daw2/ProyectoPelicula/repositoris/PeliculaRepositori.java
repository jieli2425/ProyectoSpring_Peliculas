package edu.fje.daw2.ProyectoPelicula.repositoris;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PeliculaRepositori extends MongoRepository<Peli, String> {
    List<Peli> findByTitol(String titol);
}