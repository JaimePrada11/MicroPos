package com.micropos.app.products.domain.model;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public void deactive(){
        this.status = ProductStatus.INACTIVE;
        this.deletedAt = LocalDateTime.now();
    }

    public void active(){
        this.status = ProductStatus.ACTIVE;
        this.deletedAt = null;
    }
}
