package com.fiap.sistemaControleEstoque.DTO;

import com.fiap.sistemaControleEstoque.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        Double preco,
        Integer quantidade
) {
    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}
