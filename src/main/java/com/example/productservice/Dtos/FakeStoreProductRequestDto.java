package com.example.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductRequestDto {
    private Integer id;
    private String title;
    private Float price;
    private String description;
    private String category;
    private String image;

    public static FakeStoreProductRequestDto from(ProductRequestDto  productRequestDto) {
        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setId(productRequestDto.getId());
        fakeStoreProductRequestDto.setTitle(productRequestDto.getTitle());
        fakeStoreProductRequestDto.setPrice(productRequestDto.getPrice());
        fakeStoreProductRequestDto.setDescription(productRequestDto.getDescription());
        fakeStoreProductRequestDto.setImage(productRequestDto.getImageUrl());
        fakeStoreProductRequestDto.setCategory(productRequestDto.getCategoryName());
        return fakeStoreProductRequestDto;
    }
}
