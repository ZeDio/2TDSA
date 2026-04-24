package com.projeto.cursos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instrutores")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String especialidade;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")//FK usuario_id
    private Usuario usuario;

    @OneToMany(mappedBy = "instrutor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference //Evita loop na serialização (lado mãe)
    private List<Curso> cursos = new ArrayList<>();

    //Metodo utilitario para adicionar curso mantendo consistencia bidirecional
    public void adicionarCurso(Curso curso){
        cursos.add(curso); //Adicionar a lista
        curso.setInstrutor(this); //Sincroniza o lado ManyToOne
    }

    //Metodo utilitario para remover curso
    public void removerCurso(Curso curso){
        cursos.remove(curso);
        curso.setInstrutor(null);
    }

    public Instrutor() {
    }

    public Instrutor(String nome, String especialidade, Usuario usuario) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.usuario = usuario;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
