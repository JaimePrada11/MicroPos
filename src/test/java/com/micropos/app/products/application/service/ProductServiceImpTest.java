package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.domain.exception.ProductAlreadyExistsException;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName( "ProductService Test")
class ProductServiceImpTest {

    @Mock private ProductRepository productRepository;

    @InjectMocks private ProductServiceImp productService;


    private CreateProductRequest testProductRequest;

    @BeforeEach
    void setUp() {

        testProductRequest = CreateProductRequest.builder()
                .name("Poker")
                .description("Cerveza")
                .price(BigDecimal.valueOf(20.00))
                .category(ProductCategory.BEERS)
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

        when(productRepository.existsBySku("Test Product")).thenReturn(false);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.create(testProductRequest);

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

    @Nested
    class CreateTests {

        @Test
        void creatWhenSkuIsNew(){
            when(productRepository.existsBySku(anyString()))
                    .thenReturn(false);

            when(productRepository.save(any(Product.class))).thenAnswer(inv -> {
                Product p = inv.getArgument(0);
                return  Product.builder()
                        .id(1L)
                        .sku(p.getSku())
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .category(p.getCategory())
                        .status(ProductStatus.ACTIVE)
                        .build();
            });

            Product result = productService.create(testProductRequest);

            assertThat(result.getId()).isEqualTo(1L);
            Assertions.assertEquals(result.getName(), testProductRequest.name());
            Assertions.assertEquals(result.getPrice(), testProductRequest.price());
            Assertions.assertEquals(result.getCategory(), testProductRequest.category());
            Assertions.assertEquals(result.getStatus(), ProductStatus.ACTIVE);

            assertThat(result.getSku()).startsWith("BEER-");

            ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

            verify(productRepository).save(captor.capture());
            Product capturedProduct = captor.getValue();

            assertThat(capturedProduct.getSku()).startsWith("BEER-");
        }

        @Test
        void creatWhenSkuIsExist(){
            when(productRepository.existsBySku(anyString())).thenReturn(true);

            assertThatThrownBy(()->  productService.create(testProductRequest))
                    .isInstanceOf(ProductAlreadyExistsException.class);

            verify(productRepository, never()).save(any(Product.class));

        }
    }


}