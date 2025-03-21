package io.github.renatoschlogel.produtosapi.controller;

import io.github.renatoschlogel.produtosapi.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    @GetMapping("/oi")
    public String oi (){
        return "oi";
    }

    @PostMapping
    public void save(@RequestBody Product product) {
        System.out.println("product:" + product);
    }

}
