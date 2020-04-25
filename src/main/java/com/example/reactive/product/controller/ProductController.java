package com.example.reactive.product.controller;

import com.example.reactive.product.model.Product;
import com.example.reactive.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("{id}")
    public Mono<Product> byId(@PathVariable String id) {
        return productService.byId(id);
    }
}
