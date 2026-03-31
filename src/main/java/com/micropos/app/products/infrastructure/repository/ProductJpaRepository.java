package com.micropos.app.products.infrastructure.repository;

import com.micropos.app.products.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {


    boolean existsByName(String name);
    Optional<ProductEntity> findByName(String name);
}

