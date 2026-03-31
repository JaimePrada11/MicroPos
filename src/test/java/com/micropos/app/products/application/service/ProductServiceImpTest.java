package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName( "ProductService Test")
class ProductServiceImpTest {

    @Mock private ProductRepository productRepository;

    @InjectMocks private ProductServiceImp productService;

    private CreateProductRequest testProductRequest;

    @BeforeEach
    void setUp() {

        testProductRequest = CreateProductRequest.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Product")
                .price(BigDecimal.valueOf(20.00))
                .build();

    }

    @Test
    void save() {

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Product Description")
                .price(new BigDecimal("20.00"))
                .build();

        when(productRepository.existsByName("Test Product")).thenReturn(false);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.save(testProductRequest);

        Assertions.assertEquals(savedProduct.getId(), result.getId());
        Assertions.assertEquals(new BigDecimal("20.00"), result.getPrice());

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());

        Product capturedProduct = captor.getValue();

        Assertions.assertEquals(testProductRequest.name(), capturedProduct.getName());
        Assertions.assertEquals(testProductRequest.description(), capturedProduct.getDescription());
        Assertions.assertEquals(testProductRequest.price(), capturedProduct.getPrice());


        verify(productRepository).save(any(Product.class));

    }
}