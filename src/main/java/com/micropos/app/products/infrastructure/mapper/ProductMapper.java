package com.micropos.app.products.infrastructure.mapper;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity e) {
        return Product.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .price(e.getPrice())
                .build();
    }

    public ProductEntity toEntity(Product p) {
        return ProductEntity.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())

                .build();
    }
}
