package com.micropos.app.products.domain.repository;

import com.micropos.app.products.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    boolean existsByName(String name);
    void deleteById(Long id);
}
