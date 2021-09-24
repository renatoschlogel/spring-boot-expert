package com.renatoschlogel.service;

import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.domain.entity.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

}
