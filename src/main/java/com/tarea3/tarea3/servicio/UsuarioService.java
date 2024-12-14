package com.tarea3.tarea3.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea3.tarea3.model.Usuario;
import com.tarea3.tarea3.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findUsuariosByProyectoNombre(String nombreProyecto) {
        return usuarioRepository.findUsuariosByProyectoNombre(nombreProyecto);
    }

    public List<Usuario> findUsuariosByProyectoSubconsulta(String nombreProyecto) {
        return usuarioRepository.findUsuariosByProyectoSubconsulta(nombreProyecto);
    }
}

