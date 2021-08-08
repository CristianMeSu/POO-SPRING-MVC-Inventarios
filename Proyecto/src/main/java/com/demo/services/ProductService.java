package com.demo.services;

import com.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void createProduct(Product product);

    Optional<Product> findById(int id);

    void removeProduct(int id);

    List<Product> findAll();
}
