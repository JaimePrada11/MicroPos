package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.application.dto.request.UpdateProductRequest;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ProductService {

    Product create(CreateProductRequest request);

    Product findById(Long id);

    Product findBySku(String sku);

    Page<Product> findAll(Pageable pageable);

    Product update(Long id, UpdateProductRequest request);

    void delete(Long id);

}
