package com.micropos.app.products.domain.repository;

import com.micropos.app.products.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findBySku(String name);
    Page<Product> findAll(Pageable pageable);
    boolean existsBySku(String sku);
    void deleteById(Long id);
}
