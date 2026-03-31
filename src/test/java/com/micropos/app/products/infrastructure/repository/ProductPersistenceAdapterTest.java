package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPersistenceAdapterTest {

    @Mock
    private ProductJpaRepository productJpaRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductPersistenceAdapter repository;


    @Test
    void save() {

        Product product = Product.builder()
                .name("Sprite")
                .description("Bebida")
                .price(BigDecimal.valueOf(20.00))
                .build();

        ProductEntity entity = ProductEntity.builder()
                .name("Sprite")
                .description("Bebida")
                .price(BigDecimal.valueOf(20.00))
                .build();

        when(productMapper.toEntity(product)).thenReturn(entity);
        when(productJpaRepository.save(entity)).thenReturn(entity);
        when(productMapper.toDomain(entity)).thenReturn(product);

        Product result = repository.save(product);

        assertNotNull(result);
        assertEquals(product, result);
    }
}
