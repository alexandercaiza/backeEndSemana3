package com.tarea3.tarea3;

import com.tarea3.tarea3.controller.ProyectoController;
import com.tarea3.tarea3.model.Proyecto;
import com.tarea3.tarea3.servicio.ProyectoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProyectoControllerTest {

    @Mock
    private ProyectoService proyectoService;

    @InjectMocks
    private ProyectoController proyectoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(proyectoController).build();
    }

    @Test
    public void testGetProyecto() throws Exception {
        Optional<Proyecto> proyecto = Optional.ofNullable(new Proyecto());
        when(proyectoService.obtenerProyectoPorId(1L)).thenReturn(proyecto);

        mockMvc.perform(get("/proyectos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Proyecto A"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del proyecto A"));
    }

    @Test
    public void testCreateProyecto() throws Exception {
        Proyecto proyecto = new Proyecto();
        Proyecto createdProyecto = new Proyecto();

        when(proyectoService.crearProyecto(proyecto)).thenReturn(createdProyecto);

        mockMvc.perform(post("/proyectos")
                        .contentType("application/json")
                        .content("{\"nombre\": \"Proyecto B\", \"descripcion\": \"Descripción del proyecto B\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Proyecto B"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del proyecto B"));
    }
}