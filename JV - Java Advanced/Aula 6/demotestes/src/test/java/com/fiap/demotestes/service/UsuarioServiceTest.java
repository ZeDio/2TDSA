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

import static org.assertj.core.api.Assertions.assertThat;
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
}
