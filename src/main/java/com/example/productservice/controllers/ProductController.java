package com.example.productservice.controllers;

import com.example.productservice.Dtos.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ResponseEntity<ProductResponseDto>  responseEntity = new ResponseEntity<>(ProductResponseDto.from(product), HttpStatus.OK);
        return responseEntity;
    }
}
