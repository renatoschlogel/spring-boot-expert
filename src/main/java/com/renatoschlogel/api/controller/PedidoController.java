package com.renatoschlogel.api.controller;

import com.renatoschlogel.api.rest.dto.InformacoesItemPedido;
import com.renatoschlogel.api.rest.dto.InformacoesPedidoDTO;
import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.domain.entity.ItemPedido;
import com.renatoschlogel.domain.entity.Pedido;
import com.renatoschlogel.exception.RegraNegocioException;
import com.renatoschlogel.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.salvar(pedidoDTO).getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer idPedido){
        return pedidoService.obterPedidoCompleto(idPedido)
                .map(pedido -> converterParaDTO(pedido))
                .orElseThrow(() -> new RegraNegocioException("Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converterParaDTO(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .data(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .valorTotal(pedido.getValorTotal())
                .itens(converterItensParaDto(pedido.getItens()))
                .build();
    }

    private Set<InformacoesItemPedido> converterItensParaDto(Set<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)){
            return new HashSet<>();
        }

        return itens.stream()
                    .map(item -> InformacoesItemPedido
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .quantidade(item.getQuantidade())
                            .precoUnitatio(item.getProduto().getPrecoUnitatio())
                            .build()
                    ).collect(Collectors.toSet());
    }
}
