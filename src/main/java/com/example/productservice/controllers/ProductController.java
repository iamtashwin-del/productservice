package com.example.productservice.controllers;

import com.example.productservice.Dtos.ProductRequestDto;
import com.example.productservice.Dtos.ProductResponseDto;
import com.example.productservice.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ResponseEntity<ProductResponseDto>  responseEntity = new ResponseEntity<>(ProductResponseDto.from(product), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDto = new ArrayList<>();
        for (Product product : products) {
            productResponseDto.add(ProductResponseDto.from(product));
        }
        ResponseEntity<List<ProductResponseDto>> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.createProduct(productRequestDto);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return responseEntity;
    }


}
