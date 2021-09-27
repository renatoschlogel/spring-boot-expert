package com.renatoschlogel.api.rest.dto;

import com.renatoschlogel.domain.entity.ItemPedido;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    @NotNull(message = "É obrigatório informar o cliente.")
    private Integer idCliente;

    @NotNull(message = "É obrigatório informar o valor total do pedido")
    private BigDecimal valorTotal;

    private List<ItemPedidoDTO> itens;

}
