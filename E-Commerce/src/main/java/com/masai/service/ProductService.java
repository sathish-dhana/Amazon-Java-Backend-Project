package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Product;
import com.masai.repository.ProductCrudRepo;

@Service
public class ProductService implements ProductServiceInterface{

	@Autowired
	private ProductCrudRepo productRepo;
	
	@Override
	public Product addProduct(Product product) {
		
		Product savedProduct = productRepo.save(product);
		return savedProduct;
		
	}

}
