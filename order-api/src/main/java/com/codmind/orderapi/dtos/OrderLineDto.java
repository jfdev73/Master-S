package com.codmind.orderapi.dtos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderLineDto {
	
	
	private Long id;
	private Order order;
	private ProductDto product;
	private Double price;
	private Double quantity;
	private Double total;

}
