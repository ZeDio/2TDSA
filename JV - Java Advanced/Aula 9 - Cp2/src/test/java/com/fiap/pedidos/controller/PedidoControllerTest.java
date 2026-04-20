package com.fiap.pedidos.controller;

import com.fiap.pedidos.DTO.ItemPedidoDTO;
import com.fiap.pedidos.DTO.PedidoRequestDTO;
import com.fiap.pedidos.model.ItemPedido;
import com.fiap.pedidos.model.Pedido;
import com.fiap.pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PedidoService pedidoService;

    @Test
    @DisplayName("POST /api/pedidos - Deve criar pedido com sucesso")
    void criarPedido_DeveRetornar201() throws Exception {

        ItemPedidoDTO itemDTO = new ItemPedidoDTO(
                "P001",
                "Produto Teste",
                2,
                BigDecimal.valueOf(100)
        );

        PedidoRequestDTO request = new PedidoRequestDTO(
                "Cliente Teste",
                List.of(itemDTO)
        );

        ItemPedido item = new ItemPedido(
                "P001",
                "Produto Teste",
                2,
                BigDecimal.valueOf(100)
        );

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente("Cliente Teste");
        pedido.setData(LocalDateTime.now());
        pedido.setItens(List.of(item));
        pedido.setValorTotal(BigDecimal.valueOf(220));
        pedido.setFrete(BigDecimal.valueOf(20));
        pedido.setDesconto(BigDecimal.ZERO);
        pedido.setStatus("AGUARDANDO_PAGAMENTO");

        when(pedidoService.criarPedido(request)).thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cliente").value("Cliente Teste"))
                .andExpect(jsonPath("$.status").value("AGUARDANDO_PAGAMENTO"));
    }
}