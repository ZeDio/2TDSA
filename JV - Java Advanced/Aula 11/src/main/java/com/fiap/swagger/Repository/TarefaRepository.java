package com.fiap.swagger.Repository;

import com.fiap.swagger.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefas,Long> {
    List<Tarefas> findByConcluida(Boolean concluida);
    List<Tarefas> findByTituloContainig(String palavra);
}