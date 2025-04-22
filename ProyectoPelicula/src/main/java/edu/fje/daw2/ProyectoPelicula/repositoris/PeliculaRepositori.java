package edu.fje.daw2.ProyectoPelicula.repositoris;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Repositorio para acceder a los datos de películas almacenadas en MongoDB.
 * Extiende de {@link MongoRepository} para proporcionar operaciones CRUD.
 */
public interface PeliculaRepositori extends MongoRepository<Peli, String> {

    /**
     * Busca una lista de películas por su título exacto.
     *
     * @param titol título de la película a buscar
     * @return lista de películas con el título especificado
     */
    List<Peli> findByTitol(String titol);
}