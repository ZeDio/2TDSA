package com.fiap.pedidos.DTO;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        String produtoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal precoUnitario
) {
}