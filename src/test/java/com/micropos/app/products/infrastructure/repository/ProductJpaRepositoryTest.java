package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.*;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository repository;

    private ProductEntity product;

    @BeforeEach
    void setup() {
        product = ProductEntity.builder()
                .sku("BEER-TEST001" )
                .name("Cerveza 001" )
                .description("Bebida carbonatada")
                .price(new BigDecimal("3500.00"))
                .status(ProductStatus.ACTIVE)
                .category(ProductCategory.BEERS)
                .build();

        // repository.deleteAll();

    }

    @Test
    void savePersistProduct() {

        ProductEntity savedProduct = repository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Cerveza 001", savedProduct.getName());
        assertNotNull(savedProduct.getCreatedAt());
        assertNotNull(savedProduct.getUpdatedAt());
    }

    @Test
    void shouldFindProductById() {
        ProductEntity savedProduct = repository.save(product);
        Optional<ProductEntity> foundProduct = repository.findById(product.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
        assertEquals(product.getId(), foundProduct.get().getId());
        assertEquals(product.getSku(), foundProduct.get().getSku());
    }

    @Test
    void shouldReturnEmptyWhenProductByIdNotExists() {
        Optional<ProductEntity> foundProduct = repository.findById(999L);
        assertFalse(foundProduct.isPresent());

    }

    @Test
    void shouldFindAllProducts() {
        repository.save(product);
        List<ProductEntity> products = repository.findAll();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void shouldUpdateProduct() {
        ProductEntity savedProduct = repository.save(product);
        savedProduct.setName("Cola & Pola");
        savedProduct.setStatus(ProductStatus.OUT_OF_STOCK);

        repository.save(savedProduct);

        Optional<ProductEntity> updatedPrd = repository.findById(savedProduct.getId());

        assertTrue(updatedPrd.isPresent());
        assertEquals(savedProduct, updatedPrd.get());



    }
}