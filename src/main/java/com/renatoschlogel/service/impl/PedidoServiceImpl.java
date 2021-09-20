package com.renatoschlogel.service.impl;

import com.renatoschlogel.domain.repository.PedidoRepository;
import com.renatoschlogel.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

}
