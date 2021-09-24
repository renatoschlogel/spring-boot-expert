package com.renatoschlogel.api.rest.dto;

import com.renatoschlogel.domain.entity.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Integer idCliente;

    private BigDecimal valorTotal;

    private List<ItemPedidoDTO> itens;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
