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
                .sku(e.getSku())
                .name(e.getName())
                .description(e.getDescription())
                .image(e.getImage())
                .price(e.getPrice())
                .status(e.getStatus())
                .category(e.getCategory())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .deletedAt(e.getDeletedAt())
                .build();
    }

    public ProductEntity toEntity(Product p) {
        return ProductEntity.builder()
                .id(p.getId())
                .sku(p.getSku())
                .name(p.getName())
                .description(p.getDescription())
                .image(p.getImage())
                .price(p.getPrice())
                .status(p.getStatus())
                .category(p.getCategory())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .deletedAt(p.getDeletedAt())
                .build();
    }
}
