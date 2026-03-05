package com.fiap.minha_primeira_api.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.minha_primeira_api.DTO.UsuarioResponseDTO;
import com.fiap.minha_primeira_api.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;  // LINHA 15: Para criar links
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn; // LINHA 16: Para referenciar métodos

/**
* Controller responsável por gerenciar requisições de usuários
*/
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {
 
 // "Banco de dados" em memória
 private final List<Usuario> usuarios = new ArrayList<>();
 private final AtomicLong contador = new AtomicLong(1);
 
 /**
  * Construtor - Inicializa com dados de exemplo
  */
 public UsuarioController() {
     
     usuarios.add(new Usuario(
         contador.getAndIncrement(),  
         "João Silva",                
         "joao@email.com",             
         25                            
     ));
     
     
     usuarios.add(new Usuario(
         contador.getAndIncrement(),  
         "Maria Santos",              
         "maria@email.com",           
         30                            
     ));
 }
 
 /**
  * GET - Listar todos usuários
  * @return ResponseEntity com lista de usuários
  */
 @GetMapping
 public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
     
     List<UsuarioResponseDTO> dtos = usuarios.stream()
         .map(usuario -> {
             
             UsuarioResponseDTO dto = UsuarioResponseDTO.fromUsuario(usuario);
             
             
             try {
                
                 dto.add(
                     linkTo(
                         methodOn(UsuarioController.class)
                             .buscarPorId(usuario.getId())  
                     ).withSelfRel()
                 );
                 
                 
                 dto.add(
                     linkTo(
                         methodOn(UsuarioController.class)
                             .listarTodos()  
                     ).withRel("todos-usuarios")
                 );
             } catch (Exception e) {
                 
                 System.err.println("Erro ao adicionar links HATEOAS: " + e.getMessage());
             }
             
             return dto;
         })
         .toList();
     
     return ResponseEntity.ok(dtos);
 }
 
 /**
  * GET - Buscar usuário por ID
  * @param id ID do usuário
  * @return ResponseEntity com usuário encontrado
  */
 @GetMapping("/{id}")
 public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
     
     
     Usuario usuario = usuarios.stream()
         .filter(u -> u.getId().equals(id))
         .findFirst()
         .orElse(null);
     
     if (usuario == null) {
         return ResponseEntity.notFound().build();  // Status 404
     }
     
     UsuarioResponseDTO dto = UsuarioResponseDTO.fromUsuario(usuario);
     
    
     try {
         
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .buscarPorId(id)  
             ).withSelfRel()
         );
         
         
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .listarTodos()
             ).withRel("todos-usuarios")
         );
     } catch (Exception e) {
         System.err.println("Erro ao adicionar links HATEOAS: " + e.getMessage());
     }
     
     return ResponseEntity.ok(dto);
 }
 
 /**
  * POST - Criar novo usuário
  */
 @PostMapping
 public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody Usuario usuario) {
     
     if (usuario.getNome() == null || usuario.getEmail() == null) {
         return ResponseEntity.badRequest().build();  // Status 400
     }
     
     usuario.setId(contador.getAndIncrement());
     usuarios.add(usuario);
     
     UsuarioResponseDTO dto = UsuarioResponseDTO.fromUsuario(usuario);
     
     
     try {
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .buscarPorId(usuario.getId())
             ).withSelfRel()
         );
         
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .listarTodos()
             ).withRel("todos-usuarios")
         );
     } catch (Exception e) {
         System.err.println("Erro ao adicionar links HATEOAS: " + e.getMessage());
     }
     
     return ResponseEntity
         .created(
             linkTo(
                 methodOn(UsuarioController.class)
                     .buscarPorId(usuario.getId())
             ).toUri()
         )
         .body(dto);
 }
 
 /**
  * PUT - Atualizar usuário
  */
 @PutMapping("/{id}")
 public ResponseEntity<UsuarioResponseDTO> atualizar(
         @PathVariable Long id,
         @RequestBody Usuario usuario
 ) {
     int index = -1;
     for (int i = 0; i < usuarios.size(); i++) {
         if (usuarios.get(i).getId().equals(id)) {
             index = i;
             break;
         }
     }
     
     if (index == -1) {
         return ResponseEntity.notFound().build();
     }
     
     usuario.setId(id);
     usuarios.set(index, usuario);
     
     UsuarioResponseDTO dto = UsuarioResponseDTO.fromUsuario(usuario);
     
     
     try {
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .buscarPorId(id)
             ).withSelfRel()
         );
         
         dto.add(
             linkTo(
                 methodOn(UsuarioController.class)
                     .listarTodos()
             ).withRel("todos-usuarios")
         );
     } catch (Exception e) {
         System.err.println("Erro ao adicionar links HATEOAS: " + e.getMessage());
     }
     
     return ResponseEntity.ok(dto);
 }
 
 /**
  * DELETE - Remover usuário
  */
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deletar(@PathVariable Long id) {
     
     boolean removido = usuarios.removeIf(
         usuario -> usuario.getId().equals(id)
     );
     
     if (removido) {
         return ResponseEntity.noContent().build();  // Status 204
     } else {
         return ResponseEntity.notFound().build();   // Status 404
     }
 }
}