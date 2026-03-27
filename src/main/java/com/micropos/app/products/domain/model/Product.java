package com.micropos.app.products.domain.model;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private ProductStatus status;
    private ProductCategory category;
}
