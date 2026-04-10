package com.fiap.demotestes.service;

import com.fiap.demotestes.model.Usuario;
import com.fiap.demotestes.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock //Cria um mock (simulação) do Repository
    private UsuarioRepository usuarioRepository;

    @InjectMocks //Injetar os mocks na classe a ser testada
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach //Executa antes de cada teste, prepara os dados comuns para todos os testes
    void setUp() {
        usuario = new Usuario(1L, "João da Silva", "joao@email.com", 25);
    }

    @Test
    @DisplayName("Deve listar todos os usuários com sucesso")
    void listarTodos_DeveRetornarListaDeUsuarios(){
        //Preparação (Arrange)
        List<Usuario> usuarios = List.of(usuario, new Usuario(2L,"Maria","maria@email.com",30));
        when (usuarioRepository.findAll()).thenReturn(usuarios);

        //Act(execução)
        List<Usuario> resultado = usuarioService.listarTodos();

        //Assert (verificação)
        assertThat(resultado).hasSize(2);
        assertThat(resultado).containsExactlyElementsOf(usuarios);
        verify(usuarioRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Devo criar um usuário com sucesso quando o email não existir")
    void criar_QuandoEmailNaoExiste_DevoSalvarUsuario(){
        Usuario novoUsuario = new Usuario(null,"Ana Souza","ana@email.com",22);
        when(usuarioRepository.existsByEmail("ana@email.com")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn((new Usuario(3L,"Ana Souza","ana@email.com",22)));

        //Ação
        Usuario resultado = usuarioService.criar(novoUsuario);

        //asserts
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(3L);
        assertThat(resultado.getEmail()).isEqualTo("ana@email.com");
        verify(usuarioRepository, times(1)).existsByEmail("ana@email.com");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Devo lançar uma exceção ao criar usuário com email já existente")
    void criar_QuandoEmailJaExiste_DeveLancarExcecao(){
        Usuario novoUsuario = new Usuario(null,"João Silva","joao@email.com",25);
        when(usuarioRepository.existsByEmail("joao@email.com")).thenReturn(true);

        //Ação e Asserções
        assertThatThrownBy(() -> usuarioService.criar(novoUsuario))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email já cadastrado: joao@email.com");
    }

    @Test
    @DisplayName("Devo atualizar o usuário com sucesso")
    void atualizar_QuandoUsuarioExiste_DevoAtualizar(){
        Usuario usuarioAtualizado = new Usuario(null,"João Silva Atualizado","joao@email.com",26);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioAtualizado);
        //Ação
        Usuario resultado = usuarioService.atualizar(1L,usuarioAtualizado);

        //Asserts
        assertThat(resultado.getNome()).isEqualTo("João Silva Atualizado");
        assertThat(resultado.getIdade()).isEqualTo(26);
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deletar_QuandoUsuarioExiste_DevoRemover(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioService.deletar(1L);

        verify(usuarioRepository,times(1)).findById(1L);
        verify(usuarioRepository,times(1)).delete(usuario);
    }
}
