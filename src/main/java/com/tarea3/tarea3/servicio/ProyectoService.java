package com.tarea3.tarea3.servicio;

import com.tarea3.tarea3.model.Proyecto;
import com.tarea3.tarea3.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Crear un nuevo proyecto
    public Proyecto crearProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    // Obtener todos los proyectos
    public List<Proyecto> obtenerTodosProyectos() {
        return proyectoRepository.findAll();
    }

    // Obtener un proyecto por su ID
    public Optional<Proyecto> obtenerProyectoPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    // Actualizar un proyecto existente
    public Proyecto actualizarProyecto(Long id, Proyecto proyecto) {
        if (proyectoRepository.existsById(id)) {
            proyecto.setId(id);
            return proyectoRepository.save(proyecto);
        } else {
            return null;
        }
    }

    // Eliminar un proyecto por su ID
    public boolean eliminarProyecto(Long id) {
        if (proyectoRepository.existsById(id)) {
            proyectoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
