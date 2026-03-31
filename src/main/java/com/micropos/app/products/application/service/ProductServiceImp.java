package com.micropos.app.products.application.service;

import com.micropos.app.products.application.dto.request.CreateProductRequest;
import com.micropos.app.products.application.dto.request.UpdateProductRequest;
import com.micropos.app.products.domain.SkuGenerator;
import com.micropos.app.products.domain.exception.ProductAlreadyExistsException;
import com.micropos.app.products.domain.exception.ProductNotFoundException;
import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(CreateProductRequest request) {

        String sku = SkuGenerator.generate(request.category());

        if(productRepository.existsBySku(sku)) throw  new ProductAlreadyExistsException(sku);
        Product product = Product.builder()
                .sku(sku)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .image(request.image())
                .category(request.category())
                .build();

        return productRepository.save(product);
    }


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product findBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException(sku));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product update(Long id, UpdateProductRequest request) {
        Product productExits = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        Product update = Product.builder()
                .id(productExits.getId())
                .sku(productExits.getSku())
                .name(request.name() != null ? request.name() : productExits.getName())
                .description(request.description() != null ? request.description() : productExits.getDescription())
                .price(request.price() != null ? request.price() : productExits.getPrice())
                .image(request.image() != null ? request.image() : productExits.getImage())
                .category(request.category() != null ? request.category() : productExits.getCategory())
                .build();
        if (request.status() != null) {
            update.setStatus(request.status());
        }

        return productRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.deactivate();
        productRepository.save(product);
    }
}
