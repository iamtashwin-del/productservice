package com.example.productservice.services;

import com.example.productservice.Dtos.FakeStoreProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductResponseDto responseDto=
                restTemplate.getForObject
                        ("https://fakestoreapi.com/products/" + id, FakeStoreProductResponseDto.class);
        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
