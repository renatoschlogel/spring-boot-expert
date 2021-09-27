package com.renatoschlogel.api.controller;

import com.renatoschlogel.api.rest.dto.AtualizacaoStatusPedidoDTO;
import com.renatoschlogel.api.rest.dto.InformacoesItemPedido;
import com.renatoschlogel.api.rest.dto.InformacoesPedidoDTO;
import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.domain.entity.ItemPedido;
import com.renatoschlogel.domain.entity.Pedido;
import com.renatoschlogel.domain.enuns.StatusPedido;
import com.renatoschlogel.exception.RegistroNaoEncontrado;
import com.renatoschlogel.exception.RegraNegocioException;
import com.renatoschlogel.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.salvar(pedidoDTO).getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer idPedido){
        return pedidoService.obterPedidoCompleto(idPedido)
                .map(this::converterParaDTO)
                .orElseThrow(() -> new RegistroNaoEncontrado("Pedido não encontrado!"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updadeStatus(@PathVariable("id") Integer idPedido,
                             @RequestBody AtualizacaoStatusPedidoDTO dto) {
        pedidoService.atualizaStatus(idPedido, StatusPedido.valueOf(dto.getStatus()));

    }

    private InformacoesPedidoDTO converterParaDTO(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .data(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .valorTotal(pedido.getValorTotal())
                .itens(converterItensParaDto(pedido.getItens()))
                .status(pedido.getStatus().name())
                .build();
    }

    private List<InformacoesItemPedido> converterItensParaDto(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream()
                    .map(item -> InformacoesItemPedido
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .quantidade(item.getQuantidade())
                            .precoUnitatio(item.getProduto().getPrecoUnitatio())
                            .build()
                    ).collect(Collectors.toList());
    }
}
