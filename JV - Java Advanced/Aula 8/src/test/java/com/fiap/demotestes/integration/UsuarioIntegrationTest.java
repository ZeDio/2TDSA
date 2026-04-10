package com.fiap.demotestes.integration;

import com.fiap.demotestes.model.Usuario;
import com.fiap.demotestes.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import tools.jackson.databind.ObjectMapper;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp(){
        //Limpa o banco de dados antes de cada teste
        usuarioRepository.deleteAll();

        //Criar dados de teste no banco real
        Usuario usuario = new Usuario(null,"João Silva","joao@email.com",25);
        UsuarioRepository.save(usuario);
    }

    @Test
    @DisplayName("Teste intregadro: Criar e buscar usuario")
    void criarEBuscarUsuario_FluxoCompleto() throws Exception{
        //1. Criar um novo usuario via API
        Usuario novoUsuario = new Usuario(null, "Maria Santos", "maria@email.com", 20);
        String responseJson = mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoUsuario)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPatch("$.id",notNullValue()))
                .andExpect((ResultMatcher) jsonPath("$.nome", is("Maria Santos")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //2. Extrair o ID do usuario Criado
        Usuario usuarioCriado = objectMapper.readValue(responseJson, Usuario.class);
        long id = usuarioCriado.getId();

        //3. Buscar o usuario pelo ID via API
        mockMvc.perform(get("/api/usuarios"+id))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id",is(id.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.nome", is("Maria Santos")))
                .andExpect((ResultMatcher) jsonPath("$.email", is("maria@emial.com")));

        //4. Verificar que o usuario está no banco de dados
        assert usuarioRepository.findById(id).isPresent();
        assert usuarioRepository.findById(id).get().getNome().equals("Maria Santos");
    }

    @Test
    @DisplayName("Teste integrado: listar todos os usuarios")
    void listarTodos_DeveRetornarUsuariosDoBancos() throws Exception{
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].nome", "João Silva"))
                .andExpect(jsonPath("$[0].email", "joao@email.com"))
    }

    @Test
    @DisplayName("Teste integrado - Atualizar Usuario")
    void atualizar_DeveAlterarDadosNoBanco(){
        //Primeiro, busca o ID do usuario criado no setUp
        Usuario usuarioExistente = usuarioRepository.findByEmail("joao@email.com").get();
        Long id = usuarioExistente.getId();

        //Segundo, preapra os dados atulizados
        Usuario usuarioAtualizado = new Usuario(null, "João Silva Atualizado", "joao@emial.com", 30);

        //Terceiro, atualiza nossa API
        mockMvc.perform(put("/api/usuario"+id)
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(usuarioAtualizado)))
                .andExpect(status().isOK())
                .andExpect((ResultMatcher) jsonPath("$.nome", is("João Silva Atualizado")))
                .andExpect((ResultMatcher) jsonPath("$.idade", is(30)));

        //Quarto, verifica no banco de dados
        Usuario usuarioNoBanco = usuarioRepository.findById(id).get();
        assert usuarioNoBanco.getNome().equals("João Silva Atulizado");
        assert usuarioNoBanco.getIdade() == 30;
    }

    @Test
    @DisplayName("Teste integrado: Deletar usuario")
    void deletar_DeveRemoverDoBanco() throws Exception{
        //Primeiro, busca o ID do usuairo criado no setUp
        Usuario usuarioExistente = usuarioRepository.findByEmail("joao@email.com").get();
        Long id = usuarioExistente.getId();

        //Segundo, deleta via API
        mockMvc.perform(delete("/api/usuarios"+id))
                .andExpect(status().isNoContent());

        //Terceiro, verifica se foi removido do banco de dados
        assert usuarioRepository.findById(id).isEmpty();

        //Quarto, verifica se a listagem esta vazia
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(0)));
    }
}