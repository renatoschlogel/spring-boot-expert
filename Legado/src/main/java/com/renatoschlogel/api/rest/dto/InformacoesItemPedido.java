package com.renatoschlogel.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedido {

    private String descricaoProduto;
    private BigDecimal precoUnitatio;
    private Integer quantidade;

}
