package com.micropos.app.products.domain.model;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
public class Product {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    @Setter
    private ProductStatus status;
    private ProductCategory category;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public boolean getAvailable() {
        return ProductStatus.ACTIVE.equals(this.status);
    }

    public void deactivate() {
        this.status    = ProductStatus.INACTIVE;
        this.deletedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status    = ProductStatus.ACTIVE;
        this.deletedAt = null;
    }

    public void markOutOfStock() {
        this.status = ProductStatus.OUT_OF_STOCK;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product p)) return false;
        return Objects.equals(sku, p.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }
}
