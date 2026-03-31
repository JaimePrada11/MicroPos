package com.micropos.app.products.application.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record CreateProductRequest (
       Long id,
       String name,
       String description,
       BigDecimal price)
{
}
