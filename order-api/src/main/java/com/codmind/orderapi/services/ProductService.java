package com.codmind.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.repositories.ProductRepository;
import com.codmind.orderapi.validators.ProductValidator;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product findById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
		return product;	
	}
	
	@Transactional
	public void delete(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
		productRepository.delete(product);
	}
	
	public List<Product> products (Pageable pageable){
		List<Product> products = productRepository.findAll(pageable).toList();
		return products;
	}
	
	
	@Transactional
	public Product save(Product product) {
		
		ProductValidator.create(product);
		
		if(product.getId()==null) {
			Product newProdcut = productRepository.save(product);
			return newProdcut;
		}
		Product existproduct = productRepository.findById(product.getId())
				.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
		existproduct.setName(product.getName());
		existproduct.setPrice(product.getPrice());
		
		productRepository.save(existproduct);
		return existproduct;
	}
	
	

}
