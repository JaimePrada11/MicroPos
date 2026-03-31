package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository repository;

    private ProductEntity product;

    @BeforeEach
    void setup() {
        product = ProductEntity.builder()
                .name("Pepsi")
                .description("Bebida")
                .price(new BigDecimal("20"))
                .status(ProductStatus.ACTIVE)
                .category(ProductCategory.BEERS)
                .build();

    }

    @Test
    void shouldSavedProduct() {

        ProductEntity savedProduct = repository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Pepsi", savedProduct.getName());
    }

    @Test
    void shouldFindProductById() {
        ProductEntity savedProduct = repository.save(product);
        Optional<ProductEntity> foundProduct = repository.findById(product.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
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