package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPersistenceAdapterTest {

    @Mock
    private ProductJpaRepository productJpaRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductPersistenceAdapter repository;

    private Product product;
    private ProductEntity entity;

    @BeforeEach
    void setUp() {

        product = Product.builder()
                .id(1L)
                .sku("SODA-ABCD1234")
                .name("Sprite")
                .description("Bebida")
                .price(BigDecimal.valueOf(2500))
                .status(ProductStatus.ACTIVE)
                .category(ProductCategory.SODAS)
                .build();

        entity = ProductEntity.builder()
                .id(1L)
                .sku("SODA-ABCD1234")
                .name("Sprite")
                .description("Bebida")
                .price(BigDecimal.valueOf(2500))
                .status(ProductStatus.ACTIVE)
                .category(ProductCategory.SODAS)
                .build();
    }

    @Nested
    class SaveTests{

        @Test
        void Save(){
            when(productMapper.toEntity(product)).thenReturn(entity);
            when(productJpaRepository.save(entity)).thenReturn(entity);
            when(productMapper.toDomain(entity)).thenReturn(product);

            Product result = repository.save(product);

            assertEquals(product.getId(), result.getId());
            verify(productMapper).toEntity(product);
            verify(productJpaRepository).save(entity);
            verify(productMapper).toDomain(entity);
        }
    }
}
