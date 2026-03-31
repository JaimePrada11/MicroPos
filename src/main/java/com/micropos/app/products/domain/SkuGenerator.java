package com.micropos.app.products.domain;

import com.micropos.app.products.domain.enums.ProductCategory;

import java.util.UUID;

public final class SkuGenerator {

    private SkuGenerator() {}

    public static String generate(ProductCategory category) {
        String prefix = switch (category) {
            case BEERS    -> "BEER";
            case LEGUMES  -> "LEGM";
            case SNACKS   -> "SNCK";
            case SODAS    -> "SODA";

        };
        String uniquePart = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();

        return prefix + "-" + uniquePart;
    }
}
