package com.example.productservice.Dtos;

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
}
