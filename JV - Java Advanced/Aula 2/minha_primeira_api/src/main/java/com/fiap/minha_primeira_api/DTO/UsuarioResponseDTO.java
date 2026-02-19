package com.fiap.minha_primeira_api.DTO;

/*
* DTO (Data Transfer Object) -- Objeto para transferencia de dados
* Usado para enviar uma resposta ao Cliente sem expor a entidade completa
* Estende RepresentationModel para incluir links HATEAS
 */

import jdk.jfr.Relational;

@Relational(collectionRelation="usuarios",itemRelation="usuarios")
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO>{

    private Long id;
    private String nome;
    private String email;
    private int idade;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nome, String email, int idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Metedo helper para criar DTO a partir do modelo (model)
    public static UsuarioResponseDTO fromUsuario(com.fiap.minha_primeira_api.model.Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getIdade()
        );
    }

}
