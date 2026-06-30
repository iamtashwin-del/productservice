package com.example.productservice.services;

import com.example.productservice.Dtos.ProductRequestDto;
import com.example.productservice.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productDbService")

public class ProductDBService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setDescription(productRequestDto.getDescription());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(Double.valueOf(productRequestDto.getPrice()));
        Category category = getCategoryFromDB(productRequestDto.getCategoryName());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product partialUpdate(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        if(product.getCategory().getName() != null) {
            optionalProduct.get().setCategory(getCategoryFromDB(product.getCategory().getName()));
        }
        if(product.getPrice() != null) {
            optionalProduct.get().setPrice(Double.valueOf(product.getPrice()));
        }
        if(product.getImageUrl() != null) {
            optionalProduct.get().setImageUrl(product.getImageUrl());
        }
        if(product.getTitle() != null) {
            optionalProduct.get().setTitle(product.getTitle());
        }
        if(product.getDescription() != null) {
            optionalProduct.get().setDescription(product.getDescription());
        }
        return productRepository.save(optionalProduct.get());
    }

    @Override
    @Transactional
    public Integer deleteProduct(Integer id) throws ProductNotFoundException {
        Product product = getProductById(Long.valueOf(id));
        return productRepository.deleteProduct(id);
    }

    private Category getCategoryFromDB(String categoryName){
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if(categoryOptional.isEmpty()){
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
        }
        return categoryOptional.get();
    }
}
