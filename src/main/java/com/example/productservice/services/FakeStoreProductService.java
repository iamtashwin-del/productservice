package com.example.productservice.services;

import com.example.productservice.Dtos.FakeStoreProductRequestDto;
import com.example.productservice.Dtos.FakeStoreProductResponseDto;
import com.example.productservice.Dtos.ProductRequestDto;
import com.example.productservice.Dtos.ProductResponseDto;
import com.example.productservice.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductResponseDto responseDto=
                restTemplate.getForObject
                        ("https://fakestoreapi.com/products/" + id, FakeStoreProductResponseDto.class);

        if(responseDto==null){
            throw new ProductNotFoundException("Product not found");
        }
        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDto =
                restTemplate.getForObject
                        ("https://fakestoreapi.com/products", FakeStoreProductResponseDto[].class);
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductResponseDto responseDto1 : responseDto){
            products.add(responseDto1.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        FakeStoreProductRequestDto  fakeStoreProductRequestDto =
                FakeStoreProductRequestDto.from(productRequestDto);

        FakeStoreProductResponseDto responseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductRequestDto,
                FakeStoreProductResponseDto.class
        );
        return responseDto.toProduct();
    }

    @Override
    public Product partialUpdate(Long id, Product product) throws ProductNotFoundException {
        return null;
    }
}
