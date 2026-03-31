package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public interface ProductService {

    Product save(CreateProductRequest request);

}
