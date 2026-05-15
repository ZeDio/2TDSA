package com.fiap.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "tarefas")
@Schema(
        name = "Tarefa",
        description = "Representa uma tarefa no sistema de To-Do List"
)

public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Id unico da tarefa",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long Id;

    @NotBlank(message = "O titulo é obrigatorio")
    @Size(min = 3, max = 100, message = "O titulo deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    @Schema(
            description = "Titulo da Tarefa",
            example = "Estudar Spring Boot",
            required = true,
            minLength = 3,
            maxLength = 100
    )
    private String titulo;

    @Size(max = 500, message = "A descrição ter no maximo 500 caracteres")
    @Schema(
            description = "Descrição detalhada da tarefa",
            example = "Estudar os conceitos de spring boot, JPA e Swagger",
            maxLength = 500,
            required = false
    )
    private String descricao;

    @Schema(
            description = "Status da tarefa",
            example = "false",
            defaultValue = "false"
    )
    private boolean concluida = false;

    @Column(name = "data_criacao", updatable = false)
    @Schema(
            description = "Data e hora da criação da tarefa",
            example = "2026-01-10T10:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")@Schema(
            description = "Data e hora da atualização da tarefa",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime dataAtualizacao;

    public Tarefas() {
    }

    public Tarefas(Long id, String titulo, String descricao, boolean concluida, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        Id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @PrePersist
    protected void onCreate(){
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        if(this.concluida == true){
            this.concluida = false;
        }
    }

    @PreUpdate
    protected void onUpdate(){
        this.dataAtualizacao = LocalDateTime.now();
    }
}