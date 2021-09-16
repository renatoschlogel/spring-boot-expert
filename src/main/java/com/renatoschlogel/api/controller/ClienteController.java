package com.renatoschlogel.api.controller;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById (@PathVariable("id") Integer idCliente) {
        Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
        if (optCliente.isPresent()) {
            return ResponseEntity.ok(optCliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete (@PathVariable("id") Integer idCliente) {
        Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
        if (optCliente.isPresent()) {
            clienteRepository.delete(optCliente.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@RequestBody Cliente cliente,
                                 @PathVariable("id") Integer idCliente ) {

        return clienteRepository
                .findById(idCliente)
                .map( clienteManager -> {
                    cliente.setId(clienteManager.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity find(Cliente filtro) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withIgnoreCase()
                                               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);

        return ResponseEntity.ok(clienteRepository.findAll(example));
    }
}
