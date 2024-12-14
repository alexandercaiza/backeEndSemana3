package com.tarea3.tarea3.controller;

import com.tarea3.tarea3.model.Perfil;
import com.tarea3.tarea3.servicio.PerfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    // Crear un nuevo perfil
    @PostMapping
    public ResponseEntity<Perfil> crearPerfil(@RequestBody Perfil perfil) {
        Perfil perfilCreado = perfilService.crearPerfil(perfil);
        return ResponseEntity.ok(perfilCreado);
    }

    // Obtener todos los perfiles
    @GetMapping
    public ResponseEntity<List<Perfil>> obtenerTodosPerfiles() {
        List<Perfil> perfiles = perfilService.obtenerTodosPerfiles();
        return ResponseEntity.ok(perfiles);
    }

    // Obtener un perfil por su id
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtenerPerfilPorId(@PathVariable Long id) {
        Optional<Perfil> perfil = perfilService.obtenerPerfilPorId(id);
        return perfil.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un perfil
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> actualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfil) {
        Perfil perfilActualizado = perfilService.actualizarPerfil(id, perfil);
        if (perfilActualizado != null) {
            return ResponseEntity.ok(perfilActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un perfil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfil(@PathVariable Long id) {
        boolean eliminado = perfilService.eliminarPerfil(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
