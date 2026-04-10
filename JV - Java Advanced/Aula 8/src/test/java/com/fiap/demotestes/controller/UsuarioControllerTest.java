package com.fiap.demotestes.controller;

import com.fiap.demotestes.model.Usuario;
import com.fiap.demotestes.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;//Simula requisições HTTP

    @Autowired
    private ObjectMapper objectMapper; //Converte objetos para JSON

    @MockitoBean //Mock do service (não queremos testar o service aqui
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario(1l,"João Silva","joao@email.com",25);
    }

    @Test
    @DisplayName("GET /api/usuarios - Deve retornar lista de usuarios")
    void listarTodos_DeveRetornarListaDeUsuarios() throws Exception {
        List<Usuario> usuarios = List.of(usuario);
        when(usuarioService.listarTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk()) //Verifica status HTTP 200
                .andExpect(jsonPath("$",hasSize(1))) //Verifica o tamanho da lista
                .andExpect(jsonPath("$[0].id",is(1))) //Verifica ID do primeiro
                .andExpect(jsonPath("$[0].nome",is("João Silva")))//Verifica nome
                .andExpect(jsonPath("$[0].email",is("joao@email.com")))//Verifica email
                .andExpect(jsonPath("$[0].idade",is(25)));

        verify(usuarioService,times(1)).listarTodos();
    }

    @Test
    @DisplayName("GET /api/usuarios/{id} - Deve retornar usuário quando existir")
    void buscarPorId_QuandoUsuarioExiste_DevoRetornarUsuario() throws Exception {
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk()) //Verifica status HTTP 200
                .andExpect(jsonPath("$.id",is(1))) //Verifica ID do primeiro
                .andExpect(jsonPath("$.nome",is("João Silva")));//Verifica nome

        verify(usuarioService,times(1)).buscarPorId(1L);

    }

    @Test
    @DisplayName("POST /api/usuarios - Deve criar usuário e retornar status 201")
    void criar_DeveRetornarStatusCreated() throws Exception {
        Usuario novoUsuario = new Usuario(null,"Maria Santos","maria@email.com",28);
        Usuario usuarioSalvo = new Usuario(2L,"Maria Santos","maria@email.com",28);

        when(usuarioService.criar(any(Usuario.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/api/usuarios"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoUsuario))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(2)))
                .andExpect(jsonPath("$.nome",is("Maria Santos")));

        verify(usuarioService,times(1)).criar(any(Usuario.class));

    }

    @Test
    @DisplayName("PUT /api/usuarios/{id} - Deve atualizar usuário")
    void atualizar_DevoAtualizarUsuario() throws Exception {
        Usuario usuarioAtualizado = new Usuario(null, "João Atualizado", "joao@email.com", 26);
        Usuario usuarioSalvo = new Usuario(1L, "João Atualizado", "joao@email.com", 26);

        when(usuarioService.atualizar(eq(1L), any(Usuario.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/api/usuarios/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioAtualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome",is("João Atualizado")))
                .andExpect(jsonPath("$.idade",is(26)));


        verify(usuarioService,times(1)).atualizar(eq(1L),any(Usuario.class));
    }

    @Test
    @DisplayName("DELETE /api/usuarios/{id} - Deve deletar usuário e retornar status 204")
    void deletar_DevoRetornarStatusNoContent() throws Exception {
        doNothing().when(usuarioService).deletar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService,times(1)).deletar(1L);
    }
}
