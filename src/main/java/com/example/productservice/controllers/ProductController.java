package com.example.productservice.controllers;

import com.example.productservice.Dtos.ProductRequestDto;
import com.example.productservice.Dtos.ProductResponseDto;
import com.example.productservice.Dtos.UserDto;
import com.example.productservice.InvalidTokenException;
import com.example.productservice.ProductNotFoundException;
import com.example.productservice.clients.UserServiceClient;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private UserServiceClient userServiceClient;

    public ProductController(@Qualifier("productDbService")ProductService productService,  UserServiceClient userServiceClient) {
        this.productService = productService;
        this.userServiceClient = userServiceClient;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById
            (@PathVariable Long id, @RequestHeader("Authorization") String token)
            throws ProductNotFoundException, InvalidTokenException {
        UserDto userDto = userServiceClient.validateToken(token);
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

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> partialUpdateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) throws ProductNotFoundException {
        Product product = productService.partialUpdate(id, requestDto.toProduct());
        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(ProductResponseDto.from(product), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>("PRODUCT DELETED",HttpStatus.OK);
    }
}
