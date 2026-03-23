package com.fiap.sistemaControleEstoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.sistemaControleEstoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNomeContainingIgnoreCase(String nome); 
    
}