package com.micropos.app.products.application.dto.response;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String sku,
        String name,
        String description,
        String image,
        ProductCategory category,
        ProductStatus status,
        boolean available,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getSku(),
                p.getName(),
                p.getDescription(),
                p.getImage(),
                p.getCategory(),
                p.getStatus(),
                p.getAvailable(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }
}
