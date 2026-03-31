package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.domain.model.Product;
import com.micropos.app.products.domain.repository.ProductRepository;
import com.micropos.app.products.infrastructure.entity.ProductEntity;
import com.micropos.app.products.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
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
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public void deleteById(Long id) {

    }
}
