package com.renatoschlogel.service;

import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
