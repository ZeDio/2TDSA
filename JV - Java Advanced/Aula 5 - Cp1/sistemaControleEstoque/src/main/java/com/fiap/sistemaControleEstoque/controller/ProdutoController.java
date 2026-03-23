package com.fiap.sistemaControleEstoque.controller;

import com.fiap.sistemaControleEstoque.DTO.ProdutoRequestDTO;
import com.fiap.sistemaControleEstoque.DTO.ProdutoResponseDTO;
import com.fiap.sistemaControleEstoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    
    @Autowired
    public ProdutoController(ProdutoService service) {
        this.produtoService = service;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<ProdutoResponseDTO> produto = produtoService.buscarPorId(id);
        
        /*
        tem este modo de ser feito, mas preferi usar orElse() para testar. E deu certo kkk.
        if(livroOpt.isPresent()) {
			return ResponseEntity.ok(livroOpt.get());
		}
		return ResponseEntity.notFound().build();
        */
        return produto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @Valid @RequestBody ProdutoRequestDTO requestDTO
    ) {
        
        ProdutoResponseDTO produtoCriado = produtoService.criar(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO requestDTO
    ) {
        Optional<ProdutoResponseDTO> produtoAtualizado = produtoService.atualizar(id, requestDTO);

        return produtoAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = produtoService.deletar(id);
        
        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/busca")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(
            @RequestParam(required = false) String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return ResponseEntity.ok(produtoService.listarTodos());
        }
        List<ProdutoResponseDTO> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }
}

