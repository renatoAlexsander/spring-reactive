package com.example.reactive.product.repository;

import com.example.reactive.product.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

}
