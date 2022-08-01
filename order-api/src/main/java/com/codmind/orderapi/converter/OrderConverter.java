package com.codmind.orderapi.converter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.codmind.orderapi.dtos.OrderDto;
import com.codmind.orderapi.dtos.OrderLineDto;
import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;

public class OrderConverter extends AbstractConverter<Order, OrderDto>{
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss");
	
	private ProductConverter productConverter = new ProductConverter();

	@Override
	public OrderDto fromEntity(Order entity) {
		if(entity == null) return null;
		
		List<OrderLineDto> lines = fromOrderLineEntity(entity.getLines());
		
		return OrderDto.builder()
				.Id(entity.getId())
				.lines(lines)
				.regDate(entity.getRegDate().format(DATE_TIME_FORMATTER))
				.total(entity.getTotal())
				.build();
				
	}

	@Override
	public Order fromDto(OrderDto dto) {
if(dto == null) return null;
		
		List<OrderLine> lines = fromOrderLineDto(dto.getLines());
		
		return Order.builder()
				.id(dto.getId())
				.lines(lines)
				.total(dto.getTotal())
				.build();
	}
	
	private List <OrderLineDto> fromOrderLineEntity(List<OrderLine> lines) {
		if(lines ==null)return null;
		
		return lines.stream().map(line ->
		{
			return OrderLineDto.builder()
					.id(line.getId())
					.price(line.getPrice())
					.product(productConverter.fromEntity(line.getProduct()))
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
						
		})
				
				.collect(Collectors.toList());
				
	}
	
	private List <OrderLine> fromOrderLineDto(List<OrderLineDto> lines) {
if(lines ==null)return null;
		
		return lines.stream().map(line ->
		{
			return OrderLine.builder()
					.id(line.getId())
					.price(line.getPrice())
					.product(productConverter.fromDto(line.getProduct()))
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
						
		})
				
				.collect(Collectors.toList());
	}

}
