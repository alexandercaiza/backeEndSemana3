package com.tarea3.tarea3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tarea3.tarea3.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Consulta personalizada con subconsulta y INNER JOIN
    @Query("SELECT u FROM Usuario u INNER JOIN u.proyectos p WHERE p.nombre = ?1")
    List<Usuario> findUsuariosByProyectoNombre(String nombreProyecto);

    @Query("SELECT u FROM Usuario u WHERE u.email IN (SELECT u2.email FROM Usuario u2 JOIN u2.proyectos p WHERE p.nombre = ?1)")
List<Usuario> findUsuariosByProyectoSubconsulta(String nombreProyecto);

}
