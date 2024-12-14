package com.tarea3.tarea3;

import com.tarea3.tarea3.controller.UsuarioController;
import com.tarea3.tarea3.model.Usuario;
import com.tarea3.tarea3.servicio.UsuarioService;

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
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testGetUsuario() throws Exception {
        Optional<Usuario> usuario = Optional.ofNullable(new Usuario(1L, "Juan", "juan@mail.com"));
        when(usuarioService.getUsuarioById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("juan@mail.com"));
    }

    @Test
    public void testCreateUsuario() throws Exception {
        Usuario usuario = new Usuario(null, "Pedro", "pedro@mail.com");
        Usuario createdUsuario = new Usuario(2L, "Pedro", "pedro@mail.com");

        when(usuarioService.saveUsuario(usuario)).thenReturn(createdUsuario);

        mockMvc.perform(post("/usuarios")
                        .contentType("application/json")
                        .content("{\"nombre\": \"Pedro\", \"email\": \"pedro@mail.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.email").value("pedro@mail.com"));
    }
}