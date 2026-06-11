package com.example.productservice.Dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductRequestDto {
    private Integer id;
    private String title;
    private String description;
    private Float price;
    private String imageUrl;
    private String categoryName;

    public Product toProduct() {
        Product product = new Product();
        if(this.getTitle() != null)product.setTitle(this.getTitle());
        if(this.getDescription() != null)product.setDescription(this.getDescription());
        if(this.getImageUrl()!=null)product.setImageUrl(this.getImageUrl());
        if(this.getPrice()!=null)product.setPrice(Double.valueOf(this.getPrice()));
        Category category = new Category();
        if(this.getCategoryName()!=null)category.setName(this.getCategoryName());
        product.setCategory(category);
        return product;
    }
}
