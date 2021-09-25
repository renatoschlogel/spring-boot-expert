package com.renatoschlogel.api.rest.dto;

import com.renatoschlogel.domain.entity.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Integer idCliente;

    private BigDecimal valorTotal;

    private List<ItemPedidoDTO> itens;

}
