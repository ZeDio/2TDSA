package com.fiap.pedidos.DTO;

import java.util.List;

public record PedidoRequestDTO(
        String cliente,
        List<ItemPedidoDTO> itens
) {
}