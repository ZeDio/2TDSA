package com.fiap.pedidos.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EstoqueClient {

    private Map<String, Integer> estoque = new HashMap<>();

    public EstoqueClient() {
        estoque.put("P001", 10);
        estoque.put("P002", 0);
        estoque.put("P003", 3);
    }

    public boolean temEstoque(String produtoId, int quantidade) {
        return estoque.getOrDefault(produtoId, 0) >= quantidade;
    }
}