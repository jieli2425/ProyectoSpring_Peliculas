//package edu.fje.daw2.ProyectoPelicula.controladors;
//
//import edu.fje.daw2.ProyectoPelicula.model.Peli;
//import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private PeliculaRepositori repositori;
//
//    @GetMapping("/peliculas")
//    public String llistarPeliculas(Model model) {
//        model.addAttribute("peliculas", repositori.findAll());
//        return "admin/llistaPeliculas";
//    }
//
//    @GetMapping("/afegir")
//    public String mostrarFormAfegir(Model model) {
//        model.addAttribute("peli", new Peli());
//        return "admin/afegirPelicula";
//    }
//
//    @PostMapping("/afegir")
//    public String afegirPelicula(@ModelAttribute("peli") Peli peli) {
//        repositori.save(peli);
//        return "redirect:/admin/peliculas";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String mostrarFormEditar(@PathVariable String id, Model model) {
//        Peli peli = repositori.findById(id).orElse(null);
//        model.addAttribute("peli", peli);
//        return "admin/editarPelicula";
//    }
//
//    @PostMapping("/editar")
//    public String editarPelicula(@ModelAttribute("peli") Peli peli) {
//        repositori.save(peli);
//        return "redirect:/admin/peliculas";
//    }
//
//    @GetMapping("/eliminar/{id}")
//    public String eliminarPelicula(@PathVariable String id) {
//        repositori.deleteById(id);
//        return "redirect:/admin/peliculas";
//    }
//}

package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PeliculaRepositori repositori;

    @GetMapping("/crudAdmin")
    public String indexAdmin(Model model) {
        model.addAttribute("pelis", repositori.findAll());
        return "admin/index";
    }

    @PostMapping("/afegir")
    public String afegirPeli(@ModelAttribute Peli peli) {
        repositori.save(peli);
        return "redirect:/admin/crudAdmin";
    }

    @PostMapping("/modificar")
    public String modificarPeli(@ModelAttribute Peli peli) {
        repositori.save(peli);
        return "redirect:/admin/crudAdmin";
    }

    @PostMapping("/eliminar")
    public String eliminarPeli(@RequestParam String id) {
        repositori.deleteById(id);
        return "redirect:/admin/crudAdmin";
    }
}