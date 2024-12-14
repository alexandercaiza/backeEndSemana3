package com.tarea3.tarea3.repository;

import com.tarea3.tarea3.model.Perfil;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
