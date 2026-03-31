package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public Product save(CreateProductRequest request) {
        if(productRepository.existsByName(request.name())){
            throw new RuntimeException("Product name already exists");
        }

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();

        return productRepository.save(product);
    }
}
