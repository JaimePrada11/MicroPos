package com.micropos.app.products.application.dto.request;

import com.micropos.app.products.domain.enums.ProductCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Builder
public record CreateProductRequest (
        @NotBlank
        @Size(max = 150)
       String name,

       @Size(max = 250)
       String description,

       @NonNull
       @DecimalMin(value = "0.01")
       BigDecimal price,

        String image,

        @NonNull
        ProductCategory category
){}
