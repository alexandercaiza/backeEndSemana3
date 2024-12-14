package com.tarea3.tarea3.servicio;


import com.tarea3.tarea3.model.Perfil;
import com.tarea3.tarea3.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    // Crear un nuevo perfil
    public Perfil crearPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    // Obtener todos los perfiles
    public List<Perfil> obtenerTodosPerfiles() {
        return perfilRepository.findAll();
    }

    // Obtener un perfil por su id
    public Optional<Perfil> obtenerPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }

    // Actualizar un perfil existente
    public Perfil actualizarPerfil(Long id, Perfil perfil) {
        if (perfilRepository.existsById(id)) {
            perfil.setId(id);
            return perfilRepository.save(perfil);
        } else {
            return null; // o lanzar una excepción
        }
    }

    // Eliminar un perfil por su id
    public boolean eliminarPerfil(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}