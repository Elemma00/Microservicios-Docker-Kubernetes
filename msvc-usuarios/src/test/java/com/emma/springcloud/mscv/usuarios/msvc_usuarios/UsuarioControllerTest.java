package com.emma.springcloud.mscv.usuarios.msvc_usuarios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.emma.springcloud.mscv.usuarios.msvc_usuarios.controllers.UsuarioController;
import com.emma.springcloud.mscv.usuarios.msvc_usuarios.models.entities.Usuario;
import com.emma.springcloud.mscv.usuarios.msvc_usuarios.services.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    @DisplayName("Test listar usuarios")
    public void testListar() throws Exception {
        Usuario usuario1 = new Usuario(1L, "John Doe", "john@example.com", "password");
        Usuario usuario2 = new Usuario(2L, "Jane Doe", "jane@example.com", "password");
        when(usuarioService.listar()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("John Doe"))
                .andExpect(jsonPath("$[1].nombre").value("Jane Doe"));
    }

    @Test
    @DisplayName("Test detalle usuario")
    public void testDetalle() throws Exception {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "password");
        when(usuarioService.porId(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("John Doe"));
    }

    @Test
    @DisplayName("Test crear usuario")
    public void testCrear() throws Exception {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "password");
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"John Doe\", \"email\": \"john@example.com\", \"password\": \"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("John Doe"));
    }

    @Test
    @DisplayName("Test crear usuario con errores de validación")
    public void testEditar() throws Exception {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "password");
        when(usuarioService.porId(1L)).thenReturn(Optional.of(usuario));
        when(usuarioService.guardar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\": \"John Doe\", \"email\": \"john@example.com\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("John Doe"));
    }

    @Test
    @DisplayName("Test eliminar usuario")
    public void testEliminar() throws Exception {
        Usuario usuario = new Usuario(1L, "John Doe", "john@example.com", "password");
        when(usuarioService.porId(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}