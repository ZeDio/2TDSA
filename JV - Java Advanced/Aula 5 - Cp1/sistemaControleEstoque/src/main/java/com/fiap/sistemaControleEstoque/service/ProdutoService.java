package com.fiap.sistemaControleEstoque.service;

import com.fiap.sistemaControleEstoque.DTO.ProdutoRequestDTO;
import com.fiap.sistemaControleEstoque.DTO.ProdutoResponseDTO;
import com.fiap.sistemaControleEstoque.model.Produto;
import com.fiap.sistemaControleEstoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ProdutoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoResponseDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(ProdutoResponseDTO::fromEntity);
    }

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = new Produto(null, dto.nome(), dto.preco(), dto.quantidade());
        return ProdutoResponseDTO.fromEntity(repository.save(produto));
    }

    public Optional<ProdutoResponseDTO> atualizar(Long id, ProdutoRequestDTO dto) {
        return repository.findById(id).map(produto -> {
            produto.setNome(dto.nome());
            produto.setPreco(dto.preco());
            produto.setQuantidade(dto.quantidade());
            return ProdutoResponseDTO.fromEntity(repository.save(produto));
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ProdutoResponseDTO::fromEntity)
                .toList();
    }
}
