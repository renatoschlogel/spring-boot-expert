package com.renatoschlogel.api.controller;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.repository.ClienteRepository;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Api("API Clientes")
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ApiOperation("obter detalhes do cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontradao.  "),
            @ApiResponse(code = 404, message = "Cliente não encontrado.")
    })
    public Cliente getClienteById (@PathVariable("id") @ApiParam("Id do clientes") Integer idCliente) {
        return clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return clienteRepository.findAll(example);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Integer idCliente) {
        Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
        optCliente.ifPresent( cliente -> clienteRepository.delete(cliente));
        optCliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente update(@RequestBody @Valid Cliente cliente,
                          @PathVariable("id") Integer idCliente ) {

        return clienteRepository
                .findById(idCliente)
                .map(clienteManager -> {
                    cliente.setId(clienteManager.getId());
                    clienteRepository.save(cliente);
                    return cliente;
                 }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }
}