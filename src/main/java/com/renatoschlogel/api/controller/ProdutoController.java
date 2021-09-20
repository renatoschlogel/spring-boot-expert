package com.renatoschlogel.api.controller;

import com.renatoschlogel.domain.entity.Produto;
import com.renatoschlogel.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> find(@RequestBody Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);
    }

    @GetMapping("/{id}")
    public Produto findById(@PathVariable("id") Integer idProduto) {
        return produtoRepository
                .findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto updade (@RequestBody Produto produto,
                           @PathVariable("id") Integer idProduto){
        return produtoRepository
                .findById(idProduto)
                .map( produtoManager -> {
                    produto.setId(idProduto);
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer idProduto) {
        Optional<Produto> optProduto = produtoRepository.findById(idProduto);
        optProduto.ifPresent( produto -> produtoRepository.delete(produto));
        optProduto.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

}
