package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Product;
import com.masai.beans.ProductCategory;
import com.masai.exception.ProductNotFoundException;
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

	@Override
	public List<Product> getAllProdcuts() {
	
		List<Product> allProducts = productRepo.findAll();
		if(allProducts.isEmpty()) {
			throw new ProductNotFoundException("No product available");
		}
		return allProducts;
	}

	@Override
	public Product getProductById(Integer id) {
		
		Product product = productRepo.findById(id).get();
		if(product == null)
			throw new ProductNotFoundException("No product found in the given id");
		return product;
	}

	@Override
	public List<Product> getProductsByCategory(ProductCategory cate) {
		
		List<Product> productsCategory = productRepo.findByCategory(cate);
		
		return productsCategory;
	}
	
}
