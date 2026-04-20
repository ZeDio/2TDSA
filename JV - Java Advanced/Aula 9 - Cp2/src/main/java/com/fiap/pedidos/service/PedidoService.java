package com.fiap.pedidos.service;

import com.fiap.pedidos.DTO.ItemPedidoDTO;
import com.fiap.pedidos.DTO.PedidoRequestDTO;
import com.fiap.pedidos.client.EstoqueClient;
import com.fiap.pedidos.exception.EstoqueInsuficienteException;
import com.fiap.pedidos.exception.ProdutoDuplicadoException;
import com.fiap.pedidos.model.ItemPedido;
import com.fiap.pedidos.model.Pedido;
import com.fiap.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstoqueClient estoqueClient;

    public Pedido criarPedido(PedidoRequestDTO request) {

        validarProdutosDuplicados(request.itens());

        validarEstoque(request.itens());

        List<ItemPedido> itens = request.itens().stream()
                .map(dto -> new ItemPedido(
                        dto.produtoId(),
                        dto.nomeProduto(),
                        dto.quantidade(),
                        dto.precoUnitario()
                ))
                .collect(Collectors.toList());

        BigDecimal valorItens = calcularValorItens(itens);

        BigDecimal desconto = calcularDesconto(valorItens);

        BigDecimal frete = calcularFrete(valorItens);

        BigDecimal valorTotal = valorItens.subtract(desconto).add(frete);

        Pedido pedido = new Pedido();
        pedido.setCliente(request.cliente());
        pedido.setData(LocalDateTime.now());
        pedido.setItens(itens);
        pedido.setDesconto(desconto);
        pedido.setFrete(frete);
        pedido.setValorTotal(valorTotal);
        pedido.setStatus("AGUARDANDO_PAGAMENTO");

        return pedidoRepository.save(pedido);
    }

    private void validarProdutosDuplicados(List<ItemPedidoDTO> itens) {
        Set<String> ids = new HashSet<>();

        for (ItemPedidoDTO item : itens) {
            if (!ids.add(item.produtoId())) {
                throw new ProdutoDuplicadoException(
                        "Produto duplicado no pedido: " + item.produtoId()
                );
            }
        }
    }

    private void validarEstoque(List<ItemPedidoDTO> itens) {
        for (ItemPedidoDTO item : itens) {
            boolean temEstoque = estoqueClient.temEstoque(
                    item.produtoId(),
                    item.quantidade()
            );

            if (!temEstoque) {
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente para o produto: " + item.produtoId()
                );
            }
        }
    }

    private BigDecimal calcularValorItens(List<ItemPedido> itens) {
        return itens.stream()
                .map(item -> item.getPrecoUnitario()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularDesconto(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.valueOf(500)) > 0) {
            return valor.multiply(BigDecimal.valueOf(0.10));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularFrete(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.valueOf(300)) > 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(20);
    }
}