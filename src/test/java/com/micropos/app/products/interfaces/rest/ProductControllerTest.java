package com.micropos.app.products.interfaces.rest;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.application.service.ProductService;
import com.micropos.app.products.domain.enums.ProductCategory;
import com.micropos.app.products.domain.enums.ProductStatus;
import com.micropos.app.products.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @MockitoBean private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .sku("BEER-ABCD1234")
                .name("Club Colombia")
                .description("Cerveza rubia premium")
                .price(BigDecimal.valueOf(4500))
                .status(ProductStatus.ACTIVE)
                .category(ProductCategory.BEERS)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Nested
    class CreateTests{

        @Test
        @WithMockUser
        void createValidProduct() throws Exception {
            CreateProductRequest request = CreateProductRequest.builder()
                    .name("Club Colombia")
                    .description("Cerveza rubia")
                    .price(BigDecimal.valueOf(4500))
                    .category(ProductCategory.BEERS)
                    .build();

            when(productService.create(any(CreateProductRequest.class))).thenReturn(product);

            mockMvc.perform(post("/api/v1/products")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))

                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.data.sku").value("BEER-ABCD1234"));

        }
    }
}