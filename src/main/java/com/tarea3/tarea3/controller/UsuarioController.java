package com.tarea3.tarea3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tarea3.tarea3.model.Usuario;
import com.tarea3.tarea3.servicio.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @GetMapping("/proyecto/{nombre}")
    public List<Usuario> getUsuariosByProyecto(@PathVariable String nombre) {
        return usuarioService.findUsuariosByProyectoNombre(nombre);
    }

    @GetMapping("/subconsulta/{nombre}")
    public List<Usuario> getUsuariosBySubconsulta(@PathVariable String nombre) {
        return usuarioService.findUsuariosByProyectoSubconsulta(nombre);
    }
}
