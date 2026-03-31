package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {

        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);

        return productMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(productMapper::toDomain);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return jpaRepository.findBySku(sku)
                .map(productMapper::toDomain);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(productMapper::toDomain);
    }

    @Override
    public boolean existsBySku(String sku) {
        return jpaRepository.existsBySku(sku);
    }


    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
