package com.fiap.pedidos.service;

import com.fiap.pedidos.DTO.ItemPedidoDTO;
import com.fiap.pedidos.client.EstoqueClient;
import com.fiap.pedidos.exception.EstoqueInsuficienteException;
import com.fiap.pedidos.exception.ProdutoDuplicadoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private EstoqueClient estoqueClient;

    @InjectMocks
    private PedidoService pedidoService;

    private List<ItemPedidoDTO> itens;

    @BeforeEach
    void setup() {
        itens = List.of(
                new ItemPedidoDTO("P001", "Produto A", 2, BigDecimal.valueOf(100))
        );
    }

    @Test
    @DisplayName("calcularDesconto_deveAplicar10Porcento_quandoValorSuperior500")
    void calcularDesconto_deveAplicar10Porcento_quandoValorSuperior500() {

        BigDecimal valor = BigDecimal.valueOf(600);

        BigDecimal desconto = pedidoService.calcularDesconto(valor);

        assertEquals(BigDecimal.valueOf(60), desconto);
    }

    @Test
    @DisplayName("Deve lançar exceção quando houver produto duplicado")
    void deveLancarExcecao_QuandoProdutoDuplicado() {

        List<ItemPedidoDTO> itensDuplicados = List.of(
                new ItemPedidoDTO("P001", "Produto A", 1, BigDecimal.valueOf(100)),
                new ItemPedidoDTO("P001", "Produto A", 2, BigDecimal.valueOf(100))
        );

        assertThrows(ProdutoDuplicadoException.class,
                () -> pedidoService.criarPedido(
                        new com.fiap.pedidos.DTO.PedidoRequestDTO("Cliente", itensDuplicados)
                ));
    }

    @Test
    @DisplayName("Deve lançar exceção quando não houver estoque")
    void deveLancarExcecao_QuandoEstoqueInsuficiente() {

        when(estoqueClient.temEstoque(anyString(), anyInt())).thenReturn(false);

        assertThrows(EstoqueInsuficienteException.class,
                () -> pedidoService.criarPedido(
                        new com.fiap.pedidos.DTO.PedidoRequestDTO("Cliente", itens)
                ));
    }

    @Test
    @DisplayName("Deve retornar frete grátis quando valor > 300")
    void calcularFrete_deveSerGratis_quandoValorMaiorQue300() {

        BigDecimal valor = BigDecimal.valueOf(400);

        BigDecimal frete = pedidoService.calcularFrete(valor);

        assertEquals(BigDecimal.ZERO, frete);
    }

    @Test
    @DisplayName("Deve retornar frete 20 quando valor <= 300")
    void calcularFrete_deveSer20_quandoValorMenorOuIgual300() {

        BigDecimal valor = BigDecimal.valueOf(200);

        BigDecimal frete = pedidoService.calcularFrete(valor);

        assertEquals(BigDecimal.valueOf(20), frete);
    }
}