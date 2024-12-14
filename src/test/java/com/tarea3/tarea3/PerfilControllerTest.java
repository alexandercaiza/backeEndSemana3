package com.tarea3.tarea3;

import com.tarea3.tarea3.controller.PerfilController;
import com.tarea3.tarea3.model.Perfil;
import com.tarea3.tarea3.servicio.PerfilService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class PerfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PerfilService perfilService;

    @InjectMocks
    private PerfilController perfilController;

    private Perfil perfil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(perfilController).build();
        perfil = new Perfil();
        perfil.setId(1L);
        perfil.setDescripcion("Perfil de prueba");
    }

    @Test
    void crearPerfil() throws Exception {
        when(perfilService.crearPerfil(perfil)).thenReturn(perfil);

        mockMvc.perform(post("/perfiles")
                .contentType("application/json")
                .content("{\"descripcion\":\"Perfil de prueba\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Perfil de prueba"));
    }

    @Test
    void obtenerPerfilPorId() throws Exception {
        when(perfilService.obtenerPerfilPorId(1L)).thenReturn(java.util.Optional.of(perfil));

        mockMvc.perform(get("/perfiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Perfil de prueba"));
    }
}