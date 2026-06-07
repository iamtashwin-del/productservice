package com.example.productservice.services;

import com.example.productservice.Dtos.ProductRequestDto;
import com.example.productservice.Dtos.ProductResponseDto;
import com.example.productservice.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts();

    public Product createProduct(ProductRequestDto productRequestDto);
}
