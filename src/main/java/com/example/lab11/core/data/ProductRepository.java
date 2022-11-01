package com.example.lab11.core.data;


import com.example.lab11.core.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    ProductEntity findByProductId(String productId);

    ProductEntity findByProductIdOrTitle(String productId, String title);
}
