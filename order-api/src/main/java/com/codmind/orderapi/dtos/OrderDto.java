package com.codmind.orderapi.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.codmind.orderapi.entity.OrderLine;

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
public class OrderDto {
	
	private Long Id;
	
	private String regDate;
	
	private List<OrderLineDto> lines;
	
	private Double total;
	
	

}
