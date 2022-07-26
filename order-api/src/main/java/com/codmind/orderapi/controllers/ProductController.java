package com.codmind.orderapi.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.orderapi.converter.ProductConverter;
import com.codmind.orderapi.dtos.ProductDto;
import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.services.ProductService;
import com.codmind.orderapi.utils.WrapperResponse;

@RestController
public class ProductController {
	private ProductConverter productConverter = new ProductConverter();
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<WrapperResponse<ProductDto>> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		
		ProductDto productDto = productConverter.fromEntity(product);
		return new WrapperResponse<ProductDto>(true, "succes", productDto)
				.createResponse(HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productService.delete(id);
		return new WrapperResponse(true, "succes", null)
				.createResponse(HttpStatus.OK);
		
		
	}
	
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDto>> findAll(
			@RequestParam (value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam (value = "pageSize", required = false, defaultValue = "5") int pageSize){
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productService.products(pageable);
		
		List<ProductDto> dtoProducts =  productConverter.fromEntity(products);
		return new WrapperResponse(true, "succes", dtoProducts)
				.createResponse(HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/products")
	public ResponseEntity <ProductDto> create(@RequestBody ProductDto productD){
		Product newProduct = productService.save(productConverter.fromDto(productD));
		
		ProductDto productDto = productConverter.fromEntity(newProduct);
		return new WrapperResponse(true, "succes", productDto)
				.createResponse(HttpStatus.CREATED);
		
		
	}
	
	@PutMapping(value = "/products")
	public ResponseEntity <ProductDto> update(@RequestBody ProductDto productD){
		Product updateProduct = productService.save(productConverter.fromDto(productD));
		ProductDto productDto = productConverter.fromEntity(updateProduct);
		return new WrapperResponse(true, "succes", productDto)
				.createResponse(HttpStatus.OK);
		
		
	}
	

}
