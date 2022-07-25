package com.codmind.orderapi.validators;

import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.exceptions.ValidateServiceException;

public class ProductValidator {
	
	public static void create(Product product) {
		
		if(product.getName() ==null || product.getName().trim().isEmpty()) {
			
			throw new ValidateServiceException("El nombre debe ser ingresado");
			
		}
		if(product.getName().length()>100) {
			throw new ValidateServiceException("El nombre es muy largo max(100)");
		}
		
		if(product.getPrice() == null) {
			throw new ValidateServiceException("El precio debe ser ingresado");
		}
		
		if(product.getPrice() <0) {
			throw new ValidateServiceException("El precio es incorrecto");
		}
		
	}

}
