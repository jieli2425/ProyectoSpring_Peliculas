//package edu.fje.daw2.ProyectoPelicula.controladors;
//
//import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class PeliculasController {
//
//    @Autowired
//    private PeliculaRepositori repositori;
//
//    @GetMapping("/peliculas")
//    public String consultarPeliculas(Model model) {
//        model.addAttribute("peliculas", repositori.findAll());
//        return "usuari/consultarPeliculas";
//    }
//}

package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UsuarioController {

    @Autowired
    private PeliculaRepositori repositori;

    @GetMapping("/consultar")
    public String indexUsuari(@RequestParam(required = false) String titol, Model model) {
        List<Peli> pelis = new ArrayList<>();

        if (titol != null && !titol.isEmpty()) {
            Peli peli = repositori.findByTitol(titol);
            if (peli != null) pelis.add(peli);
        } else {
            pelis = repositori.findAll();
        }

        model.addAttribute("pelis", pelis);
        return "index";
    }
}
