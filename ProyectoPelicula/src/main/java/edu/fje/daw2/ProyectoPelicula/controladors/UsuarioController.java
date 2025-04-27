package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Peli;
import edu.fje.daw2.ProyectoPelicula.model.Usuario;
import edu.fje.daw2.ProyectoPelicula.repositoris.UsuarioRepositori;
import edu.fje.daw2.ProyectoPelicula.repositoris.PeliculaRepositori;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que gestiona las funcionalidades relacionadas con los usuarios,
 * incluyendo login, listado, creación, modificación y eliminación de usuarios.
 */
@Controller
public class UsuarioController {

    @Autowired
    private PeliculaRepositori peliculaRepositori;
    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @PostMapping("/like/{idPelicula}")
    public String likePelicula(@PathVariable String idPelicula, HttpSession session, Model model) {
        // Obtener el usuario actual de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario"); // Asumiendo que el usuario está en la sesión

        if (usuario != null) {
            Peli pelicula = peliculaRepositori.findById(idPelicula).orElse(null);

            if (pelicula != null) {
                if (pelicula.getLikes().contains(usuario.getNombreUsuario())) {
                    pelicula.getLikes().remove(usuario.getNombreUsuario());
                } else {
                    pelicula.getLikes().add(usuario.getNombreUsuario());
                }
                peliculaRepositori.save(pelicula);
            }
        }

        model.addAttribute("pelis", peliculaRepositori.findAll());
        return "consultarPeli";
    }

    /**
     * Muestra la página de login.
     *
     * @return nombre de la vista de login
     */
    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    /**
     * Procesa el formulario de login.
     *
     * @param username nombre de usuario introducido
     * @param password contraseña introducida
     * @param session  sesión HTTP para almacenar el usuario autenticado
     * @param model    modelo para pasar mensajes a la vista
     * @return redirección al área correspondiente según el rol o vista de login si hay error
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Usuario usuario = usuarioRepositori.obtenerNombreUsuario(username);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            session.setAttribute("usuario", usuario);
            if (usuario.getRol().equals("admin")) {
                return "redirect:/admin/crudAdmin";
            } else {
                return "redirect:/consultar";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    /**
     * Lista todos los usuarios para el administrador.
     *
     * @param model modelo con la lista de usuarios
     * @return vista con la tabla de usuarios
     */
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepositori.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    /**
     * Muestra el formulario para crear un nuevo usuario.
     *
     * @return vista de creación de usuario
     */
    @GetMapping("/admin/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario() {
        return "admin/crearUsuario";
    }

    /**
     * Procesa la creación de un nuevo usuario.
     *
     * @param nombreUsuario nombre de usuario
     * @param contrasena    contraseña
     * @param rol           rol (admin o usuario)
     * @return redirección al listado de usuarios
     */
    @PostMapping("/admin/usuarios")
    public String crearUsuario(@RequestParam String nombreUsuario, @RequestParam String contrasena, @RequestParam String rol) {
        Usuario nuevoUsuario = new Usuario(0, nombreUsuario, contrasena, rol);
        usuarioRepositori.agregarUsuario(nuevoUsuario);
        return "redirect:/admin/usuarios";
    }

    /**
     * Elimina un usuario a partir de su ID.
     *
     * @param id identificador del usuario a eliminar
     * @return redirección al listado de usuarios
     */
    @GetMapping("/admin/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        usuarioRepositori.eliminarUsuario(id);
        return "redirect:/admin/usuarios";
    }

    /**
     * Muestra el formulario para editar un usuario existente.
     *
     * @param id    identificador del usuario
     * @param model modelo con los datos del usuario
     * @return vista de edición de usuario
     */
    @GetMapping("/admin/usuarios/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = usuarioRepositori.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "admin/editarUsuario";
    }

    /**
     * Procesa la modificación de un usuario.
     *
     * @param id            identificador del usuario
     * @param nombreUsuario nombre de usuario
     * @param contrasena    contraseña
     * @param rol           rol (admin o usuario)
     * @return redirección al listado de usuarios
     */
    @PostMapping("/admin/usuarios/editar")
    public String editarUsuario(@RequestParam int id, @RequestParam String nombreUsuario, @RequestParam String contrasena, @RequestParam String rol) {
        Usuario usuarioActualizado = new Usuario(id, nombreUsuario, contrasena, rol);
        usuarioRepositori.actualizarUsuario(usuarioActualizado);
        return "redirect:/admin/usuarios";
    }

    /**
     * Cierra la sesión del usuario y redirige al login.
     *
     * @param session sesión HTTP actual
     * @return redirección a la vista de login
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}