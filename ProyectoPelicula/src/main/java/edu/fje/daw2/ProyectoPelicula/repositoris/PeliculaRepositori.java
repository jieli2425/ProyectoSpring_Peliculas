//package edu.fje.daw2.sm72.repositoris;
//import edu.fje.daw2.sm72.model.Client;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import java.util.List;

//public interface ClientRepositori extends MongoRepository<Client, String> {
    //Client findByNom(String nom);
    //List<Client> findByCognom(String cognom);
    //List<Client> findByVolumCompres(int volumCompres);
//}

package edu.fje.daw2.ProyectoPelicula.repositoris;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeliculaRepositori extends MongoRepository<Peli, String> {
    Peli findByTitol(String titol);
}

