package com.fiap.sistemaControleEstoque.DTO;

import jakarta.validation.constraints.*;

public record ProdutoRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 200)
        String nome,

        @NotNull
        @Positive(message = "Preço deve ser positivo")
        Double preco,

        @NotNull
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidade
) { }
