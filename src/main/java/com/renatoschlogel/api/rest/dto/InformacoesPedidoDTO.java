package com.renatoschlogel.api.rest.dto;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String data;
    private String cpf;
    private String nomeCliente;
    private BigDecimal valorTotal;
    private String status;
    private List<InformacoesItemPedido> itens;

}
