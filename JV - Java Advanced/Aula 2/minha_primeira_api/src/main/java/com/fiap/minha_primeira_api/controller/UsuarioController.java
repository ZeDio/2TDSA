package com.fiap.minha_primeira_api.controller;

/*
* Controller é responsavel por gerenciar requisições do usúario
 */

import com.fiap.minha_primeira_api.DTO.UsuarioResponseDTO;
import com.fiap.minha_primeira_api.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController // Indica que esta classe é uma controladora REST
@RequestMapping("/api/usuarios") // Define a URL base para todas as endpoints desta classe
public class UsuarioController {

    //"Banco de dados" em memoria para exemplo da aplicação
    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1); //Gerador de IDS

    public UsuarioController(){
        usuarios.add(new Usuario(
                contador.getAndIncrement(),
                "João Silva",
                "joao.silva@gmail.com",
                25
        ));
        usuarios.add(new Usuario(
                contador.getAndIncrement(),
                "José Diogo",
                "jose.diogo100407@gmail.com",
                18
        ));

    }
    // Criar um Get para listar todos os usuarios da aplicação
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> ListarTodos(){
        List<UsuarioResponseDTO> dtos = usuarios.stream()
                .map(usuario -> {//Inicio da função lambda para cada usuario
                    UsuarioResponseDTO dto = UsuarioResponseDTO.fromUsuario(usuario);
                    dto.add(
                            linkTo(
                                    methodOn(UsuarioController.class)
                                    buscaPorId(usuario.getId())
                            ).whifSelfRel()
                    );
                    dto.add(
                            linkTo(
                                    methodOn(UsuarioController.class)
                                            .listarTodos()
                            ).withfRel("Todos-Usuarios")
                    );
                    return dto;
                })
        .toList();
        return ResponseEntity.ok(dtos);
    }
    private Object methodOn(Class<UsuarioController> class1){
        //Todo Auto-generated method stub
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long Id){
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst() // Pega primeiro elemento que passou no filtro (Optional)
                .orElse(null); // Se não encontrou, retorna null

        //Verifica se o usuario foi encontrado
        // significa que não existe usuario com esse id
        if (usuario == null){
            return ResponseEntity.notFound().build(); //Status 404
        }
    }
}