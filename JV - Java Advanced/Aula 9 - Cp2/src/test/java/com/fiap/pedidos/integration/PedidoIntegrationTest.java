package com.fiap.pedidos.integration;

import com.fiap.pedidos.DTO.ItemPedidoDTO;
import com.fiap.pedidos.DTO.PedidoRequestDTO;
import com.fiap.pedidos.client.EstoqueClient;
import com.fiap.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    @MockBean
    private EstoqueClient estoqueClient;

    @Test
    @DisplayName("Teste integração: criar pedido com sucesso")
    void criarPedido_ComSucesso() throws Exception {

        
        when(estoqueClient.temEstoque("P001", 2)).thenReturn(true);

        
        ItemPedidoDTO item = new ItemPedidoDTO(
                "P001",
                "Produto Teste",
                2,
                BigDecimal.valueOf(150)
        );

        PedidoRequestDTO request = new PedidoRequestDTO(
                "Cliente Teste",
                List.of(item)
        );

        
        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is("AGUARDANDO_PAGAMENTO")))
                .andExpect(jsonPath("$.frete", is(0)))
                .andExpect(jsonPath("$.valorTotal", is(270)));
    }
}