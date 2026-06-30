package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    @Modifying
    @Query(
            value = "DELETE FROM product WHERE id = :id",
            nativeQuery = true
    )
    Integer deleteProduct(Integer id);
}
