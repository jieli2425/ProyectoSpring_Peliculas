package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que gestiona las operaciones CRUD para las películas
 * desde el panel de administración.
 *
 * Ruta base: /admin
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Repositorio para acceder a las películas almacenadas en MongoDB.
     */
    @Autowired
    private PeliculaRepositori repositori;

    /**
     * Muestra la página principal del CRUD de administración con todas las películas.
     *
     * @param model Objeto para pasar datos a la vista
     * @return nombre de la vista HTML "admin/index"
     */
    @GetMapping("/crudAdmin")
    public String indexAdmin(Model model) {
        model.addAttribute("pelis", repositori.findAll());
        return "admin/index";
    }

    /**
     * Agrega una nueva película al repositorio.
     *
     * @param peli Objeto de película a agregar
     * @return redirección a la página del CRUD
     */
    @PostMapping("/afegir")
    public String afegirPeli(@ModelAttribute Peli peli) {
        repositori.save(peli);
        return "redirect:/admin/crudAdmin";
    }

    /**
     * Muestra el formulario de edición de una película existente.
     *
     * @param id ID de la película a editar
     * @param model Modelo para pasar la película a la vista
     * @return nombre de la vista para editar, o redirección si la película no existe
     */
    @GetMapping("/modificar/{id}")
    public String mostrarFormEditar(@PathVariable String id, Model model) {
        Peli peli = repositori.findById(id).orElse(null);
        if (peli != null) {
            model.addAttribute("peli", peli);
            return "admin/editarPelicula";
        } else {
            return "redirect:/admin/crudAdmin";
        }
    }

    /**
     * Modifica una película existente guardando los cambios en el repositorio.
     *
     * @param peli Objeto de película con los datos modificados
     * @return redirección a la página principal del CRUD
     */
    @PostMapping("/modificar")
    public String modificarPeli(@ModelAttribute Peli peli) {
        repositori.save(peli);
        return "redirect:/admin/crudAdmin";
    }

    /**
     * Elimina una película a partir de su ID.
     *
     * @param id ID de la película a eliminar
     * @return redirección a la página principal del CRUD
     */
    @PostMapping("/eliminar")
    public String eliminarPeli(@RequestParam String id) {
        repositori.deleteById(id);
        return "redirect:/admin/crudAdmin";
    }
}