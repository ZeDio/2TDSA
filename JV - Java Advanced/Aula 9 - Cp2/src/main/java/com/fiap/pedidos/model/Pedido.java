package com.fiap.pedidos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private String cliente;

    private BigDecimal valorTotal;

    private BigDecimal frete;

    private BigDecimal desconto;

    private String status;

    @ElementCollection
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime data, String cliente,
                  BigDecimal valorTotal, BigDecimal frete,
                  BigDecimal desconto, String status,
                  List<ItemPedido> itens) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.frete = frete;
        this.desconto = desconto;
        this.status = status;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public String getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}