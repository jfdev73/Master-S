package com.codmind.orderapi.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Setter
@Getter
public class ProductDto {
	
	private Long id;
	
	private String name;
	
	private Double price;

}
