package com.codmind.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repositories.ProductRepository;
import com.codmind.orderapi.validators.ProductValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product findById(Long id) {
		try {
			
			Product product = productRepository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			return product;	
		}catch( ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException();
			
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			Product product = productRepository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));
			productRepository.delete(product);
			
		}catch( ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException();
			
		}
	}
	
	public List<Product> products (Pageable pageable){
		try {
			List<Product> products = productRepository.findAll(pageable).toList();
			return products;
			
		} catch( ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException();
			
		}
	}
	
	
	@Transactional
	public Product save(Product product) {
		try {
			
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
		} catch( ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException();
			
		}
		
	}
	
	

}
