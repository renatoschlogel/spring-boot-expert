package com.renatoschlogel.api.rest.dto;

import com.renatoschlogel.domain.entity.ItemPedido;
import com.renatoschlogel.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer idCliente;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal valorTotal;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;

}
