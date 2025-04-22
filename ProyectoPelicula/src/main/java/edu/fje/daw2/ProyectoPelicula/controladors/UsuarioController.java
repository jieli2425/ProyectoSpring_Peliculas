package edu.fje.daw2.ProyectoPelicula.controladors;

import edu.fje.daw2.ProyectoPelicula.model.Usuario;
import edu.fje.daw2.ProyectoPelicula.repositoris.UsuarioRepositori;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepositori usuarioRepositori;

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

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

    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepositori.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    @GetMapping("/admin/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario() {
        return "admin/crearUsuario";
    }

    @PostMapping("/admin/usuarios")
    public String crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String rol) {
        Usuario nuevoUsuario = new Usuario(0, username, password, rol);
        usuarioRepositori.agregarUsuario(nuevoUsuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/admin/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        usuarioRepositori.eliminarUsuario(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/admin/usuarios/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = usuarioRepositori.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "admin/editarUsuario";
    }

    @PostMapping("/admin/usuarios/editar")
    public String editarUsuario(@RequestParam int id, @RequestParam String username, @RequestParam String password, @RequestParam String rol) {
        Usuario usuarioActualizado = new Usuario(id, username, password, rol);
        usuarioRepositori.actualizarUsuario(usuarioActualizado);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

