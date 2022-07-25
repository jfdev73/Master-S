package com.codmind.orderapi.converter;

import com.codmind.orderapi.dtos.ProductDto;
import com.codmind.orderapi.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDto>{

	@Override
	public ProductDto fromEntity(Product entity) {
		return	ProductDto.builder()
		.id(entity.getId())
		.name(entity.getName())
		.price(entity.getPrice())
		.build();
		 
	}

	@Override
	public Product fromDto(ProductDto dto) {
		return	Product.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
	}

}
