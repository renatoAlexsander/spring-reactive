package com.example.reactive.product.service;

import com.example.reactive.product.model.Product;
import com.example.reactive.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> byId(String id) {
        return productRepository.findById(id);
    }
}
