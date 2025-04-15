package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("llistaPeliculas")
public class PeliculasController {

    @Autowired
    private PeliculaRepositori repositori;

    private int numId = 1;

    @ModelAttribute("llistaPeliculas")
    public List<Peli> inicializarLlistaPeliculas() {
        return new ArrayList<>();
    }

    @PostMapping("/afegirPelicula")
    public String afegirPelicula(
            @SessionAttribute("llistaPeliculas") ArrayList<Peli> pelis,
            @RequestParam(required = true) String titol,
            @RequestParam(required = true) String director,
            @RequestParam(required = true) int any,
            @RequestParam(required = true) String descripcio,
            Model model) {
        Peli peli = new Peli(titol, director, any, descripcio);
        pelis.add(peli);
        model.addAttribute("peliAfegida", peli);
//        model.addAttribute("llistaPeliculas", pelis);
        return "redirect:/consultarPeliculas";
    }

    @GetMapping("/consultarPeliculas")
    public String consultarPeliculas(Model model) {
        return "peliculas/consultarPeliculas";
    }

    @PostMapping("/esborrarPelicula")
    public String esborrarPelicula(
            @SessionAttribute("llistaPeliculas") ArrayList<Peli> pelis,
            @RequestParam(required = true) String id,
            Model model) {
        pelis.removeIf(p -> p.getId().equals(id));
        model.addAttribute("llistaPeliculas", pelis);
        return "redirect:/consultarPeliculas";
    }

    @PostMapping("/modificarPelicula")
    public String modificarPelicula(
            @SessionAttribute("llistaPeliculas") ArrayList<Peli> pelis,
            @RequestParam(required = true) String id,
            @RequestParam(required = true) String titol,
            @RequestParam(required = true) String director,
            @RequestParam(required = true) int any,
            @RequestParam(required = true) String descripcio,
            Model model) {
        Peli peli = pelis.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (peli != null) {
            peli.setTitol(titol);
            peli.setDirector(director);
            peli.setAny(any);
            peli.setDescripcio(descripcio);
        }
        model.addAttribute("llistaPeliculas", pelis);
        return "redirect:/consultarPeliculas";
    }
}