package com.renatoschlogel.api.controller;

import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        System.out.println("oooooiiii");
        return pedidoService.salvar(pedidoDTO).getId();
    }

}
