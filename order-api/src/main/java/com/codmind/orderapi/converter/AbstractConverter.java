package com.codmind.orderapi.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity,Dto> {
	
	public abstract Dto fromEntity(Entity entity);
	
	public abstract Entity fromDto(Dto dto);
	
	public List<Dto> fromEntity(List<Entity> entitys){
		return entitys.stream()
				.map(e -> fromEntity(e))
				.collect(Collectors.toList());
		
	}
	
	public List<Entity> fromDto(List<Dto> dtos){
		return dtos.stream()
				.map(e -> fromDto(e))
				.collect(Collectors.toList());
		
	}

}
