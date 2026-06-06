package com.example.productservice.Dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public static ProductResponseDto from(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.id = product.getId();
        productResponseDto.title = product.getTitle();
        productResponseDto.description = product.getDescription();
        productResponseDto.price = product.getPrice();
        productResponseDto.imageUrl = product.getImageUrl();
        productResponseDto.categoryName = product.getCategory().getName();
        return productResponseDto;
    }
}
